package br.jabuti.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


/**
 * This class is responsible to show information about
 * the autors of the tool.
 * 
 * @version: 1.0
 * @author: Auri Vincenzi
 * @author: Marcio Delamaro
 */
class AvailableMemoryDialog extends JDialog {  
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -2583465933455573860L;

	public AvailableMemoryDialog() {  
        super(JabutiGUI.mainWindow(), "Free Memory Available...", true);         
        Container contentPane = getContentPane();

		JPanel panel = new JPanel();
		
		panel.setLayout( new GridLayout(4,1) );
		
		System.gc();
		
		//Getting the total amount of memory
		Runtime runtime = Runtime.getRuntime();
		
		long factor = 1048576;
		String unit = "Mb";
		
		long totalMemory = runtime.totalMemory() / factor;
		long freeMemory = runtime.freeMemory() / factor;
		long usedMemory = totalMemory - freeMemory;

//		long maxMemory = runtime.maxMemory() / factor;
				
//		JLabel maxLabel = new JLabel( "Maximum Memory: " + maxMemory + unit );
		JLabel totalLabel = new JLabel( "Total Memory: " + totalMemory + unit );
		JLabel freeLabel = new JLabel( "Free Memory: " + freeMemory + unit );		
		JLabel usedLabel = new JLabel( "Used Memory: " + usedMemory + unit );
				
//		panel.add( maxLabel );
		panel.add( totalLabel );
		panel.add( freeLabel );
		panel.add( usedLabel );

        contentPane.add(panel,BorderLayout.CENTER);

        // Ok button closes the dialog
      
        JButton ok = new JButton("Ok");

        ok.addActionListener(new 
                ActionListener() {  
                    public void actionPerformed(ActionEvent evt) { 
                        setVisible(false); 
                    } 
                }
                );

        // add Ok button to southern border
      
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(ok);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setSize(340, 250);
    }
}