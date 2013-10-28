type: post
status: published
title: Une excellente initative
tags: code, informatique, java
date: Thu Jan 06 15:33:41 CET 2005
~~~~~~
# Une excellente initative

Pourquoi les programmes java sont si longs ?? lancer et occupent tellement de place ? Parce qu'avant de se lancer, chaque programme Java lance sa propre instance de la JVM (qu'elle soit Sun ou autre) puis se lance.Ce lancement de la JVM est effectivement obligatoire, mais pas forc??ment au lancement de l'application.Il pourrait avoir lieu par exemple au d??marrage de la machine. Puis, chaque application se connecterait ?? la machine virtuelle d??ja lanc??e pour s'ex??cuter. Dans ce cas, l'utilisateur gagne, pour chaque application Java, le temps de lancement de la JVM et son occupation m??moire.Foin de r??ve ! ce miracle existe d??ja : c'est [NailGun](http://www.martiansoftware.com/nailgun/index.html) qui n'a qu'un inconv??nient (logique). Tout programme Java utilisant dans son code la m??thode [java]System.exit()[/java] arr??te cette JVM serveur. Dommage. Mais il me semble que ??a fait partie des projets de Sun pour l'avenir ...via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)