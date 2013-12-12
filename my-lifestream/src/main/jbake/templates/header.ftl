<#include "_functions.ftl">

<#macro css>
    <link href="<#nested/>css/bootstrap.min.css" rel="stylesheet">
    <!-- I don't use ascii doctor, so no need for the associated stylesheet which unfortunatly loads font-awesome from a CDN -->
    <link href="<#nested/>css/asciidoctor.css" rel="stylesheet">
    <link href="<#nested/>css/base.css" rel="stylesheet">
    <link href="<#nested/>css/bootstrap-responsive.min.css" rel="stylesheet">
</#macro>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Lifestream</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Nicolas Delsaux">
    <meta name="keywords" content="">

    <!-- The styles -->
    <#if (content.depth)??>
    	<@css>${content.depth}</@css>
    <#elseif (path)??>
    	<@css>${path}</@css>
	<#else>
    	<@css></@css>
    </#if>
    <!-- Some additional thingies -->

  </head>
  <body>
    <div id="wrap">
