package br.jabuti.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import br.jabuti.lookup.Program;
import br.jabuti.runner.gui.MetricsPanel;
import br.jabuti.util.ToolConstants;


public class MetricsPanelTest
{
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        try {
        	Program p = Program.createFromBaseClass("br.jabuti.metrics.Metrics");
        	String[] classes = {"br.jabuti.metrics.Metrics"};
            
            MetricsPanel panel = new MetricsPanel( p, classes );
            frame.getContentPane().add( new JLabel( " NORTH LABEL " ), BorderLayout.NORTH );
        	frame.getContentPane().add( panel, BorderLayout.CENTER );
            frame.getContentPane().add( new JLabel( " SOUTH LABEL " ), BorderLayout.SOUTH );        	
        } catch (Exception e) {
            ToolConstants.reportException(e, ToolConstants.STDERR);
            System.exit(0);
        }
        
        frame.pack();
        frame.setVisible(true);
    }
}
