type: post
status: published
title: lifestream version ... pfiouh
tags: java, lifestream, maven, mavie
date: Tue Aug 27 20:31:15 CEST 2013
~~~~~~
# lifestream version ... pfiouh

Le titre dit à peu près tout, mais pas tout.

J'ai donc décidé il y a quelques temps de me relancer dans la n-ième version de mon lifestream.

Tout est parti, en fait, de [shaarli](http://riduidel.wordpress.com/2013/06/17/un-plus-joli-shaarli/ "Un plus joli shaarli …"). Shaarli, c'est quand même la base de la réappropriation des données : on part d'un service web bien connu (delicious), et on se dit qu'il serait bien mieux à la maison. Et puis après, en discutant avec d'autres shaarlieurs, on se dit que ce serait super cool de pouvoir discuter entre nous via shaarli (comme les tweets, mais en version décentralisée). Et encore après, en fouillant un peu, je tombe sur une communauté de développeurs qui bossent précisément dans ce genre de domaines - hélas, je n'ai apparement pas conservé de lien - ou la recherche de Shaarli ne marche pas encore assez bien).

Ces développeurs expliquent, en gros, qu'il ya  plusieurs façons de faire du lifestream

1. Tout écrire dans une interface, et faire en sorte que les messages soient postés sur les bons service
2. Ecrire dans chaque service, et rappatrier les données via un sweetcron, par exemple
Bon, ben j'ai choisi, et c'est la deuxième solution que je prends maintenant avec mon nouveau projet [lifestream](https://github.com/Riduidel/lifestream).

Cette fois-ci, j'assume complètement mon côté vieux en reprenant tout en java/maven tout ce que j'avais déja fait pour Goodreads en Groovy ... et je dois bien avouer que ça va vachement vite à écrire pour un monoculturel comme moi.

En bonus, je me suis offert quelques gadgets rigolos : les articles sont tous transformés en Markdown, je vais utiliser JBake pour produire le site web (et les wagons maven pour le distribuer). Bref, je m'amuse pas mal.