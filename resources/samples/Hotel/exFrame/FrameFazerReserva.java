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
public class FrameFazerReserva implements ActionListener
{
	JFrame frame;
	JComboBox comboHoteis;
	JComboBox comboAcomodacoes;
	JTextField jtf_qtde, jtf_preco, jtf_dataIni, jtf_dataFim,  jtf_precoAcomod,
	           jtf_txDesconto, jtf_desconto, jtf_precoTotal, jtf_disponivel,
	           jtf_nomeCli, jtf_contCli, jtf_emailCli;
	IFazerReserva IFR;// = new GerReserva();
	DetalhesHotel DH;
	DadosCliente DC;
	
	public FrameFazerReserva()
	{		
		IFR = new GerReserva();
		createAndShowGUI();		
	}

	
	public void selecionaHotel()
	{
		int numHotel = comboHoteis.getSelectedIndex();
        String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	
        
        comboAcomodacoes.removeAllItems();
        
		DH= new DetalhesHotel();
		//System.out.println("Hotel Selecionado: " + hotelSelecionado);
		DH = IFR.obterDetalheHotel(hotelSelecionado);
		
		Vector tiposAcomodacao = DH.getTiposAcomodacao();		
		for (int i=0; i<tiposAcomodacao.size();i++)
		{
			comboAcomodacoes.addItem((String)tiposAcomodacao.get(i));
		}
	}
	
	public void selecionaAcomodacao()
	{
		InfoTipoAcomodacao ifa;// = new IGesHotel.InfoTipoAcomodacao();
		int num = comboAcomodacoes.getSelectedIndex();
        String acomod = (String) comboAcomodacoes.getItemAt(num);            
        
		ifa = IFR.obterInfoTipoAcomodacao(acomod);
		jtf_qtde.setText("");
		jtf_precoAcomod.setText("");
		jtf_qtde.setText((new Integer(ifa.getQtdePessoas())).toString());
		jtf_precoAcomod.setText((new Float(ifa.getPreco())).toString());
		
	}
	
	public JPanel cria_panel_hoteis()
	{
		JLabel l_hotel = new JLabel("Hoteis");
		
		Vector listaHoteis = IFR.obterHoteis();
		
		comboHoteis = new JComboBox();
		comboHoteis.addActionListener(this);		
		for (int i=0; i<listaHoteis.size(); i++)
		{
			comboHoteis.addItem(new String((String) listaHoteis.get(i)));
		}
		comboHoteis.setActionCommand("SelecionaHotel");
								
		JPanel p1 = new JPanel(); 
		p1.add(l_hotel);
        p1.add(comboHoteis);
        p1.setLayout(new FlowLayout());                        
        
        JLabel l_Acomod = new JLabel("Acomodações");
		
		comboAcomodacoes = new JComboBox();    
		comboAcomodacoes.addActionListener(this);
        comboAcomodacoes.setActionCommand("SelecionaAcomodacao");
        
               
        p1.add(l_Acomod);
        p1.add(comboAcomodacoes);                
        
        return p1;
	}
	
	public JPanel cria_panel_infoAcomod()
	{		
		JLabel l_Acomod = new JLabel((String) comboAcomodacoes.getItemAt(comboAcomodacoes.getSelectedIndex()));
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JLabel l_qtde = new JLabel("Qtde Pessoas: ");
		jtf_qtde = new JTextField(4);
		
		JLabel l_preco = new JLabel("Preço: ");
		jtf_precoAcomod = new JTextField(4);			
		
		p3.add(l_qtde);
		p3.add(jtf_qtde);
		p3.add(l_preco);
		p3.add(jtf_precoAcomod);				
		
		return p3;
	}
	
	public JPanel cria_panel_datas()
	{				
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		
		JLabel l_dataIni = new JLabel("Inicio: ");
		JLabel l_dataFim = new JLabel("Fim: ");
			
		jtf_dataIni = new JTextField(7);
		jtf_dataFim = new JTextField(7);
		
		JButton b_verificar = new JButton("Verificar");
		b_verificar.addActionListener(this);
		b_verificar.setActionCommand("Verificar");
				
		p4.add(l_dataIni);
		p4.add(jtf_dataIni);
		p4.add(l_dataFim);
		p4.add(jtf_dataFim);
		p4.add(b_verificar);				
		
		return p4;
	}
	
	public JPanel cria_panel_precoDesconto()
	{				
		JPanel p4a = new JPanel();
		p4a.setLayout(new FlowLayout());
		
		JLabel l_preco = new JLabel("Preço: ");
		JLabel l_txDesconto = new JLabel("TxDesc.: ");
		JLabel l_desconto = new JLabel("Desc.: ");
			
		jtf_preco = new JTextField(5);
		jtf_txDesconto = new JTextField(3);
		jtf_desconto = new JTextField(5);				
				
		p4a.add(l_preco);
		p4a.add(jtf_preco);
		p4a.add(l_txDesconto);
		p4a.add(jtf_txDesconto);
		p4a.add(l_desconto);
		p4a.add(jtf_desconto);
		
		return p4a;
	}
	
	public JPanel cria_panel_precoTotal_disponibilidade()
	{				
		JPanel p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		
		JLabel l_preco = new JLabel("Preco Total: ");
		JLabel l_disponivel = new JLabel("Disponivel?: ");
			
		jtf_precoTotal = new JTextField(5);
		jtf_disponivel = new JTextField(9);
				
		p5.add(l_preco);
		p5.add(jtf_precoTotal);
		p5.add(l_disponivel);
		p5.add(jtf_disponivel);				
		
		return p5;
	}
	
	public JPanel cria_panel_botoes()
	{				
		JPanel p6 = new JPanel();
		p6.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Fazer Reserva");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("OK");
		
		JButton b_Limpar = new JButton("Limpar");
		b_Limpar.addActionListener(this);
		b_Limpar.setActionCommand("Limpar");
		
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		p6.add(b_OK);
		p6.add(b_Limpar);
		p6.add(b_Fechar);						    
					
		return p6;
	}
	
	public JPanel cria_panel_cliente1()
	{		
		
		JPanel p7_1 = new JPanel();
		p7_1.setLayout(new FlowLayout());
		
		JLabel l_nome = new JLabel("Nome ");
		jtf_nomeCli = new JTextField(10);
		
		p7_1.add(l_nome);
		p7_1.add(jtf_nomeCli);
		
		JButton b_locCli = new JButton("Localizar Cliente");
		b_locCli.addActionListener(this);
		b_locCli.setActionCommand("LocCli");
		
		p7_1.add(b_locCli);
		
		return p7_1;
	}
	
	public JPanel cria_panel_cliente2()
	{		
		JPanel p7_2 = new JPanel();
		p7_2.setLayout(new FlowLayout());
		
		JLabel l_cont = new JLabel("Contato: ");
		jtf_contCli = new JTextField(6);
		
		p7_2.add(l_cont);
		p7_2.add(jtf_contCli);
		
		JLabel l_email = new JLabel("E-mail:");
		jtf_emailCli = new JTextField(10);
		
		p7_2.add(l_email);
		p7_2.add(jtf_emailCli);
		
		return p7_2;
	}
		
		
	public void createAndShowGUI()
	{
		 JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        JFrame frame = new JFrame("FAZER RESERVA");
        //frame.setSize(new Dimension(400,200));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel jl = new JLabel("____________________________________________________");
        
        JPanel pGeral, p1, p3, p4, p4a, p5, p6, p7_1, p7_2, p7_3;
	
		pGeral= new JPanel();

		pGeral.setLayout(new GridLayout(9,1));
        
		p1=cria_panel_hoteis();        
        p3=cria_panel_infoAcomod();
        p4=cria_panel_datas();
        p4a=cria_panel_precoDesconto();
        p5=cria_panel_precoTotal_disponibilidade();
        p6=cria_panel_botoes();
        p7_1=cria_panel_cliente1();
        p7_2=cria_panel_cliente2();                       
               
        pGeral.add(p7_1);
        pGeral.add(p7_2);
        pGeral.add(jl);
        pGeral.add(p1);                           
        pGeral.add(p3);
        pGeral.add(p4);
        pGeral.add(p4a);
        pGeral.add(p5);                
        pGeral.add(p6);
        
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);
        
        selecionaHotel();
        selecionaAcomodacao();

	}
			
	private int getDia(String st)
	{
		char[] s = st.toCharArray();
		
		String retorno="";
		int cont=0, i=0;
		while ((cont<1) && (i<st.length()))
		{									
			if (s[i]=='/')
			   cont=cont+1;
			   else
			   {
			   		retorno = retorno + s[i];
			   		i++;
			   }
			
		}
		
		Integer it = new Integer(retorno);
		return it.intValue();
	}
	
	private int getMes(String st)
	{
		char[] s = st.toCharArray();
		
		String retorno="";
		int cont=0, i=0;
		while ((cont<2) && (i<st.length()))
		{						
			
			if (s[i]=='/')
			   cont=cont+1;			   
			   else
			   {	
			   		if (cont==1)			   		
			   			retorno = retorno + s[i];			   		
			   }
			  i++;
			
		}
		
		Integer it = new Integer(retorno);
		return it.intValue();
	}
	
	private int getAno(String st)
	{
		char[] s = st.toCharArray();
		
		String retorno="";
		int cont=0, i=0;
		while ((cont<3) && (i<st.length()))
		{						
			
			if (s[i]=='/')
			   cont=cont+1;			   
			   else
			   {	
			   		if (cont==2)			   		
			   			retorno = retorno + s[i];			   		
			   }
			  i++;
			
		}
		
		Integer it = new Integer(retorno);
		return it.intValue();
	}
	
	
    public void actionPerformed(ActionEvent event) {
   	
    	if (event.getActionCommand().equals("SelecionaHotel"))  	    
        	selecionaHotel();
        	
        if (event.getActionCommand().equals("SelecionaAcomodacao"))       	    
        	selecionaAcomodacao();
		
    	if (event.getActionCommand().equals("Verificar"))
    	{   		
 			DetalhesReserva res = new DetalhesReserva();
 			int numHotel = comboHoteis.getSelectedIndex();
        	String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	
        	res.setHotelID(DH.getHotelID());
    		IFR.selecionaHotel(hotelSelecionado);
    		
    		int diaIni = getDia(jtf_dataIni.getText());
    		int mesIni = getMes(jtf_dataIni.getText());
    		int anoIni = getAno(jtf_dataIni.getText());
    		
    		int diaFim = getDia(jtf_dataFim.getText());
    		int mesFim = getMes(jtf_dataFim.getText());
    		int anoFim = getAno(jtf_dataFim.getText());
    		
    		    		
    		res.setDataIni( new java.util.Date(anoIni-1900, mesIni, diaIni));
    		res.setDataFim( new java.util.Date(anoFim-1900, mesFim, diaFim));    		
    		
    		int num = comboAcomodacoes.getSelectedIndex();
        	String acomod = (String) comboAcomodacoes.getItemAt(num);  
        	res.setTipoAcomodacao(acomod);    	        	        	
            res.setHotelID(DH.getHotelID());    		    		    		    		    		    		    		    		
    		
    		float preco = IFR.obterPrecoReserva(res);    		
    		jtf_preco.setText(String.valueOf(preco));
    		res.setCliID(DC.getCliID());
    		float txDesc = IFR.obterTXDescontoReserva(res);
    		jtf_txDesconto.setText(String.valueOf(txDesc));
    		
    		float desconto = preco*txDesc;
    		jtf_desconto.setText(String.valueOf(desconto));
    		
    		jtf_precoTotal.setText(String.valueOf(preco-desconto));    		    		
    		
    		if (IFR.verificarDisponibilidadePeriodo(res))    		
				jtf_disponivel.setText("Disponível ");
				else jtf_disponivel.setText("Não Disponível"); 
    	}
    	
    	
    	if (event.getActionCommand().equals("OK"))
    	{
    		int numHotel = comboHoteis.getSelectedIndex();
        	String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	
    		IFR.selecionaHotel(hotelSelecionado);
    		
    		int diaIni = getDia(jtf_dataIni.getText());
    		int mesIni = getMes(jtf_dataIni.getText());
    		int anoIni = getAno(jtf_dataIni.getText());
    		
    		int diaFim = getDia(jtf_dataFim.getText());
    		int mesFim = getMes(jtf_dataFim.getText());
    		int anoFim = getAno(jtf_dataFim.getText());
    		
    		DetalhesReserva res = new DetalhesReserva();
    		res.setDataIni( new java.util.Date(anoIni-1900, mesIni-1, diaIni));
    		res.setDataFim( new java.util.Date(anoFim-1900, mesFim-1, diaFim));
    		
    		res.setHotelID(DH.getHotelID());
    		
    		int num = comboAcomodacoes.getSelectedIndex();
        	String acomod = (String) comboAcomodacoes.getItemAt(num);  
        	res.setTipoAcomodacao(acomod);        	
        	
        	DC = new DadosCliente();
        	DC.setNome(jtf_nomeCli.getText());
        	DC.setContato(jtf_contCli.getText());
        	DC.setEmail(jtf_emailCli.getText());        	    	
        	
        	String cod = IFR.fazerReserva(res, DC);   		
    		System.out.println("EFETUOU RESERVA - CÓDIGO : "+ cod);
    	}
    	
    	if (event.getActionCommand().equals("LocCli"))
    	{
    		DC = IFR.obterCliente(jtf_nomeCli.getText());
    		jtf_contCli.setText(DC.getContato());
    		jtf_emailCli.setText(DC.getEmail());
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameFazerReserva ffr = new FrameFazerReserva();
	}
}