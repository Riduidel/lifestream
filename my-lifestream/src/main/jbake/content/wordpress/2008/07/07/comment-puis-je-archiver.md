type: post
status: published
title: Comment puis-je archiver ?
tags: 
date: Mon Jul 07 11:36:00 CEST 2008
~~~~~~
# Comment puis-je archiver ?

C'est vrai, quoi, maintenant que j'ai explosé ma vie numérique, et que mes données sont disponibles sur quatre ou cinq serveurs répartis des deux côtés de l'atlantique, comment est-ce que je peux avoir la garantie qu'il existe quelque part une sauvegarde de ces données qui résiste
* A ma piètre gestion de mon matériel informatique
* A la grandeur et à la décadence des empires numériques
* A mes envies possibles de changements ultérieurs

J'en avais parlé dans mon précédent blog, mais j'ai hélas perdu le message ... Bon, l'idée essentielle, c'est qu'en m'inspirant de [Rui Carmo](http://the.taoofmac.com/space/Data%2520For%2520The%2520Ages), je compte faire en sorte que mes données soient toujours accessibles pour l'avenir, et toutes stockées dans un format commun.

Vous allez me dire que ça n'est pas le cas, puisque je recrée ce blog à partir de rien. C'est vrai, et faux en même temps, puisque j'ai sur mon ordinateur un gros dossier avec 90 % de tout ce que j'ai écrit depuis mes débuts sur internet.

Bref ... Maintenant que j'utilise des services distants, il faut donc que je trouve un moyen de rappartier les données sur ma machine, pour pouvoir les stocker dans des fichiers. Parce que comme le dit encore Rui, les bases de données, c'est pas forcément parfait.

La solution, c'est bien sûr d'utiliser les flux RSS fournis par ces hébergeurs pour faire un backup des messages. Et grâce à des solutions comme [Feed Bag](http://users.rsise.anu.edu.au/~mreid/code/feed_bag.html), je vais pouvoir assez facilement écrire un script de backup, mais dans des répertoires de mon OS, et pas dans une base de données qui reste malghré tout un système assez fermé.

Enfin, il me reste encore à l'écrire.

Et malheureusement, j'ai pas mal de trucs du même type en attente, comme par exemple un outil pour exporter toutes mes lectures dans goodreads. Mais je crois que je le ferais après le script de backup. D'abord parce que ce script sera plus simple à coder, et en plus parce que ça me permettra d'être plus homogène.

Bon, ben il n'y a plus qu'à réinstaller ruby ...

Quoi qu'il me semble que Java dispose de bonnes libraires de lecture RSS (en particulier [ROME](https://rome.dev.java.net/)). Je vais donc peut-être pouvoir me passer de réinstaller ruby. D'ailleurs faudra que je fasse un article pour expliquer pourquoi je n'en fais plus ...