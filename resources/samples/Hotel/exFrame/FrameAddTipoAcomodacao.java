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
public class FrameAddTipoAcomodacao implements ActionListener
{
	JFrame frame;
	JComboBox comboHoteis;	
	
	JTextField jtf_descricao, jtf_qtdePessoas, jtf_preco;
	
	IGesHotel IGH;// = new GerReserva();	
	
	public FrameAddTipoAcomodacao()
	{	
		IGH = new GesHotel();
		createAndShowGUI();		
	}
	
	public void selecionaHotel()
	{
		int numHotel = comboHoteis.getSelectedIndex();
        String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	                    		
        IGH.selecionaHotel(hotelSelecionado);
	}
	
		
	public JPanel cria_panel_hoteis()
	{		
		JLabel l_hotel = new JLabel("Hoteis");
		
		Vector listaHoteis = IGH.obterHoteis();
		
		comboHoteis = new JComboBox();		
		for (int i=0; i<listaHoteis.size(); i++)
		{
			comboHoteis.addItem(new String((String) listaHoteis.get(i)));
		}		
								
		JPanel p1 = new JPanel(); 
		p1.add(l_hotel);
        p1.add(comboHoteis);
        p1.setLayout(new FlowLayout());                                        
        
        return p1;
	}
	
	
	public JPanel cria_panel_TipoAcomod1()
	{						
		JPanel p2_1 = new JPanel();
		p2_1.setLayout(new FlowLayout());
		
		JLabel l_descricao = new JLabel("Descricao: ");
		jtf_descricao = new JTextField(8);
		
		p2_1.add(l_descricao);
		p2_1.add(jtf_descricao);				
		
		return p2_1;
	}
	
	public JPanel cria_panel_TipoAcomod2()
	{						
		JPanel p2_2 = new JPanel();
		p2_2.setLayout(new FlowLayout());
		
		JLabel l_qtde = new JLabel("Qtde Pessoas: ");
		jtf_qtdePessoas = new JTextField(4);
		
		JLabel l_preco = new JLabel("Preço: ");
		jtf_preco = new JTextField(4);			
		
		p2_2.add(l_qtde);
		p2_2.add(jtf_qtdePessoas);
		p2_2.add(l_preco);
		p2_2.add(jtf_preco);				
		
		return p2_2;
	}
	
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Cadastrar TipoAcomodacao");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("AddTipoAcomodacao");
						
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
        JFrame frame = new JFrame("CADASTRO DE TIPOS DE ACOMODACAO");
        //frame.setSize(new Dimension(400,200));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2_1, p2_2, p3;
	
		pGeral= new JPanel();

		pGeral.setLayout(new GridLayout(4,1));
        
		p1=cria_panel_hoteis();        
        p2_1=cria_panel_TipoAcomod1();
        p2_2=cria_panel_TipoAcomod2();
        p3=cria_panel_botoes();
               
        pGeral.add(p1);                           
        pGeral.add(p2_1);
        pGeral.add(p2_2);        
        pGeral.add(p3);        
        
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);
	}
			

	
    public void actionPerformed(ActionEvent event) {
		
    	if (event.getActionCommand().equals("AddTipoAcomodacao"))
    	{   		
    		Integer qt = new Integer(jtf_qtdePessoas.getText());
    		Float prc = new Float(jtf_preco.getText());    		
    		selecionaHotel();
    	   	IGH.addTipoAcomodacao(jtf_descricao.getText(),qt.intValue(),prc.floatValue());
    	   	System.out.println("Add TipoAcomodacao");
 			
    	}    	    	    	    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAddTipoAcomodacao FATA = new FrameAddTipoAcomodacao();
	}
}