package br.jabuti.gui;

import javax.swing.JRadioButtonMenuItem;

/**
 * This class represents a JRadioButton which stores the text of
 * the radio button and also the corresponding plataform info of a
 * given look and feel.
 * 
 * @author Auri Vincenzi
 */
class LookAndFeelRadioButton extends JRadioButtonMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1059178686754558002L;
	private String lookInfo; // the class name of the look and feel class
	private String name;	  // the name of the look and feel 
	
	public LookAndFeelRadioButton() {
		super();
	}
	
	public void setLookInfo(String p) {
		if ( p != null ) {
			lookInfo = p;
			int pos = p.lastIndexOf('.');
			name = p.substring(pos+1);
		}
	}
	
	public String getLookInfo() {
		return lookInfo;
	}
	
	public String getName() {
		return name;
	}
}