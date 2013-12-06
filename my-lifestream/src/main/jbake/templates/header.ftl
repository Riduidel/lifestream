<#function fix_uri uri>
	<#assign text = uri>
	<#assign text = text?replace("%5C", "/")>
	<#assign text = text?replace("\\", "/")>
	<#if text?starts_with("/")>
		<#assign text = text?substring(1)>
		<#return text>
	<#else>
		<#return text>
	</#if>
</#function>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Lifestream</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Nicolas Delsaux">
    <meta name="keywords" content="">

    <!-- Le styles -->
    <#if (content.depth)??>
    <link href="${content.depth}css/bootstrap.min.css" rel="stylesheet">
    <link href="${content.depth}css/asciidoctor.css" rel="stylesheet">
    <link href="${content.depth}css/base.css" rel="stylesheet">
    <link href="${content.depth}css/bootstrap-responsive.min.css" rel="stylesheet">
	<#else>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/asciidoctor.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    </#if>

  </head>
  <body>
    <div id="wrap">
