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
import GesAcesso.*;
import Tipos.*;
import java.util.Vector;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FrameAssociaOperacaoPapel implements ActionListener, ListSelectionListener
{
	JFrame frame;
	
	JComboBox comboPapeis;		
	
	private static JList LOp, LOpPapel;
	private static DefaultListModel LM_Op, LM_OpPapel = new DefaultListModel();
	
		
	IGesAcesso IGA;
		
	
	public FrameAssociaOperacaoPapel()
	{	
		IGA = new GesAcesso();
		createAndShowGUI();		
	}

	public JPanel cria_panel_papel()
	{			
		Vector listaPapeis = IGA.obterPapeis();
		
		comboPapeis = new JComboBox();
		comboPapeis.addActionListener(this);
		comboPapeis.setActionCommand("SelecionaPapel");
						
		for (int i=0; i<listaPapeis.size(); i++)
			comboPapeis.addItem(new String((String) listaPapeis.get(i)));
												
		JPanel p1 = new JPanel(); 
		p1.setLayout(new FlowLayout());                        
		
		JLabel l_papeis = new JLabel("Papel: ");
		p1.add(l_papeis);
        p1.add(comboPapeis);                        
        
        return p1;
	}
	
    public JPanel cria_panel_todasOp()
    {
    	LM_Op = new DefaultListModel();
    	
    	Vector listaOp = IGA.obterOperacoes();
    	for (int i=0; i<listaOp.size(); i++)
    	    LM_Op.addElement((String) listaOp.get(i));
    	
        LOp = new JList(LM_Op);        
        LOp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        LOp.setSelectedIndex(0);
        LOp.addListSelectionListener(this);
        //list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(LOp);
        
        JPanel p2 = new JPanel();        
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        JLabel l_op = new JLabel("Operacoes do Sistema");
        p2.add(l_op);
        p2.add(listScrollPane);
        //p2.setPreferredSize(new Dimension(150,300));
        return p2;
    }
    
    public JPanel cria_panel_OpPapel()
    {
    //	LM_OpPapel = new DefaultListModel();    	    	    	
    	
        LOpPapel = new JList(LM_OpPapel);        
        LOpPapel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //LOp.setSelectedIndex(0);
        LOpPapel.addListSelectionListener(this);
        //list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(LOpPapel);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
        
        JLabel l_opPapel = new JLabel("Operações do Papel");
        p4.add(l_opPapel);
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
        JFrame frame = new JFrame("FAZER ASSOCIAR PAPEL -> OPERAÇÕES");
        //frame.setSize(new Dimension(400,200));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pGeral, p1, p2, p3, p4, pM;
	
		pGeral= new JPanel();

		pGeral.setLayout(new BorderLayout());
	//	pGeral.setPreferredSize(new Dimension(400,400));
        
		p1=cria_panel_papel();          
		p2=cria_panel_todasOp();      
		p3=cria_panel_OpPapel();
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

		selecionaPapel();
	}
	
	public void selecionaPapel()
	{
		int ind = comboPapeis.getSelectedIndex();
        String papelSelecionado = (String) comboPapeis.getItemAt(ind);        
        //LM_OpPapel = new DefaultListModel();
       
        if (LM_OpPapel!=null)
        {        
        	LM_OpPapel.removeAllElements();               	
        }

       Vector listaOpPapel = IGA.obterOperacoesPapel(papelSelecionado);       
       for (int i=0; i<listaOpPapel.size(); i++)
    	   LM_OpPapel.addElement((String) listaOpPapel.get(i));        		
	}

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (LOp.getSelectedIndex() == -1) {
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
    		int indPapel = comboPapeis.getSelectedIndex();
       		String papelSelecionado = (String) comboPapeis.getItemAt(indPapel);
    		
    		System.out.println("11111111");
    		String nomeOp="";
    		for (int i=0; i<LM_OpPapel.size(); i++)
    		{
    			nomeOp = (String)LM_OpPapel.getElementAt(i);
    			IGA.atribuirOperacaoPapel(papelSelecionado, nomeOp);
    		}
    		
    		System.out.println("Associado");
    		//int numHotel = comboHoteis.getSelectedIndex();
        	//	String hotelSelecionado = (String) comboHoteis.getItemAt(numHotel);     	
    	}           	
        	        
    	if (event.getActionCommand().equals("SelecionaPapel"))
    	{
    		selecionaPapel();     	
    	}          
    	
    	if (event.getActionCommand().equals("add"))
    	{
    		int ind = LOp.getSelectedIndex();
    		String elemento = (String) LM_Op.getElementAt(ind);
    		
    		if (!LM_OpPapel.contains(elemento))    		    
    			LM_OpPapel.addElement(elemento);
    	}
    	
    	
    	if (event.getActionCommand().equals("exc"))
    	{
    		int ind = LOpPapel.getSelectedIndex();
    		if (ind>=0)
    			LM_OpPapel.removeElementAt(ind);
    	}
    	
    	if (event.getActionCommand().equals("excAll"))
    	{    		
    		if (LM_OpPapel!=null)
    			LM_OpPapel.removeAllElements();
    	}
    	
    	if (event.getActionCommand().equals("addAll"))
    	{
    		if(LM_OpPapel!=null)
    		{
    			LM_OpPapel.removeAllElements();
    		}
    		
    		String oper = "";
    		for (int i=0; i<LM_Op.size(); i++)
    		{
    			oper = (String) LM_Op.getElementAt(i);
    			LM_OpPapel.addElement(oper);
    		}    		
    	}
    	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameAssociaOperacaoPapel FAOP = new FrameAssociaOperacaoPapel();
	}
}