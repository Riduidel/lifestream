type: post
status: published
title: Un peu de DevOps à la maison ...
tags: dns323, github, productivité
date: Mon Apr 29 22:13:01 CEST 2013
~~~~~~
# Un peu de DevOps à la maison ...

A cause de cette histoire de Google Reader, j'installe de plus en plus de petits scripts PHP sur mon serveur (et j'envisage même d'installer ... [d'autres choses](http://forum.dsmg600.info/t2091-Polipo-proxy-funplug.html)). Et forcément, il y a de l'admin à faire. Comme je n'aime pas ça, j'essaye de faire les choses d'une façon qui m'intéresse ... Comme par exemple en faisant du DevOps ... Et pour ça, j'ai commencé par me créer [un repository GitHub](https://github.com/Riduidel/my-dns-323) avec les fichiers de configuration des différents programmes que j'utilise (sauf les clés SSH et les fichiers de mot de passe utilisés par le serveur web - à peu près pas fou).

Bon, le plus grand intérêt, c'est quand même de pouvoir utiliser le bugtracker de GitHub pour mieux gérer le bazar ... et ce sera par ailleurs une excellente occasion d'essayer [Huboard](http://huboard.com/) !

Au début, je voulais utiliser un système genre [todo.txt](http://todotxt.com/) synchronisé avec Dropbox mais ... je trouve la possibilité de fermer un bug depuis un commit git vraiment géniale. En fait, j'aurais bien aimé pouvoir me connecter au truc via Jabber (qui reste ma technologie inutilisée préférée), malheureusement je n'ai rien trouvé de bien génial. Tiens, par exemple, IFTTT a bien [un channel Google Talk](https://ifttt.com/google_talk), mais je suis extrêmement déçu par la pauvreté du truc à chaque fois que je le regarde. Bon, et la [concurrence](http://alternativeto.net/software/ifttt/)[principale ](https://zapier.com/)a l'inconvénient majeur pour moi d'être orientée entreprise (et donc massivement payante).

Bon, d'un autre côté (et ça me fera bien plus de boulot) il semble possible d'écrire des bots Jabber en PHP ... Mmmh