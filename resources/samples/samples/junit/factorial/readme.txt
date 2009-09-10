How to import test cases...

1 - Open JaBUTi, create a testing project and ask to save the instrumented
    classes in the Project Manager window. It is created a .jar file containig
    the instrumented classes.


2 - Create a TestCase using the JUnit framework. Observe that it isn important that
    such a class belongs to the same package to the class under testing.

$ cat TestFactorial.java
package samples;
...

Deve existir o método que é executado após a execução de cada caso
de teste. Nesse método é necessário invocar o método
estático DefaultProber.dump() para que a informação de trace seja
salva no arquivo como um novo casos de teste.

protected void tearDown() {
	DefaultProber.dump();
}


auri@aurimrv ~/Meus documentos/junit-samples/factorial
$ javac -d . -classpath "c:\junit3.8.1\junit.jar;fact_instr.jar;e:\auri\Meus documentos\Tools\jabuti" TestFactorial.java


3 - Call the TestRunner to execute the test case and collect the
    trace file...

auri@aurimrv ~/Meus documentos/junit-samples/factorial
$ java -DDEFAULT_PROBER="fact.trc" -classpath "c:\junit3.8.1\junit.jar;fact_instr.jar;e:\auri\Meus documentos\Tools\jabuti;." junit.swingui.TestRunner