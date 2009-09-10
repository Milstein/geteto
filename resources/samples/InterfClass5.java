import java.util.*;
import java.io.*;
// import java.awt.*;
import java.lang.*;


// Alteração nesta classe para compreender métricas LCOM, LCOM2 e LCOM3
// metodos static compartilham var de instancia

class InterfClass5 implements InterfaceExemplo {
    static int inteiro = 1;
    int parametro;

    // implementação do metodo declarado na interface
    public void metodo1(int param) {
        System.out.println("Valor de param = " + param);
    }

    // metodo q nao eh da interface
    void metodo2() {
        System.out.println("Metodo extra-interface");
    }
	
    // metodo q nao eh da interface
    void metodo3(int param) {
        System.out.println("Metodo extra-interface");
        parametro = param;
    }

    // metodo q nao eh da interface
    static void metodo4() {
        System.out.println("Metodo extra-interface");
        System.out.println("A variavel constante desta classe eh" + inteiro);
    }

    // metodo q nao eh da interface
    int metodo5() {
        return parametro;
    }
}
