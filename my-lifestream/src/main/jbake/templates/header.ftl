<#ftl output_format="HTML">
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8"/>
    <#if title??>
    <#elseif (content.title)??>
    <#assign title=content.title>
    <#else>
    <#assign title=config.site_title>
    </#if>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="generator" content="JBake">

    <!-- Le styles -->
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/bootstrap.min.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/asciidoctor.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/base.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/prettify.css" rel="stylesheet">
    <link href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>css/solarized-light-theme.css" rel="stylesheet">
	
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="<#if (content.rootpath)??>${content.rootpath}<#else></#if>js/html5shiv.min.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <!--<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">-->
    <link rel="shortcut icon" href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>favicon.ico">
    
    <!-- RSS Feed! -->
	<link rel="alternate" type="application/rss+xml" title="RSS Feed" href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>${config.feed_file}" />
  </head>
  <body onload="prettyPrint()">
    <div id="wrap">
   