package samples;


public class b3 {
int x;
int[] v;
static int y;


	public static void main(String[] args)
	{
		b3 r, s;
		
		// uma instrucao pode ter diversas configuracoes de pilha
		// é o caso abaixo. qdo a atribuicao for feita para o campo x
		// atraves de putfield, podemos ter na pilha ou a variavel local 
		// 1 ou a variavel local 2. Assim temos definocao de L@1.x e de
		// L@2.x
		// Exemplo de Classe 4 tbem...
		// Exemplo de classe 6. Antes de chamar o construtor, um DUP
		// é executado. Nesse caso o elemento no topo jah estava com um DC
		// pois eh resultado da execucao de um NEW, mas mesmo que nao
		// estivesse, o elemento duplicado teria umm DC
		r = new b3();
		s = new b3();
		
		(r.x == 0? r : s).x = 20;
		
		
		// o mesmo nao acontece abaixo pois temos 2 instrucoes putfield
		if ( r.x == 0 )
			r.x = 20;
		else
			s.x = 20;

/*		b3 o = null;		
		if ( r.x == 0 )
			o = r;
		else
			o = s;
		
		o.x = 15;
*/			

		// classe 1 - a atribuicao vai ser feita a DC.x, ou seja, 
		// nao se sabe onde a atribuicao estah sendo feita, nem
		// variavel local nem variavel de instancia.
		(new b3()).x = 15;		
		
		r.m().x = 15;
		
		// classe 2 - toda vez que uma uma referencia a um array que estah no topo
		// da pilha é usada para recuperar um elemento do array, entao caracteriza-se
		// um uso de um elemento do array.
		// no exemplo temos um uso de L@3[] e de L@3[][]
		// Classe 8. o Objeto array criado é atribuído aa variavel f, portanto
		// temos definicao de L@3
		
		int [][] f = new int[10][10];
		
		r.x = f[1][2];
		
		// classe 3 - quando uma referencia a um array que estah no topo da pilha
		// é usada para armazenar um elemento do array, entao caracteriza-se
		// uma definição de um elemento do array. No exemplo teríamos
		// um uso de 
		// classe 7 - A primeira coisa feita é carregar na pilha a referencia
		// ao vetor que estah armazenada na variável f (local 3). Assim temos
		// um uso de L@3.
		f[1][2] = 15;
		
		// classe 5. definicao de campo estatico. temos definicao de S@samples.b3.y 
		r.y = 15; // ou
		b3.y = 15;
		
		// classe 9. Um campo é utilizado. No caso abaixo temos o uso de x,
		// cuja referencia ao objeto estah armazenada na var local 2. Portanto,
		// uso de L@2.x
		b3.y = s.x; 
		// aqui tbem temos um uso de uma variavel de instancia, v: L@2.v
		s.v[0] = 0;
		
		// classe 10. Uso de variavel estatica
		s.x = b3.y;
		
		// classe 11;
		// incremento de variavel local: uso e definição de L@4
		int w = 0;
		
		if ( w != 0)
			w++;
		// aqui abaixo teriamos uso e definicao de L@2.x mas a instrucao IINC
		// nao é usada...
		s.x++;
		
	}	
	
	
	public b3 m()
	{
		return this;
	}
	
	
}

