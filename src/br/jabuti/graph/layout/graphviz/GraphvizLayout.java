/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
 */

// GraphViz.java - a simple API to call dot from Java programs

/*$Id$*/
/*
 ******************************************************************************
 *                                                                            *
 *              (c) Copyright 2003 Laszlo Szathmary                           *
 *                                                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms of the GNU Lesser General Public License as published by   *
 * the Free Software Foundation; either version 2.1 of the License, or        *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This program is distributed in the hope that it will be useful, but        *
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY *
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public    *
 * License for more details.                                                  *
 *                                                                            *
 * You should have received a copy of the GNU Lesser General Public License   *
 * along with this program; if not, write to the Free Software Foundation,    *
 * Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                              *
 *                                                                            *
 ******************************************************************************
 */
package br.jabuti.graph.layout.graphviz;

import java.io.*;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.jabuti.graph.layout.GraphLayout;
import br.jabuti.graph.view.gvf.GVFNode;

/**
 * <dl>
 * <dt>Purpose: GraphViz Java API
 * <dd>
 * 
 * <dt>Description:
 * <dd>With this Java class you can simply call dot from your Java programs
 * <dt>Example usage:
 * <dd>
 * 
 * <pre>
 * GraphViz gv = new GraphViz();
 * gv.addln(gv.start_graph());
 * gv.addln(&quot;A -&gt; B;&quot;);
 * gv.addln(&quot;A -&gt; C;&quot;);
 * gv.addln(gv.end_graph());
 * System.out.println(gv.getDotSource());
 * 
 * File out = new File(&quot;out.gif&quot;);
 * gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
 * </pre>
 * 
 * </dd>
 * 
 * </dl>
 * 
 * @version v0.1, 2003/12/04 (Decembre)
 * @author Laszlo Szathmary (<a href="szathml@delfin.unideb.hu">szathml@delfin.unideb.hu</a>)
 */
public class GraphvizLayout implements GraphLayout
{
	/**
	 * The dir where temporary files will be created.
	 */
	private static String TEMP_DIR = System.getProperty("java.io.tmpdir");

	/**
	 * Where is your dot program located? It will be called externally.
	 */
	private final static String DOT_W = "c:\\Arquivos de programas\\Graphviz2.22\\bin\\dot.exe";
	private final static String DOT_L = "/usr/bin/dot";
	private static String DOT = null;

	/**
	 * The source of the graph written in dot language.
	 */
	private StringBuffer graph = new StringBuffer();

	private String windowsFindDot()
	{
		String s = getGraphVizInstallPath();
		if (s == null)
			return DOT_W;
		else
			return s += File.separator + "bin" + File.separator + "dot.exe";
	}

	public static String getGraphVizInstallPath()
	{
		final String REGQUERY_UTIL = "reg query ";
		final String REGSTR_TOKEN = "REG_EXPAND_SZ";
		final String COMPUTER_WINDOWS_GRAPHVIZ_FOLDER = REGQUERY_UTIL
						+ "\"HKLM\\SOFTWARE\\AT&T Research Labs\\Graphviz\" /v InstallPath";

		try {
			Process process = Runtime.getRuntime().exec(COMPUTER_WINDOWS_GRAPHVIZ_FOLDER);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process
							.getInputStream()));
			process.waitFor();
			String result = reader.readLine();
			int p = result.indexOf(REGSTR_TOKEN);
			if (p == -1)
				return null;
			else
				return result.substring(p + REGSTR_TOKEN.length()).trim();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Constructor: creates a new GraphViz object that will contain a graph.
	 * 
	 * @throws FileNotFoundException
	 */
	public GraphvizLayout()
	{
		if (DOT == null) {
			String s = System.getProperty("os.name").toUpperCase();
			if ("LINUX".equals(s)) {
				DOT = DOT_L;
			} else if (s != null && s.startsWith("WINDOWS")) {
				DOT = windowsFindDot();
				DOT = DOT_W;
			} else {
				DOT = JOptionPane.showInputDialog(null, "Please enter path:",
								"Cannot find GraphViz layouter (dot).", JOptionPane.ERROR_MESSAGE);

			}
			while (DOT != null) {
				File f = new File(DOT);
				if (f.isFile() && f.canRead())
					break;
				DOT = JOptionPane.showInputDialog(null, "Please enter path:",
								"Cannot find GraphViz layouter at " + DOT,
								JOptionPane.ERROR_MESSAGE);

			}
			if (DOT == null) {
				DOT = "";
				throw new RuntimeException(new FileNotFoundException("Cannot find GraphViz."));
			}
		}
	}

	/**
	 * Returns the graph's source description in dot language.
	 * 
	 * @return Source of the graph in dot language.
	 */
	public String getDotSource()
	{
		return graph.toString();
	}

	public void addNode(GVFNode node)
	{
		graph.append(node.getSource() + " [width=\"0.50\", height=\"0.50\"];\n");
	}

	public void addEdge(GVFNode src, GVFNode dest)
	{
		graph.append(src.getSource() + " -> " + dest.getSource() + "\n");
	}

	private String getDotLayout(String dot_source)
	{
		File dot;
		String img_stream = null;

		try {
			dot = writeDotSourceToFile(dot_source);
			if (dot != null) {
				img_stream = runDotLayout(dot);
				if (dot.delete() == false)
					System.err.println("Warning: " + dot.getAbsolutePath()
									+ " could not be deleted!");
			}
			return img_stream;
		} catch (Exception ioe) {
			return null;
		}
	}

	private String runDotLayout(File dot) throws IOException, InterruptedException
	{
		File output = File.createTempFile("graph_", ".dot", new File(TEMP_DIR));

		Runtime rt = Runtime.getRuntime();
		String cmd = DOT + " -Tdot " + dot.getAbsolutePath() + " -o" + output.getAbsolutePath();
		Process p = rt.exec(cmd);
		p.waitFor();
		return output.getAbsolutePath();
	}

	/**
	 * Writes the source of the graph in a file, and returns the written file as a File object.
	 * 
	 * @param str Source of the graph (in dot language).
	 * @return The file (as a File object) that contains the source of the graph.
	 */
	private File writeDotSourceToFile(String str) throws java.io.IOException
	{
		File temp;
		try {
			temp = File.createTempFile("graph_", ".dot.tmp", new File(this.TEMP_DIR));
			FileWriter fout = new FileWriter(temp);
			fout.write(str);
			fout.close();
		} catch (Exception e) {
			System.err.println("Error: I/O error while writing the dot source to temp file!");
			return null;
		}
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jabuti.gvf.layout.graphviz.GraphLayout#start_graph()
	 */
	public void start_graph()
	{
		graph.append("digraph G {\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jabuti.gvf.layout.graphviz.GraphLayout#end_graph()
	 */
	public void end_graph()
	{
		graph.append("}");
	}

	public void layout(Vector vNodes, Vector vLinks)
	{

		String result = getDotLayout(getDotSource());

		File f = new File(result);
		try {
			DotParser dt = new DotParser(vNodes, vLinks, new FileInputStream(f));
			dt.parse();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			f.delete();
		}
	}
}
