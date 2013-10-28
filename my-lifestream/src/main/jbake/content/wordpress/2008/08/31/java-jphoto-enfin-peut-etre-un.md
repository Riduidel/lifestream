type: post
status: published
title: [Java] jPhoto ... enfin, peut-être un jour
tags: 
date: Sun Aug 31 15:34:00 CEST 2008
~~~~~~
# [Java] jPhoto ... enfin, peut-être un jour

Depuis le temps que ça traîne dans mes brouillons, je vais vous parler de "mon jPhoto"(1).

Sous ce nom habilement trouvé se cache un projet, que j'espère fructueux, de refaire un logiciel comparable à [iPhoto](http://fr.wikipedia.org/wiki/IPhoto), mais en Java (donc multiplateforme)(3). Mais pourquoi donc refaire iPhoto ?
1. Parce que ma femme a un nouveau portable sous Windows, et que
2. iPhoto, Picasa et les autres ne gèrent pas bien les tags [EXIF](http://fr.wikipedia.org/wiki/Exif), et que
3. Comme le dirait [Rui Carmo](http://the.taoofmac.com/space/Data%20For%20The%20Ages), ce qui compte,c e ne sont pas les logiciels, mais les données. Et en termes d'image, la seule manière de garantir que les métadonnées survivront, c'est d'utiliser le standard de l'appareil Photo, et donc l'EXIF.

Donc, je m'en vais prendre quelques bonnes idées d'iPhoto, et les packager dans du code Java.

Seulement voilà, comme la vie n'est pas simple, lire (et plus encore écrire) lire des métadonnées EXIF dans du JPEG, c'est loin d'être de la tarte.

Du coup, je cherche des solutions. Et heureusement, grâce à [un petit tutorial](http://www.screaming-penguin.com/node/7485), je crois enfin avoir trouvé la solution(4) : [Sanselan](http://incubator.apache.org/sanselan/site/index.html). Le code a l'air très simple, le tutorial déja évoqué nous montre clairement qu'on peut lire des trucs, je vais tester. Et sinon, au pire, je retournerai voir du côté de JAI ...

Bon, par contre, au niveau GUI, c'est pas encore ça ... C'est même tellement pas ça que je ne me ferais pas la honte d'inclure une capture.

Allez, j'y retourne.

PS : encore un truc bien avec posterous : mon brouillon n'est pas dans le blog machintruc, mais dans GMail, et je trouve ça mieux (enfin, moi tout seul, quoi).

 
* (1) : Parce que j'ai cherché si le nom [jPhoto](http://www.google.com/search?q=jphoto&ie=utf-8&oe=utf-8) existait déja, et il se trouve malheureusement que oui
* (2) : j'aurais bien fait une note de pied de page avec un lien, mais GMail ne sait pas faire
* (3) : tout ça, c'est la faute à mon [hubris](http://fr.wikipedia.org/wiki/Hubris).
* (4) : ... attendez un instant. on me souffle depuis cette [URL](http://easyproblemsolutions.blogspot.com/2008/06/java-and-jpeg-metadata.html) qu'il suffirait d'installer une librairie supplémentaire à mon JDK pour que ça marche ... je m'en vais vérifier ça tout de suite. Bon, maintenant, faut être conscient du fait que patcher le JDK pour ça, ça va pas aider les utilisateurs futurs, hein, sauf si on peut télécharger la librairie ddans un JNLP. Le pire, c'est qu'à tout coup, je ne saurais pas écrire mes métadonnées ... Bon, apparement, si, mais ça a l'air trop loin d'être simple. Donc retour au texte normal et fin de la parenthèse [JAI](https://jai-imageio.dev.java.net/).