type: post
status: published
title: L'adaptation, c'est l'avenir
tags: design, java
date: Mon Jun 27 14:14:00 CEST 2011
~~~~~~
# L'adaptation, c'est l'avenir

Il y a des ann??es, sur le blog de [Manageability](http://www.manageability.org/blog/stuff/adaptor-oriented-programming/view?searchterm=iadaptable), j'ai d??couvert le pattern [Universal Adapter d'Eclipse](http://www.eclipsezone.com/articles/what-is-iadaptable/).  
Pendant des ann??es, j'ai trouv?? que c'??tait de la sorcellerie pure. Sans doute que je n'??tais pas encore all?? assez loin dans les arcanes du Java.En tout cas, il y a deux, j'ai ??t?? confront?? ?? un probl??me (que j'ai h??las oubli??) pour lequel c'??tait une solution pas loin d'??tre parfaite.Alors, avec [mes petits camarades](http://www.dooapp.com/fr)[de jeu](http://www.viadeo.com/fr/profile/nicolas.petitprez) de l'??poque, nous avons d??fini notre propre interface adaptable :



[https://gist.github.com/1048734](https://gist.github.com/1048734)

Comme vous le voyez, elle est deux fois plus grosses que l'interface originale. Elle fournit cependant un gadget bien pratique : la m??thode canAdapt qui nous permet d'??viter la lanc??e d'exceptions tous les quatre matins. Parce qu'en fait, parfois, on veut adapter quelque chose qui n'est pas adaptable. Et dans ces cas-l??, appeler tout de suite getAdapter ne peut conduire qu'?? une chose : une belle exception.

C'??tait bien pratique, mais en fait pas encore assez, puisqu'il manque plusieurs choses :
* 
* 
* un adapteur (au sens MouseAdapter, par exemple) pour nous faciliter la vieJe l'ai impl??ment?? r??cement (mais son code est peut-??tre un peu long). L'id??e est assez simple : si dans votre classe vous ??crivez des m??thodes < **UnType** >  **UnType**  toUnType() {...}, il me semble assez naturel de consid??rer que ces m??thodes sont des adapteurs pour la classe **UnType**, non ? Donc, avec un peu d'introspection, je d??tecte ces m??thodes, je les associe dans une b??te Map ???? **UnType**, et je peux du coup tr??s facilement vous transformer votre objet en un objet **UnType** en vous faisant faire seulement le boulot int??ressant. ??Pratique, non ? 


* Une classe adaptant les inadaptables (??a sonne un peu [@humourdedroite](http://twitter.com/humourdedroite), ??a, non ?)Bon, ??a, c'est vraiment trivial, hein, s'agit d'une esp??ce de classe magique type Registry d'IoC associant des types d'entr??e et des types de sortie. Cet objet serait, ?? grands coups d'appels ?? [reflections](http://code.google.com/p/reflections/), remplie d'instances d'une interface **Adapter** d??finie un peu comme ??a :



[https://gist.github.com/1048747](https://gist.github.com/1048747)

Bien s??r, on y trouverait aussi les adaptations "naturelles" fournies par **Adaptable**. Et, finallement, l'utilisateur pourrait y ajouter les siennes.Avec ??a, les histoires de cast, franchement, ce ne sont plus que des souvenirs. En fait, quand on utilise ??a, la seule chose qui importe, c'est que tous les objets soient adaptables. Et d'un coup, la vie devient plus facile. Je le sais, je l'ai d??ja fait.Ah, vous savez pas ?? quoi ??a sert ? Ben par exemple, vous r??cup??rez un objet du domaine, vous lui demandez si il peut se transformer en JComponent (rassurez-vous, lui ne sait pas faire cette transformation, mais quelque part il y a cette fameuse Registry qui le sait, et elle est inject??e dans votre objet qui s'en sert dans les cas o?? il ne sait pas s'adapter directement). Et d'un coup, votre code devient limpide :



[https://gist.github.com/1048754](https://gist.github.com/1048754)

Enfin, limpide, pour peu qu'il n'y ait pas trop de bordel dans les ??couteurs d'??v??nements du composant repr??sentant l'objet.

La prochaine fois, si j'ai le courage, je vous parlerai de l'??trangement nomm?? **ConfigurationStore**.