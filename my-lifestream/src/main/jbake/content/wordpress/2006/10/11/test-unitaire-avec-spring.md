type: post
status: published
title: Test unitaire avec Spring
tags: code, informatique, java
date: Wed Oct 11 11:14:00 CEST 2006
~~~~~~
# Test unitaire avec Spring

Pour ceux qui écrivent une application basée sur Spring, et qui se demandent comment faire des tests unitaires, c'est très simple. Il vous suffit de créer une classe dérivée de [TestCase](http://junit.sourceforge.net/javadoc/junit/framework/TestCase.html "TestCase (JUnit API)") et de modifier la méthode setUp de la manière suivante :



[https://gist.github.com/266117](https://gist.github.com/266117)

Et bien sûr, si vous écrivez une classe de test dérivant de ce pattern, vous surchargez la méthode setUp pour récupérer le bean à tester.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)