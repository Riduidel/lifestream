type: post
status: published
title: pr??conditions en Java 
tags: concepts, d??veloppement, java, pr??conditions, test
date: Tue Feb 17 15:57:45 CET 2009
~~~~~~
# pr??conditions en Java 

Quand on d??veloppe en Java et qu'on ??crit des m??thodes, on a souvent le cas de param??tres ayant des valeurs incorrectes (genre un param??tre nul).

Ce qu'on fait d'ahbitude, c'est une v??rification simple :  


if(toto==null) {

    toto = "toto";

}  


C'est laid, hein ?

Et en plus, ??a pollue le code.

En me baladant sur internet ?? la recherche de la solution ?? un probl??me compliqu?? de collection bas??e sur le hash contenant des ??l??ments mutables, je suis tomb?? sur cet article : [mutable entries in a collection](http://javablog.co.uk/2008/10/19/mutable-entries-in-a-collection/) qui m'a men?? ?? celui-ci (beaucoup plus int??ressant) : [RuntimeExceptions and Gurus failing to meditate](http://javablog.co.uk/2008/10/18/runtimeexceptions-and-gurus-failing-to-meditate/) bon, j'aime bien la GuruFailedToMeditateException, mais ce que j'aime surtout beaucoup, c'est les v??rifications de pr??conditions avec la classe [Preconditions](http://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/base/Preconditions.html). J'aime m??me tellement ??a que je me demande comment je pourrais inclure ??a dans mon projet ?? grands coups d'annotations (pour que ??a marche bien et que ??a puisse aussi g??n??rer de la doc).

Du coup, j'ai commenc?? ?? re-jeter un coup d'oeil aux frameworks de [programmation par contrat](http://fr.wikipedia.org/wiki/Programmation_par_contrat) en Java.
* [modern-jass](http://modernjass.sourceforge.net/) a l'air tr??s bien
* Je me demande si on ne peut pas b??tir quelque chose pour ??a en utilisant jUnit et son d??sormais fameux [assertThat](http://joe.truemesh.com/blog/000511.html).
* Et [cet article](http://www.tomsquest.com/blog/de-la-programmation-defensive/) m'explique ce dont je me doutais d??ja : les assertions sont un premier pas d??ja satisfaisant, et [hamcrest](http://code.google.com/p/hamcrest/) et [FEST-assert](http://fest.easytesting.org/assert/wiki/pmwiki.php) sont de tr??s bonnes solutions, gr??ce essentiellement ?? leurs [interfaces "fluentes"](http://en.wikipedia.org/wiki/Fluent_interface).

Bref, c'est un sujet assez int??ressant, sur lequel je vais essayer de creuser un peu.