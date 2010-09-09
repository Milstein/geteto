package br.jabuti.gvf;


/**
 * This class interacts with ILOG's JView graph visualization API
 * to allow for layout definition for the GVF Objects.
 *
 * @author  Plinio Vilela
 * @version 1.0
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


class GVFLayout {

    // Plan to make class more general 
    // and allow it to change the layout algorithm on the fly.
    Vector vNodes;
    Vector vLinks;

    int maxX, maxY;
	
    public GVFLayout(Vector vN, Vector vL) {
        vNodes = vN;
        vLinks = vL;
        maxX = 0;
        maxY = 0;
        
        try {
			performLayout();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
        for (Enumeration e = vNodes.elements(); e.hasMoreElements();) {
            GVFDisplayable n = (GVFDisplayable) e.nextElement();
			
            if (((GVFNode) n).getX() > maxX) {
                maxX = ((GVFNode) n).getX();
            }
			
            if (((GVFNode) n).getY() > maxY) {
                maxY = ((GVFNode) n).getY();
            }
			
          //  n.translate(36, 36);
        }
    }

    public void performLayout() throws FileNotFoundException, ParseException {
    	GraphViz gviz = new GraphViz();
    	gviz.addln(gviz.start_graph());
    	for (int i = 0; i < vNodes.size(); i++)
    	{
    		GVFNode node = (GVFNode) vNodes.get(i);

    		gviz.addln(node.getSource() + " [width=\"0.50\", height=\"0.50\"];");
    	}
    	for (int i = 0; i < vLinks.size(); i++)
    	{
    		GVFLink link = (GVFLink) vLinks.get(i);
    		gviz.addln(link.getSourceNode().getSource() + " -> " + 
    				link.getDestinationNode().getSource());
    	}    	
    	gviz.addln(gviz.end_graph());

    	String result = gviz.getDotGraph(gviz.getDotSource());

    	//System.out.println("RESULT\n*********\n" + result + "\n*********\n");
    	
    	File f = new File(result);
    	DotParser dt = new DotParser(vNodes, vLinks, new FileInputStream(f));
    	dt.parse();

    	f.delete();
    }

    public Vector getNodes() {
        return vNodes;
    }

    public Vector getLinks() {
        return vLinks;
    }

    public int getMaxX() { 
        return maxX;
    }

    public int getMaxY() { 
        return maxY;
    }
}
