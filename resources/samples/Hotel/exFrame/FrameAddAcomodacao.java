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
import GesHotel.*;
import Tipos.*;
import java.util.Vector;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FrameAddAcomodacao implements ActionListener
{
	JFrame frame;
	JComboBox comboHoteis;
	JComboBox comboAcomodacoes;
	JTextField jtf_numero;
	IGesHotel IGH;// = new GerReserva();
	DetalhesHotel DH;
	
	public FrameAddAcomodacao()
	{	
		IGH = new GesHotel();
		createAndShowGUI();		
	}
	
	public void selecionaHotel()
	{
		int numHotel = comboHoteis.getSelectedIndex();
        String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	
        
        comboAcomodacoes.removeAllItems();
        
		DH= new DetalhesHotel();
		DH = IGH.obterDetalheHotel(hotelSelecionado);
		
		Vector tiposAcomodacao = DH.getTiposAcomodacao();		
		for (int i=0; i<tiposAcomodacao.size();i++)
		{
			comboAcomodacoes.addItem((String)tiposAcomodacao.get(i));
		}
	}
	
	public JPanel cria_panel_hoteis()
	{
		JPanel p1 = new JPanel(); 
		p1.setLayout(new FlowLayout());
		p1.setPreferredSize(new Dimension(350,30));
		
		JLabel l_hotel = new JLabel("Hoteis");
		
		Vector listaHoteis = IGH.obterHoteis();
		
		comboHoteis = new JComboBox();
		comboHoteis.addActionListener(this);		
		for (int i=0; i<listaHoteis.size(); i++)
		{
			comboHoteis.addItem(new String((String) listaHoteis.get(i)));
		}
		comboHoteis.setActionCommand("SelecionaHotel");
								
		p1.add(l_hotel);
        p1.add(comboHoteis);
                                
        
        JLabel l_Acomod = new JLabel("Acomodações");
		
		comboAcomodacoes = new JComboBox();    
		comboAcomodacoes.addActionListener(this);
        comboAcomodacoes.setActionCommand("SelecionaAcomodacao");
        
               
        p1.add(l_Acomod);
        p1.add(comboAcomodacoes);                
        
        return p1;
	}
	
	public JPanel cria_panel_numAcomod()
	{		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		JLabel l_numero = new JLabel("Num. Acomodação: ");
		jtf_numero = new JTextField(4);				
		
		p2.add(l_numero);
		p2.add(jtf_numero);		
		
		return p2;
	}
		
	
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cadastrar Acomodacao");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("AddAcomodacao");
						
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
        JFrame frame = new JFrame("CADASTRO DE ACOMODACOES");
        
        JPanel pGeral, p1, p2, p3;
	
		pGeral= new JPanel();

		pGeral.setLayout(new GridLayout(3,1));
        
		p1=cria_panel_hoteis();        
        p2=cria_panel_numAcomod();
        p3=cria_panel_botoes();
                       
        pGeral.add(p1);                           
        pGeral.add(p2);                           
        pGeral.add(p3);        
        
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);
        
        selecionaHotel();
        //selecionaAcomodacao();
	}
			
		
    public void actionPerformed(ActionEvent event) {
   	
    	if (event.getActionCommand().equals("SelecionaHotel"))  	    
        	selecionaHotel();
        	
        if (event.getActionCommand().equals("AddAcomodacao"))
    	{   		    		
    		//InfoTipoAcomodacao ifa;// = new IGesHotel.InfoTipoAcomodacao();
			int num = comboAcomodacoes.getSelectedIndex();
        	String acomod = (String) comboAcomodacoes.getItemAt(num);
        	
        	Integer numero = new Integer(jtf_numero.getText());
        	
        	IGH.addAcomodacao(numero.intValue(),acomod);
        	System.out.println("ADD AComodacao");
    	}

    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAddAcomodacao FAA = new FrameAddAcomodacao();
	}
}