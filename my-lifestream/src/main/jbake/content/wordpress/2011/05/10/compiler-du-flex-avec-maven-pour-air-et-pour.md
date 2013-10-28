type: post
status: published
title: Compiler du flex avec maven pour air et pour flex
tags: air, flex, flexmojos, maven
date: Tue May 10 14:47:56 CEST 2011
~~~~~~
# Compiler du flex avec maven pour air et pour flex

Avertissement pr??alable : ce message concerne un sujet technique, si vous ??tes l?? pour la d??conne ... ben c'est pas pour vous.  


Supposons que vous soyez moi. Ou vous dit que vous devez (enfin, vous imaginez plut??t qu'on vous a dit ??a) trouver une solution pour compiler tous vos jolis projets Flex avec maven. Pour ??a, ??videment, vous savez qu'il existe [flexmojos](http://flexmojos.sonatype.org/). Et pendant un moment, tout va bien. Vous arrivez ?? compiler vos [SWC](http://www.filesuffix.com/extension/swc.html) et [SWF](http://the-labs.com/MacromediaFlash/SWF-Spec/SWFfileformat.html) sans trop de probl??mes (si on met de c??t?? [les num??ros de version louches](https://repository.sonatype.org/content/groups/flexgroup/com/adobe/flex/framework/flex-framework/), l'absence de [certains artefacts](http://opensource.adobe.com/wiki/display/cairngorm/Cairngorm+Libraries#CairngormLibraries-MavenBugbase) - d'une utilit?? discutable, cela dit -, la gestion "[d??licate](http://groups.google.com/group/flex-mojos/browse_thread/thread/3c03f33c2f32d809?pli=1)" des th??mes - cherchez pas, c'est pas la m??thode que j'utilise -, ou encore la gestion des include dans le code). Mais, ?? un moment, vous allez tomber sur du code "compatible" Flex/AIR. Et l??, c'est vraiment pas gagn??. Parce que la m??thode "[canonique](http://anirudhs.chaosnet.org/blog/2008.08.25.html)" pour ??crire du code Flex pour les applets ou les applications, c'est d'utiliser [l'??quivalent Flex](http://livedocs.adobe.com/flex/3/html/help.html?content=compilers_21.html) des [macros C](http://en.wikipedia.org/wiki/C_preprocessor) :  




[https://gist.github.com/964393](https://gist.github.com/964393)

Pas vraiment beau, et surtout pas facile de faire rentrer ??a dans la logique Maven qui dit "un projet, un artefact". parce que l??, le but, c'est avec un seul projet de produire deux artefacts : un SWF pour AIR et un SWF pour Flex.

Heureusement, en fouillant un peu, j'ai trouv?? [un d??but de solution](http://blog.jayway.com/2010/01/21/one-artifact-with-multiple-configurations-in-maven/) : les [classifieurs](http://maven-guide-fr.erwan-alliaume.com/maven-reference-fr/site/reference/profiles-sect-platform-classifier.html). Bien s??r, j'aurais pu utiliser les [profiles](http://maven.apache.org/guides/mini/guide-building-for-different-environments.html), mais ils sont, d'apr??s ce que j'ai compris, exclusifs. Il fallait cependant que j'adapte ??a ?? mon probl??me. Pas forc??ment facile. N??anmoins, flexmojos fournit les param??tres de configuration permettant de [d??finir les propri??t??s conditionnelles](http://repository.sonatype.org/content/sites/flexmojos-site/4.0-pre-alpha-1/compile-swc-mojo.html#defines). Du coup, le fragment de POM suivant permet de produire un SWF ayant le classifieur "flex" et un autre ayant le classifieur "air" :



[https://gist.github.com/964401](https://gist.github.com/964401)

Bon, ??videment, comme mon artefact utilise un POM parent (pour d??finir tout le bordel des d??pendances flex), j'ai d?? enlever la compilation swc par d??faut, c'est pour ??a que (gr??ce ?? [cet article](http://thomaswabner.wordpress.com/2010/02/16/howto-disable-inherited-maven-plugin/)) j'ai r??associ?? l'ex??cution default-compile-swc ?? la phase none.

Et dire que ces histoires n'en sont qu'?? leur commencement ...