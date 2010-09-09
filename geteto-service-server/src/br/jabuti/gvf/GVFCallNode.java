package br.jabuti.gvf;


/**
 * This class represents the CFGCallNode (square and circle). 
 * It defines a CallNode in a graph within the Graph Visualization 
 * Framework.
 * It can be used directly or subclassed to include application
 * specific behavior.
 * 
 * @author Plinio Vilela
 * @version 1.0 Aug 2001
 */

import java.awt.*;
import java.awt.geom.*;

import br.jabuti.project.*;
import br.jabuti.util.*;

import br.jabuti.graph.*;


public class GVFCallNode extends GVFNode {

    public GVFCallNode(GraphNode gn, ClassMethod m) {
        super(gn, m);
    }

    public void draw(Graphics g) {
        int c = 0;

        // Calculate the new size of the node
        // w.r.t. its label.
        updateShapeSize(g);
    	
        updateLocation();
        g.clearRect(X - ((int) width / 2), Y - ((int) height / 2), width, height);

        g.setColor(color);

        Integer colorNumber = (Integer) gn.getUserData(ToolConstants.LABEL_COLOR);

        if (colorNumber != null) {
            c = colorNumber.intValue();
        }

        // Changing a Rectangle by a Circle (Ellipse)
        Ellipse2D.Float circle = new Ellipse2D.Float();
        Ellipse2D.Float circle2 = new Ellipse2D.Float();
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setStroke(new BasicStroke(2.0F));

        Rectangle2D rect = shape.getBounds2D();

        circle.setFrame(rect);
                
        g2.draw(circle);
        
        g2.setColor(ToolConstants.getColor(c));
        g2.fill(circle);        
        g2.setColor(color);
		
        circle2.setFrameFromCenter(rect.getCenterX(), rect.getCenterY(), rect.getX() + 3, rect.getY() + 3);
        g2.draw(circle2);
		
        g2.setColor(ToolConstants.getColor(c));
        g2.fill(circle2);        
        g2.setColor(color);
		
        drawLabel(g);
    }

}

