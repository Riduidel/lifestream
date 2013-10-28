type: post
status: published
title: pas si compliqu??, tiens, ...
tags: euler, gpars, groovy
date: Wed Feb 16 12:45:37 CET 2011
~~~~~~
# pas si compliqu??, tiens, ...

Le [probl??me 30](http://projecteuler.net/index.php?section=problems&id=30), sous ses faux airs, n'est pas si compliqu?? que ??a.  


D'ailleurs, la solution est assez triviale :??



[https://gist.github.com/829095](https://gist.github.com/829095)

Il faut d'abord disposer des puissances cinqui??mes (histoire de ne pas les recalculer ?? chaque fois), puis penser ?? trouver une borne suffisament grande. J'ai pris 1000000, mais c'est un pur hasard.J'ai aussi essay?? d'utiliser [GPars](http://gpars.codehaus.org/) pour optimiser un peu le temps de calcul, mais je dois bien reconna??tre que ??a n'a rien donn??. D'ailleurs, si quelqu'un peut m'expliquer pourquoi mon code est aussi lent avec GPars que sans, je suis preneur (et oui, j'ai un double-coeur, donc th??oriquement le temps de calcul de cette partie - la plus co??teuse - devrait ??tre divis?? par deux).Je voulais aussi utiliser [Perf4J](http://perf4j.codehaus.org/) (d'apr??s la suggestion de [Guillaume Laforge](http://glaforge.free.fr/weblog/) dans [les castcodeurs](http://lescastcodeurs.com/2011/02/les-cast-codeurs-podcast-episode-35-leerooooooooy-jenkiiiiiiiinnnns/)), mais comme je n'ai trouv?? aucun article n'expliquant comment l'int??grer dans mes scripts Groovy, j'ai pr??f??r?? m'en passer.