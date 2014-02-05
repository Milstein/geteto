/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation, either version 3 of the      
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
*/


package br.jabuti.ui.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import org.w3c.dom.Document;

import br.jabuti.util.*;

/**
 * This class is responsible to show information about
 * the autors of the tool.
 * 
 * @version: 1.0
 * @author: Auri Vincenzi
 */
public class CustomReportsDialog extends javax.swing.JDialog 
								 implements ItemListener {
    
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -2694207377714155404L;
	/** Creates new form CustomReportsDialog */
    public CustomReportsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
	    projectChk = new JCheckBox( "Project Info" );
	    projectChk.setSelected( true );
	    projectChk.setEnabled( false ); // always includes the project info
	    projectChk.addItemListener( this );
	    
	    classChk = new JCheckBox( "Class Info" );
	    classChk.setSelected( true );
	    classChk.addItemListener( this );
	    classPanel = new JPanel();
	    classPanel.setLayout( new BorderLayout() );
	    classPanel.setBorder( BorderFactory.createEmptyBorder( 0, 20, 0, 0) );
	    classPanel.add( classChk, BorderLayout.WEST );
	    
	    methodChk = new JCheckBox( "Method Info" );
	    methodChk.setSelected( true );
	    methodChk.addItemListener( this );
	    methodPanel = new JPanel();
	    methodPanel .setLayout( new BorderLayout() );
	    methodPanel .setBorder( BorderFactory.createEmptyBorder( 0, 40, 0, 0) );
	    methodPanel .add( methodChk, BorderLayout.WEST );
	    

	    testSetChk = new JCheckBox( "Test Set Info" );
	    testSetChk.setSelected( true );
	    testSetChk.addItemListener( this );

	    testCaseChk = new JCheckBox( "Test Case Info" );
	    testCaseChk.setSelected( true );
	    testCaseChk.addItemListener( this );
	    testCasePanel = new JPanel();
	    testCasePanel.setLayout( new BorderLayout() );
	    testCasePanel.setBorder( BorderFactory.createEmptyBorder( 0, 20, 0, 0) );
	    testCasePanel.add( testCaseChk, BorderLayout.WEST );


	    testCasePathsChk = new JCheckBox( "Test Case Paths Info" );
	    testCasePathsChk.setSelected( true );
	    testCasePathsChk.addItemListener( this );
	    testCasePathsPanel = new JPanel();
	    testCasePathsPanel.setLayout( new BorderLayout() );
	    testCasePathsPanel.setBorder( BorderFactory.createEmptyBorder( 0, 40, 0, 0) );
	    testCasePathsPanel.add( testCasePathsChk, BorderLayout.WEST );

	    fileLabel = new JLabel( "Output File" );
	    fileTextField = new javax.swing.JTextField();
	    
	    filePanel = new JPanel();
	    filePanel.setLayout( new BorderLayout() );
	    filePanel.add( fileLabel, BorderLayout.NORTH );
	    filePanel.add( fileTextField, BorderLayout.SOUTH );
	    

		buttonPanel = new JPanel();
		buttonPanel.setLayout( new FlowLayout() );
			    
	    okButton = new JButton( "Ok" );    
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButton_ActionPerformed(evt);
            }
        });

	    closeButton = new JButton( "Close" );
        closeButton .addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButton_ActionPerformed( evt );
            }
        });
	    
	    buttonPanel.add( okButton );
	    buttonPanel.add( closeButton );
	
		completePanel = new JPanel();
		completePanel.setLayout(new GridLayout(8,1));
		completePanel.setBorder( BorderFactory.createEmptyBorder(10,10,10,10) ); 
	    
        completePanel.add(projectChk);
        completePanel.add(classPanel);
        completePanel.add(methodPanel);
        completePanel.add(testSetChk);
        completePanel.add(testCasePanel);
        completePanel.add(testCasePathsPanel);
        completePanel.add(filePanel);
        completePanel.add(buttonPanel);
	    
	    getContentPane().setLayout( new BorderLayout() );
	    getContentPane().add( completePanel, BorderLayout.CENTER );
	    
		setTitle( "Custom HTML Report..." );
		setSize( 260, 380 );
		// pack();
    }

    private void okButton_ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        System.out.println("Ok button clicked...");
        
        // Setting the text field with the server configuration info
        String fileName = fileTextField.getText();
        
        if ( fileName.length() > 0 ) {
	        if ( !fileName.endsWith( ".html" ) ||
	             !fileName.endsWith( ".htm" ) ) {
	        	fileName = fileName + ".html";
	        }
	     	
	     	if ( JabutiGUI.getProject() == null ) {
	            JOptionPane.showMessageDialog(null,
	                    "No project is active!!!",
    	                "Warning",
	                    JOptionPane.WARNING_MESSAGE);
	            return;
	     	}

			File repFile = new File( fileName );
	        if ( repFile.exists() ) {
				int option = JOptionPane.showConfirmDialog(null,
				    "File \"" + fileName + "\" already exists. Overwrite it?", "Warning",
				    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if ( option == JOptionPane.NO_OPTION )
					return;
	        }
	     	
	     	Document htmlDoc = HTMLGen.customReport( JabutiGUI.getProject(),
	     						  projectChk.isSelected(),
	     						  classChk.isSelected(),
	     						  methodChk.isSelected(),
	     						  testSetChk.isSelected(),
	     						  testCaseChk.isSelected(),
	     						  testCasePathsChk.isSelected() );
            System.out.println( "********* Saving Custom Report *********" );
	    	try{
				XMLPrettyPrinter.writeDocument( htmlDoc, 
												fileName );
	    	} catch ( Exception pce ) {
	    		ToolConstants.reportException( pce, ToolConstants.STDERR);
	            JOptionPane.showMessageDialog(null,
	                    "Error saving the file " + fileName + "!!!",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	    	} finally {
				htmlDoc = null;
				HTMLGen.restart();
	    	}
            System.out.println( "****************************************" );
            JOptionPane.showMessageDialog(null,
                    "Custom Report " + fileName + " generated successfully.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
	    }
	    else {
            JOptionPane.showMessageDialog(null,
                    "Enter the name of the output file!!!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
     	}
    }

    private void closeButton_ActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        dispose();
    }

    /** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();

        if (source == projectChk) {
	        if (e.getStateChange() == ItemEvent.DESELECTED) {
	        	classChk.setSelected( false );
	        }
        } else if (source == classChk) {
	        if (e.getStateChange() == ItemEvent.DESELECTED) {
	        	methodChk.setSelected( false );
	        }
        } else if (source == methodChk) {
	        if (e.getStateChange() == ItemEvent.SELECTED) {
	        	classChk.setSelected( true );
	        }
        } else if (source == testSetChk) {
	        if (e.getStateChange() == ItemEvent.DESELECTED) {
	        	testCaseChk.setSelected( false );
	        }
        } else if (source == testCaseChk) {
	        if (e.getStateChange() == ItemEvent.DESELECTED) {
	        	testCasePathsChk.setSelected( false );
	        } else {
	        	testSetChk.setSelected( true );
	        }
        } else if (source == testCasePathsChk) {
	        if (e.getStateChange() == ItemEvent.SELECTED) {
	        	testCaseChk.setSelected( true );
	        }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new CustomReportsDialog(new javax.swing.JFrame(), true).setVisible(true);
    }

	private JPanel completePanel;
	
    // Variables declaration - do not modify
    private JCheckBox projectChk;
    private JCheckBox classChk;
    private JPanel	  classPanel;
    
    private JCheckBox methodChk;
    private JPanel	  methodPanel;
        
    private JCheckBox testSetChk;
    private JCheckBox testCaseChk;
    private JPanel	  testCasePanel;    
    
    private JCheckBox testCasePathsChk;
	private JPanel	  testCasePathsPanel;    
    
    private JPanel filePanel;
    private JLabel fileLabel;
    private JTextField fileTextField;
    
    private JPanel buttonPanel;
    private JButton okButton;    
    private JButton closeButton;

    // End of variables declaration
}