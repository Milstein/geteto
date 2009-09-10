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
import GerReserva.*;
import Tipos.*;
import java.util.Vector;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FrameCancelarReserva implements ActionListener
{	
	JFrame frame;
	JTextField jtf_codReserva, jtf_nomeHotel, jtf_nomeCliente, jtf_DataIni, 
	jtf_DataFim, jtf_tipoAcomodacao;
	ICancelarReserva ICR;	
	
	public FrameCancelarReserva()
	{
		ICR = new GerReserva();
		createAndShowGUI();		
	}

			
	public JPanel cria_panel_codReserva()
	{
		JLabel l_Reserva = new JLabel("Codigo Reserva:");
		jtf_codReserva = new JTextField(7);
		
		JButton b_verificar = new JButton("Verificar Reserva");
		b_verificar.addActionListener(this);
		b_verificar.setActionCommand("Verificar Reserva");
												
		JPanel p1 = new JPanel(); 
		p1.setLayout(new FlowLayout());        
		p1.add(l_Reserva);
        p1.add(jtf_codReserva);
        p1.add(b_verificar);        
        
        return p1;
	}		
	
	public JPanel cria_panel_dadosReserva1()
	{				
		
		JPanel p2_1 = new JPanel();
		p2_1.setLayout(new FlowLayout());
		
		JLabel l_nomeHotel = new JLabel("Hotel: ");
		jtf_nomeHotel = new JTextField(10);
		
		p2_1.add(l_nomeHotel);
		p2_1.add(jtf_nomeHotel);				
		
		return p2_1;
	}
	
	public JPanel cria_panel_dadosReserva2()
	{				
		JPanel p2_2 = new JPanel();
		p2_2.setLayout(new FlowLayout());
		
		JLabel l_nomeCliente = new JLabel("Cliente: ");
		jtf_nomeCliente = new JTextField(10);
		
		p2_2.add(l_nomeCliente);
		p2_2.add(jtf_nomeCliente);
		
		return p2_2;
	} 
	
	public JPanel cria_panel_dadosReserva3()
	{				
		JPanel p2_3 = new JPanel();
		p2_3.setLayout(new FlowLayout());
		
		JLabel l_DataIni = new JLabel("Data Ini:");
		jtf_DataIni = new JTextField(10);
		
		p2_3.add(l_DataIni);
		p2_3.add(jtf_DataIni);
		
		return p2_3;
	}
	
	public JPanel cria_panel_dadosReserva4()
	{				
		JPanel p2_4 = new JPanel();
		p2_4.setLayout(new FlowLayout());
		
		JLabel l_DataFim = new JLabel("Data Fim:");
		jtf_DataFim = new JTextField(10);
		
		p2_4.add(l_DataFim);
		p2_4.add(jtf_DataFim);
		
		return p2_4;
	}
	
	public JPanel cria_panel_dadosReserva5()
	{				
		JPanel p2_5 = new JPanel();
		p2_5.setLayout(new FlowLayout());
		
		JLabel l_tipoAcomodacao = new JLabel("TipoAcomod:");
		jtf_tipoAcomodacao = new JTextField(10);
		
		p2_5.add(l_tipoAcomodacao);
		p2_5.add(jtf_tipoAcomodacao);
		
		return p2_5;
	}		
	
	
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cancelar Reserva");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("OK");
						
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
        JFrame frame = new JFrame("CANCELAR RESERVA");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2_1, p2_2, p2_3, p2_4, p2_5, p3;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(7,1));
        
		p1=cria_panel_codReserva();
        p2_1=cria_panel_dadosReserva1();
        p2_2=cria_panel_dadosReserva2();
        p2_3=cria_panel_dadosReserva3();
        p2_4=cria_panel_dadosReserva4();
        p2_5=cria_panel_dadosReserva5();
        p3=cria_panel_botoes();
                       
        pGeral.add(p1);                       
        pGeral.add(p2_1);
        pGeral.add(p2_2);
        pGeral.add(p2_3);
        pGeral.add(p2_4);
        pGeral.add(p2_5);
        pGeral.add(p3);
                
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                
	}
			
		
    public void actionPerformed(ActionEvent event) 
    {
   		DetalhesReserva res;
   	
    	if (event.getActionCommand().equals("Verificar Reserva"))
    	{
    		res = ICR.obterReserva(jtf_codReserva.getText());
    		if ((res!=null) && (res.getStatus()==false))
    		{    			
    			jtf_nomeHotel.setText(ICR.obterNomeHotel(res.getHotelID()));
    			jtf_nomeCliente.setText(ICR.obterNomeCliente(res.getCliID()));
    			
    			
    			String dI = String.valueOf(res.getDataIni().getDate()) + "/" + 
    			     		String.valueOf(res.getDataIni().getMonth()+1) + "/" + 
    			     		String.valueOf(res.getDataIni().getYear()+1900);
    			     
    			String dF = String.valueOf(res.getDataFim().getDate()) + "/" + 
    			     		String.valueOf(res.getDataFim().getMonth()+1) + "/" + 
    			     		String.valueOf(res.getDataFim().getYear()+1900);
    			
    			jtf_DataIni.setText(dI);
    			jtf_DataFim.setText(dF);
    			jtf_tipoAcomodacao.setText(res.getTipoAcomodacao());
    		}
    	}
    	
    	if (event.getActionCommand().equals("OK"))
    	{
    		System.out.println("Cancelar: " + ICR.cancelarReserva(jtf_codReserva.getText()));
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameCancelarReserva ffr = new FrameCancelarReserva();
	}
}