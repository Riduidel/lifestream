type: post
status: published
title: Comment bien gérer ses photos ?
tags: 
date: Fri Jul 11 10:23:31 CEST 2008
~~~~~~
# Comment bien gérer ses photos ?

Je ne sais pas si je vous l'ai d??ja dit (en fait, si, je sais), mais depuis quelques temps, je suis pass?? ?? la photo num??rique. Sur la foi d'un [tr??s bon site](http://www.nemodus.com/fz50/fz50.php), par ailleurs bourr?? de d??tails pratiques, j'ai donc fait l'acquisition de ce magnifique Panasonic FZ-50.  


Le point important, c'est qu'avec la photo num??rique, on passe de l'??re mat??rielle ?? l'??re virtuelle.

C'??tait un changement d??licat pour ma femme et moi, qui ??tions plut??t bien habitu??s ?? notre "vieux" [Canon EOS 300](http://en.wikipedia.org/wiki/Canon_EOS_300) (d??sol??, je n'ai pas trouv?? de lien francophone pour cette vieillerie argentique). En effet, les photos num??riques, avant de passer sur papier, peuvent (et devraient, dans l'id??al) passer par une phase de pr??traitement par l'utilisateur beaucoup plus importante. Dans cette phase on trouve des op??rations comme :
* le cataloguage
* la retouche
* et plein d'autres

Pour l'instant, la seule ??tape qui nous int??resse est le cataloguage. Parce qu'on passe doucement au num??rique.

Il y a encore tr??s peu de temps, on utilisait un [iBook G4](http://fr.wikipedia.org/wiki/Ibook) ?? la maison. Et pour ce qui est du cataloguage, mon [Mac OS 10.4](http://fr.wikipedia.org/wiki/Mac_OS_X_v10.4) dispose d'une application bien connue et tr??s pratique : [iPhoto](http://fr.wikipedia.org/wiki/IPhoto). C'est un peu le temple du catalogue facile et notre usage en ??tait assez simple : de retour d'un ??v??nement quelconque, on branchait l'appareil photo en USB, le Mac d??tectait l'appareil photo, lan??ait iPhoto, et je posais tranquillement mes mots-cl??s, mes commentaires, mes titres et mes classements.

Seulement voil??, pour des raisons que je d??velopperais peut-??tre ult??rieurement, ma femme a voulu remplacer cet iBook par un portable sous Windows (en l'occurence, un chouette [Toshiba](http://www.journaldugeek.com/?2008/03/18/10602-toshiba-satellite-u400)). Il a donc fallu que j'exporte ma biblioth??que iPhoto sous Windows, avec toutes les m??tadonn??es qui me permettent de bien cataloguer ma biblioth??que (donc les mots-cl??s des noms des gens sur les photos et autres).

H??las, il n'existe actuellement rien de valable pour exporter ces donn??es d'iPhoto dans un format ouvert (en l'occurence, ici, il s'agit de [l'EXIF](http://fr.wikipedia.org/wiki/Exchangeable_image_file_format))

La mort dans l'??me, j'ai donc entam?? une laborieuse phase de saisie pour recopier des donn??es dont je dispose d??ja dans iPhoto.

Sous Windows, il y a quelques logiciels qui permettent ??a :
* [iTag](http://www.itagsoftware.com/) : super simple, permet raisonnablement facilement de faire de la mise ?? jour en masse, mais d'une ergonomie contestable.
* [Geosetter](http://www.geosetter.de/en/index.html) : complet, mais h??las trop orient?? vers le geotagging (en m??me temps, vu son nom, c'est un peu logique).

Le probl??me, c'est que j'ai d??ja pr??s de 2000 photos ?? retagger. Ca va ??tre trop long.

Surtout qu'en plus, je pars en vacances bient??t, et les photos vont ?? nouveau s'accumuler.

J'ai donc plusieurs solutions, pas forc??ment exclusives :
1. Ecrire un peu de Java pour r??cup??rer les infos du fichier XML de iPhoto pour remettre les tags d??ja existants automatiquement. C'est un peu de boulot, mais ??a ira plus vite que de tout refaire.
2. Trouver un logiciel de d??tection de visages connus, qui me permettre d'affecter les bons tags automatiquement

L'avantage de la deuxi??me solution, c'est que j'ai la garantie d'avoir une liste compl??te des gens sur les photos. L'inconv??nient, c'est que je ne sais pas si elle existe ... Et apr??s quelques rapides recherches, tout ??a semble au mieux exp??rimental. Dommage.

En tout cas, en ce qui concerne l'exif, un des int??r??ts quasi-inutiles que je vois,c 'est qu'en plus des tags pour identifier les gens sur une photo, je vais en plus pouvoir jouer ?? g??ocoder comme un dinge. Je ne sais pas encore si c'est bien pratique, mais, dans une optique tr??s googlienne, je pense qu'augmenter la quantit?? de m??tadonn??es ne peut pas ??tre une mauvaise chose, m??me si c'est du boulot.

En fait, la seule angoisse qui me reste, c'est : est-ce que Picasa lit mes tags exif pour que je puisse rechercher mes photos facilement via des "albums intelligents" ?