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
public class FrameOcuparReserva implements ActionListener
{
	JFrame frame;
	JComboBox comboHoteis;
	JComboBox comboAcomodacoes;
	JTextField jtf_codReserva, jtf_nomeHotel,  jtf_DataIni, 
	jtf_DataFim, jtf_tipoAcomodacao, jtf_preco, jtf_nomeCliente, jtf_RG, jtf_CPF,
	jtf_end, jtf_cidade, jtf_bairro, jtf_FONE, jtf_email, jtf_numAcomod, jtf_CEP, 
	jtf_txDesconto, jtf_precoTotal, jtf_desconto;	
	int cliID;
	
	IOcuparReserva IOR;
	
	public FrameOcuparReserva()
	{	
		IOR = new GerReserva();
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
	
	
	public JPanel cria_panel_dadosReserva()
	{
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4,1));									
		
		JPanel p2_0 = new JPanel();
		p2_0.setLayout(new FlowLayout());
						
		JLabel l_nomeHotel = new JLabel("Hotel: ");
		jtf_nomeHotel = new JTextField(17);													
		
		p2_0.add(l_nomeHotel); p2_0.add(jtf_nomeHotel);
		
		JPanel p2_1 = new JPanel();
		p2_1.setLayout(new FlowLayout());
		
		JLabel l_DataIni = new JLabel("Data Ini:");
		jtf_DataIni = new JTextField(7);		
				
		JLabel l_DataFim = new JLabel("Data Fim:");
		jtf_DataFim = new JTextField(7);
		
		p2_1.add(l_DataIni); p2_1.add(jtf_DataIni);
		p2_1.add(l_DataFim); p2_1.add(jtf_DataFim);
		
		
		JPanel p2_2 = new JPanel();
		p2_2.setLayout (new FlowLayout());
		
		JLabel l_tipoAcomodacao = new JLabel("Tipo:");
		jtf_tipoAcomodacao = new JTextField(8);
		
		JLabel l_preco = new JLabel("Preço: ");
		jtf_preco = new JTextField(7);				
		
		p2_2.add(l_tipoAcomodacao); p2_2.add(jtf_tipoAcomodacao);
		p2_2.add(l_preco); p2_2.add(jtf_preco);						
		
		
		
		JPanel p4a = new JPanel();
		p4a.setLayout(new FlowLayout());		
		
		JLabel l_txDesconto = new JLabel("TxDesc.: ");
		JLabel l_desconto = new JLabel("Desc.: ");
		JLabel l_precoTotal = new JLabel("Preço Total: ");			
		
		jtf_txDesconto = new JTextField(3);
		jtf_desconto = new JTextField(5);				
		jtf_precoTotal = new JTextField(5);
						
		p4a.add(l_txDesconto);
		p4a.add(jtf_txDesconto);
		p4a.add(l_desconto);
		p4a.add(jtf_desconto);
		p4a.add(l_precoTotal);
		p4a.add(jtf_precoTotal);		
		
		p2.add(p2_0); p2.add(p2_1); p2.add(p2_2); p2.add(p4a);
		
		return p2;						
	}
	
	public JPanel cria_panel_cliente()
	{
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(6,1));		
		
		JPanel p3_1 = new JPanel();
		p3_1.setLayout(new FlowLayout());	
						
		JLabel l_nomeCliente = new JLabel("Cliente: ");
		jtf_nomeCliente = new JTextField(18);													
		
		p3_1.add(l_nomeCliente); p3_1.add(jtf_nomeCliente);
				
		
		JPanel p3_2 = new JPanel();
		p3_2.setLayout(new FlowLayout());	
		
		JLabel l_RG = new JLabel("RG:");
		jtf_RG = new JTextField(9);		
				
		JLabel l_CPF = new JLabel("CPF:");
		jtf_CPF = new JTextField(9);

		p3_2.add(l_RG); p3_2.add(jtf_RG);
		p3_2.add(l_CPF); p3_2.add(jtf_CPF);		
		
		
		JPanel p3_3 = new JPanel();
		p3_3.setLayout(new FlowLayout());	
		
		JLabel l_end = new JLabel("Endereco:");
		jtf_end = new JTextField(18);
		
		p3_3.add(l_end); p3_3.add(jtf_end);
		
		
		JPanel p3_4 = new JPanel();
		p3_4.setLayout(new FlowLayout());	
		
		JLabel l_cidade = new JLabel("Cidade: ");
		jtf_cidade = new JTextField(9);
		
		JLabel l_bairro = new JLabel("Bairro: ");
		jtf_bairro = new JTextField(9);

		p3_4.add(l_cidade); p3_4.add(jtf_cidade);
		p3_4.add(l_bairro); p3_4.add(jtf_bairro);		
		
		
		JPanel p3_5 = new JPanel();
		p3_5.setLayout(new FlowLayout());	
		
		JLabel l_FONE = new JLabel("FONE: ");
		jtf_FONE = new JTextField(8);							
		
		JLabel l_CEP = new JLabel("CEP: ");
		jtf_CEP = new JTextField(9);
		
		p3_5.add(l_FONE); p3_5.add(jtf_FONE);
		p3_5.add(l_CEP); p3_5.add(jtf_CEP);
				
		JPanel p3_6 = new JPanel();
		p3_6.setLayout(new FlowLayout());	
				
		JLabel l_email = new JLabel("E-mail:");
		jtf_email = new JTextField(18);									
		
		p3_6.add(l_email); p3_6.add(jtf_email);
		
		p3.add(p3_1);
		p3.add(p3_2);
		p3.add(p3_3);
		p3.add(p3_4);
		p3.add(p3_5);
		p3.add(p3_6);
		
		
		return p3;						
	}
	
	public JPanel cria_panel_botoes()
	{				
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Ocupar Reserva");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("Ocupar Reserva");				
		
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		JLabel l_num = new JLabel("Numero: ");
		jtf_numAcomod = new JTextField (5);
		
		p4.add(b_OK);
		p4.add(b_Fechar);
		p4.add(l_num);
		p4.add(jtf_numAcomod);
					
		return p4;
	}

	
	public void createAndShowGUI()
	{
		 JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        JFrame frame = new JFrame("OCUPAR RESERVA");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3, p4;				
	
		pGeral= new JPanel();
		//pGeral.setLayout(new GridLayout(11,1));
		//pGeral.setLayout(new BorderLayout());
		//pGeral.setSize(400, 400);
		pGeral.setLayout(new BoxLayout(pGeral, BoxLayout.Y_AXIS));
        
		p1=cria_panel_codReserva();
        p2=cria_panel_dadosReserva();
        p3=cria_panel_cliente();
        p4=cria_panel_botoes();
                       
        //pGeral.add(p1);  
        /*                     
        pGeral.add(p2_1);
        pGeral.add(p2_2);
        pGeral.add(p3_1);
        pGeral.add(p3_2);
        pGeral.add(p3_3);
        pGeral.add(p3_4);
        pGeral.add(p3_5);
        pGeral.add(p3_6);*/
       // pGeral.add(p2);
        //pGeral.add(p3);
        //pGeral.add(p4);
        
        JPanel pcentro = new JPanel();
        pcentro.setLayout(new GridLayout(1,1));
        pcentro.add(p2); pcentro.add(p3);
                
        pGeral.add(p1);//,BorderLayout.NORTH);                    
        pGeral.add(p2);//,BorderLayout.CENTER);                
        pGeral.add(p3);//,BorderLayout.CENTER);                        
        pGeral.add(p4);//,BorderLayout.SOUTH);        
                
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);
	}
	

				
	
	
    public void actionPerformed(ActionEvent event) {
   	
   		DetalhesReserva res = new DetalhesReserva();
   		DadosCompletoCliente cli = new DadosCompletoCliente();
    	if (event.getActionCommand().equals("Verificar Reserva"))
    	{
    		res = IOR.obterReserva(jtf_codReserva.getText());
    		if ((res!=null) && (!res.getStatus()))
    		{    			
    		    //Dados Reserva
    		    System.out.println("1111111111111111111111111111");
    			jtf_nomeHotel.setText(IOR.obterNomeHotel(res.getHotelID()));    		    			
    			
    			String dI = String.valueOf(res.getDataIni().getDate()) + "/" + 
    			     		String.valueOf(res.getDataIni().getMonth()) + "/" + 
    			     		String.valueOf(res.getDataIni().getYear()+1900);
    			     
    			String dF = String.valueOf(res.getDataFim().getDate()) + "/" + 
    			     		String.valueOf(res.getDataFim().getMonth()) + "/" + 
    			     		String.valueOf(res.getDataFim().getYear()+1900);
    			
    			jtf_DataIni.setText(dI);
    			jtf_DataFim.setText(dF);
    			res.setCodReserva(jtf_codReserva.getText());
    			
    			float preco=IOR.obterPrecoReserva(res);
    			jtf_preco.setText(String.valueOf(preco));
    			jtf_tipoAcomodacao.setText(res.getTipoAcomodacao());
    			
    			//Dados Cliente
    			
    			//jtf_nomeCliente, jtf_RG, jtf_CPF,
				//jtf_end, jtf_cidade, jtf_bairro, jtf_FONE, jtf_email, jtf_numAcomod, jtf_CEP;
    			cli = IOR.obterClienteReserva(jtf_codReserva.getText());
    			
    			res.setCliID(cli.getCliID());
    			DadosCliente DC = new DadosCliente();
    			DC.setCliID(cli.getCliID());
    			DC.setNome(cli.getNome());
    			DC.setContato(cli.getContato());
    			DC.setEmail(cli.getEmail());
    			float txDesc = IOR.obterTXDescontoReserva(res);
    			jtf_txDesconto.setText(String.valueOf(txDesc));
    		
    			float desconto = preco*txDesc;
    			jtf_desconto.setText(String.valueOf(desconto));
    		
	    		jtf_precoTotal.setText(String.valueOf(preco-desconto)); 
    			
    			
    			cliID = cli.getCliID();    			
    			jtf_nomeCliente.setText(cli.getNome());    			
    			jtf_RG.setText(cli.getRG());    			
    			jtf_CPF.setText(cli.getCPF());    			
    			jtf_end.setText(cli.getEndereco());
    			jtf_cidade.setText(cli.getCidade());
    			jtf_bairro.setText(cli.getBairro());
    			jtf_FONE.setText(cli.getFone());
    			jtf_email.setText(cli.getEmail());
    			jtf_CEP.setText(cli.getCEP());
    			}
    	}
    	
    	if ((event.getActionCommand().equals("Ocupar Reserva")))
    	 {    	    	    	    	    
    	    cli.setCliID(cliID);
			cli.setNome(jtf_nomeCliente.getText());
			cli.setRG(jtf_RG.getText());
			cli.setCPF(jtf_CPF.getText());
			cli.setEndereco(jtf_end.getText());
			cli.setCidade(jtf_cidade.getText());
			cli.setBairro(jtf_bairro.getText());
			cli.setFone(jtf_FONE.getText());
			cli.setCEP(jtf_CEP.getText());
			IOR.completarDadosCliente(cli);
			int NUM = IOR.iniciarEstada(jtf_codReserva.getText(), cli);			
			jtf_numAcomod.setText(String.valueOf(NUM));
			System.out.println("OCUPOU RESERVA");
		 }
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameOcuparReserva ffr = new FrameOcuparReserva();
	}
}