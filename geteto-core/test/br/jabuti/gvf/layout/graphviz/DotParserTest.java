package br.jabuti.gvf.layout.graphviz;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Matcher;

import org.junit.Test;

import br.jabuti.graph.layout.graphviz.DotParser;
import br.jabuti.graph.layout.graphviz.ParseException;

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

	@Test
	public void testParse4() throws FileNotFoundException, ParseException
	{
		InputStream is = new FileInputStream("/home/magsilva/Projects-ICMC/JaBUTi-PAE/resources/graphs/graph_1288632004114594164.dot");
		DotParser dp = new DotParser(is);
		dp.parse();
	}

	@Test
	public void testParse5() throws FileNotFoundException, ParseException
	{
		InputStream is = new FileInputStream("/home/magsilva/Projects-ICMC/JaBUTi-PAE/resources/graphs/graph_1288632004114594164_full.dot");
		DotParser dp = new DotParser(is);
		dp.parse();
	}

	
	@Test
	public void testRE1()
	{
		String str = "e,21.637,34.945 21.637,151.05 14.782,140.53 6.731,126.12 3.1298,112 -1.0433,95.635 -1.0433,90.365 3.1298,74 5.8307,63.408 11.035\\\n" +
			",52.656 16.36,43.502";
		
		String lineBreak = Matcher.quoteReplacement("\n");
		String carriageReturn = Matcher.quoteReplacement("\r");
		String endLineSlash = Matcher.quoteReplacement("\\");
		String quote = Matcher.quoteReplacement("\"");
		
		String result = str.replaceAll(lineBreak, "").replaceAll(carriageReturn, "").replaceAll(endLineSlash, "").replaceAll(quote, "").substring(2);
		
		assertEquals("21.637,34.945 21.637,151.05 14.782,140.53 6.731,126.12 3.1298,112 -1.0433,95.635 -1.0433,90.365 3.1298,74 5.8307,63.408 11.035,52.656 16.36,43.502", result);
	}

}
