type: post
status: published
title: M??me quand c'est pas bien, l'open-source, c'est mieux
tags: glassfish, java, maven, opensource
date: Tue Aug 02 13:57:14 CEST 2011
~~~~~~
# M??me quand c'est pas bien, l'open-source, c'est mieux

Parmi toutes les raisons qui me font aimer l'open-source, il y en a une qui, si elle n'est pas souvent utile, peut faire gagner des jours et des jours. Il s'agit de la possibilit?? d'acc??der facilement au code source pour comprendre pourquoi ??a marche (ou pas).  
Tiens, par exemple, le plugin [maven-glassfish-plugin](http://java.net/projects/maven-glassfish-plugin). Il permet th??oriquement de g??rer un serveur Glassfish en ??crivant les informations de configuration utiles dans le pom.xml de maven. Pratique, non ?Je voulais m'en servir pour cr??er un domaine, y cr??er des ressources JMS et y d??ployer mon application web.Seulement, pas moyen de comprendre pourquoi mes ressources ne se cr??aient pas.En jetant ce matin un coup d'oeil dans les sources, j'ai compris : il y a [un bug](http://java.net/jira/browse/MAVEN_GLASSFISH_PLUGIN-9). Et ce bug, sans acc??s aux sources (comme c'est souvent le cas pour les produits commerciaux), je crois que j'aurais vraiment mis beaucoup de temps ?? le d??tecter.Et d'ailleurs, comme vous le voyez, en guise de contribution ?? ce projet, le bug que j'ai trouv??, je me suis permis de le remonter ?? l'??quipe de d??velopper. Parce que pour les d??veloppeurs open-source, le feedback, c'est [important](http://blog.frankel.ch/give-back-to-the-community-please).