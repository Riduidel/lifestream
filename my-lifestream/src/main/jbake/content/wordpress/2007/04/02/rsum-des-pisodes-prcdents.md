type: post
status: published
title: Résumé des épisodes précédents
tags: code, informatique, lebliki, ruby
date: Mon Apr 02 16:25:16 CEST 2007
~~~~~~
# Résumé des épisodes précédents

Pour faire suite à mon post sur Pimki et Mephisto, je vais donc m'amuser à coder ce qui devrait (un jour, si je n'abandonne pas entre temps) le bliki (ben oui, après le blog). Mais pour ça, et comme je suis un professionnel de la profession, je vais faire le point sur mes besoins.Idéalement, je cherche à faire quelque chose qui ressemble un peu à The tao of mac. Pas en termes d'interface (je vais reprendre mon moche look actuel), mais en termes de fonctionnalités. Il me faut donc 

* Pouvoir utiliser des WikiWords. 
* Pouvoir ajouter des entrées de blog qui soient en méme temps des entrées de wiki. 
* Avoir des catégories arborescentes. 
* Et des tags. Idéalement, pour reprendre une idée de Yann Minh <i class="footnote">(Comme je ne retrouve pas le message sur fras, voilé l'idée : si je fais suivre mon tag par un pourcentage, c'est l'adéquation entre le tag et l'article que je qualifie. Par exemple, pour cet article ruby:60 le-bliki:100 nous indique qu'il concerne plus le bliki que le ruby.)</i>, il faudrait que ces tags soient pondérés. 
Heureusement, des listes de fonctionnalités, j'en ai des tonnes. J'ai celle que j'ai envoyé aux développeurs de pimki : 

* Un support complet des accents
* Des catégories visiblement arborescentes
* Des commentaires et trackbacks (ou notifications dé?URLs entrantes)
* un bliki facilement publiable
* Ma bibliothéque SF !
* Des articles multilangues
* Une coloration syntaxique multilangage (un geshi en Ruby, quoi)
* Le support de l'API metaweblog (pour que je puisse continuer à envoyer mes delicious 
Mais aussi celle de Rui Carmo <i class="footnote">(Celui de Tao Of Mac)</i> quand j'en ai discuté avec lui : 

* Search (mine is atrocious, I really have to do something about it)
* the SeeAlso box at the bottom
* Decent, downgradable HTML for mobile devices (mine degrades mostlyOK, except for very long pages)
* an RSS feed 
Et pour finir, il y a d'autres trucs, comme par exemple les cartes du site utilisant graphviz de pimki, ou encore l'authentification des auteurs (voire méme une gestion évoluée des droits, mais on n'y est pas encore), les commentaires, ... et le reste, si je l'ai oublié, c'est sans doute que ça n'est pas important. via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)