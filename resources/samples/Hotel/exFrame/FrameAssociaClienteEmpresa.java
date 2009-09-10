/*
 * Created on 19/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 package exFrame;
 
import javax.swing.ListModel;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import GesEmpresa.*;
import GesCliente.*;
import Tipos.*;
import java.util.Vector;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FrameAssociaClienteEmpresa implements ActionListener, ListSelectionListener
{
	JFrame frame;
	
	JComboBox comboEmpresas;		
	
	private static JList LCli, LEmpCli;
	private static DefaultListModel LM_Cli, LM_EmpCli = new DefaultListModel();
	
		
	IGesEmpresa IGE = new GesEmpresa();
	IGesCliente IGC = new GesCliente();
		
	
	public FrameAssociaClienteEmpresa()
	{	
		createAndShowGUI();		
	}	
	
	public JPanel cria_panel_empresa()
	{			
		Vector listaEmpresas = IGE.obterEmpresas();
		
		comboEmpresas = new JComboBox();
		comboEmpresas.addActionListener(this);
		comboEmpresas.setActionCommand("SelecionaEmpresa");
						
		for (int i=0; i<listaEmpresas.size(); i++)
			comboEmpresas.addItem(new String((String) listaEmpresas.get(i)));
												
		JPanel p1 = new JPanel(); 
		p1.setLayout(new FlowLayout());                        
		
		JLabel l_empresas = new JLabel("Empresa: ");
		p1.add(l_empresas);
        p1.add(comboEmpresas);                        
        
        return p1;
	}
	
    public JPanel cria_panel_todosCli()
    {
    	LM_Cli = new DefaultListModel();
    	
    	Vector listaCli = IGC.obterClientes();
    	for (int i=0; i<listaCli.size(); i++)
    	    LM_Cli.addElement((String) listaCli.get(i));
    	
        LCli = new JList(LM_Cli);        
        LCli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        LCli.setSelectedIndex(0);
        LCli.addListSelectionListener(this);
        //list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(LCli);
        
        JPanel p2 = new JPanel();        
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        JLabel l_Cli = new JLabel("Clientes Cadastrados");
        p2.add(l_Cli);
        p2.add(listScrollPane);
        //p2.setPreferredSize(new Dimension(150,300));
        return p2;
    }
    
    public JPanel cria_panel_EmpCli()
    {
    //	LM_OpPapel = new DefaultListModel();    	    	    	
    	
        LEmpCli = new JList(LM_EmpCli);        
        LEmpCli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //LOp.setSelectedIndex(0);
        LEmpCli.addListSelectionListener(this);
        //list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(LEmpCli);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
        
        JLabel l_EmpCli = new JLabel("Clientes da Empresa");
        p4.add(l_EmpCli);
        p4.add(listScrollPane);
       // p4.setPreferredSize(new Dimension(150,150));
        
        return p4;
    }
    
    public JPanel cria_panel_botoesMeio()
    {
    	JPanel pM = new JPanel();
    	pM.setLayout(new GridLayout(4,1));
    	
    	JButton b_add = new JButton(">>");
    	b_add.addActionListener(this);
    	b_add.setActionCommand("add");
    	    	
    	JButton b_exc = new JButton("<<");
    	b_exc.addActionListener(this);
    	b_exc.setActionCommand("exc");
    	
    	JButton b_all = new JButton(">>>>");
    	b_all.addActionListener(this);
    	b_all.setActionCommand("addAll");
    	
    	JButton b_excAll = new JButton("<<<<");
    	b_excAll.addActionListener(this);
    	b_excAll.setActionCommand("excAll");
    	
    	pM.add(b_add);
    	pM.add(b_exc);
    	pM.add(b_all);
    	pM.add(b_excAll);
    	
    //	pM.setPreferredSize(new Dimension(30,200));
    	return pM;
    }
	
	
	public JPanel cria_panel_botoes()
	{				
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JButton b_OK = new JButton("Associar");
		b_OK.addActionListener(this);
		b_OK.setActionCommand("Associar");
		
		JButton b_Limpar = new JButton("Limpar");
		b_Limpar.addActionListener(this);
		b_Limpar.setActionCommand("Limpar");
		
		JButton b_Fechar = new JButton("Fechar");
		b_Fechar.addActionListener(this);
		b_Fechar.setActionCommand("Fechar");
		
		p3.add(b_OK);
		p3.add(b_Limpar);
		p3.add(b_Fechar);						    
					
		return p3;
	}
	
	public void createAndShowGUI()
	{
		 JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        JFrame frame = new JFrame("Associar Cliente -> Empresa");
        //frame.setSize(new Dimension(400,200));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3, p4, pM;
	
		pGeral= new JPanel();

		pGeral.setLayout(new BorderLayout());
	//	pGeral.setPreferredSize(new Dimension(400,400));
        
		p1=cria_panel_empresa();          
		p2=cria_panel_todosCli();      
		p3=cria_panel_EmpCli();
        p4=cria_panel_botoes();
        pM=cria_panel_botoesMeio();
                       
        pGeral.add(p1, BorderLayout.NORTH);      
        pGeral.add(p2, BorderLayout.LINE_START);                     
        pGeral.add(p3, BorderLayout.LINE_END);                     
        pGeral.add(p4, BorderLayout.SOUTH);        
        pGeral.add(pM, BorderLayout.CENTER);                     
        
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                

		selecionaEmpresa();
	}
	
	public void selecionaEmpresa()
	{			
		int ind = comboEmpresas.getSelectedIndex();
        String empresaSelecionada = (String) comboEmpresas.getItemAt(ind);        
        //LM_OpPapel = new DefaultListModel();
       
        if (LM_EmpCli!=null)
        {        
        	LM_EmpCli.removeAllElements();        
        }

       Vector listaEmpCli = IGE.obterClientesEmpresa(empresaSelecionada);       
       for (int i=0; i<listaEmpCli.size(); i++)
    	   LM_EmpCli.addElement((String) listaEmpCli.get(i));        		
	}

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (LCli.getSelectedIndex() == -1) {
            //No selection, disable fire button.
               // fireButton.setEnabled(false);

            } else {
            //Selection, enable the fire button.
                //fireButton.setEnabled(true);
            }
        }
    }
	
	
    public void actionPerformed(ActionEvent event) {
   	
    	if (event.getActionCommand().equals("Associar"))
    	{
    		int indEmp = comboEmpresas.getSelectedIndex();
       		String empresaSelecionada = (String) comboEmpresas.getItemAt(indEmp);
    		DadosCliente cli = new DadosCliente();    		
    		String nomeCli="";
    		for (int i=0; i<LM_EmpCli.size(); i++)
    		{
    			nomeCli = (String)LM_EmpCli.getElementAt(i);
    			cli=IGC.obterCliente(nomeCli);
    			IGE.associaClienteEmpresa(cli, empresaSelecionada);
    		}
    		
    		System.out.println("Associado");
    		//int numHotel = comboHoteis.getSelectedIndex();
        	//	String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	
    	}           	
        	        
    	if (event.getActionCommand().equals("SelecionaEmpresa"))
    	{
    		selecionaEmpresa();     	
    	}          
    	
    	if (event.getActionCommand().equals("add"))
    	{
    		int ind = LCli.getSelectedIndex();
    		String elemento = (String) LM_Cli.getElementAt(ind);
    		
    		if (!LM_EmpCli.contains(elemento))    		    
    			LM_EmpCli.addElement(elemento);
    	}
    	
    	
    	if (event.getActionCommand().equals("exc"))
    	{
    		int ind = LEmpCli.getSelectedIndex();
    		if (ind>=0)
    			LM_EmpCli.removeElementAt(ind);
    	}
    	
    	if (event.getActionCommand().equals("excAll"))
    	{    		
    		if (LM_EmpCli!=null)
    			LM_EmpCli.removeAllElements();
    	}
    	
    	if (event.getActionCommand().equals("addAll"))
    	{
    		if(LM_EmpCli!=null)
    		{
    			LM_EmpCli.removeAllElements();
    		}
    		
    		String cli = "";
    		for (int i=0; i<LM_Cli.size(); i++)
    		{
    			cli = (String) LM_Cli.getElementAt(i);
    			LM_EmpCli.addElement(cli);
    		}    		
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAssociaClienteEmpresa FACE = new FrameAssociaClienteEmpresa();
	}
}