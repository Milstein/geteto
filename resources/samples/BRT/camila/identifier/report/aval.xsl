<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/report">
<html>
<head>
	<title>Relatorio</title>
</head>
<body bgcolor="#FFFFFF">
	<center><span style="color:#000000;font-family:roman;font-size:30;">Cobertura</span></center><br/>

		<xsl:for-each select="junit">
		      <xsl:if test="attribute::id = 'aluno'">

 	              <table border="0" bgcolor="#FFFFFF">

			<th  bgcolor="#AAAAAA" colspan="2">Junit</th>
			<tr>
				<td bgcolor="#DDDDDD" align="center"><b>Cobertura:</b> <xsl:value-of select="attribute::id"></xsl:value-of></td>
			</tr>
			<tr>
				<td><b>Taxa sucesso:</b> <xsl:value-of select="sucess_rate"></xsl:value-of></td>
			</tr>
			<tr>
				<td><b>Teste:</b> <xsl:value-of select="tests"></xsl:value-of></td>
			</tr>
			<tr>
				<td><b>Falha:</b> <xsl:value-of select="failures"></xsl:value-of></td>
			</tr>

                        <tr>
				<td><b>Error:</b> <xsl:value-of select="errors"></xsl:value-of></td>
			</tr>

                      </table>
	      </xsl:if>
             </xsl:for-each>
             
             
             <xsl:for-each select="coverage">
                   <xsl:for-each select="jabuti">
			<xsl:if test="attribute::id = 'aluno'">

 	              <table border="0" bgcolor="#FFFFFF">

			<th  bgcolor="#AAAAAA" colspan="2">Junit</th>
			<tr>
				<td bgcolor="#DDDDDD" align="center"><b>Cobertura:</b> <xsl:value-of select="attribute::id"></xsl:value-of></td>
			</tr>
	                 <tr>
				<td bgcolor="#00DDDD" align="center"><b>Jabuti:</b></td>
			</tr>

        		<tr>
	       			<td><b>All Nodes:</b> <xsl:value-of select="All_Nodes/attribute::ei"></xsl:value-of></td>
			</tr>
			<tr>
				<td><b>All Edges:</b> <xsl:value-of select="All_Edges/attribute::ei"></xsl:value-of></td>
			</tr>
			<tr>
				<td><b>All Uses:</b> <xsl:value-of select="All_Uses/attribute::ei"></xsl:value-of></td>
			</tr>

                        <tr>
				<td><b>All Pot-Uses:</b> <xsl:value-of select="All_PotUses/attribute::ei"></xsl:value-of></td>
			</tr>

               </table>
	      </xsl:if>	       
             </xsl:for-each>
            </xsl:for-each>








</body>
</html>
</xsl:template>

</xsl:stylesheet>
