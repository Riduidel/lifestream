type: post
status: published
title: Am I working in the seventies ?
tags: informatique
date: Tue Mar 01 12:35:00 CET 2005
~~~~~~
# Am I working in the seventies ?

  Pour le projet sur lequel je travaille, nous utilisons PVCS comme gestionnaire de source (c'est le standard du client). Malheureusement, la gestion des versions, et plus spécialement la création de nouvelles versions de notre produit, n'est pas simple. Le build peut se décrire de la manière suivante :
1. Conserver une liste de tous les fichiers ajoutés/supprimés/modifiés.
2. Quand vient le temps d'une nouvelle version, envoyer cette liste au chef de projet.
3. Là, il doit se mettre à bosser !Il doit labeller tout le projet, puis faire un get complet et, parmi les fichiers récupérés, supprimer les fichiers supprimés. Malheureusement les fichiers ajoutés ne sont pas pris (PVCS ne peut pas labeller des fichiers qui ne l'ont jamais été). Donc il doit aussi récupérer les nouveaux fichiers, les labeller et finalement compiler sa version (ce qui se fait sans Ant).

Donc nous cherchons un moyen de simplifier ça. Evidement, je voudrais faire tout ça avec Ant. Mais il ne semble pas exister de tâche Ant pour PVCS capable de faire un get des fichiers récents. De plus, comme [la base de savoir PVCS](http://www.pvcs.synergex.com/kb/pvcskb--31.asp) l'explique :

> NOTE: If you delete the archive from the directory, you will not be able to recover it at a later date.  We suggest you copy it into a directory of unused files or do a system back up.

Donc il est impossible de supprimer une classe utilisée par une version précédente ? Suis-je de retour dans les années 70 ? Cauchemar !via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)