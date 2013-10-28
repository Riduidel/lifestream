type: post
status: published
title: [Java]Mylyn, c'est vraiment génial
tags: 
date: Sat Aug 16 11:31:14 CEST 2008
~~~~~~
# [Java]Mylyn, c'est vraiment génial

Vous ne le savez sans doute pas, mais dans ma vie de tous les jours, je suis d??veloppeur Java/Swing.  


C'est un boulot int??ressant, mais parfois assez compliqu??. Car comme tout d??veloppeur, on a des tonnes de fichiers ?? ??diter. Heureusement, pour ??a, on a des tonnes d'outils, dont au moins un mastodonte dans lequel on essaye de faire rentrer tous les autres. Cet outil, c'est [Eclipse](http://fr.wikipedia.org/wiki/Eclipse_(logiciel)). C'est celui que j'utilise presque depuis que j'ai commenc?? le Java (bon, pas tout ?? fait, parce que j'ai commenc?? avec un mdoeste ??diteur de texte).

Mais dans le monde professionnel, on utilise aussi des gestionnaires de bugs, comme [mantis](http://fr.wikipedia.org/wiki/Mantis_Bug_Tracker), ou bugzilla, voire m??me des solutions professionnelles de moins bonne qualit??, mais plus ch??res. Bon, dans ma bo??te, on utilise mantis. Et c'est pas mal. Sauf que quand on corrige des bugs, on passe son temps ?? passer d'un ensemble de fichiers A ?? un ensemble de fichiers B. Et rapidement, on se retrouve avec cinquante fichiers ouverts, on ne retrouve plus ses petits et c'est l'enfer.

Heureusement, [mylyn](http://fr.wikipedia.org/wiki/Mylyn) est arriv??.

Comme le dit la wwikipedia, mylyn vise ?? faciliter la vie du d??veloppeur. Et pour ??a, il y a un truc fabuleux. Tellement fabuleux, m??me, que maintenant, je demande syst??matiquement aux gens de faire des bugs mantis, juste pour pouvoir utiliser cette fonctionnalit??. Il s'agit du contexte. Bon, je ne vous mets pas de liens vers le site d'eclipse, parce que c'est toujours le bazar.

En fait, le contexte, c'est la liste des fichiers ouverts, mais aussi la doc et d'autres trucs. Et l'aspect magiquement puissant de mylyn, c'est que quand on passe d'une t??che ?? l'autre, myly se souvient du contexte de chacune de ces t??ches, et rouvre donc les fichiers associ??s. Je vous aurais bien montr?? des vid??os, parce que ??a se voit mieux en vid??o, mais je n'en ai pas trouv??. La seule chose que j'ai, c'est cet article de [developerWorks](http://www.ibm.com/developerworks/java/library/j-mylyn2/), qui est n??anmoins assez explicite.

Tout ??a pour dire que mylyn, c'est bien. Et en bonus, il y a plein de connecteurs pour se connecter ?? des t??ches ext??rieures (tiens, par exemple, des items dans mantis). Du coup, quand mes coll??gues remplissent leurs bugs dans mantis, je leur associe un contexte (avec les fichiers sur lesquels je travaille pour corriger le bug) et, si il faut travailler ?? nouveau sur le bug (ce qui h??las peut arriver), je peux retrouver le contexte beaucoup plus rapidement.

Et des connecteurs pour mylyn, il y en a de plus en plus, et c'est tant mieux.