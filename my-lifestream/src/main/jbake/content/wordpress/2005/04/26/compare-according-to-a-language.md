type: post
status: published
title: Compare according to a language ?
tags: code, informatique, java
date: Tue Apr 26 12:11:00 CEST 2005
~~~~~~
# Compare according to a language ?

Vous voulez comparer deux langues en utilisant la langue du système (et non l'ordre fourni par String) ? Utilisez ce comparateur :



[https://gist.github.com/266066](https://gist.github.com/266066)

Notez qu'on peut facilement l'étendre en utilisant une méthode statique retournant un CollatorComparator à partir d'un identifiant de langue. il suffit pour cela d'associer dans une Map des objets Locale à des instances de CollatorComparator.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)