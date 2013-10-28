type: post
status: published
title: Leçon du jour
tags: flex, java, maven
date: Fri Dec 09 12:01:45 CET 2011
~~~~~~
# Leçon du jour

Disclaimer : attention, aujourd'hui, ça tâche.Supposons que vous deviez pour votre travail (personne ne ferait ça par plaisir), écrire une extension Adobe CS hybride (donc contenant un peu de Flex, et un peu d'autres choses). Bon, déja, dans Flash Builder, [c'est pas facile](http://www.adobe.com/devnet/creativesuite/articles/hybrid-extensions.html). Mais alors en-dehors, c'est l'enfer. La doc d'Adobe est au mieux pas terrible, et il en manque des morceaux entiers.Heureusement, la mauvaise foi de certains développeurs (comme moi) ests ans égale.Sachant que Flash Builder est un (mauvais) IDE écrit en java sur une base Eclipse, comment faire pour savoir comment il crée une extension Creative Suite ? Ben en allant fouiller dans l'installation !<img alt="Quel_bordel_flash_builder" height="299" src="http://riduidel.files.wordpress.com/2011/12/quel_bordel_flash_builder-png-scaled-500.jpg?w=300" width="500"></img>Tout de suite, on remarque
1. que c'est un beau bazar
2. qu'il y ça un joli dossier "com.adobe.cside.exportwizard_1.5.0.201103311826" qui semble correspondre pile-poil à cette fonctionnalité " [export as ZXP](http://cssdk.host.adobe.com/sdk/1.0/docs/WebHelp/getting_started/Deploying_the_Extension.htm)"N'écoutant que le malin en moi (et mon droit au reverse engineering), j'ai donc exploré le contenu de ce dossier, pour y trouver .... des classes Java. Et franchement ...

> ahlala, chez adobe, ils sont pas bien forts : leurs plugins Eclipse sont livrés sous forme de dossiers de .class, une
vraie invitation !

Nicolas Delsaux (@riduidel)
[December 9, 2011](https://twitter.com/riduidel/status/145087962655371264)J'ai donc lancé [jd-gui](http://java.decompiler.free.fr/?q=jdgui) dans ce dossier pour comprendre comment une extension"pur Flex" et une extension "hybride" était construite.Et le code est assez limpide : c'est la classe com.adobe.cside.exportwizard.view.ExportWizard.DoExport qui fait tout le boulot :<img alt="Java_decompiler_-_exportwizard" height="286" src="http://riduidel.files.wordpress.com/2011/12/java_decompiler_-_exportwizard-png-scaled-500.jpg?w=300" width="500"></img>Plus précisément, on peut distinguer deux parties : la génération d'une extension "pure flex" et la génération d'une extension hybride.Pour une extension Flex (la méthode generateZXP), on va construire un dossier contenant
* le dossier bin-debug
* le dossier .staged-extension/CSXS
* toutes les librairies swcAvant d'invoquer sur ce dossier le fameux "UCF" qui permet de signer le dossier de sortie[](http://riduidel.files.wordpress.com/2011/12/et_pouf_ucf.png)Bon, par contre, pour les extension hybrides, c'est un peu plus chaud ...Enfin, pas tant que ça : on copie les différentes ressources définies dans le [MXI](http://help.adobe.com/en_US/extensionmanager/cs/using/WSef3735c8b4d78bef5dd58210e53c97942-7fe9a.html) dans le dossier de sortie, on signe le tout, et basta !Bon, maintenant, évidement, j'ai plusieurs questions :
* Vous voulez vraiment open-sourcer Flex ? Vu comment ça m'a l'air fait par des demis-branquignols, ça risque d'attirer les foules ... de moqueurs
* C'était vraiment nécessaire de tout faire pour étre anti-intégration continue ?
* A votre avis, il faut combien de temps pour refaire ça dans un plugin maven ?