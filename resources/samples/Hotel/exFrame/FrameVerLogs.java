/*
 * Created on 19/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 package exFrame;
 
import java.awt.*;
import javax.swing.*;
import javax.swing.JTable;
import GesRegistroOp.*;
import GerRegistroOp.*;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.util.Vector;
/**
 * @author mareler
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FrameVerLogs implements ActionListener
{
	JFrame frame;	
	
	JTextField jtf_usuario;
	JTable table;
	JPanel pGeral;
			
	public FrameVerLogs()
	{	
		createAndShowGUI();		
	}
		
	public JPanel cria_panel_usuario()
	{
		JLabel l_usuario = new JLabel("Usuário");		
		
		jtf_usuario = new JTextField(10);
		
		JButton b_ver = new JButton("Verificar");
		b_ver.addActionListener(this);
		b_ver.setActionCommand("b_ver");												
						
		JPanel p1 = new JPanel(); 
		p1.add(l_usuario);
        p1.add(jtf_usuario);
        p1.add(b_ver);
        p1.setLayout(new FlowLayout());                        
        
        return p1;
	}
			
		
	public void createAndShowGUI()
	{
	    JFrame.setDefaultLookAndFeelDecorated(true);
   
         //Create and set up the window.
        frame = new JFrame("VERIFICAR LOGS");
        //frame.setSize(new Dimension(400,200));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        
        JPanel p1;
	
		pGeral= new JPanel();

		pGeral.setLayout(new BorderLayout());
        
		p1=cria_panel_usuario();                
		
		//TableModel dataModel = new AbstractTableModel();
		
	//	dataModel                        
        
        table = new JTable();                
                               
        pGeral.add(p1, BorderLayout.NORTH);                                   
        pGeral.add(table, BorderLayout.CENTER);
        
        frame.getContentPane().add(pGeral);
        frame.pack();
        frame.setVisible(true);                

	}					
	
    public void actionPerformed(ActionEvent event) {
   	
    	if (event.getActionCommand().equals("b_ver"))  	    
        	{
             
                System.out.println("VER");      		
        		IGesRegistroOp IGRO = new GesRegistroOp();
        		
        		RegistroOp GRO = new RegistroOp();
        		Vector vt = GRO.obterRegistrosOperacao();
        		//Vector vt = IGRO.obterRegistrosOperacao();
        		Vector vt2 = new Vector();
        		
        		for (int i=0; i<vt.size(); i++)
        		{        		
				    Vector v = new Vector();
				    v.add(vt.get(i));
				    vt2.add(v);
				}
        		        		        		        		        		
        		/*        		        		
                TableModel dataModel = new AbstractTableModel() {
         		public int getColumnCount() { return 1; }
          		public int getRowCount() { return vt.size();}
          		public Object getValueAt(int row, int col) { return vt.get(row); }
      			};      			
      			
      			table.setModel(dataModel);
				*/	
				pGeral.remove(1);
				
				
				Vector cab = new Vector();
				cab.add("log");
				JScrollPane jsp = new JScrollPane(new JTable(vt2, cab));
				
				pGeral.add(jsp, 1);      //new JScrollPane(new JTable(vt, cab)), 1);
				pGeral.validate();
				
				frame.pack();

        	} 	
    }
	
	public static void main(String[] args) {
//		Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        FrameVerLogs fvl = new FrameVerLogs();
	}
}