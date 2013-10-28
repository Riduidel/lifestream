type: post
status: published
title: Les metaClass me rendent perplexe
tags: groovy, map, metaclass
date: Tue Oct 19 13:51:39 CEST 2010
~~~~~~
# Les metaClass me rendent perplexe

Pour [un projet que j'ai entam?? il y a longtemps](http://gaedo.origo.ethz.ch/), je [me suis dit](http://gaedo.origo.ethz.ch/issues/36) r??cement qu'il serait bien d'ajouter un binding groovy, ?? la fois ?? titre d'exemple de ce qu'on peut faire facilement, mais aussi parce que le groovy, c'est chouette (et que je pourrai ainsi utiliser toute la puissance mod??r??e de [gaedo](http://gaedo.origo.ethz.ch/) avec [gaelyk](http://gaelyk.appspot.com/) (et ??a aura une bon sang de classe - surtout en utilisant [maven-gaelyk](http://code.google.com/p/maven-gaelyk/), qui m'a l'air d'??tre la meilleure chose depuis [le visage de J??sus sur des tranches de pain grill??](http://home.insightbb.com/~jmengel4/bread/bread.html)).  
J'ai donc commenc?? ?? coder des adaptations de mes composants de base ?? Groovy (parce que comme vous le savez, en groovy, on n'a pas de properties, mais des metaproprerties, ce qui change tout ... ou pas). Donc, ??a avan??ait bien (mis ?? part [un probl??me de gestion des annotations](http://stackoverflow.com/questions/3966459/what-is-the-difference-between-groovys-annotationnode-and-javas-annotation)), jusqu'?? ce que j'essaye de mettre mes objets dans une map en leur associant leur nom.L??, je crois que j'ai rencontr?? [le d??mon](http://stackoverflow.com/q/3966757/15619).Imaginez un peu.Si dans une map vous faites

map["a"]="b";

Ca marche parfaitement.Par contre, si vous faites

map["metaClass"]="d";

Ca part en sucette, comme le montre ma question sur StackOverflow, ou m??me le script original sur [Groovy Web Console](http://groovyconsole.appspot.com/script/290001).En fait, c'est simple.Dans n'importe quel objet Groovy, il y a une propri??t?? qui s'appelle metaClass, et qui est l'??quivalent Groovy de la class Java. Et, comme le dit fort justement la r??ponse ?? ma question, ??crire map["unAttribut"] revient pour l'interpr??teur Groovy ?? ??crrire map.setUnAttribut (ou tout au moins ?? essayer de le faire). Hors, map.setMetaClass, c'est une m??thode qui existe. C'est m??me une m??thode qui est red??finie pour la classe Object en groovy : (voir sa doc, [Object#setMetaClass()](http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html#setMetaClass(groovy.lang.MetaClass))). Du coup, je ne peux pas le faire.Remarquez, je m'attendais un peu ?? ??a, ?? cause des superbes [Expando](http://groovy.codehaus.org/gapi/groovy/util/Expando.html) de Groovy. Mais je ne pensais pas que ??a venait tout droit d'Object.Bon, c'est pas trop grave, parce que ??a ne me sert que dans du code de test. Mais c'est quand m??me troublant, je trouve.