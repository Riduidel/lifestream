<#include "_functions.ftl">

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
    <!-- Some additional thingies -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

  </head>
  <body>
    <div id="wrap">
