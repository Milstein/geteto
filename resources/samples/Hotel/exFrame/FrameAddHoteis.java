/*
 * Created on 19/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package exFrame;
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 
 import GesHotel.*;
 
public class FrameAddHoteis implements ActionListener
{	
	JFrame frame;
	JTextField jtf_nomeHotel;
	private static IGesHotel IGH;// = new GesHotel();
	
	public FrameAddHoteis()
	{
		IGH = new GesHotel();
		createAndShowGUI();		
	}		
			
	public JPanel cria_panel_nomeHotel()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JLabel l_nomeHotel = new JLabel("Nome Hotel:");
		jtf_nomeHotel = new JTextField(10);				
        
        p1.add(l_nomeHotel);
        p1.add(jtf_nomeHotel);
        
        return p1;
	}		
				
	public JPanel cria_panel_botoes()
	{				
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cadastrar Hotel");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("AddHotel");
						
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		p2.add(b_OK);		
		p2.add(b_Fechar);
					
		return p2;
	}
	
	
	
	public void createAndShowGUI()
	{
		 JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        JFrame frame = new JFrame("CADASTRO DE HOTEIS");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(2,1));
        
		p1=cria_panel_nomeHotel();
        p2=cria_panel_botoes();
                             
        pGeral.add(p1);                       
        pGeral.add(p2);        
                
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                
	}
			
		
    public void actionPerformed(ActionEvent event) 
    {   		  	
    	if (event.getActionCommand().equals("AddHotel"))
    	{
    		IGH.addHotel(jtf_nomeHotel.getText());    		
    		System.out.println("Add Hotel");
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAddHoteis FAH = new FrameAddHoteis();
	}
}