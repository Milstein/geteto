package GerAutenticacao;
 
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */  
 
public class FrameLogin extends JDialog implements ActionListener
    {	
		
	JTextField jtf_login, jtf_senha;	
	private boolean loginOK = false;
	
	
	public FrameLogin(Frame frm, String titulo, boolean modal)
	{
		super(frm, titulo, modal);
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
	
				
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Login");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("Login");
						
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		p3.add(b_OK);		
		p3.add(b_Fechar);
					
		return p3;
	}
	
	
	
	public void createAndShowGUI()
	{
		 //JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        //JFrame frame = new JFrame("LOGIN DE USUÁRIO");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(4,1));
        
		p1=cria_panel_login();
		p2=cria_panel_senha();
		p3=cria_panel_botoes();
                             
        pGeral.add(p1);                       
        pGeral.add(p2);        
        pGeral.add(p3);         
                
        getContentPane().add(pGeral);
        pack();
        setVisible(true);                
	}
			
		
    public void actionPerformed(ActionEvent event) 
    {   		  	
    	if (event.getActionCommand().equals("Login"))
    	{    	
    	   // System.out.println("LLLOOOOOOOOOGGGGGGGGIIIIIIIIIIINNNNNNNNN");
    		String login = jtf_login.getText();
    		String senha = jtf_senha.getText();    
    		
    		Autentica aut = new Autentica();
    		boolean ok = aut.efetuarLogin(login,senha);
    		if (ok)    				    		    		    	
    		{
    			System.out.println("USUÁRIO LOGADO NO SISTEMA: " + login);
    			loginOK=true;
    			setVisible(false);
    			//frame.close();
    		}    
    		else System.out.println("LOGIN INCORRETO");		
    	}
    	
    }
    
    public boolean getStatusLogin()
    {
    	return loginOK;
    }
	/*
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameLogin FL = new FrameLogin();
	}*/
}