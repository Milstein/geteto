import java.util.*;
import java.io.*;
// import java.awt.*;
import java.lang.*;


// Altera��o nesta classe para compreender m�tricas LCOM, LCOM2 e LCOM3
class InterfClass2 implements InterfaceExemplo {
    // implementa��o do metodo declarado na interface
    public void metodo1(int param) {
        System.out.println("Valor de param = " + param);
    }

    // metodo q nao eh da interface
    void metodo2() {
        System.out.println("Metodo extra-interface");
    }
	
    // metodo q nao eh da interface
    void metodo3() {
        System.out.println("Metodo extra-interface");
    }

    // metodo q nao eh da interface
    static void metodo4() {
        System.out.println("Metodo extra-interface");
    }
}
