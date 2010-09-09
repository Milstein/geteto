package br.jabuti.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


import br.jabuti.util.*;

/**
 * This class is responsible to show information about
 * the autors of the tool.
 * 
 * @version: 1.0
 * @author: Auri Vincenzi
 * @author: Marcio Delamaro
 */
class AboutDialog extends JDialog {  
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 1415420631163977766L;

	public AboutDialog() {  
        super(JabutiGUI.mainWindow(), "About...", true);         
        Container contentPane = getContentPane();

		// add Jabuti About figure
		// Locating the JaBUTi gif logo image     
		
			
        JLabel logo = new JLabel(new ImageIcon(
        	                 ToolConstants.getToolBaseResource(
       	                 	     ToolConstants.aboutLogo)), SwingConstants.CENTER);
        contentPane.add(logo,BorderLayout.WEST);

        // add HTML label to center
        contentPane.add(new JLabel(
                "<HTML><CENTER><P><H2><I>" 
                + ToolConstants.toolName + " " 
                + ToolConstants.toolVersion
                + "</I></H2><HR ALIGN=center WIDTH=80%>" 
                + "<B>Auri Marcelo Rizzo Vincenzi </B><BR>(<TT>auri@fundanet.br</TT>)<P>" 
                + "<B>M&aacute;rcio Eduardo Delamaro<BR>Eric Wong<BR>Jos&eacute; Carlos Maldonado</B>" 
                + "<BR><I>Copyright &copy; 2002</I>.</CENTER><P></HTML>"),
                BorderLayout.EAST);

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
      
        JPanel panel = new JPanel();

        panel.add(ok);
        contentPane.add(panel, BorderLayout.SOUTH);

        setSize(340, 250);
    }
}