type: post
status: published
title: Resistance is futile !
tags: euler, groovy, java
date: Tue Feb 08 14:49:00 CET 2011
~~~~~~
# Resistance is futile !

Depuis deux jours, et apr??s un bien long hiatus (et surtout suite ?? une discussion avec un coll??gue), j'ai replong?? dans l'enfer de Project Euler. Un enfer, parce qu'il promet ?? mon cerveau assoif?? de r??compenses la garantie de r??compenses bien plus imm??diates que celles que peut procurer le travail. En l'occurence, j'ai d??cid?? de m'attaquer au [probl??me 12](http://projecteuler.net/index.php?section=problems&id=12). le probl??me 12, c'est le seul des 25 premiers qui me r??siste. Et ??a m'??nerve.  
Vous voulez savoir ce que ma derni??re tentative (que je ne montrerai pas) a donn?? comme r??sultat ?



[https://gist.github.com/816432](https://gist.github.com/816432)

Oui, c'est d??cevant.Surtout quand le programme a ??t?? lanc?? avec un JAVA_OPTS="-Xmx1200M" (la m??thode [recommand??e](http://groovy.codehaus.org/Running) pour changer la quantit?? de m??moire disponible dans du code Groovy).Surtout quand je vois qu'ailleurs sur internet, d'autres ont trouv?? la solution - en groovy - en [moins d'une minute](http://keyzero.wordpress.com/2010/01/27/project-euler-problem-12/). Surtout (enfin) quand je sais (toujours gr??ce ?? ce coll??gue) que [la solution](http://code.google.com/p/projecteuler-solutions/wiki/ProjectEulerSolutions) est 76576500. Et l??, je ne ferais pas comme pour le [Dropquest 2011](https://www.dropbox.com/dropquest2011), je n'irais pas aligner les r??ponses les unes derri??re les autres.Reprenons donc.Le but est de trouver le premier [nombre triangulaire](http://fr.wikipedia.org/wiki/Nombre_triangulaire) ayant 500 [diviseurs](http://fr.wikipedia.org/wiki/Diviseur). Pour trouver ces diviseurs, je m'??tais dit que la solution la plus simple ??tait de [d??composer le nombre en facteurs premiers](http://fr.wikipedia.org/wiki/D??composition_en_produit_de_facteurs_premiers). Seulement, pour ??a, il me faut des gros paquets de nombres premiers, qui doivent ??tre calcul??s d??s le d??but. Donc, pas forc??ment une bonne id??e.Cela dit, je crois que je devrais r??essayer la m??thode initiale (qui consiste ?? faire de b??tes divisions par des nombres de 1 ?? racine de n) ... en y ajoutant peut-??tre un peu de [GPars](http://gpars.codehaus.org/) pour rire ... ou pas.