type: post
status: published
title: majick-properties
tags: java, majickproperties
date: Tue Dec 16 22:04:54 CET 2008
~~~~~~
# majick-properties

La semaine derni??re, j'ai eu [une super id??e](http://snippets.dzone.com/posts/show/6687).

Tellement super, m??me, que je me suis dit que j'allais en faire quelque chose ...  


Et donc nous y voil??.

Bien ??videment, je devrais plut??t vous expliquer pourquoi je ne me fatigue m??me pas ?? r??utiliser [bean-properties](https://bean-properties.dev.java.net/), mais je n'en ai pas vraiment envie.

En fait, je vais plut??t vous parler des d??cisions que j'ai d??ja prises.  


Contr??le de source

J'aurais pu utiliser Git, et donc [GitHub](http://github.com/), pour me la p??ter, avoir du style, tout ??a. La raison en est bien simple : les plugins git pour Eclipse et NetBeans sont pour l'instant exp??rimentaux, alors que subversion est une solution beaucoup plus largement ??prouv??e.

Par voie de cons??quence, si je peux dire, ??a me conduit tout droit ?? la solution que j'utilise g??n??rallement, et que mon fan n'appr??cie apparement pas trop (mais s'il se contente de dire "c'est nul", autant qu'il se taise, hein) : Google code. Ils offrent une gestion de projet simple, un svn, un wiki, des listes de diffusion, tout ??a pour pas bien cher, quand m??me. Du coup, pouf, voil?? : [majick-properties](http://code.google.com/p/majick-properties/).

Tests divers

Bon, je ne sais pas encore si je vais choisir jUnit ou TestNG comme base de tests, mais je sais en tout cas que je vais utiliser [Fest](http://code.google.com/p/fest/).

Parce que bon, j'ai d??ja fait des tests avec des outils comme jMock et Abbott. Mais le probl??me des d??pendances de tests, c'est qu'elles risquent parfois d'??tre incompatibles entre elles, comme pr??cis??ment les deux du dessus.

Et comme Fest fait au moins le m??me boulot que ces deux-l??, ??a vaut largement le coup.

Et si mon fan se pose encore la question, j'ai d??ja cr???? mon projet Maven. Mais comme je suis un dingue, et gr??ce ?? maven, je vais essayer de tout faire sans Eclipse, mais avec Netbeans.

Le sens de la manoeuvre

A partir de maintenant, ??a n'est plus bien compliqu?? :
1. Ecrire le code de l'interface.
2. Ecrire les impl??mentations n??cessaires pour les cas que je connais d??ja.
3. Faire les tests unitaires qui vont bien.
4. G??n??rer les composants graphiques ?? partir de ces propri??t??s.

Et faire un truc qui d??chire, surtout.