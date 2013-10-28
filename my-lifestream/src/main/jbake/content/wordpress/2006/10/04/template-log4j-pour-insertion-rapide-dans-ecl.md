type: post
status: published
title: template log4j pour insertion rapide dans Eclipse
tags: code, informatique, java
date: Wed Oct 04 18:01:00 CEST 2006
~~~~~~
# template log4j pour insertion rapide dans Eclipse

Pour créer facilement des loggers log4j dans Eclipse, aller dans "Preferences... Java/Editor/Templates" et ajouter un template log4j avec le code suivant

<pre>private static final Logger logger = Logger.getLogger(${enclosing_type}.class);</pre>via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)