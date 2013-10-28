type: post
status: published
title: D??ployer un artefact vers un repository alternatif
tags: java, maven
date: Tue Nov 22 11:21:06 CET 2011
~~~~~~
# D??ployer un artefact vers un repository alternatif

Une petite note pour le moi futur ...  
Le jour o?? je dois d??ployer un artefact vers un repository maven diff??rent de celui indiqu?? dans son "[distributionManagement](http://maven.apache.org/pom.html#Distribution_Management)", il faut "juste" utiliser l'argument "[altDeploymentRepository](http://maven.apache.org/plugins/maven-deploy-plugin/deploy-mojo.html#altDeploymentRepository)" avec un identifiant complet de repository maven.Et si vous voulez savoir pourquoi, eh bien c'est pour pouvoir utiliser le [maven-nar-plugin](https://github.com/duns/maven-nar-plugin) qui (contrairement ?? ce que sa page github indique) n'est pas disponible dans un repository maven dans GitHub (sinon mon Nexus arriverait ?? lire les artefacts dedans).