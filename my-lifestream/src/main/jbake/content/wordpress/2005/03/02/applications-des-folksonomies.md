type: post
status: published
title: Applications des folksonomies
tags: informatique, leblog, pourlouvreboite, web
date: Wed Mar 02 16:21:49 CET 2005
~~~~~~
# Applications des folksonomies

Suite à un trés long et encore plus intéressant [article](http://louvre-boite.viabloga.com/news/18.shtml) de {L'ouvre-boîte}, voici mes commentaires.


> En avant-propos, une représentation graphique d'un espace de tags del.icio.us par Alf Eaton.
J'aime bien cette exploitation des tags delicious, mais je lui préfére cependant [celle-ci](http://www.kevan.org/extispicious.cgi?name=riduidel). Pour aller plus loin, la visualisation simple d'un graphe de tags delicious me paraît assez peu pertinente. J'aurais tendance à lui préférer une [représentation hyperbolique](http://pro.chemist.online.fr/cours/rep1.htm) (qui s'apparente plutét dans ce document à un Fish Eye), qui permet d'avoir les points essentiels nettement plus visibles.


> Et quelques remarques :- La représentation / exploration graphique (recherche, métadonnées) est une nécessité soulignée dans de nombreux billets sur Explorations et sur l'ouvre-boîte.
Et assez évidente., même si delicious permet de lister les liens associés à un ou plusieurs tags (par le biais, par exemple, d'un lien ``).


> - Le graphique d'Alf Eaton est basé sur l'ancêtre (sic) TouchGraph, qui avait déjé proposé un GoogleBrowser aux fonctionnalités proches de celles de Kartoo, et aussi un LivejournalBrowser, une intégration avec une encyclopédie, un WikiBrowser, etc. (allez voir !). Vous pouvez aussi retrouver Touch Graph sur SourceForge. Il y a une littérature abondante sur les représentations visuelles, et de nombreux projets [1] dont par ex. prefuse (Java), ou encore FlickrGraph rçalisé en Flash.
Donc une utilisation efficace de Java pour produire autre chose que des pages à la noix ! Formidable ! (c'est le développeur Java qui parle).


> - Alf a aussi rçalisé une cartographie des utilisateurs del.icio.us ("users as defined by their inbox subscription lists"). Bref, vive les API pour construire des Web Services ...
Il existe aussi, parmi [les innombrables outils delicious](http://pchere.blogspot.com/2005/02/absolutely-delicious-complete-tool.html), [un site](http://gre.gario.us/) permettant d'afficher les gens dont on est proche car taggant de manière proche les mêmes sites.


> Et si cela ne suffit pas :-) pour donner des idées aux membres de l'ouvre-boîte pour améliorer blogs, wikis, logos-puces, to-do lists, recommandations, sites sociaux, (etc.)... allez, une petite citation extraite de LivejournalBrowser :The Touch Graph Live Journal Browser displays users as nodes connected by edges indicating friendship. Above the users float their mutually shared interests. Moving the mouse over an interest highlights the users that share that interest, and moving a mouse over a user highlights the friends and interests of that user. By examining at the interests above and between clusters one can see the subjects that bring together individuals and communities.
Ca ressemble d'assez prés à un projet de [navigateur FOAF](http://www.foafnaut.org/), permettant donc de se promener dans un réseau social, pour peu que les gens publient leur FOAF.


> Une vraie soupe de tags , c'est le risque que soulignent de nombreux commentateurs, dont Stéphane Le Solliec dans cet article. "Oui, sauf qu'au final, c'est ça donne un gros bordel avec des tags, des catégories, des mots-clés dans tous les sens, ... rien n'est inter-opérable, et l'on reste dans un univers plat, où si je cherche Indochine, j'aurais autant de réponses concernant le groupe de rock que le pays".
Ce qui n'est pas interopérable, ce sont les web-services d'exploitation. **Rien** n'empêche une bande de courageux développeurs d'écrire la glue permettant, via une interface unifiée, d'accéder, à la delicious, à des liens vers tous les outils connaissant tel ou tel tag, avec même la mise en relation. Bref, le vrai moteur de tags.


> Par exemple, on pourrait utiliser des tags en deux parties. Cette idée a été exprimée de nombreuses fois. Par ex. sur wixonomy on la met en action, et Stéphane Le Solliec la décrit ainsi : "(exemples :) groupe:indochine et pays:indochine, groupe:Frantz Ferdinand et personne:Frantz Ferdinand, (...) Avec ces tags en deux morceaux, cela donne la possibilité de faire des listings genre listing de tous les groupes, listing de toutes les personnes, listing de toutes les villes".
Jusqu'au jour où on constatera qu'il faut un troisième champ, pour une raison X ou Y. Il y a lé une espèce de mauvaise pratique, à mon sens, même si, fondamentalement, le concept de folksonomie implique l'adaptation à l'utilisateur. Pour ma part, pour distinguer les duplicats, je rajoute des tags. Par exemple, une page sur le groupe sera taggée "musique indochine" et le site touristique du pays "tourisme asie".


> Mais on peut faire mieux que des listes ... On peut désambiguiser. On peut rechercher des significations univoques (autrement dit, à partir des folksonomies, personnaliser et partager des taxonomies).
Il y a là un point intéressant : on ne parle ici "que" de tags. Nous oublions les tous premiers fournisseurs de données et de meta-données : les pages web taggées. Qu'il s'agisse de liens delicious, d'actions 36trucs, ou d'autres choses (je ne suis pas trop au fait des autres ouvertures de boîtes), il y a toujours, quelque part, une donnée à laquelle le tag se réfère. Pourquoi ne pas s'en servir ?


> Imaginez un graphe dont les noeuds soient des vocables (tags). Les arêtes reliant ces noeuds sont des relations entre les vocables (tags). En première analyse, il n'est pas nécessaire de caractériser (par ex. : égalité, inclusion, etc.) ces relations.
En seconde non plus. Si on caractérise ces relations, on va passer d'un type de géométrie relativement utilisable à un autre nettement plus complexe.


> où et quand opérer des renforcements ? Exemple d'un site de signets partagés Cela peut être fait lors de l'utilisation d'un bookmarklet. L'interface demanderait quel(s) tag(s) utiliser, interrogerait ensuite le site de signets, puis présenterait les tags liés, et de quoi renforcer (ou ajouter) des liens.
Ou mieux, dans un navigateur qui pourrait par ce moyen proposer des sites au contenu proche de celui lu actuellement. une espèce de super-assistant qui dirait "ça aussi, ça peut vous intéresser", à la manière des références d'autres acheteurs qu'on peut trouver sur des sites marchands bien connus.


> Cela peut aussi se faire (avec probablement un GUI plus riche et plus de features) sur le site de signets. Sur ce site, la description des signets comporterait une liste de tags avec leur poids, et si possible indiquerait quels tags sont insuffisamment liés (incitation aux visiteurs du site à travailler la question) Une page relative à un tag, ou à un utilisateur, présenterait une liste de tags "à lier" (sorte de to do) ainsi que les graphes des tags liés, etc.
Ca me donne bien envie d'essayer un truc en Javascript, ce bazar. Quoique produire un diagramme cross-browser en Javascript ... c'est plutôt chaud !


> ce qui permettrait d'ailleurs un couplage potentiel avec un service de to do partagés comme 36 trucs).
Le couplage de sites de ce type avec d'autres applications web me paraît indispensable. Si l'idée de partager ses trucs est intéressantes, se contenter de ça est à mon sens trop limité.


> Il est évident qu'on approfondit considérablement les usages des folksonomies ou taxonomies si on peut conférer des attributs aux relations. La relation a::b peut, par exemple, être dotée de l'attribut "
Encore une fois, si l'exploitation de concepts simplement proches est relativement aisée, l'utilisation de caractérisations de cette proximité rajoute un nombre de règles complètement ahurissant, mais fournit cependant une richesse fonctionnelle évidente.


> Puisque le business model du search est bien établi (encore que ...), un enrichissement des services de recherche (pertinence, matching) par des technologies de tags se fera dans le cadre de ces business models.
C'est exactement ce que fait [Zniff.com](http://zniff.com/), qui doit être la partie rentable de Spurl (qui n'a du coup pas à investir dans une technologie de crawling chère et parsant de nombreuses pages inutiles).


> Ceci (puisque Seb Paquet le fait, l'ouvre-boîte aussi :-) ) est un appel à développeurs pour implémenter (Open Source, merci; que cela puisse librement être amélioré, intégré ...) dans un Web Service l'exploration et le renforcement des chemins dans un graphe. En commençant par le plus simple : des relations "non caractérisées" (voir plus haut).
Qui héberge le projet ? Sourceforge (solution évidente) ? Et qui l'administre et le fait vivre ?


> On pourrait commencer, en francophonie (ou européanité), par un GIE de maquettage de services innovants (On sait ce qu'il est advenu du GIE Airbus :-) ).
Société de R&D externalisée ?
via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)