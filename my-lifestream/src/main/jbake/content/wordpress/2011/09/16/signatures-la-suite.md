type: post
status: published
title: Signatures, la suite
tags: citations, quotebook
date: Fri Sep 16 13:35:00 CEST 2011
~~~~~~
# Signatures, la suite

Ce message fait largement suite à [celui d'hier](http://riduidel.posterous.com/petite-reflexion), alors si vous ne l'avez pas lu, évidement, vous serez embêté.

J'étais donc en train de faire ma 28ème partie d'[entanglement ](http://entanglement.gopherwoodstudios.com/light)(oui, je joue à des jeux de plateau en ligne quand la connexion réserau du bureau est en mode très dégradé), quand j'ai réalisé qu'une bonne façon de faire apparaître mes citations préférées sur internet était de mélanger deux choses :
* Avec le web sémantique et les microformats, je peux très bien disposer sur ce blog d'une catégorie qui va bien dans laquelle je sais que chaque article contient une ou plusieurs citations correctement définies.
* Avec RndSig, je peux facilement insérer ces signatures dans des artices, en tant que signatures (justement). Il faut "juste" que je les redécore, mais ça, c'est pas très compliqué.

Ni un ni deux, j'ai donc installé RndSig, configuré son raccourci clavier sur CTRL+ALT+SHIFT+F11 (peu de risque que je tape ça au pif, hein) et les signatures à utiliser.

Maintenant, il ne me reste plus qu'à trouver un format qui colle pour ems citations. Je pense à quelque chose comme ça :



[https://gist.github.com/1221929](https://gist.github.com/1221929)

Et avec ça, je pourrais utiliser l'API posterous pour récupérer, depuis un script Groovy, ces signatures et les associer via une distance de Levenshtein à mon texte, et ce sera cosmiquement bien. Vous en pensez quoi ? Moi, en tout cas, ça me botte !

 

> Il faut toujours prendre le maximum de risques avec le maximum de précautions.[Rudyard Kipling](http://fr.wikipedia.org/wiki/Rudyard_Kipling)