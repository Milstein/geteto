package GerCriptografia;

public abstract aspect GerCriptografia
{
	public String criptografa(String info2)
	{
		char aux;
		char info[] = info2.toCharArray();
		int tam = info2.length();
		for (int i=0; i<(tam/2);i++)
		{
			aux = info[i];
			info[i]=info[tam-i-1];
			info[tam-i-1]=aux;
		}
		
		String sret = "";
		for (int j=0; j<tam; j++)
		   sret = sret + info[j];
		
		return sret;
	}
	
	public String decriptografa(String info2)
	{
		char aux;
		char info[] = info2.toCharArray();
		int tam = info2.length();
		for (int i=0; i<(tam/2);i++)
		{
			aux = info[i];
			info[i]=info[tam-i-1];
			info[tam-i-1]=aux;
		}
				
		String sret = "";
		for (int j=0; j<tam; j++)
		   sret = sret + info[j];
		return sret;
	}
	
	public abstract pointcut OperacaoCriptografar();
	public abstract pointcut OperacaoDescriptografar();
	
	Object around(String login, String st): OperacaoCriptografar() && args(login) && args(st)
		{
			String st_md = criptografa(st);
			System.out.println("Dentro do aspecto - info cripto: " + st_md);
			st = st_md;
			return proceed(login,st_md);
		}

	
	Object around(String st): OperacaoDescriptografar() && args(st)
		{
		    Object retorno = proceed(st);
		    String st_or = decriptografa(retorno.toString());
			System.out.println("Dentro do aspecto - info decripto: " + st_or);
			Object o = (Object) st_or;
			return o;
		}

}