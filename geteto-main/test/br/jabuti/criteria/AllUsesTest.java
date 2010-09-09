package br.jabuti.criteria;


import org.junit.Before;

public class AllUsesTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	/*
    public static void main(String args[])
            throws Exception {
        JavaClass java_class;

        java_class = new ClassParser(args[0]).parse();	// May throw IOException
        ConstantPoolGen cp =
                new ConstantPoolGen(java_class.getConstantPool());
        ClassGen cg = new ClassGen(java_class);
        Method[] methods = java_class.getMethods();

        for (int i = 0; i < methods.length; i++) {
            System.out.println("\n\n--------------------------");
            System.out.println(methods[i].getName());
            System.out.println("--------------------------");
            MethodGen mg =
                    new MethodGen(methods[i], java_class.getClassName(), cp);
            CFG g = new CFG(mg, cg);

            AllUses an = new AllUses(g, false);

//            g.print(System.out);

            Object[] req = an.getRequirements();

            System.out.println("Requirements PRIMARY: ");
            for (int j = 0; j < req.length; j++) {
                DefUse gn = (DefUse) req[j];

                System.out.println(gn);
            }

            an = new AllUses(g, true);

//            g.print(System.out);

            req = an.getRequirements();

            System.out.println("Requirements SECONDARY: ");
            for (int j = 0; j < req.length; j++) {
                DefUse gn = (DefUse) req[j];

                System.out.println(gn);
            }

            an.addPath(an.changePath(g, 
                    new String[] {"0", "1", "3"}
                    ), "path 1");  
            an.addPath(an.changePath(g, 
                    new String[] {"0", "2", "3"}
                    ), "path 3");  
            
            int[] cv = an.getCoverage();

            System.out.println();
            System.out.println("Covered: ");
            for (int j = 0; j < cv.length; j++) {
                System.out.println(req[j] + " covered " + cv[j] + "  times");
            }
        }
    }*/

}
