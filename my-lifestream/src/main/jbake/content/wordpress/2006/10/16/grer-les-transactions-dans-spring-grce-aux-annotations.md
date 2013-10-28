type: post
status: published
title: G??rer les transactions dans Spring gr??ce aux annotations
tags: code, informatique, java
date: Mon Oct 16 18:47:00 CEST 2006
~~~~~~
# G??rer les transactions dans Spring gr??ce aux annotations

La doc dit qu'il n'y a que deux manières de gérer des transactions dans Spring :
* Par le code (en utilisant un TransactionTemplate). C'est difficile, dangereux, bref, à éviter. 
* Par les déclarations XML en se rajoutant des tonnes de beans. C'est verbeux, non contrôlé, bref à éviter.  
* [Ce blog](http://blog.exis.com/colin/archives/2005/07/18/spring-12s-java-5-based-transaction-annotations/ "sampa : colin’s blog    &raquo; Spring 1.2’s Java 5 Based Transaction Annotations") nous donne LA solution : utiliser les annotations. Franchement, ça vaut vraiment la lecture pour éviter de ce prendre la tête avec toutes ces déclarations pénibles. Bien sûr, c'est encore une raison pour créer une interface pour chaque service, mais je trouve que le jeu en vaut la chandelle. Pas vous ? via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)