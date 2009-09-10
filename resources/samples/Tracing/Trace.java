public aspect Trace {

    /*
     * Functional part
     */

    /**
     * There are 3 trace levels (values of TRACELEVEL):
     * 0 - No messages are printed
     * 1 - Trace messages are printed, but there is no indentation
     *     according to the call stack
     * 2 - Trace messages are printed, and they are indented
     *     according to the call stack
     */
    public static int TRACELEVEL = 2;
    protected static PrintStream stream = null;
    protected static int callDepth = 0;

    /**
     * Initialization.
     */
    public static void initStream(PrintStream s) {
        stream = s;
    }

    protected static void traceEntry(String str, Object o) {
        if (TRACELEVEL == 0) return;
        if (TRACELEVEL == 2) callDepth++;
        printEntering(str + ": " + o.toString());
    }

    protected static void traceExit(String str, Object o) {
        if (TRACELEVEL == 0) return;
        printExiting(str + ": " + o.toString());
        if (TRACELEVEL == 2) callDepth--;
    }

    private static void printEntering(String str) {
        printIndent();
        stream.println("--> " + str);
    }

    private static void printExiting(String str) {
        printIndent();
        stream.println("<-- " + str);
    }


    private static void printIndent() {
        for (int i = 0; i < callDepth; i++)
            stream.print("  ");
    }

    pointcut myClass(Object obj):
        this(obj) &&
        (within(TwoDShape) || within(Circle) || within(Square));

    pointcut myConstructor(Object obj): myClass(obj) && execution(new(..));
       /**
        * The methods of those classes.
        */
       // toString is called from within our advice, so we shouldn't
       // advise its executions.  But if toString is overridden, even
       // this might not be enough, so we might want
       //    && !cflow(execution(String toString()))
       pointcut myMethod(Object obj): myClass(obj) &&
           execution(* *(..)) && !execution(String toString());

       before(Object obj): myConstructor(obj) {
           traceEntry("" + thisJoinPointStaticPart.getSignature(), obj);
       }
       after(Object obj): myConstructor(obj) {
           traceExit("" + thisJoinPointStaticPart.getSignature(), obj);
       }

       before(Object obj): myMethod(obj) {
           traceEntry("" + thisJoinPointStaticPart.getSignature(), obj);
       }
       after(Object obj): myMethod(obj) {
           traceExit("" + thisJoinPointStaticPart.getSignature(), obj);
       }
   }
