type: post
status: published
title: hg-git, c'est pas gagn?? ou c'est moi ?
tags: gephi, git, hg
date: Wed Feb 23 13:55:33 CET 2011
~~~~~~
# hg-git, c'est pas gagn?? ou c'est moi ?

Depuis quelques temps, j'ai [une petite id??e](http://riduidel.posterous.com/hg-git-mon-cul-oui), ?? la [gource](http://code.google.com/p/gource/), qui me trotte dans la t??te. j'en avais d??ja parl??, essentiellement pour en profiter pour me plaindre de l'aspect [DIY](http://fr.wikipedia.org/wiki/Do_it_yourself) de [GitHub](http://github.com). Hier, apr??s une journ??e de merge, j'ai replong??. Profitant du fait que j'avais pr??c??demnt install?? [hg-git](http://hg-git.github.com/), j'ai [clon?? (enfin, fork??) chez moi](https://github.com/Riduidel/gexf4j-core) le d??p??t de l'auteur de [gexf4j](http://gexf.net/gexf4j/). J'en ai alors profit?? pour corriger tous les bugs dans les tests unitaires, avant de commiter dans mon d??p??t local et de pousser ces modifications dans mon d??p??t GitHub.  
Et c'est l?? que ??a chie.Parce que, quand je pousse, hg-git me dit

E:java-extgexf4jgexf4j-core>hg push git+ssh://[git@github.com](mailto:git@github.com):Riduidel/gexf4j-core.git  
 pushing to git+ssh://[git@github.com](mailto:git@github.com):Riduidel/gexf4j-core.git  
importing Hg objects into Git  
creating and sending data  
?? ??default::refs/heads/version0-2 => GIT:61a30e14  
 ?? ??default::.have => GIT:b7c0f5d2  
?? ??default::refs/heads/dynFloat => GIT:b7c0f5d2  
?? ??default::refs/heads/v2 => GIT:76262214  
?? ??default::refs/heads/v3 => GIT:cb2f4173  
?? ??default::refs/heads/master => GIT:8f769e5a  


Seulement, quand je regarde dans l'interface web, je ne vois pas mon commit. Bizarre, non ?  
Alors si un quelconque professionnel de GitHub passe par l??, j'aimerais bien qu'il m'explique comment rendre visible mon commit dans mon d??p??t chez GitHub (sans m??me parler de proposer de r??int??grer les changements dans le d??p??t principal) ...  
