type: post
status: published
title: La meilleure façon de rater son projet grâce à Maven2
tags: java, maven
date: Fri Sep 04 17:09:17 CEST 2009
~~~~~~
# La meilleure façon de rater son projet grâce à Maven2



A chaque fois que je vois un développeur Java passer son temps à regarder Maven tourner, j'y vois une perte de productivité.
D'autant plus que je vois très régulièrement cette tendance dans les projets, au sein d'OCTO et même dans ma propre utilisation.
Attention, je pense que Maven est très efficace pour améliorer la productivité des développements, mais le plus gros piège reste de passer son temps dans la console.

**Pourquoi c'est mal ?**
Quand on s'occupe de faire marcher Maven en bidouillant le POM et en regardant le build tourner dans la console : on ne développe pas.
Lorsqu'on développe, on doit passer son temps dans son IDE (eclipse, InteliJ, netbean, notepad ou vi selon les gouts) et coder du code, des tests.

<img alt="maven is building" height="360" src="http://blog.octo.com/wp-content/uploads/2009/09/maven-building.png" title="maven is building" width="413"></img>*Cette image est un détournement, voici l'image originale* : [http://xkcd.com/303/](http://xkcd.com/303/)**Alors pourquoi on passe son temps dans la console ?**
En cherchant bien, j'en viens à la conclusion que la principale raison pour laquelle je passe mon temps dans la console est que mon IDE n'est pas bien configuré : un build dans mon IDE produit un résultat différent d'un build Maven, je ne fais plus confiance à mon IDE et la seule façon d'être sûr que mon code marche c'est de lancer la commande "mvn install" et d'attendre le message "Build Successfull". Un cas typique est l'utilisation du filtering des ressources par Maven qui n'est pas géré nativement par Eclipse (le plugin m2eclipse résout ce problème).

**Oui, mais ça ne marche pas : les tests dans mon IDE ont un comportement différents que dans Maven**
Dans ce cas, je n'ai qu'un seul conseil : faites le marcher !
Votre configuration doit être assez bonne pour que quand vos tests passent dans l'IDE, vous devez être assez confiants pour pousser votre code dans votre gestionnaire de source.
Si vraiment il y a un problème, [l'intégration continue](http://martinfowler.com/articles/continuousIntegration.html) va vous prévenir.

Lancer un build Maven sur votre poste et bidouiller vos configurations (Maven ou IDE) doit être rare :

* quand l'UDD a retourné une erreur et que vous avez diagnostiqué que le problème vient d'une configuration
* quand justement vous avez modifié votre POM (pour rajouter un plugin, ajouter un module, etc.)
Ces 2 cas doivent rester marginaux :

* si votre UDD vous retourne constamment des erreurs de configuration, prenez le temps de les corriger durablement
* si vous modifiez trop souvent vos POM, il est surement temps de prendre un peu de recul et de stabiliser la configuration des projets.
En résumé : **Ne passez pas votre temps dans la console Maven, passez-le à coder dans votre IDE**via [blog.octo.com](http://blog.octo.com/la-meilleure-facon-de-rater-son-projet-grace-a-maven2/)
C'est marrant, ça, mais chez IT-Finance, on a passé pas mal de temps (trop, peut-être ?) à jouer avec le script maven qui permettait de générer l'application. Et bien des fois, j'ai suggéré à mes collégues de réfléchir à notre usage de maven. Est-ce qu'on n'essayait pas d'en faire trop avec maven ? Est-ce qu'on faisait les choses dans le bon sens avec cet outil qui, définitivement, n'est pas une silver-bullet ? Est-ce qu'on n'aurait pas dé rester avec ant (et éventuellement, utiliser un outil séparé de gestion des dépendances comme ivy) ? Je n'aurais sans doute jamais la réponse à cette question. Ce que je sais, en revanche, c'est que c'est encore un domaine où, peut-être sans même sans rendre compte, on réinventait beaucoup de choses.

