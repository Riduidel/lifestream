type: post
status: published
title: Et vive les plugins de Rails
tags: code, informatique, lebliki, ruby
date: Mon Apr 02 17:25:16 CEST 2007
~~~~~~
# Et vive les plugins de Rails

Grâce à ma Pizza On Rails, et en particulier à Nicolas Mérouze, je sais maintenant que [des plugins Rails](http://agilewebdevelopment.com/plugins), il en existe des tonnes (comme d'ailleurs des outils associés, et des tutoriaux). Je vais donc ici faire une liste rapide des plugins qui pourraient me simplifier le code. 

*  Plugin migrations comme je vais utiliser des tonnes de plugins, un truc me permettant de migrer mes plugins serait sympa. 
*  Bingo ! acts_as_blog va me permettre de transformer du texte encodé en Redcloth <i class="footnote">(Autrement dit Textile , BlueclothOu encore Markdown)</i> ou SmartyPants en HTML, et même d'éditer ce texte initial. 
*  acts_as_commentable permettra de commenter les entrées du bliki. 
*  acts_as_taggable_on_steroids pour gérer les tags de mes pages et leur nuages. Encore qu'il faudra si je vois s'il est possible de restreindre les tags affichés à ceux de la catégorie recherchée. 
*  ordered tree pour faire un arbre des catégories 
*  acts_as_urlnameable pour avoir de jolies URLs (ce que je trouve important - et Google aussi. 
*  acts_as_threaded pour avoir de jolies files de commentaires, contrairement aux blogs 
*  acts_as_network a l'air sympa pour faire des liens entre entrées, si je peux automatiser la création de ces liens. 
*  act as authenticated est trés bien pour rajouter la gestion de l'authentification à une application déja existante. Je le rajouterais donc quand tout sera codé pour étre seul maître à bord de mon bliki. Et, s'il le faut, je mettrais en plus authorization pour me faire un truc aux petits oignons. Quoiqu'authorization m'ait paru assez peu évident à mettre en place la derniére fois. 
*  loads_from_amazon a l'air sympa pour charger des données depuis amazon, il faudra juste ajouter le chargement de données additionnelles. 
Bon, il doit y en avoir d'autres, mais je verrais au besoin. via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)