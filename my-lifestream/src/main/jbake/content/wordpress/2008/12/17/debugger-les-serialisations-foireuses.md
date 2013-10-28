type: post
status: published
title: Debugger les serialisations foireuses
tags: code, informatique, java
date: Wed Dec 17 15:25:16 CET 2008
~~~~~~
# Debugger les serialisations foireuses

Je sais pas vous, mais moi, les probl??mes de s??rialisation qui foirent, j'aime pas beaucoup ??a.Alors, quand je tombe sur [cet article](http://crazybob.org/2007/02/debugging-serialization.html), je suis content.Et encore plus quand je lui ajoute un log de tous les objets ??crits dans le flux : [JAVA]int currentDepth = currentDepth();             if (returned instanceof IOException && currentDepth == 0) {                 broken = true;             }             if (!broken) {                 truncate(currentDepth);                 stack.add(returned);             }             for(int index=0; indexvia [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)