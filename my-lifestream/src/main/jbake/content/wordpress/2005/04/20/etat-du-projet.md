type: post
status: published
title: Etat du projet
tags: bookmarcs, code, informatique, ruby
date: Wed Apr 20 10:51:56 CEST 2005
~~~~~~
# Etat du projet

A l'heure actuelle voici ce que sait faire boo.km.arcs :
* Lire et ??crire les favoris d'IE
* Lire et ??crire les bookmarks Opera
* Lire les liens delicious - par le biais du fichier RSS public, on n'a donc que les 30 derniers
* Lire les liens blogmarks - par le biais du fichier RSS publicEt donc, voici ce qu'il me reste ?? faire avant d'en avoir fini avec ce projet rigolo :
* Lire et ??crire les favoris de Firefox
* Ecrire les liens delicious (??a n??cessite une authentification HTTP)
* Ecrire les liens blogmarks (??a n??cessite une authentification HTTP)
* R??ussir ?? s'authentifier en HTTP avec du Ruby. Et ??a, ??a ne m'a pas l'air ??vident : je dois r??cuper le code r??ponse du Net::HTTP#get, v??rifier qu'il s'agit du bon, soumettre le couple login/pwd encod?? sous la bonne forme, et r??cup??rer ma r??ponse.
* Finir la d??duplication qui plante un peu (une sombre histoire de r??f??rences que j'utilise mal, ?? mon avis).via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)