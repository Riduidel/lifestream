type: post
status: published
title: Contiperf
tags: ca sert à presque rien, java, junit
date: Fri Feb 15 17:10:46 CET 2013
~~~~~~
# Contiperf

Vous connaissez, j'en sui sûr, cette phrase de [Jeff Atwood](http://www.codinghorror.com/blog/2011/06/performance-is-a-feature.html)

> Performance is a Feature
Quand j'ai lu cet article (ou peu de temps après, je me souviens plus), je me suis dit - bien aidé par l'article -  si la performance est une feature, alors comme toutes les autres elle doit être testée. Donc il me faut des outils de tests.

Si je faisais seuklement du web qui fait papa-maman, je pourrais me contenter d'un test de performances web : combien de temps pour afficher cette page, etc, ...

Mais je ne fais pas ce genre de développement (plutôt [un truc](http://www.perigee.fr/cmsms/index.php/fr_FR/nos-solutions-new/perigee-digital-express.html) ... compliqué techniquement, mais simple fonctionnellement). Et pour ce truc, réaliser des tests de performances intégrés à maven peut s'avérer délicat. Heureuesement, j'ai découvert le superbe [Contiperf](http://databene.org/contiperf.html) qui permet de réaliser facilement des tests de performance dans JUnit, tout en générant de très beaux rapports de performance. D'ailleurs, je vais m'en servir dans [gaedo](https://github.com/Riduidel/gaedo/issues/52).