package jabuti.ws.gui;

import static jabuti.ws.gui.DataUtils.*;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JabutiCLIResults {

	private JList jList;
	private JLabel jLabel;
	private JScrollPane jScrollPane;
	private JScrollPane jScrollPane1;
	private String projectName;
	private JTextArea jTextArea;
	private JButton jButton1;
	private String pathProject;
	private JFrame jframe;

	public JabutiCLIResults(JFrame jframe) {
		initialize();
		this.jframe = jframe;
	}

	private void initialize() {
		pathProject = getProjectsDir();
		Object fileNames[] = getProjectNames();
		jList = new JList(fileNames);
		jScrollPane = new JScrollPane(jList);
		jScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jTextArea = new JTextArea();
		jTextArea.setEditable(false);
		jScrollPane1 = new JScrollPane(jTextArea);

		jLabel = new JLabel();

		jButton1 = new JButton("Send");

	}

	public void refreshSpago4QName(String projectName) {
		jList.setSelectedValue(projectName, true);
	}

	public void resForm(JLayeredPane jLayeredPane) {
		jLabel.setText("Results Available");
		jLabel.setBounds(10, 10, 200, 17);
		jLayeredPane.add(jLabel, JLayeredPane.DEFAULT_LAYER);

		jScrollPane.setBounds(10, 30, 200, 400);
		jLayeredPane.add(jScrollPane, JLayeredPane.DEFAULT_LAYER);

		jScrollPane1.setBounds(250, 30, 550, 400);
		jLayeredPane.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);

		jButton1.setBounds(30, 440, 150, 27);
		jLayeredPane.add(jButton1, JLayeredPane.DEFAULT_LAYER);

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String xmlPath = openFile();
				Script.sendXmlFile(getEndPoint(), getProjectId(projectName),
						xmlPath);
			}
		});

		jList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						projectName = jList.getSelectedValue().toString();

						if (!new File(getProjectPath(projectName) + "/"
								+ getProjectId(projectName) + "-Spago4Q.xml")
								.exists())
							jTextArea
									.setText("There is no local results for "
											+ projectName
											+ "\n"
											+ "If the JaBUTI WS are running in this machine: "
											+ "Run the scripts or Upload a xml Spago4Q "
											+ "\n" + "otherwise: "
											+ "Run the scripts");
						else
							showFileContent();
					}
				});
	}

	private void showFileContent() {
		File a = new File(getProjectPath(projectName) + "/"
				+ getProjectId(projectName) + "-Spago4Q.xml");
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(a));
			String str;
			while (in.ready()) {
				str = in.readLine() + System.getProperty("line.separator");
				content += str;
			}
			in.close();
		} catch (IOException e) {
		}
		jTextArea.append(content);
	}

	public String openFile() {
		JFileChooser dir = new JFileChooser();
		dir.setDialogTitle("Open File/Directory");
		dir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		dir.showOpenDialog(jframe);
		return dir.getSelectedFile().getAbsolutePath();
	}

	public void reflesh() {
		initialize();
	}

}
