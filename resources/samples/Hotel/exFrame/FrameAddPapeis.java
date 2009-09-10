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
import GesAcesso.*;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */  
 
public class FrameAddPapeis implements ActionListener
{	
	JFrame frame;
	JTextField jtf_papel, jtf_descricao;	
	private static IGesAcesso IGA;// = new GesHotel();
	
	public FrameAddPapeis()
	{
		IGA = new GesAcesso();
		createAndShowGUI();		
	}		
			
	public JPanel cria_panel_papel()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JLabel l_papel = new JLabel("Papel: ");
		jtf_papel = new JTextField(10);				
        
        p1.add(l_papel);
        p1.add(jtf_papel);
        
        return p1;
	}		
	
	public JPanel cria_panel_descricao()
	{
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		JLabel l_descricao = new JLabel("Desc: ");
		jtf_descricao = new JTextField(10);				
        
        p2.add(l_descricao);
        p2.add(jtf_descricao);
        
        return p2;
	}	
					
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cadastrar Papel");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("AddPapel");
						
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		p3.add(b_OK);		
		p3.add(b_Fechar);
					
		return p3;
	}
	
	
	
	public void createAndShowGUI()
	{
		 JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        JFrame frame = new JFrame("CADASTRO DE PAPÉIS");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(3,1));
        
		p1=cria_panel_papel();
		p2=cria_panel_descricao();		
        p3=cria_panel_botoes();
                             
        pGeral.add(p1);                       
        pGeral.add(p2);        
        pGeral.add(p3);         
                
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                
	}
			
		
    public void actionPerformed(ActionEvent event) 
    {   		  	
    	if (event.getActionCommand().equals("AddPapel"))
    	{    		
    		IGA.addPapel(jtf_papel.getText(), jtf_descricao.getText());    		
    		System.out.println("Add Papel");
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAddPapeis FAU = new FrameAddPapeis();
	}
}