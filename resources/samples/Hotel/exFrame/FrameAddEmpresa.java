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
import Tipos.*;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 
 import GesEmpresa.*;
 
public class FrameAddEmpresa implements ActionListener
{	
	JFrame frame;
	JTextField jtf_nomeEmpresa, jtf_CGC, jtf_txDesconto;
	private IGesEmpresa IGE;
	
	public FrameAddEmpresa()
	{
		IGE = new GesEmpresa();	
		createAndShowGUI();		
	}
				
	public JPanel cria_panel_empresa()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JLabel l_nomeEmpresa = new JLabel("Nome Empresa:");
		jtf_nomeEmpresa = new JTextField(10);				
        
        p1.add(l_nomeEmpresa);
        p1.add(jtf_nomeEmpresa);
        
        return p1;
	}		
	
	public JPanel cria_panel_CGC()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JLabel l_CGC = new JLabel("CGC:");
		jtf_CGC = new JTextField(10);				
        
        p1.add(l_CGC);
        p1.add(jtf_CGC);
        
        return p1;
	}
	
	public JPanel cria_panel_desconto()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JLabel l_txDesconto = new JLabel("txDesconto:");
		jtf_txDesconto = new JTextField(10);				
        
        p1.add(l_txDesconto);
        p1.add(jtf_txDesconto);
        
        return p1;
	}
				
	public JPanel cria_panel_botoes()
	{				
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cadastrar Empresa");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("AddEmpresa");
						
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
        
        JPanel pGeral, p1, p2, p3, p4;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(4,1));
        
		p1=cria_panel_empresa();
		p2=cria_panel_CGC();
		p3=cria_panel_desconto();
        p4=cria_panel_botoes();
                             
        pGeral.add(p1);                       
        pGeral.add(p2);
        pGeral.add(p3);        
        pGeral.add(p4);                
                
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                
	}
			
		
    public void actionPerformed(ActionEvent event) 
    {   		  	
    	if (event.getActionCommand().equals("AddEmpresa"))
    	{    		    	
    		DadosEmpresa de = new DadosEmpresa();
    		de.setNomeEmpresa(jtf_nomeEmpresa.getText());
    		de.setCGC(jtf_CGC.getText());
    		
    		Float ft = new Float(jtf_txDesconto.getText());    		    		
    		de.setTxDesconto(ft.floatValue());
    			
    		IGE.addEmpresa(de);    		
    		System.out.println("Add Empresa");
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAddEmpresa FAH = new FrameAddEmpresa();
	}
}