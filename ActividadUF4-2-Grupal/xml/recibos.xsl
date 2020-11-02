<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<html lang="en">
	<head>
		<meta charset="UTF-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<link rel="stylesheet" href="../css/styles_02.css"/>
		<title>UTF4. Grupal XSL - Recibos</title>
	</head>
	<body>
		<div class="container">
		<xsl:for-each select="recibos/recibo">
			<div class="recibo">
				<h1>Recibo: <xsl:value-of select="numero"/></h1>
				<p>Fecha: <xsl:value-of select="fecha"/></p>
				<table border="3">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
							<th>Unidades</th>
							<th>Precio</th>
						</tr>
					</thead>
					<tbody>
						<xsl:for-each select="detalle">
							<tr>
								<td><xsl:value-of select="codigo"/></td>
								<td><xsl:value-of select="descripcion"/></td>
								<td><xsl:value-of select="unidades"/></td>
								<td><xsl:value-of select="precio"/></td>
							</tr>
						</xsl:for-each>
					</tbody>
				</table>
			</div>
		</xsl:for-each>
		</div>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>

