Comandos B�sicos JaBUTi
-----------------------

Para executar a Jabuti
   java -cp Jabuti-bin.zip br.jabuti.gui.JabutiGUI


Para executar um caso de teste
   java -cp Jabuti-bin.zip br.jabuti.probe.ProberLoader -p <nome projeto> <classe a executar> [par�metros para execu��o]


Para instrumentar e armazenar instrumentado
   java -cp Jabuti-bin.zip br.jabuti.probe.ProberInstrum -o <arquivo .jar> -p <nome projeto> <classe a executar>


Uma vez instrumentado, para executar um caso de teste
   java -cp <arquivo .jar> <classe a executar> [par�metros para execu��o]