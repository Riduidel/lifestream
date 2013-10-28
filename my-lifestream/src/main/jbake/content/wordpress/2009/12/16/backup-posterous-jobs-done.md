type: post
status: published
title: Backup posterous ? Job's done ! 
tags: backup, groovy, posterous, posterousbackup, web
date: Wed Dec 16 17:06:00 CET 2009
~~~~~~
# Backup posterous ? Job's done ! 

Et voilà !  
Après une petite journée de travail, j'ai un script de backup posterous en version "alpha".En fait, je me suis appuyé sur la doc de l'[API de Posterous](http://posterous.com/api/reading) pour écrire un script Groovy qui prend toutes mes entrées, et les stocke dans un dossier de mon choix. Evidement, comme je suis un peu fainéant,c e script ne marche qu'avec un groovy installé sur la machine. Bien sûr, je pourrais packager ça dans un jar qui ne nécessiterait plus que Java. Avec Griffon, je pourrais même en faire une applet ... Mais ça, c'est pour les versions suivantes.  
Pour l'instant, c'est un petit projet qui utilise essentiellement les [expandos](http://groovy.codehaus.org/Collections) (regardez à la fin) et [HTTPBuilder](http://groovy.codehaus.org/modules/http-builder/) pour faire des trucs rigolos.Voici maintenant, dans l'ordre, les développements suivants
1. Backuper les médias joints (images, vidéos, fichiers groovy ...)
2. Créer une UI digne de ce nom pour choisir l'utilisateur, le mot de passe, le dossier de sortie, ...
3. Faire du backup "incrémental" en n'ajoutant que les nouveaux fichiers pour gagner du temps (quoique je ne crois pas que posterous puisse me les trier dans l'ordre de dernière modification, ce qui m'obligera quand même à tout récupérer)
4. Faire un outil à la [webgen](http://webgen.rubyforge.org/) pour créer un site affichant toutes ces entrées avec des tags, une timeline, ...
5. Trouver une licence (pour l'instant, on peut le considérer en [creative commons paternité - pas d'utilisation commerciale - pas de modification](http://creativecommons.org/licenses/by-nc-nd/2.0/fr/))

Comme je suis un mec sympa, le fichier est joint (et aussi pour augmenter ses chances de survivre). Ah, tiens, ça ne semble pas marcher. je vais le mettre ailleurs alors (mais je ne sais pas encore où).