type: post
status: published
title: Trouver d'où vient une classe ... facilement
tags: développement, debugger, java
date: Mon Apr 08 16:27:06 CEST 2013
~~~~~~
# Trouver d'où vient une classe ... facilement

Vous savez qui sait d'où vient la classe Java que vous avez sous les yeux dans le débuggeur, et qui ne correspond pas ... exactement .... à ce que vous croyez ?

Le ClassLoader qui l'a chargé.

Et vous pouvez même lui demander :<span class="linkifyplus"><a class="linkifyplus" href="https://gist.github.com/Riduidel/5337125">https://gist.github.com/Riduidel/5337125</a></span>

C'est rudement pratique pour débugger du code un peu tordu (comme [un connecteur neo4j JCA](https://github.com/Riduidel/neo4j-connector), par exemple).