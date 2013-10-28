type: post
status: published
title: Int??grer des tests externes dans Hudson 
tags: groovy, hudson, webservice
date: Tue Feb 16 10:33:31 CET 2010
~~~~~~
# Int??grer des tests externes dans Hudson 

Parfois, dans le monde de l'entreprise, les choses ne sont pas roses.On n'a pas forc??ment de code Java sous la main, et les tests unitaires n'existent pas vraiment (alors l'int??gration continue, c'est plus du domaine de la science-fiction qu'autre chose).C'est ce qui m'est arriv?? quand je suis arriv?? chez [Perigee](http://riduidel.posterous.com/et-cest-reparti-pour-un-tour).Je suis pass?? ?? un autre langage sans tests unitaires, et donc sans int??gration continue.Au bout d'un moment, j'en ai eu assez. J'ai donc d??velopp?? un ??quivalent r??duit de [jUnit](http://www.junit.org/) version 2 ou 3. La seule diff??rence avec jUnit, c'est que mon ??quivalent produit "spontan??ment" le fameux [rapport XML](http://stackoverflow.com/questions/442556/spec-for-junit-xml-output). Et ce rapport peut ??tre r??cup??r?? via un appel ?? un web service local. Une fois qu'on a ??a, j'ai envie de dire que l'int??gration continue est au coin de la rue : il faut "juste" un client web service qui stocke ce fichier XML dans [Hudson](http://hudson-ci.org/) ...Ce qui se fait assez bien en groovy avec [GroovyWS](http://groovy.codehaus.org/GroovyWS), comme le code ci-dessous le montre :



[https://gist.github.com/305411](https://gist.github.com/305411)  


Il y a plusieurs "trucs" ?? noter dans ce script groovy :
* D'abord, GroovyWS est utilis??e avec [grape](http://groovy.codehaus.org/Grape), ce qui fait que les d??pendances sont t??l??charg??es ?? la premi??re ex??cution (ce qui est terriblement long).
* Ensuite, GroovyWS est en fait, d'apr??s cve que j'ai compris, une surcouche d'Apache CXF (c'est ce qui fait que je n'ai pas ?? d??clarer de classe c??t?? client avant d'utiliser le web service, je crois)
* Enfin, le fichier XML cr???? localement doit ??tre indiqu?? ?? Hudson, via l'option "[Publier le rapport des r??sultats des tests JUnit](http://blog.zenika.com/index.php?post/2009/03/05/Int%C3%A9gration-de-CppUnit-dans-Hudosn-%E2%80%93-Part-1)"

Et une fois que c'est fait ? Eh bien, une fois que c'est fait, Hudsonv a r??guli??rement interroger notre application non Java pour r??cup??rer les r??sultats des tests unitaires. C'est puissant, et ??a marche franchement bien.