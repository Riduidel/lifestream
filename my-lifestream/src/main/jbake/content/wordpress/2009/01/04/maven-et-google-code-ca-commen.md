type: post
status: published
title: Maven et google code, ??a commence ?? rouler 
tags: java, maven
date: Sun Jan 04 21:56:31 CET 2009
~~~~~~
# Maven et google code, ??a commence ?? rouler 

Sur les diff??rents projets que j'ai d??velopp?? en open-source, j'ai toujours utilis?? Google code. Pas pour des raisons philisophico-fanatiques de fid??lit?? absolue ?? Mountain View, mais plut??t parce que 
* la cr??ation de projet y est tr??s facile, 
* il utilise [svn](http://fr.wikipedia.org/wiki/Subversion_(logiciel)) en standard , 
* il y a un wiki int??gr?? (accessible via svn),
* il y a un syst??me de suivi de bugs (interfa??able avec [mylyn](http://eclipse.dzone.com/articles/using-mylyn-with-google-code-u), ce qui est un plus vraiment pas n??gligeable)

H??las, la seule chose qui me manquait, c'??tait la possibilit?? de g??n??rer facilement un jar d??ploy?? dans un repository accessible dans le monde entier.

Mais, gr??ce ?? cette page de [je ne sais quel](http://code.google.com/p/raisercostin/wiki/Maven2DistributionManagementOnGooglecode) projet google, j'ai trouv?? ma solution (qui, comme je l'ai indiqu?? dans la page associ??e a n??cessit?? un peu de travail que je vais devoir refaire).

Et du coup, j'ai d??ploy?? ma version 0.1 sur google tr??s facilement.

Bon, il faut encore que je fouille un peu pour trouver comment uploader automatiquement les sources et la javadoc du projet (histoire de pouvoir bosser proprement, bon, apparement, tout est sur le site de [guice](http://code.google.com/p/google-guice/issues/detail?id=219)), mais ??a commence ?? rouler ...

On verra tout ??a demain, je pense.