type: post
status: published
title: [Java] Et on s'??tonnera que Java ne soit pas r??put?? pour la 2D et les images
tags: iptc, java
date: Sun Sep 14 21:07:00 CEST 2008
~~~~~~
# [Java] Et on s'??tonnera que Java ne soit pas r??put?? pour la 2D et les images

Je suis dégoûté.

On peut faire plein de trucs en Java, et la plupart du temps, c'est très simple. Et avec le temps, j'ai découvert qu'on voit très bien le degré d'intérêt d'un domaine au nombre de bibliothèques **de qualité** qui existent pour travailler dessus.

Et hélas, ça ne semble vraiment pas être le cas de la gestion des images. J'ai testé plein de librairies plus ou moins exotiques ([sanselan](http://incubator.apache.org/sanselan/site/index.html), [imagero](http://reader.imagero.com/), ...). Et elles souffrent toutes des mêmes défauts : buggées, incomplètes, limite non documentées. C'est vraiment trop chiant.

Et vous savez ce qui est le pire, c'est que le mec qui fourgue Imagero prétend qu'il vend son truc parce qu'il doit bien gagner sa vie blablaba. Le tout, avec un truc non documenté, et surtout dont la documentation (et même les forums pour tout dire) est pire qu'inutile.

Du coup, ne serait-ce que pour inciter les gens à bosser correctement, je vais reprendre sanselan qui, même buggé, fournit au moins un exemple clair.

[EDIT]  Franchement, je ne sais pas trop quoi penser. En plus, il y a quelques articles concernant l'utilisation d'Advanced JAI pour lire ces tags (comme [How to read EXIF and IPTC with Java Image I/O API](http://www.barregren.se/blog/how-read-exif-and-iptc-java-image-i-o-api) ou [Java and JPEG Metadata](http://easyproblemsolutions.blogspot.com/2008/06/java-and-jpeg-metadata.html)) Et puis j'ai aussi trouvé [Java Exif](http://www.toanthang.net/modules.php?name=News&new_topic=2&catid=7), mais ça ne m'a pas l'air bien sérieux. Bref, je ne sais pas trop quoi faire.