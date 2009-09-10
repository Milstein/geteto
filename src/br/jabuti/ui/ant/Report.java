package br.jabuti.ui.ant;

import org.apache.tools.ant.Task;

/*
3 - Gerar relatorios
Se a op<E7><E3>o -o n<E3>o for usada, sempre ser<E1> gerado o relat<F3>rio no arquivo jabuti_report.xml.

3.1 - Relat<F3>rio do projeto
   java -cp .:../Jabuti-bin.zip br.jabuti.cmdtool.JabutiReport -p vending.jbt -pr -o projeto.xml

3.2 - Relat<F3>rio de classes (inclui o relat<F3>rio do projeto)
   java -cp .:../Jabuti-bin.zip br.jabuti.cmdtool.JabutiReport -p vending.jbt -cl -o classes.xml

3.3 - Relat<F3>rio de m<E9>todos (inclui o relat<F3>rio do projeto e de classes)
   java -cp .:../Jabuti-bin.zip br.jabuti.cmdtool.JabutiReport -p vending.jbt -me -o metodos.xml

3.4 - Relet<F3>rio de conjunto de teste (idem ao de projeto em termos de cobertura)
   java -cp .:../Jabuti-bin.zip br.jabuti.cmdtool.JabutiReport -p vending.jbt -ts -o test-set.xml

3.5 - Relat<F3>rio de casos de teste (inclui de de conjunto de teste)
   java -cp .:../Jabuti-bin.zip br.jabuti.cmdtool.JabutiReport -p vending.jbt -tc -o test-case.xml

3.6 - Relat<F3>rio completo
   java -cp .:../Jabuti-bin.zip br.jabuti.cmdtool.JabutiReport -p vending.jbt -all -o completo.xml

 */
public class Report extends Task
{

}
