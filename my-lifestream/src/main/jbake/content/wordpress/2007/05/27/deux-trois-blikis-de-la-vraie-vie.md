type: post
status: published
title: Deux trois blikis de la vraie vie
tags: code, informatique, lebliki, ruby
date: Sun May 27 16:25:00 CEST 2007
~~~~~~
# Deux trois blikis de la vraie vie

Comme je cherche encore comment matérialiser le côté blog du bliki, je cherche des exemples d’implémentations (autres que Martin Fowler ou Rui Carmo). Donc, via cette page, j’en ai trouvé quelques uns :
* [http://socialsynergy.typepad.com/social_synergy/bliki/index.html](http://socialsynergy.typepad.com/social_synergy/bliki/index.html)
* [http://www.sourcextreme.org/index.php/Bliki:Jason](http://www.sourcextreme.org/index.php/Bliki:Jason)
* [http://www.sourcextreme.org/index.php/User:N8dgr8](http://www.sourcextreme.org/index.php/User:N8dgr8)

Et, globalement, ils en arrivent tous au même truc : pouvoir commenter, afficher les billets (éventuellement d’une catégorie donnée) par ordre chronologique inverse. Tout ça me donne une bonne idée d’une implémentation d’un bliki facile. Je vous explique : l’idée, ce serait d’avoir une page listant les entrées de la catégorie bliki par ordre chronologique inverse.

C’est d’autant plus facile que tout est déja codé, sauf la redirection qui va bien. Le seul truc à ajouter dans cette page par rapport à nicolas-delsaux.homelinux.net/wiki/list (enfin, par rapport à la version à jour de cette page, version qui a beaucoup de choses supplémentaires), c’est un bouton pour faire une nouvelle entrée, ainsi que la capcité de donner à une entrée un nom qui ne soit pas unique (et c’est ce que j’ai fait avec mes alias, d’ailleurs een y pensant bien, j’ai un petit bug).via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)