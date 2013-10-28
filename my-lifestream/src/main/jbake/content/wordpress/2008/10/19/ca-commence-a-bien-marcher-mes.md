type: post
status: published
title: Ca commence ?? bien marcher mes histoires locales ...
tags: 
date: Sun Oct 19 21:52:08 CEST 2008
~~~~~~
# Ca commence ?? bien marcher mes histoires locales ...

Vous vous en souvenez, j'avais commenc?? ?? jouer avec les flux RSS pour cr??er un site perso qui reprenne toutes les donn??es de mes divers sites web ...

Et ??a marche franchement bien.  


Bien s??r, je n'ai pas encore de flux RSS mais ??a ne tardera plus (gr??ce ?? [webgen](http://webgen.rubyforge.org/documentation/sourcehandler/feed.html), encore une fois).

Par contre, j'ai bien r??fl??chi aujourd'hui, et je ne mettrais pas mon flux Google Reader dans mon site web, parce qu'il me fera une copie du message initial, et donc introduira dans mon site web des donn??es que je n'ai pas ??crites.

Du coup, j'ai impl??ment?? le support de delicious, mais avec une subtilit??.

Il y a dans delicious des ??l??ments que je ne veux pas mettre dans mon site (allez savoir pourquoi ...), et donc je lis le flux associ?? au tag @toblog. Comme ??a, si j'ajoute une url que je veux blogger, il n'y a pas grand chose ?? faire en plus pour que ??a marche. Et comme vous le verrez bient??t sur mon site perso, ??a marche au poil.

Maintenant, il faut que je rende la chose vraiment utilisable.

Et ??a, c'est pas bien compliqu?? : je cr??e une t??che planifi??e dans Windows pour lancer le script Ruby qui va g??n??rer le site web, et apr??s il faut encore que j'uploade les fichiers chez free ... Bon, ??a, c'est un peu moins bien. J'ai bien trouv?? [un peu de doc](http://www.textheavy.com/tutorials/batchfile.html), mais je ne la trouve pas fameusement sympatoche ... Pour tout dire, j'ai m??me l'impression qu'??crire un peu de ruby pour g??rer l'upload FTP apr??s l'appel de webgen, ??a ne sera pas plus compliqu?? que de faire un script batch ...

Du coup, qu'est-ce qu'il me reste ?? faire ?
* le flux RSS
* une r????criture d'URL pour faire en sorte que le maximum de liens restent dans le site web.
* Ce FTP dont je parle plus haut
* Et puis tous les trucs de webgen qui seront si bien : [les tags](http://rubyforge.org/pipermail/webgen-users/2008-October/000328.html), les archives, ...
* Am??liorer les pages des diff??rents sites d'entr??e
* Et peut-??tre am??liorer encore les pages goodreads (parce que j'en ai d??ja 153)