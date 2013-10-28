type: post
status: published
title: Rules for personal projects
tags: développeur, maven
date: Thu Jan 17 15:07:59 CET 2013
~~~~~~
# Rules for personal projects

[Rules for personal projects](http://movieos.org/blog/2013/rules-for-personal-projects/ "Rules for personal projects")

Pas mal cet article (trouvé chez [Rui Carno](http://the.taoofmac.com/space/links/2013/01/16/2115)). Si je résume (et que j'adapte ça à mes projets [GitHub](https://github.com/Riduidel?tab=repositories) par exemple), ça donne
* "*There should be a one-line command in the root of the checkout called run that starts the thing and does something useful.*" La plupart du temps je claque ça dans mon build maven avec un profil et une utilisation du [exec-maven-plugin](http://mojo.codehaus.org/exec-maven-plugin/).
* "*There should be a one-line command to grab the current deployed ‘live’ database/state, or something close to it, and pull it to the local machine.*" Je suis plutôt partisan d'avoir les dumps dans un **src/main/resources/whatever**, de façon à pouvoir les transporter facilement. Et, dans l'idéal, ces données sont lisibles à l'oeil nu (pas comme, par exemple, une base neo4j).
* "*There should be a one-line command that deploys the service to live (assuming there is a live).*" **mvn clean install** est mon mantra : une fois que c'est fait, théoriquement, tout doit être installé correctement et pouvoir s'exécuter.
* "*There should be a requirements.txt (Python) or a Makefile.PL (Perl) – or whatever the language I happen to be working in at the time uses as it’s local equivalent – in the local directory, and it should be an accurate and complete list of the project’s dependencies.*" Bon ben ça c'est facile : c'est le rôle initial de maven, donc ça aide. Je vais toutefois ajouter un truc : autant que possible, je vérifie toujours tous les éléments de l'environement à l'aide d'appels à [maven-enforcer-plugin](https://maven.apache.org/enforcer/maven-enforcer-plugin/).
* "*I’m allowed to assume a certain set of local running services. Databases and Redis and a system Python. Everything that’s peculiar to this project needs to be deployed according to Rule 4.*" C'est ce que je dis au-dessus : tout ce qui est supposé exister dans le build **doit** être vérifié avant l'install, et avec un message clair.

Finalement quand je vois ça, je me dis qu'en fait Maven est quand même diaboliquement génial : il offre absolument tous les services de déploiement, de configuration d'IDE, de validation, ... dont on peut rêver.

Alors ...

Pourquoi lui en veut-on tellement ?