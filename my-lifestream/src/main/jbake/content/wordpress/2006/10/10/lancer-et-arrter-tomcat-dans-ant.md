type: post
status: published
title: Lancer et arr??ter Tomcat dans Ant
tags: code, informatique, java
date: Tue Oct 10 10:55:00 CEST 2006
~~~~~~
# Lancer et arr??ter Tomcat dans Ant

Grâce à un collègue, j'ai enfin trouvé une façon simple de démarrer et d'arrêter Tomcat [depuis mon build Ant](http://ptrthomas.wordpress.com/2006/03/25/how-to-start-and-stop-tomcat-from-ant/ "How to start and stop Tomcat from ANT &laquo; Incremental Operations"). La seule chose qui lui manque, évidement, c'est la possibilité d'activer ou de désactiver ces tâches simplement. Pour moi c'est facile, grâce à ma technique des flags autogénérés :



[https://gist.github.com/266092](https://gist.github.com/266092)

Et de cette manière, Tomcat se lance et s'arrête seulement si ce flag est positionné à True (case sensitive).via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)

 