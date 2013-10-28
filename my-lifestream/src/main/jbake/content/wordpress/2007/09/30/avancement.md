type: post
status: published
title: Avancement
tags: code, informatique, lebliki, ruby
date: Sun Sep 30 16:25:16 CEST 2007
~~~~~~
# Avancement

Bon, je viens de faire la demande d'enregistrement du projet chez sourceforge. Maintenant, il faut trouver des implémentations des fonctionnalités dont je disposais en Rails. Surtout que j'ai pris une décision historique, structurante, dingue.Comme dans [yaki](http://code.google.com/p/yaki/), je vais stocker mes données dans des fichiers plats. Grâce à la FAQ, on apprend en effet que Rui considére les éditeurs dans des pages web peu convaincants. Et, à bien y réfléchir, et même si j'édite actuellement une entrée dans une page web, je suis plutét d'accord avec lui. Je veux dire que, même si on peut faire des trucs dans ce genre d'éditeur, rien ne nous empêche de faire de même dans, par exemple, un fichier YAML <i class="footnote">(L'une de mes meilleures découvertes en Ruby, YAML dispose de plusieurs implémentations Java : jyaml et jvyaml. Ce dernier bénéficie de l'avantage énorme de faire partie de JRuby.)</i>. Et puis, une fois que je serais capable de traiter mes fichiers YAML correctement, je pourrais facilement créer une page générant mon fichier YAML. Bon, ça, c'était pour le gros changement. Après, il y a toutes les réimplémentations de la liste des fonctionnalités prévues.

* Un arbre des catégories, qui existe, je crois, dans Wicket, mais pour lequel il ne faudra pas oublier le tag à utiliser dans les pages
* Le support des commentaires et des trackbacks, qui se fait pour sa majeure partie à la main, enfin je crois
* Une administration facile des utilisateurs, des commentaires et trackbacks, des pages
* 'intégration des ASIN d'Amazon <i class="footnote">(Voir par exemple a4j)</i>
* L'i18n, aussi bien pour les articles que pour l'appli. Au moins, pour l'appli, ça a l'air simple : [wicket i18n](http://cwiki.apache.org/WICKET/general-i18n-in-wicket.html)
* La coloration syntaxique multi-langage, grâce à un bout de Javascript trouvé sur google code : [syntaxhighlighter](http://code.google.com/p/syntaxhighlighter/), à moins que ce ne soit [pretify](http://code.google.com/p/google-code-prettify/). Dans tous les cas, c'est vraiment pas compliqué à intégrer (car complétement codé en javascript) et ça, c'est bien !
* L'inclusion de flux RSS (comme ma page LinuxFr) doit pouvoir se faire raisonnablement facilement (voir sur [Java-source](http://java-source.net/open-source/rss-rdf-tools))
* La recherche se fera évidement avec [Lucene](http://lucene.apache.org/java/docs/), j'espére juste qu'il s'intégre bien dans Wicket ...
* lien automatique des articles proches. Comme je ne l'avais pas encore codé en Ruby, je ne sais pas si c'est facile. En revanche, ce que j'avais déja fait, et qui était raisonnablement simple, c'est les liens entrants/sortants.
* Les flux RSS, bon ben c'est comme l'inclusion de sources RSS, sauf que ça a l'air plus facile (voir [code poet](http://www.jroller.com/wireframe/entry/wicket_and_rss_feeds))
* du HTML dégradable pour mon futur n800, ça, c'est plus une façon de faire <i class="footnote">(D'autant moins importante que le n800 utilise Opera8 !)</i>
* Le nuage de tags, c'est pas bien compliqué à faire, ça
* L'authentification, avec Wicket a l'air de se faire assez bien (comme le montre [cet article](http://www.demay-fr.net/blog/index.php/2006/10/15/24-l-authentification-avec-wicket))
* Générer des graphes des liens entre pages, auteurs, commentaires et autres devrait se faire avec une quelconque API de graphe Java, mais je vous en reparlerai plus tard.
* Le CSS (Cascading Style Sheets) se fera comme je l'ai déja dit avec Blueprint
* Et pour OpenID, on verra plus tard.
Bon, ben ça me fait du boulot, tout ça, hein. Alors allons-y !
via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)