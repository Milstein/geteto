/*
 * Created on 19/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package exFrame;
 
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.*;
import GesAcesso.*;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */  
 
public class FrameAddUsuarios implements ActionListener
{	
	JFrame frame;
	JTextField jtf_login, jtf_senha;
	JComboBox comboPapeis;
	private IGesAcesso IGA;
	
	public FrameAddUsuarios()
	{
		IGA = new GesAcesso();
		createAndShowGUI();		
	}
			
	public JPanel cria_panel_login()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JLabel l_login = new JLabel("Login: ");
		jtf_login = new JTextField(10);				
        
        p1.add(l_login);
        p1.add(jtf_login);
        
        return p1;
	}		
	
	public JPanel cria_panel_senha()
	{
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		JLabel l_senha = new JLabel("Senha: ");
		jtf_senha = new JTextField(10);				
        
        p2.add(l_senha);
        p2.add(jtf_senha);
        
        return p2;
	}	
	
	public JPanel cria_panel_papel()
	{			
		Vector listaPapeis = IGA.obterPapeis();
		
		comboPapeis = new JComboBox();
						
		for (int i=0; i<listaPapeis.size(); i++)
			comboPapeis.addItem(new String((String) listaPapeis.get(i)));
												
		JPanel p3 = new JPanel(); 
		p3.setLayout(new FlowLayout());                        
		
		JLabel l_papeis = new JLabel("Papel: ");
		p3.add(l_papeis);
        p3.add(comboPapeis);                        
        
        return p3;
	}
				
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cadastrar Usuario");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("AddUsuario");
						
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
        JFrame frame = new JFrame("CADASTRO DE USUÁRIOS");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3, p4;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(4,1));
        
		p1=cria_panel_login();
		p2=cria_panel_senha();
		p3=cria_panel_papel();
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
    	if (event.getActionCommand().equals("AddUsuario"))
    	{
    		int ind = comboPapeis.getSelectedIndex();
        	String papel = (String) comboPapeis.getItemAt(ind); 
    		
    		System.out.println("Add Usuario");
    		IGA.addUsuario(jtf_login.getText(), jtf_senha.getText(), papel);
    		IGA.setSenhaUsuario(jtf_login.getText(), jtf_senha.getText());    		
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAddUsuarios FAU = new FrameAddUsuarios();
	}
}