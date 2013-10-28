type: post
status: published
title: Vis ma vie de développeur de merde avec Java sur Mac
tags: développement, java, macosx
date: Mon Aug 19 16:00:07 CEST 2013
~~~~~~
# Vis ma vie de développeur de merde avec Java sur Mac

Bon ...

Je ne vais pas forcément rentrer dans les détails, ils sont plus sordides que tout ce que vous pourriez imaginer, il suffit pour l'instant de faire [l'hypothèse](https://groups.google.com/d/msg/lescastcodeurs/g5dCkJWAFTc/HEPOE6BeVagJ) que je travaille pour une entreprise développant une application partiellement écrite en Java et dont un composant peut fonctionner sur Mac OS.

Notez bien que, personnellement, ça fait bien longtemps que j'ai compris la nature profonde de l'OS de Cuppertino, mais bon, hein, il faut savoir faire des con-promis (ceci n'est pas une faute).

Donc ...

Et n'allez pas croire que je crois aux présages, mais quand même, Jobs sort ces jours-ci, et nous nous prosternons devant l'homme qui a, mieux que Bill Gates, mieux que les rêves les plus fous d'Orwell, mieux que la pub qu'il faisait en 84, réduire nos libertés individuelles au droit de faire ce qu'il veut (mêeme depuis sa tombe) puisque nous ne sommes que des invités chez lui.

Donc ... (on va finir par se croire dans la NLDD ici)

Je me suis retrouvé la semaine dernière à corriger un bug approchant de [cette histoire traînant sur les ML d'Apple](https://lists.apple.com/archives/java-dev/2013/Jun/msg00083.html).

En gros, mon application charge un composant [JMX](http://fr.wikipedia.org/wiki/JMX) (ça, c'est prévu) qui, lui, charge [AWT](http://fr.wikipedia.org/wiki/AWT) (le système de fenêtrage originel de Java) qui, lui, refuse de se charger pour une raison inconnue.

A l'origine de tout ça, une classe du système d'instrumentation de Java : *com.sun.jmx.trace.Trace*. Vous ne la trouverez pas sur le web puisque, comme l'indique son nom de package, c'est une classe privée de chez Sun/Oracle (par convention, les packages *com.sun.** sont privés et ne sont pas accessibles aux simples développeurs, **mais** on peut y jeter un coup d'oeil dans les sources du JDK).

N'écoutant que mon courage (et la pile d'exception), j'ai été jeter un coup d'oeil autour des lignes indiquées par la pile d'exception : 

> at com.sun.jmx.trace.Trace.out(Trace.java:180)

Parce que souvent, c'est là que les problèmes se trouvent ...

Et là, je cite mon Eclipse : 

[](http://riduidel.files.wordpress.com/2013/08/java-com-sun-jmx-trace-trace-eclipse_2013-08-19_15-48-33.png)

 

Eh ouais, la ligne n'existe pas dans la version dont je dispose. Et c'est là que j'ai compris que je partais tout droit en enfer, juste armé d'une brosse à dents.

Parce que figurez-vous que le JDK d'un Mac n'est pas celui d'un Windows/Linux/N'importe quoi.

Non. Il y a quelques années, Apple voulait pousser en avant Java. Ils se sont donc mis d'accord avec Sun/Oracle pour repackager un JDK Apple avec quelques additions sympathiques.

Du temps est passé sous les ponts, et cet accord s'est arrêté vers ... 2013 ? Je crois, à la demande d'Apple, qui préférait miser sur son propre langage de kalitaye (encore une fois ça n'est pas une faute) : [Objective-C](http://fr.wikipedia.org/wiki/Objective_C) aussi pratique sur un iMac que sur un iPhone, ou encore un iFuckYouDeep.

Mais chez Apple, on aime bien laisser des bonnes surprises en partant.

Par exemple, la [mise à jour Java 16](http://support.apple.com/kb/HT5797?viewlocale=fr_FR) de Java qui, sur tous les ordinateurs utilisant MacOS, casse méthodiquement JMX en lui ajoutant une dépendance débile sur AWT. Parce que bon, cette mise à jour passe le Java des Mac en version 1.6.0_51. Cherchez donc chez oracle cette version ... elle n'existe pas. Et ouais, l'effet [zone 51](http://fr.wikipedia.org/wiki/Zone_51) joue à fond.

Vous en voulez encore ?

Les ordinateurs ayant reçus cette mise à jour sont tous ceux tournant sous Mac OS 10.6.8. Pour bon nombre d'entre eux, la mise à jour vers 10.7 ou 10.8 est interdite par Apple (comme ils ont interdit d'ailleurs la mise à jour de mon iBook il y a quelques années - je vous laisse chercher dans le blog).  Ah, et en bonus Oracle ne livre pas de versiond e Java 7 pour cette version de Mac OS. Donc si vous utilisez cette version, vous êtes baisés et re-baisés.

En bonus caché, certaines applications utilisent Java en version 32 bits (ou i386 si vous préférez). Or, quand bien même vous installez Java 7, il ne s'agit que de la version 64 bits ! Du coup vous vous faites chier à faire la mise à jour, mais le bug continue à exister pour toutes les vieilles applications. Et comment faire pour corriger ? Oh, be simplement demander à Adobe de se bouger le cul pour recompiler leurs applis en 64 bits.

Bref, vous êtes baisés, re-baisés, et re-re-baisés.

Comme moi maintenant qui dois trouver un contournement.