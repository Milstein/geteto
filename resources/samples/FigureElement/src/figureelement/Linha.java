package figureelement;

public class Linha implements ElementoDeFigura {
  private Ponto p1, p2;
  Ponto getP1() { return p1; }
  Ponto getP2() { return p2; }
  void setP1(Ponto p1) { this.p1 = p1; }
  void setP2(Ponto p2) { this.p2 = p2; }
  public void move(int dx, int dy) { p1.move(dx,dy); p2.move(dx,dy); }
}
