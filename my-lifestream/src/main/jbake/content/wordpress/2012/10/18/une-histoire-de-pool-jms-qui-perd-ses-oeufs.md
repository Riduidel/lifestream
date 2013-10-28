type: post
status: published
title: Une histoire de pool JMS qui perd ses oeufs
tags: glassfish, javaee, jms
date: Thu Oct 18 12:35:44 CEST 2012
~~~~~~
# Une histoire de pool JMS qui perd ses oeufs

Ca ne vous est jamais arrivé, hein ? Normal, c'est un problème tordu que j'ai enfin résolu cette semaine, et j'en suis fort content.

J'ai donc une application qui échange des messages JMS via des MDB et toutes ces cochonneries JavaEE.

Problème, mon application "perd" des connexions JMS et se retrouve régulièrement à avoir consomé tout son pool de connexion JMS, ce qui me donne dans Glassfish de bien beaux messages :
> In-use connections equal max-pool-size and expired max-wait-time. Cannot allocate more connections.
Utile, non ?

Du coup, la question se pose sous plusieurs angles : comment savoir combien de connexions sont actuellement utilisées ? Combien de connexions sont actuellement dans les choux ? Peut-on savoir quelles sont les connexions perdues.

Heureusement, pour poser des questions, il y a ... [StackOverflow](http://stackoverflow.com/q/12911992/15619) et twitter :

https://twitter.com/riduidel/status/258144170693255168

La réponse de Thibaud-de-chez-[Onyme](http://onyme.com/)-en-face est pas mal, puisque imqcmd me permet d'afficher ce genre de logs :


<pre>imqcmd metrics svc -n jms -m cxn
----------------------------------------------------
 Num JVM Heap Bytes Threads
 Connections Total Free Active Min Max
 ----------------------------------------------------
 0 518979584 328325792 0 10 1000
 0 518979584 328319376 0 10 1000
 5 518979584 296628128 10 10 1000
 21 518979584 293279856 42 10 1000
 31 518979584 198781504 62 10 1000
 41 518979584 244688728 82 10 1000
 51 518979584 284880160 102 10 1000
 61 518979584 183096536 122 10 1000
 71 518979584 227618856 142 10 1000
 81 518979584 270680512 162 10 1000
 85 518979584 200182296 170 10 1000
 95 518979584 226605752 190 10 1000
 105 518979584 257838528 210 10 1000
 115 518979584 289606864 230 10 1000
 116 518979584 306336280 242 10 1000</pre>
Déja, on apprend plein de trucs (comme par exemple le fait que, oui, effectivement, il y a bien des connexions JMS toutes les 5 secondes). Bon, si vous vous inquiétez du nombre de connexions, pas d'affolement, c'est un test conçu spécifiquement pour exposer ce bug.

Malheureusement, ça ne suffit pas à détecter la faille. Heureusement pour moi, Glassfish est là !

https://twitter.com/riduidel/status/258208910668857344

Eh oui, regardez :

[](http://riduidel.files.wordpress.com/2012/10/edit-connector-connection-pool-advanced-attributes-opera_2012-10-18_11-55-061.png)

(merci [Greenshot](http://getgreenshot.org/)) Une fois ces deux options activées, le log de Glassfish va afficher une "belle" pile d'exception à chaque fois qu'une connexion est perdue. Avec ça, trouver l'origine du problème est d'une facilité.

Si vous voulez tout savoir, l'origine du problème, c'est qu'à cause du manque de [try-with-resources](http://adiguba.developpez.com/tutoriels/java/7/#LI-F) dans Java6, je créais une connexion JMS que je ne fermais pas correctement. Idiot, pénible, et sacrément difficile à détecter sans ces outils !