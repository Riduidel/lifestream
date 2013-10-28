type: post
status: published
title: Et ce sera sans PluXML
tags: dns-323, lifestream, maven, php
date: Mon May 13 21:27:41 CEST 2013
~~~~~~
# Et ce sera sans PluXML

Puisque je parle de mon serveur, autant le dire, j'ai testé ce soir [PluXML](http://www.pluxml.org/), histoire de voir si je pouvais facilement y ajouter des articles.

Je vais quand même vous expliquer pourquoi.

Je suis toujours à la recherche d'une solution robuste (c'est-à-dire facile à faire fonctionner, facile à analyser, facile à étendre) pour récupérer mes traces sur le web. Et, comme PluXML stocke ses données dans des fichiers XML, je m'étais dit qu'il me serait facile de créer des fichiers contenant mes données et les copier dans le bon dossier. Mais hélas il n'en est rien. En créant un fichier pouet.xml et en le copiant dans le dossier data/articles/, je pensais qu'il apparaîtrai. Mais non !

Bien sûr, j'aurais pu demander au support de PluXML un petit coup de main, et je suis sûr qu'ils auraient été fort heureux de m'aider. Mais j'ai eu la flemme (ce qui est également un droit).

Donc, maintenant, j'ai plusieurs alternatives

* Repartir du code d'export Goodreads/Posterous que j'avais écrit il y a bien longtemps et tenter de le refaire marcher
* Trouver des gens dont je partage les idées, et faire marcher leurs idées avec mes données. Parce que **si j'y ai pensé, quelqu'un l'a fait**.
Cette dernière phrase est quand même un [motto ](http://fr.wiktionary.org/wiki/motto)sacrément important pour ne pas se prendre les pieds dans l'informatique moderne. Et du coup, en cherchant un peu, j'ai quand même trouvé quelques pages donnant des informations sur les générateurs de sites statiques en Java (ou approchants)

* [static](http://nakkaya.com/static.html), qui génère des sites pour peu qu'on utilise [Leiningen ](https://github.com/technomancy/leiningen)... Parfaitement.
* La question obligatoire sur [StackOverflow](http://stackoverflow.com/q/14801598/15619).
* Et [une question plus large](http://stackoverflow.com/questions/186290), supprimée, mais toujours intéressante.