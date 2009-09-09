package br.jabuti.gvf.layout.graphviz;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

public class DotParserTest {

	@Test
	public void testParse1() throws FileNotFoundException, ParseException
	{
		InputStream is = new FileInputStream("/home/magsilva/Projects-ICMC/JaBUTi-PAE/resources/graphs/graph_6270300739783148648.dot");
		DotParser dp = new DotParser(is);
		dp.parse();
		
	}
	
	@Test
	public void testParse2() throws FileNotFoundException, ParseException
	{
		InputStream is = new FileInputStream("/home/magsilva/Projects-ICMC/JaBUTi-PAE/resources/graphs/graph_6270300739783148648_noedge.dot");
		DotParser dp = new DotParser(is);
		dp.parse(); 
	}

	
	@Test
	public void testParse3() throws FileNotFoundException, ParseException
	{
		InputStream is = new FileInputStream("/home/magsilva/Projects-ICMC/JaBUTi-PAE/resources/graphs/graph_6270300739783148648_empty.dot");
		DotParser dp = new DotParser(is);
		dp.parse();
	}

}
