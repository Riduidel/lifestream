type: post
status: published
title: Transformer un projet ant en artefact maven
tags: ant, groovy, javamaven
date: Fri Oct 07 14:08:40 CEST 2011
~~~~~~
# Transformer un projet ant en artefact maven

Gr??ce ?? mon coll??gue Alexandre, une petite astuce qui pourra un jour vous sauver la vie (moi, en tout cas, elle aurait pu me la sauver).  
Si vous avez d??ja d?? r??cup??rer un JAR dans la nature pour le mettre dans un projet maven, vous savez que ??a peut ??tre gal??re.Toutefois, si ce projet est compil?? avec ant, tout n'est pas perdu. [Eric Hauser](http://erichauser.net/2009/10/26/ant2maven-easy-migration-from-ant-to-maven-with-nexus/) a ??crit [ant2maven](https://github.com/ewhauser/ant2maven) qui, ?? partir du build.xml et d'un peu de configuration, va extraire les d??pendances et construire un pom ?? peu pr??s correct.Et, pour v??rifier que le POM est bien minimal (c'est-??-dire exprime des d??pendances canoniques), maintenant que m2eclipse n'int??gre plus de dependency hierarchy, vous pouvez g??n??rer un graphe des d??pendances avec depgraph (parce que bon, dependency:tree, c'est pas toujours tr??s lisible).