package br.jabuti.gui;

import java.awt.*;
import javax.swing.*;

import br.jabuti.util.*;

/**
 * This class is responsable to create a JPanel
 * containig the text that is placed in the bottom
 * of each report window.
 *
 * @version: 1.0
 * @author: Auri Vincenzi
 * @author: Marcio Delamaro
 */
class SyntesePanel extends JPanel {

    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 3441924209952339938L;
	public SyntesePanel(String l1, String l2, String l3) {
        super();
		
        setContent(l1, l2, l3);
    }

    public void setContent(String l1, String l2, String l3) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constrains = new GridBagConstraints();
		
        setBorder(BorderFactory.createEtchedBorder());
		
        constrains.fill = GridBagConstraints.BOTH;			
	
        setLayout(layout);

        constrains.weightx = 2.0;
        constrains.gridwidth = 1;
        constrains.insets = new Insets(20, 10, 10, 10);
        makelabel(l1, layout, constrains);
        constrains.insets = new Insets(20, 10, 10, 10);			
        makelabel(l2, layout, constrains);
        constrains.insets = new Insets(20, 10, 10, 10);				    	
        constrains.gridwidth = GridBagConstraints.REMAINDER;
        makelabel(l3, layout, constrains);	
 	
    }
	
    protected void makelabel(String name,
            GridBagLayout gridbag,
            GridBagConstraints c) {
        JTextField label = new JTextField(name);
		label.setToolTipText( name );
        label.setHighlighter(null);
        label.setHorizontalAlignment(JTextField.CENTER);
        label.setEditable(false);
        label.setFont(ToolConstants.titleFont);
        gridbag.setConstraints(label, c);
        add(label);
    }
}
