type: post
status: published
title: maven, google code, svn, toussa 
tags: java, majickproperties, maven
date: Wed Jan 14 10:29:18 CET 2009
~~~~~~
# maven, google code, svn, toussa 

Bon, ??a chie un peu tout ??a.

ce week-end, je me suis diot que j'allais faire une release de majick-properties, parce que le code me paraissait suffisant pour une alpha 0.1.  


Donc, n'??coutant que mon mavenisme, j'ai lanc?? un simple

mvn release:prepare

Et d??s le d??part, grosse d??convenue.  


En standard, j'avais mis mon projet maven en UTF-8, et je demandais ?? Eclipse d'enregistrer (par d??faut) en cp-1252. J'ai doinc d?? transcoder tous mes fichiers en UTF-8 ?? grands coups d'??dition dans Notepad++. Long, p??nible, et surtout, inutile.

Inutile parce que, dans Notepad++, il y a **deux** UTF-8 :
* UTF-8 classique
* UTF-8 sans BOM

Et figurez-vous que le compilateur Java a [un bug connu avec l'UTF-8 classique](http://bug  s.sun.com/bugdatabase/view_bug.do?bug_id=4  508058) : il lit le BOM comme un octet normal et consid??re que ce n'est pas un ??l??ment valide.

Du coup, j'ai d?? reconvertir tous mes fichiers en UTF-8 sans BOM. Temps perdu ? environ 1/2 heure.

Ensuite, j'ai relanc?? maven, qui m'a dit que je n'avais pas de client svn valide. sans doute parce que pour faire des releases, il faut avoir un client svn en ligne de commande, et non pas celui int??gr?? ?? Eclipse ... Attendez une seconde ... Si j'avais fait le mvn release:prepare dans Eclipse, ??a aurait pu marcher ? J'essayerais ce soir, tiens.

En attendant, j'ai t??l??charg?? slicksvn, qui semble ??tre devenu le client officiel.

Et maintenant, ??a coince juste sur le svn tag, qui semble ??tre li?? au fait que je lance mon mvn release:prepare sans donner mon login/pwd ...

Du coup, je suis un peu blas??. Mais je vais y arriver quand m??me (de toute fa??on, je n'ai pas trop le choix, si je veux pouvoir utiliser majick-properties partout dans le monde, je dois passer par les releases.