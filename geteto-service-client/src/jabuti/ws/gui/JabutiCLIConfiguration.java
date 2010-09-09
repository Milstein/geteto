package jabuti.ws.gui;

import static jabuti.ws.gui.DataUtils.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JabutiCLIConfiguration {
	private JTextField field3;
	private JTextField field4;
	private JTextField field5;
	private JTextField field6;
	private JLabel label6;
	private JTextField field8;
	private JLabel label8;
	private JTextField field9;
	private JTextField field10;
	private JFrame jframe;
	private JButton submit;
	private JLabel mandatory;
	private JLabel optional;
	private int position;
	
	public JabutiCLIConfiguration(JFrame jframe){
		this.initialize();
		this.jframe = jframe;
	}
	
	private void initialize (){
	 	new JTextField();
	 	field3 = new JTextField();
	 	field4 = new JTextField();
	 	field5 = new JTextField();
	 	field6 = new JTextField();
	 	label6 = new JLabel();
	 	field8 = new JTextField();
	 	label8 = new JLabel();
	 	field9 = new JTextField();
	 	field10 = new JTextField();
	 	mandatory = new JLabel();
	 	optional = new JLabel();
	 	submit = new JButton("Create File");
	 	position = 0;
	 	
	}
	
	//Gives de position of next Component
	private void setPosition(int newPosition){
		position = newPosition;
	}
	
	public void refreshSpago4QName(String spago4qName){
        field8.setText(spago4qName);
        field8.setEditable(false);
	}
	
	public void confFileForm(JLayeredPane jLayeredPane){
			
			mandatory.setText("Mandatory");
			mandatory.setBounds(10, position, 200, 17);
			jLayeredPane.add(mandatory, JLayeredPane.DEFAULT_LAYER);
			
	        putComponent(field3, jLayeredPane, "Directory with original classes");
	        putComponent(field4, jLayeredPane, "Directory of test script");
	        putComponent(field5, jLayeredPane, "Directory of test output");
	        
	        label6.setText("Command to execute the tests");
	        setPosition(position+20);
	        label6.setBounds(30, position, 500, 17);
	        jLayeredPane.add(label6, JLayeredPane.DEFAULT_LAYER);
	        setPosition(position+15);
	        field6.setBounds(30, position, 500, 17);
	        jLayeredPane.add(field6, JLayeredPane.DEFAULT_LAYER);
	        label8.setText("Project ID in Spago4Q");
	        setPosition(position+20);
	        label8.setBounds(30, position, 200, 17);
	        jLayeredPane.add(label8, JLayeredPane.DEFAULT_LAYER);
	        setPosition(position+15);
	        
	        field8.setEditable(false);
	        field8.setBounds(30, position, 500, 17);
	        
	       // field8.setEditable(false);
	        jLayeredPane.add(field8, JLayeredPane.DEFAULT_LAYER);
	        
			optional.setText("Optional");
			setPosition(position+40);
			optional.setBounds(10, position, 200, 17);
	
			jLayeredPane.add(optional, JLayeredPane.DEFAULT_LAYER);
			
		
	        putComponent(field9, jLayeredPane, "ORIG_JAR");
	        putComponent(field10, jLayeredPane, "INSTRUM_JAR");
	        
	        submit.setBounds(300, position + 30, 150, 27 );
	        jLayeredPane.add(submit, JLayeredPane.DEFAULT_LAYER);
	        submit.addActionListener(new java.awt.event.ActionListener() {
	    		@Override
				public void actionPerformed(ActionEvent evt) {
	    			if(field8.getText().toString().equals(""))
	   			     	JOptionPane.showMessageDialog(null, "Please choose a project ...", "Error", JOptionPane.ERROR_MESSAGE);

	    			else
	    				createConfigurationFile();
				}
	        });
	}
	
	//Put a JLabel and a JTextField in the LayeredPane according to the variable position
	public void putComponent(final JTextField field, JLayeredPane pane, String name){
	 	JLabel jLabel = new JLabel();
	 	JButton button = new JButton("Procurar");

		jLabel.setText(name);
		
        jLabel.setBounds(30, position + 20, 500, 17);
        field.setBounds(30, position + 35, 500, 17);
        button.setBounds(550,position + 35, 50, 17);
        
        pane.add(jLabel, JLayeredPane.DEFAULT_LAYER);
        pane.add(field, JLayeredPane.DEFAULT_LAYER);
        pane.add(button, JLayeredPane.DEFAULT_LAYER);
        
        button.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				String path = openFile();
				field.setText(path);
			}
        	
        });
        setPosition(position + 40);
	}
	
	public String openFile() {
		        JFileChooser dir = new JFileChooser();
		        dir.setDialogTitle("Open File/Directory");
		        dir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        dir.showOpenDialog(jframe);
		        return dir.getSelectedFile().getAbsolutePath();
		}
	
	public void createConfigurationFile(){
		String info = "############### CONFIGURATION FILE ###############";     
	        
	        info +=	append(info, "JABUTI", "Jabuti-bin.jar");
	        info += append(info, "ORIG_DIR", field3.getText());
	        info +=	append(info, "TEST_SCRIPT", field4.getText());
	        info +=	append(info, "TEST_SCRIPT_OUT", field5.getText());
	        info +=	append(info, "TEST_EXEC_CMD", field6.getText());
	        info +=	append(info, "SPAGO_ID", field8.getText());
	        info +=	append(info, "SPAGO_DIR", getProjectPath(field8.getText()));
	        info +=	append(info, "ORIG_JAR", field9.getText());
	        info +=	append(info, "INSTRUM_JAR", field10.getText());
	        
	        String path = getProjectPath(field8.getText()) + "/" + getProjectId(field8.getText())+ ".jbt";
	        System.out.println(path);
	        
	        File file = new File(path);
	        FileWriter fileWriter;
			try {
				fileWriter = new FileWriter(file);
				 fileWriter.write(info);
			     fileWriter.close();
			     JOptionPane.showMessageDialog(null, "File saved in " + path, "Succes", JOptionPane.INFORMATION_MESSAGE);
			     JabutiCLIExecution jbt = new JabutiCLIExecution(jframe);
			     jbt.refresh();
			} 
			
			catch (IOException e) {
			}
	}
	
	public String append(String original, String field, String novo){
		return System.getProperty("line.separator") + field + "=\"" + novo + "\"";	
	}
}
