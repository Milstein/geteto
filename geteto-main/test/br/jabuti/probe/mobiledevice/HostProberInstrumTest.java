package br.jabuti.probe.mobiledevice;


import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.junit.Before;

import br.jabuti.lookup.Program;
import br.jabuti.project.JabutiProject;
import br.jabuti.util.ToolConstants;

public class HostProberInstrumTest
{

	@Before
	public void setUp() throws Exception
	{
	}

    public static void main(String args[]) throws Throwable {
        String workDir = null;
        String projName = null;
        String baseClass = null; // The class file to be executed
        String outName = null;
        String[] parameters = null; // The paramenters used to execute the main file
						
        JabutiProject project = null;
        Program program = null; // The program to be tested
        HashSet toInstrumenter = null, agentClasses = new HashSet();
        String projFileName = null; // A given class or compressed file name
        String hostName = "localhost:1988";
        int delay = 2000;

        if (args.length > 0) {

            int i = 0;
			
            for (i = 0; i < args.length - 1;i++) 
            {
                // -CP: Class path
                if (("-d".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    workDir = args[i];
                } // -P: project name
                else if (("-p".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    projFileName = args[i];
                }
                else if (("-name".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    projName = args[i];
                }
                else if (("-h".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    hostName = args[i];
                } 
				else if (("-o".equals(args[i])) && (i < args.length - 1)) {
					i++;
					outName = args[i];
				} 
                else if (("-delay".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    delay = Integer.parseInt(args[i]);
                } 
                else
                	break;
            }

            if (i >= args.length) {
                System.out.println("Error: Missing base class!!!");
                HostProberInstrum.usage();
                System.exit(0);
            }
            // Ultimo par�metro... file seguido dos par�metros
            baseClass = args[i++];
            
						
            parameters = new String[args.length - i];
            for (int j = 0; i < args.length; j++, i++) {
                parameters[j] = args[i];
            }

            // Checking if all essential parameters are not null
            if ((projFileName == null) || (outName == null) ||
                 (projName == null) ) {
                System.out.println("Error: Missing parameter!!!");
                HostProberInstrum.usage();
                System.exit(0);
            }
			
            // Creating the absolute path to a given project
            String absoluteName = null;

            if (workDir != null) {
                absoluteName = workDir + File.separator + projFileName;
            } else {
                absoluteName = projFileName;
            }

				
            try {
                File theFile = new File(absoluteName);

                if (!theFile.isFile()) // verifica se existe
                {
                    System.out.println("File " + theFile.getName() + " not found");
                    System.exit(0);
                }
	          	
                project = JabutiProject.reloadProj( theFile.toString(), false );
                program = project.getProgram();
				
                toInstrumenter = project.getInstr();
            } catch (Exception e) {
                ToolConstants.reportException(e, ToolConstants.STDERR);
                System.exit(0);
            }
            
            // pega quais sao as classe que herdam da classe mucode.abstractions.MuAgent
			
			String[] agClassVet = program.getSubClassClosure
			                                 (HostProbeInsert.MUCODE_AGENT);
			
			for (int j = 0; j < agClassVet.length; j++)
			{
				agentClasses.add(agClassVet[j]);
			} 
							                  
            HostProbeInsert dpi = new HostProbeInsert(program, 
            										toInstrumenter,
            										agentClasses);
            Map mp = null;

            try {
                mp = dpi.instrument( project.getCFGOption() );
                
				JavaClass jv = (JavaClass) mp.get(baseClass);
				if ( jv == null )
				{
					System.out.println("Base class not found in project: " + baseClass);
					System.exit(0);
				}
				jv = HostProberInstrum.instrumentMain(jv, hostName, projName, delay);
				mp.put(baseClass, jv);

                // substitui os objetos JavaClass por byte[]
       			Iterator it0 = mp.keySet().iterator();
       			File outFile = new File(outName);
       			
      			while (it0.hasNext())
      			{
      				String clName = (String) it0.next();
      				jv = (JavaClass) mp.get(clName);
      				File fdir = new File(outFile, jv.getPackageName().replace('.','/'));
      				fdir.mkdirs();
      				int k = clName.lastIndexOf('.');
      				k = (k < 0 ? 0 : k+1);
      				clName = clName.substring(k, clName.length());
      				File f = new File(fdir, clName+".class");
      				jv.dump(f);
      			}
			} catch (Exception eu) {
				System.err.println(eu);
				eu.printStackTrace();
			}

        } else {
            HostProberInstrum.usage();
        }
    }


	
}
