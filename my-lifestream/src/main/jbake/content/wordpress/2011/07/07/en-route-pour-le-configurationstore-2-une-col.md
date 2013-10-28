type: post
status: published
title: En route pour le ConfigurationStore (2) : Une collection qui balance !
tags: collection, configurationstore, java, propertychangeevent
date: Thu Jul 07 14:22:25 CEST 2011
~~~~~~
# En route pour le ConfigurationStore (2) : Une collection qui balance !

Bon, si vous ??tes toujours l??, c'est que vous en voulez, alors vous allez en avoir !  
Mais avant d'aller dans les trucs vraiment dingues, on va faire un petit d??tour du c??t?? des collections et des ??v??nements.Comme vous le savez sans doute, Java dispose d'un [sacr?? paquet de collections](http://fmora.developpez.com/tutoriel/java/collections/introduction/). Des collections de tous genres, qu'apparement tous les d??veloppeurs [ne ma??trisent pas](http://stackoverflow.com/questions/tagged/java+collections) (alors que c'est pourtant, ?? mon sens, l'un des plus chouettes morceaux du JDK).Pourtant, si vous avez ??t?? fouiller dans la doc des classes de l'article pr??c??dent, vous avez sans doute constat?? que le [](http://download.oracle.com/javase/6/docs/api/java/beans/PropertyChangeSupport.html) fournit un moyen pour envoyer des ??v??nements concernant les ??l??ments d'un tableau, mais pas ceux d'une collection. Si vous ne voyez pas, je peux clarifier pour vous, il s'agit des m??thodes [fireIndexedPropertyChange](http://download.oracle.com/javase/6/docs/api/java/beans/PropertyChangeSupport.html#fireIndexedPropertyChange(java.lang.String,%20int,%20java.lang.Object,%20java.lang.Object)). Comme vous le voyez (on peut m??me regarder [le source](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/beans/PropertyChangeSupport.java#PropertyChangeSupport.fireIndexedPropertyChange(java.lang.String%2Cint%2Cjava.lang.Object%2Cjava.lang.Object)), si vous voulez), il s'agit d'envoi d'??v??nements index??s qui se font d'ailleurs passer pour des ??v??nements, comme le montre la doc de [](http://download.oracle.com/javase/1.5.0/docs/api/java/beans/IndexedPropertyChangeEvent.html). Une classe ?? priori innocente, hein ? Seulement, elle ne correspond qu'?? des cas particuli??rement sp??cifiques de collections : des collections o?? les ??l??ments sont accessibles par indice, et des changements de valeurs qui ne concernent qu'une seule valeur. C'est-??-dire que, par exemple, si votre collection est un [](http://download.oracle.com/javase/6/docs/api/java/util/Set.html), vous ??tes roul?? (parce que dans un **Set**, les donn??es n'ont pas d'indice). Et pareil pour une **Map**.Cela dit,  **IndexedPropertyChangeEvent** nous indique clairement comment faire pour ??tendre le m??canisme : il suffit, en fait, d'envoyer des ??v??nements qui sont des sous-classes de **PropertyChangeEvent**, et qui sont adapt??es ?? notre cas.  
Ce qui nous permet d'??crire quelques classes de collections supportant l'envoi d'??v??nements. Bon, je ne vais pas forc??ment vous les d??tailler, ??a fera un peu remplissage. Cela dit, pour l'exemple, voici un petit **PropertySenderSet** (les autres seront tous sur github quand j'aurais r??ussi ?? faire marcher ce ... truc) :  




[https://gist.github.com/1069394](https://gist.github.com/1069394)  
