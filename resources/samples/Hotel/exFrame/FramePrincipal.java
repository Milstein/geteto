package exFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import GerReserva.*;
import GesCliente.*;
import GesHotel.*;
import GesAcesso.*;
import Faturamento.*;
import Persistencia.*;

public class FramePrincipal implements ActionListener, WindowListener
{    
    JScrollPane scrollPane;
    private static String usuarioLogado;
    public JFrame frame;
        
    public FramePrincipal()
    {    	    		
    //	ConnectionManager.ConnectDB();      	
    	createAndShowGUI();
    }
    
    public JFrame getFrame()
    {
    	return frame;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        menu = new JMenu("Cadastros");     
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Hoteis");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddHoteis");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Tipos de Acomodacao");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddTiposAcomodacao");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Acomodacoes");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddAcomodacoes");                                    
        menu.add(menuItem);
              
        
        menuItem = new JMenuItem("Tudo");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddTudo");
        menu.add(menuItem);                        
        
        
        menu = new JMenu("Reservas");
        menuBar.add(menu);

        menuItem = new JMenuItem("Fazer Reserva");        
        menuItem.addActionListener(this);
        menuItem.setActionCommand("Fazer Reserva");
        menu.add(menuItem);

        menuItem = new JMenuItem("Alterar Reserva");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("Alterar Reserva");        
        menu.add(menuItem);
             
        menuItem = new JMenuItem("Cancelar Reserva");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("Cancelar Reserva");        
        menu.add(menuItem);

        
        
         
        menu = new JMenu("Estada");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Ocupar Reserva");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("Ocupar Reserva");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Não Comparecer");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Check Out");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("CheckOut");
        menu.add(menuItem);
                                
              
        
        menu = new JMenu("Acesso");
        menuBar.add(menu);
               
        menuItem = new JMenuItem("Add Papeis");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddPapeis");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Add Operacoes");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddOperacoes");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Add Usuarios");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddUsuarios");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Associar Papel->Operacao");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AssociaPapelOperacao");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Mudar Usuário");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("mudarUsuario");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Logoff");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("logoff");
        menu.add(menuItem);
        
        
        menu = new JMenu("Empresa");
        menuBar.add(menu);
               
        menuItem = new JMenuItem("Add Empresa");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AddEmpresa");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Associa Empresa->Cliente");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("AssociaEmpresaCliente");
        menu.add(menuItem);
                
        
        menu = new JMenu("LOGS");
        menuBar.add(menu);
               
        menuItem = new JMenuItem("Ver Logs");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("VerLogs");
        menu.add(menuItem);       
        
        
        menu = new JMenu("SAIR");
        menuBar.add(menu);
               
        menuItem = new JMenuItem("Fechar");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("SairSistema");
        menu.add(menuItem);         
        
        return menuBar;
    }

    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);              

        return contentPane;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        frame = new JFrame("SISTEMA DE RESERVA DE HOTEL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        //FramePrincipal demo = new FramePrincipal();
        frame.setJMenuBar(createMenuBar());
        frame.setContentPane(createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
    
    public void windowActivated( WindowEvent e ) 
    { 
   // ação a ser realizada se a janela foi ativada
    } 
    
    public void windowClosed( WindowEvent e ) 
	{ 
       sairSistema();
	} 
			
	public void windowClosing( WindowEvent e ) 
	{ 
	   sairSistema();
	} 
	
	public void windowDeactivated( WindowEvent e ) 
	{ 
	   // ação a ser realizada se a janela foi desativada 
	} 
	
	public void windowDeiconified( WindowEvent e ) 
	{ 
	   // ação a ser realizada se a janela foi de-iconificada 
	} 
	
	public void windowIconified( WindowEvent e ) 
	{ 
	   // ação a ser realizada se a janela foi iconificada 
	} 
	
	public void windowOpened( WindowEvent e ) 
	{ 
	   // ação a ser realizada se a janela foi aberta 
	} 
    
    public void actionPerformed(ActionEvent e) 
    {
    	
    	if (e.getActionCommand().equals("AddHoteis"))
    	{
    		FrameAddHoteis FAH = new FrameAddHoteis();
    	}
    	
    	if (e.getActionCommand().equals("AddTiposAcomodacao"))
    	{
    		FrameAddTipoAcomodacao FATA = new FrameAddTipoAcomodacao();
    	}
    	
    	
    	if (e.getActionCommand().equals("AddAcomodacoes"))
    	{
    		FrameAddAcomodacao FAA = new FrameAddAcomodacao();
    	}
    	
    	
    	if (e.getActionCommand().equals("Fazer Reserva"))
    	{
    		FrameFazerReserva FFR = new FrameFazerReserva();    		
    	}
    	
    	if (e.getActionCommand().equals("Cancelar Reserva"))
    	{    		
    		FrameCancelarReserva FCR = new FrameCancelarReserva();
    	}
    	
    	
    	if (e.getActionCommand().equals("Ocupar Reserva"))
    	{
    		FrameOcuparReserva FOR = new FrameOcuparReserva();    		
    	}
    	
    	if (e.getActionCommand().equals("Alterar Reserva"))
    	{
    		FrameAlterarReserva FOR = new FrameAlterarReserva();    		
    	}
    	
    	
    	if (e.getActionCommand().equals("AddTudo"))
    	{
    		
    		IGesHotel IGH = new GesHotel();
    		
    		IGH.addHotel("CiaNorte");
			IGH.addHotel("Plaza");
			IGH.addHotel("Ibis");
			IGH.addHotel("Ouro Verde");	
			
			IGH.selecionaHotel("CiaNorte");
			
			IGH.addTipoAcomodacao("Single",1,25);
			IGH.addTipoAcomodacao("Duplo",2,40);
			IGH.addTipoAcomodacao("Triplo",3,55);
			IGH.addTipoAcomodacao("Familia",5,65);
				
			IGH.addAcomodacao(11,"Single");
			IGH.addAcomodacao(12,"Single");
			IGH.addAcomodacao(13,"Duplo");
			IGH.addAcomodacao(14,"Duplo");
		
			IGH.addAcomodacao(21,"Single");
			IGH.addAcomodacao(22,"Single");
			IGH.addAcomodacao(23,"Duplo");
			IGH.addAcomodacao(24,"Duplo");
		
			IGH.addAcomodacao(31,"Single");
			IGH.addAcomodacao(32,"Single");
			IGH.addAcomodacao(33,"Triplo");
			IGH.addAcomodacao(34,"Familia");
		
			IGH.addAcomodacao(41,"Triplo");
			IGH.addAcomodacao(42,"Triplo");
			IGH.addAcomodacao(43,"Familia");
			IGH.addAcomodacao(44,"Familia");
		
			IGH.selecionaHotel("Plaza");
			
			IGH.addTipoAcomodacao("Kitnet",1,15);	
			IGH.addTipoAcomodacao("Single",1,25);
			IGH.addTipoAcomodacao("Duplo",2,45);
						
			IGH.selecionaHotel("Ibis");
			
			IGH.addTipoAcomodacao("Duplo",2,50);	
			IGH.addTipoAcomodacao("Triplo",3,75);
			IGH.addTipoAcomodacao("Cobertura",4,100);
		
			IGH.selecionaHotel("Ouro Verde");
			
			IGH.addTipoAcomodacao("Single",1,20);
			IGH.addTipoAcomodacao("Duplo",2,35);	
    	}
    	
    	
    	if (e.getActionCommand().equals("AddPapeis"))
    	{
    		FrameAddPapeis FAP = new FrameAddPapeis();
    	}
    	
    	
    	if (e.getActionCommand().equals("AddUsuarios"))
    	{
    		FrameAddUsuarios FAU = new FrameAddUsuarios();
    	}
    	
    	if (e.getActionCommand().equals("AddOperacoes"))
    	{
    		FrameAddOperacoes FAO = new FrameAddOperacoes();
    	}
    	
    	if (e.getActionCommand().equals("AssociaPapelOperacao"))
    	{
    		FrameAssociaOperacaoPapel FAOP = new FrameAssociaOperacaoPapel();
    	}    	    	    	
    	
    	
    	if (e.getActionCommand().equals("AddEmpresa"))
    	{
    		FrameAddEmpresa FAE = new FrameAddEmpresa();    		
    	}    
    	
    	if (e.getActionCommand().equals("AssociaEmpresaCliente"))
    	{
    		FrameAssociaClienteEmpresa FACE = new FrameAssociaClienteEmpresa();
    	}
    	
    	
    	if (e.getActionCommand().equals("mudarUsuario"))
    	{
    	//	System.out.println("Usuário atual: " + usuarioLogado);
    		//FrameMudarUsuario FMU = new FrameMudarUsuario();
    	}
    	   	
    	if (e.getActionCommand().equals("CheckOut"))
    	{    		
    		
    	}
    	
    	if (e.getActionCommand().equals("VerLogs"))
    	{    		
    		FrameVerLogs FVL = new FrameVerLogs();
    	}
    	
    	if (e.getActionCommand().equals("SairSistema"))
    	{    		
    		sairSistema();
    		frame.setVisible(false);
    	}
    	
    }
    
    public void sairSistema()
    {
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

    }
        
    
}