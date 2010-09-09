package br.jabuti.gui;

import java.awt.*;
import javax.swing.*;

import br.jabuti.util.*;

/**
 * This class is responsable to create a JPanel
 * containig the text that is placed in the top
 * of each report window.
 *
 * @version: 1.0
 * @author: Auri Vincenzi
 * @author: Marcio Delamaro
 */
class TitlePanel extends JPanel {

    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -3322186015055610846L;
	public TitlePanel(String label) {
        super();
		
        setContent(label);
    }
	
    public void setContent(String label) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constrains = new GridBagConstraints();
		
        constrains.fill = GridBagConstraints.BOTH;			
	
        setLayout(layout);

        constrains.weightx = 1.0;
        constrains.gridwidth = 1;
        constrains.insets = new Insets(20, 10, 20, 10);
			
        makelabel(label, layout, constrains);
    }		
	
    protected void makelabel(String name,
            GridBagLayout gridbag,
            GridBagConstraints c) {
        JTextField label = new JTextField(name);

        label.setHighlighter(null);
        label.setHorizontalAlignment(JTextField.CENTER);
        label.setEditable(false);
        label.setFont(ToolConstants.titleFont);
        gridbag.setConstraints(label, c);
        add(label);
    }
}
