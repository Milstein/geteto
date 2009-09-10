public aspect RegraSaldoMinimo {
  private float Conta._saldominimo;

  public float Conta.obtemSaldoDisponivel() {
    return getSaldo() - _saldominimo;
  }

  after(Conta conta) : execution(ContaPoupanca.new(..)) && this(conta) {
    conta._saldominimo = 25;
  }

}

