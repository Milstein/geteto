package br.jabuti;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.util.ClassPath;


import br.jabuti.graph.datastructure.defuse.CFG;
import br.jabuti.lookup.Program;
import br.jabuti.probe.desktop.desktop.DefaultProbeInsert;

public class TestGenInstFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long begintime = System.currentTimeMillis();
		
		//String inputfile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/junit/junit.jar";
		//String outputfile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/junit/junit_inst.jar";

		String inputfile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending.jar";
		String outputfile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending_inst.jar";

		
		System.out.println("start!");
		try {
			Program program = Program.createFromPackage(inputfile);
			String programs[] = program.getClasses();
			
			/*for (int i = 0; i < programs.length; i++) {
				RClass rc =  program.get(programs[i]);
				if (rc instanceof RClassCode) {
					RClassCode rcc = (RClassCode) rc;
					count++;
					JavaClass javaclass = rcc.getTheClass();
					ConstantPoolGen cp = new ConstantPoolGen(javaclass.getConstantPool()); 
					ClassGen cg = new ClassGen(javaclass);
					Method methods[] = javaclass.getMethods();
					
					System.out.println("-------------------------------");
					System.out.println("Class: " + javaclass.getClassName());
					
					for (int j = 0; j < methods.length; j++) {
						MethodGen mg = new MethodGen(methods[j], javaclass.getClassName(), cp);
						CFG g = new CFG(mg, cg);
						
						
						AllUses au = new AllUses(g, false);
						//g.print(System.out);
						Object req[] = au.getRequirements();
						
						System.out.println("->" + methods[j]);
						System.out.println("All-Uses: " + req.length);
						
						AllNodes an = new AllNodes(g, AllNodes.ALL);
						req = an.getRequirements();
						System.out.println("All-Nodes: " + req.length);
						
						AllEdges aes = new AllEdges(g, AllEdges.ALL);
						req = aes.getRequirements();
						
						System.out.println("All-Edges: " + req.length);
						
					}
				}
			}*/
			HashSet set = new HashSet();
			
			/*set.add("junit.runner.BaseTestRunner");
			set.add("junit.framework.TestSuite");
			set.add("junit.awtui.Logo");
			*/
			
			String classes[] = program.getClasses();
			for (int i = 0; i < classes.length; i++) {
				set.add(classes[i]);
			}
			
			DefaultProbeInsert dpi = new DefaultProbeInsert(program, set);
			Map mp = dpi.instrument(CFG.NO_CALL_NODE);
			
   			Iterator it0 = mp.keySet().iterator();
   			Hashtable ht = new Hashtable();
   			
  			while (it0.hasNext())
  			{
  				String clName = (String) it0.next();
  				JavaClass jv = (JavaClass) mp.get(clName);
  				ht.put(clName, jv.getBytes());
  			}
            
  			mp = ht;
  			
  			File outFile = new File(outputfile);
  			FileOutputStream fos = new FileOutputStream(outFile);
  			JarOutputStream outJar = new JarOutputStream(fos);
  			Iterator it = mp.keySet().iterator();
  			while (it.hasNext())
  			{
  				String clName = (String) it.next();
  				byte[] b = (byte[]) mp.get(clName);
				clName = clName.replace('.', '/');
  				JarEntry jarEntry = new JarEntry(clName+".class");
  				outJar.putNextEntry(jarEntry);
  				outJar.write(b);
  			}
  			ClassPath cp = new ClassPath(System.getProperty("java.class.path"));
  			
  			String[] ProberClasses = new String[] {
  					"br.jabuti.probe.ProbedNode",
  					"br.jabuti.probe.DefaultProber",
  					"br.jabuti.probe.DefaultProberHook"
  				};  			
  			
  			for (int z1 = 0; z1 < ProberClasses.length; z1++)
  			{
  				String clName = ProberClasses[z1];
  				byte[] b = cp.getBytes(clName, ".class");
				clName = clName.replace('.', '/');
  				JarEntry jarEntry = new JarEntry(clName+".class");
  				outJar.putNextEntry(jarEntry);
  				outJar.write(b);
  			}
  			outJar.close();		
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		long endtime = System.currentTimeMillis();
		System.out.println("Execution time: " + String.valueOf(endtime - begintime));

	}

}
