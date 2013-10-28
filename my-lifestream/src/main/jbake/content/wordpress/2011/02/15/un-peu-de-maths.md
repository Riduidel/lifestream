type: post
status: published
title: Un peu de maths ...
tags: euler, groovy
date: Tue Feb 15 20:34:44 CET 2011
~~~~~~
# Un peu de maths ...

Bon, histoire de changer, je me suis dit (pendant que j'enquillais les longueurs de piscine) que [le probl??me 28](http://projecteuler.net/index.php?section=problems&id=28) ??tait facilement abordable d??s lors qu'on y r??fl??chissait un peu (comme c'est en fait le cas pour tous les probl??mes, mais la plupart du temps je pr??f??re ne pas y penser et coder comme un goret).  
Regardons donc un peu les donn??es dont nous disposons :Si on extrait de la spirale initiale

**21** 22 23 24 **25**  
 20 ??**7** ??8 ??**9** 10  
19 ??6 ??**1** ??2 11  
 18 ??**5** ??4 ??**3** 12  
**17** 16 15 14 **13**  


Les diagonales dans leur ordre d'??criture direct, on obtient ??a (j'ai ??t?? un peu plus loin pour mieux valider mes hypoth??ses)  


?? ?? ?? D1 D2 D3 D4 Somme  
ligne?? 1?? 1 ??1 ??1 ?? 41 ?? ???? 3?? 5 ??7 ??9 ??242 ?? ?? 13 17 21 25 ??763 ?? ?? 31 37 43 49 160  
4 ?? ?? 57 65 73 81 276  


Est-ce qu'il y a l??-dedans quoi que ce soit qui vous saute aux yeux ?  


Personnellement, j'ai tout de suite remarqu?? deux choses  

1. A chaque ligne l'??cart entre les diff??rentes est constant. Il est ??gal ?? [2(n-1)](http://www.wolframalpha.com/input/?i=2%28n-1%29).
2. Les valeurs de la derni??re colonne sont des carr??s de nombre impair. D'ailleurs, leurs valeurs correspondent pr??cis??ment aux valeurs de la [suite](http://fr.wikipedia.org/wiki/Suite_d'entiers)[(2n-1)??](http://www.wolframalpha.com/input/?i=%282n-1%29%C2%B2). En passant, [WolframAlpha](http://www.wolframalpha.com/), les jours on on veut faire un peu de maths facilement, c'est trop pratique.Du coup, partant de ces deux constatations, je peux r????crire mes diagonales sous des formes plus comrpess??es :

D4 = [(2n-1)??](http://www.wolframalpha.com/input/?i=%282n-1%29%C2%B2)D3 = [(2n-1)??-2(n-1)](http://www.wolframalpha.com/input/?i=%282n-1%29%C2%B2-2%28n-1%29)D2 = [(2n-1)??-4(n-1)](http://www.wolframalpha.com/input/?i=%282n-1%29%C2%B2-4%28n-1%29)D1 = [(2n-1)??-6(n-1)](http://www.wolframalpha.com/input/?i=%282n-1%29%C2%B2-6%28n-1%29)

Du coup, la somme de ces diagonales peut s'??crire [4(2n-1)??-12(n-1)](http://www.wolframalpha.com/input/?i=4%282n-1%29%C2%B2-12%28n-1%29). Enfin, la somme pour un n donn??, hein. Une somme qui peut d'ailleurs se simplifier en 16n??-28n+16.  
Ce qui me donne, vous l'admettrez, une fa??on rapide et efficace de calculer cette sommme : [sum_(n=1)^3 (4(2n-1)??-12(n-1)))-3](http://www.wolframalpha.com/input/?i=sum_%28n%3D1%29%5E3+%284%282n-1%29%C2%B2-12%28n-1%29%29%29-3)  
Bon, je reconnais que l'??criture dans WolframAlpha est assez peu intuitive. Cela dit, ??a vous donne une id??e du total. Et le "-3" ?? la fin permet de s'affranchir du fait que 1 est compt?? 4 fois dans la formule (une fois pour chaque diagonale).  
Je pourrais donc faire tout ce calcul dans WolframAlpha, simplement en rempla??ant ^3 par [^501](http://www.wolframalpha.com/input/?i=sum_%28n%3D1%29%5E501+%284%282n-1%29%C2%B2-12%28n-1%29%29%29-3). Mais je vais quand m??me poser le one-liner Groovy qui fait tout ??a, toujours pour le fun.  
Bon, c'est pas vraiment un one-liner (honte ?? moi), mais le code est quand m??me assez court :  




[https://gist.github.com/827655](https://gist.github.com/827655)  


Avec, je trouve, une utilisation sympa de [Collection#sum(Object, Closure)](http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html#sum(java.lang.Object, groovy.lang.Closure)).  
