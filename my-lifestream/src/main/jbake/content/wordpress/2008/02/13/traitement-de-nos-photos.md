type: post
status: published
title: Traitement de nos photos
tags: dmcfz50, informatique, macosx, photos, web, webgen
date: Wed Feb 13 16:52:09 CET 2008
~~~~~~
# Traitement de nos photos

La semaine dernière, j'ai acheté un super appareil photo numérique ([un panasonic DMC-FZ50](http://www.lesnumeriques.com/article-271-1323-60.html)(Il faut vraiment que je bosse mon plugin ISBN/ASIN, comme ça, je pourrais récupérer toutes les infos depuis Amazon et j'aurais la classe !). Depuis, bien sûr, je ou plutôt nous prenons des photos, et essayons de l'utiliser au mieux. Evidement, ce sont des photos numériques, donc des fichiers, qu'on gère comme tels.

##Importation et visionnage

Comme on a un Mac, ça passe par l'importation des photos dans [iPhoto](http://fr.wikipedia.org/wiki/Iphoto) qui est vraiment très bien pour ranger les photos, leur mettre des mots-clés (grâce à [Keyword assistant](http://homepage.mac.com/kenferry/software.html), en particulier). Hélas, [il semble](http://the.taoofmac.com/space/blog/2006/09/10) que ces mots-clés, et la plupart des autres informations, soient stockés dans la base de données iPhoto, et non dans les métadonnées [EXIF](http://fr.wikipedia.org/wiki/Exchangeable_image_file_format). Et ça, c'est mal (Je ne devrais pas avoir besoin d'en dire plus que ça. Mais je vais quand même m'étendre un peu sur le sujet. Un jour ou l'autre, j'abandonnerai iPhoto. Et ce jour-là, j'aurais l'air fin, avec toutes mes photos dont une partie des informations sont dans ces métadonnées, et le reste dans iPhoto. Et puis, déja aujourd'hui, je voudrais faire d'autres choses avec ces photos, et pouur ça, garder les métadonnées, c'est bien. Bref, les métadonnées EXIF, c'est plus interopérable que la base de données iPhoto.). Mais on verra plus loin comment contourner le problème. En tout cas, je peux vous dire que créer un diaporama avec les photos, c'est d'une facilité redoutable, et en plus c'est beau. Du coup, on a déja pu épater notre monde avec notre vieil iBook.

##Exportation et backup

Parce que bon, regarder les photos dans iPhoto, c'est bien. Mais pouvoir garantir qu'elles survivent à un crash, ce serait mieux. Et les avoir sius la main sans avoir l'iBook dans le sac à dis, c'est mieux.

###Webgen à la rescousse

Heureusement, webgen supporte un mode de création de gallerie, dans lequel je vais me plonger. L'idéal, ce serait de simplement lui fournir les photos brutes (Donc du 10Mpixel en jpeg), et qu'il génère les tailles d'images utiles avant de tout uploader sur un site web bien choisi. Hélas, pour ça, je dois lire mes données d'iPhoto, transférer les photos de l'iBook vers le portable PC, etc, ... Mais je commence à avoir une idée un peu claire des étapes d'exportation.
1. Lancer un script Ruby sur l'Ibook, pour collecter les infos de la librairie iPhoto et les remettre dans les tags EXIF. A mon avis, ça va se faire avec toute une ribambelle de gems : [iphoto2](http://www.gemtacular.com/gems/iphoto2), une librarire d'écriture EXIF (mais ça, [il y en a](http://rubyforge.org/search/?type_of_search=soft&words=exif&Search=Rechercher)[plein](http://raa.ruby-lang.org/search.rhtml?search=exif))
2. Copier les photos d'une machine vers l'autre
3. Générer le site avec webgen, et avec un bon template de gallerie, pour que ça ait de l'allure.

##Impression

Evidement, on ne prend pas assez de photos pour que ça vaille financièrement le coup de les imprimer à la maison (Tout du moins pour la qualité des photos qu'on souhaite obtenir.). Donc on a cherché quelques pistes pour les faire imprimer par des studios sur internet. Pour l'instant, nous avons sélectionné
1. [photostation](http://www.photostation.fr/tirages/index.cfm)
2. [photoweb](http://www.photoweb.fr/produit/tirage/eco/tirage-economique.asp)

Mais comme on n'en a pas encore imprimé, tout ça reste de l'ordre du virtuel ...via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)