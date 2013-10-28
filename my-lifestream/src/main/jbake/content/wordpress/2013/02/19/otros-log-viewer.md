type: post
status: published
title: Otros Log Viewer
tags: ca sert à presque rien, java, logs
date: Tue Feb 19 14:41:07 CET 2013
~~~~~~
# Otros Log Viewer

Admettons que vous ayez des logs à analyser. Et par log, je veux dire des messages produits par log4j, slf4j ou JUL.

Vous pouvez utiliser grep. OU PAS.

Vous pouvez utiliser vi. OU PAS.

Vous pouvez utiliser [Apache Chainsaw](https://logging.apache.org/chainsaw/index.html) ... mais c'est un peu galère ... Surtout parce que le projet a l'air plutôt abandonné, ce qui est bien dommage.

En guise d'alternative un peu améliorée à Chainsaw, vous pouvez enfin utiliser [Otrols Log Viewer](http://code.google.com/p/otroslogviewer/), qui fait tout ce que fait Chainsaw, et y ajoute des gadgets supplémentaires (comme la lecture de logs à travers tous les systèmes de fichiers supportés par [Commons VFS](http://commons.apache.org/vfs/) - [je vous avais dit](http://riduidel.wordpress.com/2013/02/12/commons-vfs/) que c'était bien).

Une fois que vous l'aurez lancé sur [vos logs Glassfish](http://stackoverflow.com/a/10393070/15619), vous verrez votre tail -f d'un autre oeil ...