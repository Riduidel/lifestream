type: post
status: published
title: Choix de la signature
tags: code, informatique, rndsig, ruby
date: Thu Apr 07 15:35:00 CEST 2005
~~~~~~
# Choix de la signature

Maintenant que la liste des ginature est construite, il ne reste plus qu'à sélectionner la bonne ! Pour cela, et dans la mesure où Config est en fait le conteneur d'objets Signatures, on crée une méthode dans cette classe permettant cette sélection :



[https://gist.github.com/266039](https://gist.github.com/266039)

Simple, non ? On calcule le poids de chaque signature (l'appel à compute) pour le tableau de texte (processed) passé en argument. Et, une fois le tableau obtenu, on le trie par ordre décroissant. Il ne reste plus alors qu'à trouver la valeur d'une signature :



[https://gist.github.com/266041](https://gist.github.com/266041)

Mortellement complexe, non ? Une autre partie de l'application n'a pas été décrite ici (et, franchement, je ne crois pas que ça intéresse grand monde) : c'est celle mappant les touches sur l'OS. Elle est affreusement complexe, n'existe pour l'instant que pour Windows, et est de plus d'une laideur peu commune. Alors, à moins que certains lecteurs y tiennent par-dessus tout, je crois bien que je ne vais pas en parler.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)