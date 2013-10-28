type: post
status: published
title: Dropbox, c'est internet dans ma machine !
tags: dropbox, lifestream
date: Thu Aug 26 14:16:16 CEST 2010
~~~~~~
# Dropbox, c'est internet dans ma machine !

Je crois que je vous ai déja parlé de [Dropbox](http://riduidel.posterous.com/lifestream-ou-backup) (enfin, par la bande - si si, regardez vers la fin du message), ce génial programme qui synchronise tout seul comme un grand des dossiers entre plusieurs machines, qu'elles soient sous Windows, MacOS, Linux (mais hélas pas le Linux de mon NAS, qui n'est pas du x86), ...Faut bien reconnaitre que c'est de la balle, ce truc.Et ses usages sont [multiples](http://www.geekpauvre.com/2010/08/les-multiples-utilisations-de-dropbox/).
* On peut s'en servir pour synchroniser des logiciels sur plusieurs machines (personnellement, je m'en sers pour Trackmania, Pidgin, et mes scripts greasemonkey pour Opera - mais j'en ai en fait très peu). Evidement, pour éviter de mettre le bazar, le mieux à faire est encore d'installer son logiciel normalement, et ensuite de faire un [point de jonction](http://forums.dropbox.com/topic.php?id=450) (sous [Windows](http://schinagl.priv.at/nt/hardlinkshellext/hardlinkshellext.html)) ou un lien (sous Linux/Mac) entre le dossier du logiciel et le dossier de Dropbox, mais tout est possible ...
* OIn peut aussi s'en servir comme [site web minimaliste](http://wiki.dropbox.com/TipsAndTricks/HostWebsites) ... et c'est ça qui m'intéresseParce que comme je le disais dernièrement, j'ai modifié mes scripts de backup (ou plutôt j'ai créé des scripts les lançant avec la bonne configuration) posterous et goodreads pour qu'ils aillent stocker les fichiers XML dans un dossier nommé My Dropbox/Public/lifestream. Et, comme vous le savez sans doute, ce dossier est visible de l'internet (mais je ne vous donnerai pas son adresse). Grâce à ça, je dispose maintenant des articles de mon blog à peu prés partout dans l'univers, mais sous une forme assez moche :
 
[](http://getfile7.posterous.com/getfile/files.posterous.com/riduidel/xeedYLSmKdPSj6otZt9W1IxFPQeOOvSobO76L9y7FTTubQ3v6Kiwui4v7sXG/SS-2010-08-26_14.04.25.png)
 


 
Alors évidement, je voudrais remplacer ça par quelque chose de joli, et sans traitement (dans l'idéal). J'ai ... deux solutions
1. Utiliser [Java Site Generator](http://www.ohloh.net/p/java-site-generator) que j'ai écrit l'année dernière et dont je suis très mécontent (parce qu'il me parait lourd, parce que je n'ai jamais réussi à en faire un jar fonctionnel, parce qu'en fait le site généré est simplement moche, ...)
2. Modifier un peu le XML généré pour intégrer quelques informations supplémentaires (que je générais traditionnellement avec JSG) et utiliser une (ou plusieurs) XSL pour produire du HTML digne de ce nom.J'ai un peu envie de dire que cette deuxième solution tient la corde, parce qu'elle est plus "simple" fonctionnellement (mais évidement plus complexe techniquement, parce que les XSL c'est pas de la rigolade). Mais je me tâte quand même encore.En fait, la seule chose qui me manque, c'est un outil pour copier régulièrement ce site sur [nicolas.delsaux.free.fr/lifestream](http://nicolas.delsaux.free.fr/lifestream). Mais ça, les tâches planifiées et/ou un cron bien placé sauront le faire mieux que mes petites mains ...Et puis l'avantage d'une XSL, c'est qu'avec ça, si je cherche quelque chose dans ces notes, je peux afficher le fichier facilement dans mon afficheur XML local (sans passer par internet, donc) et avoir un texte plus joli que le bazar ci-dessus. Je pense que c'est donc dans cette direction que je vais aller. Et cette direction, en fait, je la dois simplement à l'existence de Dropbox, et aux bons conseils de Rui Carno (évidement).