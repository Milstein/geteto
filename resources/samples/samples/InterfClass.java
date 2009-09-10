import java.util.*;
import java.io.*;
// import java.awt.*;
import java.lang.*;


class InterfClass implements InterfaceExemplo {
    // implementação do metodo declarado na interface
    public void metodo1(int param) {
        System.out.println("Valor de param = " + param);
    }

    // metodo q nao eh da interface
    void metodo2() {
        System.out.println("Metodo extra-interface");
    }
}
