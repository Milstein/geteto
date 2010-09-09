package jabuti.ws.gui;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static jabuti.ws.gui.DataUtils.*;

public class JabutiCLIExecution {

	private JList jList;
	private JFrame jframe;
	private JLabel jLabel;
	private JScrollPane jScrollPane;
	private JScrollPane jScrollPane1;
	private String confFile;
	private JTextArea jTextArea;
	private JButton jButton;

	public JabutiCLIExecution(JFrame jframe) {
		initialize();
		this.jframe = jframe;
	}

	public void refresh() {
		initialize();
	}

	public void refreshSpago4QName(String projectName) {
		jList.setSelectedValue(projectName, true);
	}

	private void initialize() {
		Object fileNames[] = getProjectNames();
		jList = new JList(fileNames);
		jScrollPane = new JScrollPane(jList);
		jScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jTextArea = new JTextArea();
		jScrollPane1 = new JScrollPane(jTextArea);

		jTextArea
				.setText("The execution output are been showed in the application output");
		jLabel = new JLabel();

		jButton = new JButton("Executar");

	}

	public void execForm(JLayeredPane jLayeredPane) {
		jLabel.setText("Projects Available");
		jLabel.setBounds(10, 10, 200, 17);
		jLayeredPane.add(jLabel, JLayeredPane.DEFAULT_LAYER);

		jScrollPane.setBounds(10, 30, 200, 400);
		jLayeredPane.add(jScrollPane, JLayeredPane.DEFAULT_LAYER);

		jScrollPane1.setBounds(250, 30, 550, 400);
		jLayeredPane.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);

		jButton.setBounds(30, 440, 150, 27);
		jLayeredPane.add(jButton, JLayeredPane.DEFAULT_LAYER);

		jButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if ((new File(getProjectPath(confFile) + "/"
						+ getProjectId(confFile) + ".jbt")).exists())
					runScript();
				else
					JOptionPane.showMessageDialog(null,
							"Please set the configuration for this project",
							"Configuration file not found",
							JOptionPane.ERROR_MESSAGE);
			}
		});

		jList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						confFile = jList.getSelectedValue().toString();
					}
				});
	}

	public void runScript() {
		File wd = new File("./scripts");

		System.out.println("path: " + getProjectPath(confFile) + "/"
				+ getProjectId(confFile) + ".jbt");
		Process proc = null;

		try {
			proc = Runtime.getRuntime().exec("/bin/bash", null, wd);

			if (proc != null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						proc.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(proc.getOutputStream()), 1024),
						true);
				out.println("./jabuti-clean -cfg " + getProjectPath(confFile)
						+ "/" + getProjectId(confFile) + ".jbt");
				out.println("./jabuti-initialize -cfg "
						+ getProjectPath(confFile) + "/"
						+ getProjectId(confFile) + ".jbt");
				out.println("./jabuti-instrument -cfg "
						+ getProjectPath(confFile) + "/"
						+ getProjectId(confFile) + ".jbt");

				String line;
				while (!(line = in.readLine()).equals("end")) {
					System.out.println(line);
				}

				out.println("./jabuti-execute -cfg " + getProjectPath(confFile)
						+ "/" + getProjectId(confFile) + ".jbt");
				while (!(line = in.readLine()).equals("end")) {
					System.out.println(line);
				}

				out.println("./jabuti-consolidate -cfg "
						+ getProjectPath(confFile) + "/"
						+ getProjectId(confFile) + ".jbt");
				while (!(line = in.readLine()).equals("end")) {
					System.out.println(line);
				}

				out.println(renameXmlFile());
				out.println("exit");
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}

				proc.waitFor();
				in.close();
				out.close();
				proc.destroy();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"XML Spago4Q File not created!", "Success",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

	}

	private String renameXmlFile() {
		String a = "mv " + getProjectPath(confFile) + "/" + confFile
				+ "-Spago4Q.xml " + getProjectPath(confFile) + "/"
				+ getProjectId(confFile) + "-Spago4Q.xml";

		return a;
	}

}
