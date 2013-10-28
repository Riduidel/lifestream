type: post
status: published
title: Est-ce que je peux faire mieux qu'iPhoto ou Picasa ? 
tags: iptc, java, jphotoorganizer, photos
date: Wed Jan 07 13:33:16 CET 2009
~~~~~~
# Est-ce que je peux faire mieux qu'iPhoto ou Picasa ? 

Depuis un certain temps, je carresse l'id??e de me faire mon propre [logiciel de gestion de photot??que](http://code.google.com/p/jphotoorganizer/).

La raison en est simple : si [iPhoto](http://www.apple.com/fr/ilife/iphoto/) est tr??s bien, il n'est pas multiplateforme pour deux sous, et les logiciels windowsiens sont assez loin de son ergonomie (m??me [Picasa](http://picasa.google.fr/) est encore assez loin du compte). Et comme j'abandonne peu ?? peu mon iBook (bien qu'il reste encore une machine ?? surfer tr??s agr??able), il me faut une solution compatible Windows (je me pencherai plus tard sur la gestion de l'iptc dans Vista).  


Bref, je carresse l'id??e.

Seulement, j'ai d??ja ??t?? calm?? en septembre quand Google a ajout?? [la reconnaissance faciale ?? Picasa](http://www.mydigitallife.info/2008/09/03/name-tag-innovation-in-google-picasas-facial-recognition-feature/) (h??las seulement dans les albums web). Et l??, je suis encore plus scotch?? parce que [iPhoto fait la m??me chose](http://lifehacker.com/5124556/does-facial-recognition-change-your-mind-about-iphoto), mais dans le logiciel. E t ??a, ??a fait toute la diff??rence pour quelqu'un comme moi qui veut garder sa photot??que ?? la maison.

Alors bien s??r, comme Rui le dit dans son excellent article [The last macworld](http://the.taoofmac.com/space/blog/2009/01/06/2301), les tags risquent fort d'??tre enferm??s dans la base propri??taire d'IPhoto. Et les tags EXIF risquent ??galement d'??tre dans une belle m??lasse. Mais quand m??me, c'est une sacr??e bonne id??e.

H??las, iPhoto est toujours une application mac-only.

Et ??a, ??a me donne quand m??me du courage.

Parce que si je ne peux certainement pas faire aussi joli qu'iPhoto, je dois quand m??me pouvoir faire quelque chose d'un peu pratique rapidement (en partie gr??ce ?? [majick-properties](http://code.google.com/p/majick-properties/)). ou tout au moins quelque chose du niveau de l'appli de [Belzel](http://belzel7.blogspot.com/) (oui, j'ai un coll??gue qui veut faire un peu le m??me genre de trucs mais en C-Qt ou je ne sais plus quoi) ...*  


Mais avant de recommencer ?? coder (parce que j'avais d??ja fait un premier jet compl??tement bancal), j'ai plusieurs choses ?? faire :
1. Evaluer les biblioth??ques Java de lecture/??criture de tags EXIF/IPTC (j'ai des id??es assez claires l??-dessus).
2. Trouver une bonne interface graphique
3. (optionnel) trouver un moyen de reconna??tre les visages en Java

Enfin, le 3, ??a peut ??tre dans une version suivante, quand m??me ...