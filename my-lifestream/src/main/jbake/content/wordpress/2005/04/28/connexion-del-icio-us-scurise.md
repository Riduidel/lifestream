type: post
status: published
title: Connexion ?? del.icio.us s??curis??e !
tags: bookmarcs, code, informatique, ruby
date: Thu Apr 28 10:11:00 CEST 2005
~~~~~~
# Connexion ?? del.icio.us s??curis??e !

Après avoir galéré avec mon bout de code, je me suis demandé si, finallement, ça ne vallait pas le coup d'essayer ce que préconise la doc Ruby. J'ai donc modifié mon code comme ça



[https://gist.github.com/266063](https://gist.github.com/266063)

Et j'ai essayé de me connecter à del.icio.us en connexion simple. Et ça marche ! Je récupère mes 896 liens ! La suite, c'est
* Balancer tout ça à Blogmarks d'une façon compréhensible (mais j'ai une bonne idée de la manière d'y parvenir)
* Récupérer tous mes favoris Blogmarks (il doit y avoir une URL analogue au [http://del.icio.us/api/posts/all)](http://del.icio.us/api/posts/all)), mais ça, c'est encore François qui va me le dire :-)
* Générer à partir de ces folksonomies des dossiers, pour éviter que IE ne m'affiche une liste déroulante avec les petites flèches, et pour ça j'ai quelques idées, comme par exemple récupérer la liste des topics, et les organiser en arbre.

Donc voilà, ça avance. J'ai encore quelques soucis, comme par exemple la définition d'un format de configuration me permettant l'activation à la demande  dans le fichier YAML.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)