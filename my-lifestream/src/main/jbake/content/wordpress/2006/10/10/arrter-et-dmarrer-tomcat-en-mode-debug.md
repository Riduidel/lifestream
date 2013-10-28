type: post
status: published
title: Arr??ter et d??marrer Tomcat en mode debug
tags: code, informatique, java
date: Tue Oct 10 12:01:00 CEST 2006
~~~~~~
# Arr??ter et d??marrer Tomcat en mode debug

Bon, ce que j'ai écrit dans le précédent message était un tout petit peu optimiste ... En fait, le seul problème est qu'il n'est pas possible d'arrêter Tomcat grâce à la tâche tomcat-stop du [message précédent](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2006/lancer-et-arreter-tomcat-dans-ant/). La raison en est très simple : comme je lance Tomcat en mode debug, c'est-à-dire avec les options -XDebug aactivées, il faut l'arrêter de même. J'ai donc maintenant l'ensemble de tâches Ant suivant : [XML]


Starting Tomcat



Stoping Tomcat



in RUNTIME mode






in RUNTIME mode







in DEBUG mode









in DEBUG mode using JPDA_TRANSPORT ${system.JPDA_TRANSPORT}, JPDA_ADDRESS ${system.JPDA_ADDRESS}





 
 [/XML]via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)