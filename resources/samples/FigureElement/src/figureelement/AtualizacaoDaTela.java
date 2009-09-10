package figureelement;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public aspect AtualizacaoDaTela {
  public pointcut mudancaEstado() :
    call(void ElementoDeFigura.move(int,int)) ||
    call(* Ponto.set*(*)) || call(* Linha.set*(*));

  before(): mudancaEstado() {
    System.out.println("O estado vai mudar...");
  }


  after() returning : mudancaEstado() {
    Tela.atualiza();
  }

  public void ajc$afterReturning$AtualizacaoDaTela$1af()
  0 invokestatic #38 <Method void Tela.atualiza()>
  3 return

}