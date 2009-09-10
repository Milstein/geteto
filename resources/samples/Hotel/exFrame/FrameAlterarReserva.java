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
public class FrameAlterarReserva implements ActionListener
{
	JFrame frame;	
	JComboBox comboAcomodacoes;
	JTextField jtf_qtde, jtf_precoAcomod, jtf_dataIni, jtf_dataFim,
	           jtf_precoTotal, jtf_disponivel,
	           jtf_codReserva, jtf_nomeHotel, jtf_nomeCliente, jtf_tipoAcomodacao,
	           jtf_preco, jtf_desconto, jtf_txDesconto;
	
	IAlterarReserva IAR;// = new GerReserva();
	DetalhesHotel DH;
	
	public FrameAlterarReserva()
	{	
		IAR = new GerReserva();
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
	

	public void selecionaAcomodacao()
	{
		InfoTipoAcomodacao ifa;
		int num = comboAcomodacoes.getSelectedIndex();
        String acomod = (String) comboAcomodacoes.getItemAt(num);            
        
		ifa = IAR.obterInfoTipoAcomodacao(acomod);
		jtf_qtde.setText("");
		jtf_precoAcomod.setText("");
		jtf_qtde.setText((new Integer(ifa.getQtdePessoas())).toString());
		jtf_precoAcomod.setText((new Float(ifa.getPreco())).toString());
		
	}
	
	public JPanel cria_panel_acomod()
	{
		JLabel l_Acomod = new JLabel("Acomodações");
		
		comboAcomodacoes = new JComboBox();    
		comboAcomodacoes.addActionListener(this);
        comboAcomodacoes.setActionCommand("SelecionaAcomodacao");
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());       
        
        p2.add(l_Acomod);
        p2.add(comboAcomodacoes);
        
        return p2;
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
		
		JButton b_OK = new JButton("Alterar Reserva");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("OK");
		
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		p6.add(b_OK);
		p6.add(b_Fechar);
					
		return p6;
	}		
	
	public void createAndShowGUI()
	{
		 JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        JFrame frame = new JFrame("ALTERAR RESERVA");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3, p4, p4a, p5, p6;
	
		pGeral= new JPanel();
		pGeral.setLayout(new GridLayout(7,1));
        
		p1=cria_panel_codReserva();
        p2=cria_panel_acomod();
        p3=cria_panel_infoAcomod();
        p4=cria_panel_datas();
        p4a=cria_panel_precoDesconto();
        p5=cria_panel_precoTotal_disponibilidade();
        p6=cria_panel_botoes();        
               
        pGeral.add(p1);                       
        pGeral.add(p2);
        pGeral.add(p3);
        pGeral.add(p4);
        pGeral.add(p4a);
        pGeral.add(p5);        
        pGeral.add(p6);
        
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                
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
        	
        DetalhesReserva res = new DetalhesReserva();
        
       	if (event.getActionCommand().equals("Verificar Reserva"))
    	{
    		res = IAR.obterReserva(jtf_codReserva.getText());
    		if ((res!=null) && (res.getStatus()==false)) 
    		{    			
    			comboAcomodacoes.removeAllItems();
        
				DH= new DetalhesHotel();
				DH = IAR.obterDetalheHotel(res.getHotelID());
						
				Vector tiposAcomodacao = DH.getTiposAcomodacao();		
				int pos = 0;
				for (int i=0; i<tiposAcomodacao.size();i++)
				{
					if ( ((String)tiposAcomodacao.get(i)).equals(res.getTipoAcomodacao()) )					
						pos = i;					
					  comboAcomodacoes.addItem((String)tiposAcomodacao.get(i));
				} 
				
    			String dI = String.valueOf(res.getDataIni().getDate()) + "/" + 
    			     		String.valueOf(res.getDataIni().getMonth()+1) + "/" + 
    			     		String.valueOf(res.getDataIni().getYear()+1900);
    			     
    			String dF = String.valueOf(res.getDataFim().getDate()) + "/" + 
    			     		String.valueOf(res.getDataFim().getMonth()+1) + "/" + 
    			     		String.valueOf(res.getDataFim().getYear()+1900);

			//	System.out.println("obj data ini: " + jtf_DataIni);
			//	System.out.println("Di: " + dI); System.out.println("DF: " + dF);
    			jtf_dataIni.setText(dI);
    			jtf_dataFim.setText(dF);
    			
				//comboAcomodacoes.setSelectedItem((String) res.getTipoAcomodacao());
				comboAcomodacoes.setSelectedIndex(pos);
				selecionaAcomodacao();

				//jtf_tipoAcomodacao.setText(res.getTipoAcomodacao());    			    			
    		}
    	}                               
        
        if (event.getActionCommand().equals("SelecionaAcomodacao"))       	    
        	selecionaAcomodacao();
		
    	if (event.getActionCommand().equals("Verificar"))
    	{   		 			
    		//IAR.selecionaHotel(hotelSelecionado);    		
    		int diaIni = getDia(jtf_dataIni.getText());    		
    		int mesIni = getMes(jtf_dataIni.getText());
    		int anoIni = getAno(jtf_dataIni.getText());
    		
    		int diaFim = getDia(jtf_dataFim.getText());
    		int mesFim = getMes(jtf_dataFim.getText());
    		int anoFim = getAno(jtf_dataFim.getText());
    		    		    		
    		res.setDataIni( new java.util.Date(anoIni-1900, mesIni-1, diaIni));
    		res.setDataFim( new java.util.Date(anoFim-1900, mesFim-1, diaFim));
    		res.setCodReserva (jtf_codReserva.getText());
    		
    		int num = comboAcomodacoes.getSelectedIndex();
        	String acomod = (String) comboAcomodacoes.getItemAt(num);  
        	res.setTipoAcomodacao(acomod);    	        	        		    		
    		    		    		
    		//Float f = new Float(IAR.obterPrecoReserva(res));
    		
    		float preco = IAR.obterPrecoReserva(res);    		
    		jtf_preco.setText(String.valueOf(preco));
    		//res.setCliID(DC.getCliID());    		
    		float txDesc = IAR.obterTXDescontoReserva(res);
    		jtf_txDesconto.setText(String.valueOf(txDesc));
    		
    		float desconto = preco*txDesc;
    		jtf_desconto.setText(String.valueOf(desconto));
    		
    		jtf_precoTotal.setText(String.valueOf(preco-desconto));      		    		    		
    		
    		if (IAR.verificarDisponibilidadePeriodo(res))    		
				jtf_disponivel.setText("Disponível ");
				else jtf_disponivel.setText("Não Disponível"); 
    	}
    	
    	
    	if (event.getActionCommand().equals("OK"))
    	{
    		
    		DetalhesReserva novaRes = new DetalhesReserva();
    		novaRes.setHotelID(res.getHotelID());
    		novaRes.setCliID(res.getCliID());
    		    		
    		int diaIni = getDia(jtf_dataIni.getText());
    		int mesIni = getMes(jtf_dataIni.getText());
    		int anoIni = getAno(jtf_dataIni.getText());
    		
    		int diaFim = getDia(jtf_dataFim.getText());
    		int mesFim = getMes(jtf_dataFim.getText());
    		int anoFim = getAno(jtf_dataFim.getText());
    		
    		novaRes.setDataIni( new java.util.Date(anoIni-1900, mesIni-1, diaIni));
    		novaRes.setDataFim( new java.util.Date(anoFim-1900, mesFim-1, diaFim));
    		    		    		
    		int num = comboAcomodacoes.getSelectedIndex();
        	String acomod = (String) comboAcomodacoes.getItemAt(num);  
        	novaRes.setTipoAcomodacao(acomod);        	
        	
        	System.out.println("Alterar: " + IAR.alterarReserva(jtf_codReserva.getText(), novaRes));   		    		
    	}    	
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAlterarReserva ffr = new FrameAlterarReserva();
	}
}
