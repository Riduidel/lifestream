type: post
status: published
title: Problèmes avec Wordpress
tags: informatique, leblog, monwordpress, web
date: Thu Jun 02 11:27:00 CEST 2005
~~~~~~
# Problèmes avec Wordpress

Ce message est un peu un appel à l'aide.

Tout a commencé il n'y a pas si longtemps, quand je me suis rendu compte que les serveurs de [free](www.free.fr) ne tenaient pas la charge. Je me suis dit que la seule solution concevable était l'auto-hébergement. Mais je n'avais pas de machine. Grâce à mon employeur (et surtout à l'administrateur système), qui s'est débarassé de quelques brouettes (ou assimilés), j'ai récupéré un Celeron 433 qui marche à peu près. Et je lui ai [installé un Linux](http://nicolas.delsaux.free.fr/wordpress/index.php?p=219). Censément, ça peut me permettre d'avoir mon blog hébergé sur le serveur Apache fourni avec. Mais, comme je suis très beta-wave, j'ai voulu passer directement à Wordpress 1.5. Et c'est là que ça se corse. J'ai effectivement réussi à installer Wordpress, mais pour importer les articles, c'est la guerre des tranchées ! J'ai tenté une première fois de faire un export/import de la base entière, mais le nombre de colonnes a dû changer. J'ai alors décidé de passer à un export/import des tables posts, comments, links, et des relations idoines, ce qui a semble-t-il marché. Seulement, je n'arrive plus à ajouter d'articles, car l'insertion dans la table provoque des erreurs bizarres. Et depuis, je galère à réessayer mes insertions dans tous les sens. Donc, si quelqu'un, quelque part connaît un moyen de mettre à jour un Wordpress 1.2 avec les plugins
* Brian's Nested Comments
* Language Picker
* Ratings (qui est désactivé, mais quand même complètement installé (y compris les modifications dans la base)

Pitié !!! J'ai besoin d'aide ! [EDIT] Tant que j'y suis, j'essayerais de suivre [ce tutorial](http://www.tamba2.org.uk/wordpress/upgrade/upgrade_fr.html) qui m'a l'air assez bien fait. Même si j'ai peu d'espoir que ça marche ...via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)