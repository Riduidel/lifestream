type: post
status: published
title: Bonnes pratiques du suivi de projet
tags: informatique, web
date: Thu Mar 31 11:40:42 CEST 2005
~~~~~~
# Bonnes pratiques du suivi de projet

Dans le genre bonnes pratiques, [cet article](http://www.jroller.com/page/fra3k/20050330#team_software_development_best_practies) d??finit comment utiliser les outils de gestion de la production de code que sont les gestionnaires de sources, outils de tests et de build. J'en retiens notamment :
* Before considering using an automated build tool it is essential to ensure that the inputs to, and outputs from, the build can cope with the improved build speed. (think of the target infrastructure that cannot keep pace)
* The SCM repository is the slave of the project, not the other way round. It should be solid and reliable, yet flexible enough to accommodate the needs of new projects.
* Everything that can be changed, and affect the behaviour of the application, is a candidate for configuration control. (Source code, Data, Database Structure and Contents, Application Configuration, Environment Configuration)
* The build should tell all project participants what they need to know. (like overal progress tracking, quality reports)
* Automated regression tests for the environment that will compare observed behaviour both before and after changes are made.
* Automate anything that does not require human judgmentvia [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)