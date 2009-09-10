package exFrame;



import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Persistencia.*;

public class FrameEntrar implements ActionListener 
{    
    JFrame frame;
        
    public FrameEntrar()    
    {   
        ConnectionManager.ConnectDB();
        createAndShowGUI();
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        menu = new JMenu("Sistema");     
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Entrar");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("Entrar");
        menu.add(menuItem);
                
        return menuBar;
    }

    public  Container createContentPane() {
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
        //FrameEntrar demo = new FrameEntrar();
        frame.setJMenuBar(createMenuBar());
        frame.setContentPane(createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) 
    {
    	
    	if (e.getActionCommand().equals("Entrar"))
    	{
    		System.out.println("ENTRARRR");
    		iniciarAplicacao();
    	}
    	
    	
    	    	    	
    }
    
    public void iniciarAplicacao()
    {
    	FramePrincipal FP = new FramePrincipal();
    }
    
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/
        FrameEntrar FE = new FrameEntrar();
    }        				
    
}