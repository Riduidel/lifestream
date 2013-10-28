type: post
status: published
title: R??cup??ration des donn??es Wordpress
tags: informatique, ruby, web, webgen
date: Sun Jan 06 19:01:00 CET 2008
~~~~~~
# R??cup??ration des donn??es Wordpress

h2. Restauration du dump Comme la machine qui hébergeait mon site est morte, sans espoir de résurection, je le crains, mes derniers dumps sont morts avec elle. Ce qui fait que les données les plus récentes à ma disposition datent de Décembre 2006, soit un an en arrière. J'ai donc perdu quelques recherches sur l'OLPC, des tonnes d'expériences comme LeBliki ou JeBliki, plein de liens delicious de peu d'intérêt et (mais c'est heureusement récupérable grâce à Google groups) toutes mes critiques SF de cette année (en plus, il y en a beaucoup moins qu'il a pu y en avoir en 2004). Bref.

Un dump MySQL, ça a quelle tête ? Eh bien tout simplement celle-là Désolé pour Fred et les autres qui ont mis des vrais commentaires, mais vu que tout le spam reste collé dans la base, j'ai tout jeté. En fait, j'au just gardé la partie wp_posts, qui contient mes posts. Tout le reste est passé à la corbeille.



[https://gist.github.com/265941](https://gist.github.com/265941)

Donc, je vais écrire de ce pas une petite moulinette Ruby qui sera capable de parser ligne par ligne mon dump MySQl, et de transformer les lignes insérant des données dans la table wp_post en fichiers utilisables par Webgen. Et cette moulinette, la voici :



[https://gist.github.com/265942](https://gist.github.com/265942)

Muni de cet outil que la NASA m'envie, je vais donc faire une expérience de dedumpage de ce bon vieux dump de 5 Mo. Roulement de tambour ... Et après quelques avanies liées à l'utilisation de caractères pas cathodiques dans mes titres (comme des ":", des "[" ou des "]"), ça marche ! Je viens donc de récupérer 740 pages pour ce site web, à trier et placer au bon endroit ... J'ai comme l'impression que j'ai pas fini de jouer du Ruby en furie, moi. En bonus, il faut que je trouve une feinte pour générer mes menus automatiquement, sinon ça va être la misère ! Et la façon de gérer les menus automatiquement, c'est pas bien compliqué, c'est simplement de demander à chaque fichier de s'inscrire dans les catégories dont il fait partie ...via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)