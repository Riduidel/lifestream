type: post
status: published
title: Des nouvelles des properties 
tags: beans, java, majickproperties
date: Mon Dec 22 08:51:54 CET 2008
~~~~~~
# Des nouvelles des properties 

Bon, vous le savez, j'ai entam?? un projet de propri??t??s am??lior??es en Java. Voici donc quelques nouvelles en vrac.
* J'ai am??lior?? la page d'accueil [Google Code](http://code.google.com/p/majick-properties/), histoire que ??a ait l'air moins vide.
* J'ai voulu faire des tests unitaires avec [FEST-mock](http://fest.easytesting.org/mocks/), mais c'est juste une surcouche ?? EasyMock, du coup j'en suis vite revenu ?? jMock, que je connais un tout petit peu mieux.
* En revanche, dans le m??me projet, [FEST-Swing](http://fest.easytesting.org/swing/) est une tuerie. Beaucoup plus agr??able ?? utiliser qu'Abbott, avec juste un petit probl??me de dispose des ressources ?? la fin des test.
* En termes d'avancement, j'ai fini la partie "mod??le", c'est-??-dire d??finir des propri??t??s et faire en sorte que ??a colle bien, et j'ai entam?? la phase "g??n??ration d'interface graphique". Pour l'instant, je g??n??re des ??diteurs pour les textes.

Il me reste quand m??me pas mal de questions.
* Est-ce que je dois rendre mes propri??t??s s??rialisables quand c'est utile ?
* Comment faire pour utiliser au mieux les noms des propri??t??s ? ce qui en soi est un probl??me assez pointu. En effet, je peux utiliser des noms distinct du nom de la propri??t?? en java, mais je trouve que c'est mieux d'avoir un nom bien ancr?? dans le code. Dans ce cas, comment faire pour r??cup??rer une info sur un champ dont je ne connais pas la valeur, ni le nom, et peut-??tre m??me pas le type ?
* Est-ce que je dois rendre mes propri??t??s s??rializables ? Ou externalizables ? A mon avis, oui, mais faut voir ...