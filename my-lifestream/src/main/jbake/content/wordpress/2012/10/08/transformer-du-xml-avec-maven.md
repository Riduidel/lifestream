type: post
status: published
title: Transformer du XML avec Maven
tags: export, maven, xml, xslt
date: Mon Oct 08 17:23:52 CEST 2012
~~~~~~
# Transformer du XML avec Maven

[Transformer du XML avec Maven](https://intellectualcramps.wordpress.com/2011/02/27/maven-xslt-transformations/ "Transformer du XML avec Maven")

Vous savez que le XHTML est du XML ? Bien, alors avec ce plugin, je vais transformer les moches pages produites par l'export posterous en pages "simples", contenant le texte, les tags, la date de publication et les commentaires.

Avec ça et un coup de [pandoc](http://johnmacfarlane.net/pandoc/), j'aurais du joli [Markdown](https://fr.wikipedia.org/wiki/Markdown) pour un futur [Awestruct](http://awestruct.org/) (ou [nanoc](http://nanoc.stoneship.org/), ou quoi que ce soit du genre, en fait).

Et avec le même ça et un peu de magie maven+REST, j'aurais re-peuplé cette nouvelle incarnation de mon blog (en fait je ferais plutôt dans l'autre sens : d'abord vers Wordpress, puis ré-export de tout ce que j'ai dans Wordpress).