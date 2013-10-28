type: post
status: published
title: Une id??e que je n'impl??menterai (peut-??tre) pas 
tags: groovy, id??e, iptc, java, photos, windows
date: Mon Jan 11 10:34:43 CET 2010
~~~~~~
# Une id??e que je n'impl??menterai (peut-??tre) pas 

Ce week-end, j'ai fait un peu de retag de photos (ben oui, c'est indispensable pour avoir une photot??que de qualit??). Donc pour ??a, j'ai lanc?? mon plus tout jeune mais toujours vaillant iView Photo Pro, et je me suis mis au boulot. Et, ?? un moment, en regardant l'onglet [organisation](http://www.digitlife.fr/iv31b-01.jpg), j'ai eu une illumination.  
Parce que dans cet onglet, on voit plusieurs sch??mas d'organisation des photos :  

* par date
* par lieu
* par mot-cl??
* par ??v??nement

Ca ressemble un peu ?? [ce que j'avais vu](http://riduidel.posterous.com/organize-your-pictures-in-5-easy-stepsno-kidd)??il y a un moment. Sauf que l??, c'est d??ja fait par iView gr??ce ?? tout mon boulot.Et donc, j'??tais tranquille, en train de prendre ma douche, quand je me suis dit ?? peu pr??s "mais dis donc, si Iview sait le faire aussi facilement, c'est sans doute raisonnablement facile ?? faire dans du code, et c'est peut-??tre pas la peine de bouger les photos pour les voir selon ces axes d'organisation". Et j'ai eu une sacr??e bon sang d'id??e. Que je vous expose maintenant.Si je cr??e un file system pour [dokan](http://dokan-dev.net/en/)/([mac](http://code.google.com/p/macfuse/))[fuse](http://fuse.sourceforge.net/) qui reprend ces ??l??ments d'organisation, je peux "tr??s facilement" cr??er dans mon Windows un disque dur virtuel dans lequel mes photos apparaissent bien rang??es, m??me si sur le disque dur elles sont en tas, non ?Pour ??a, tout ce qu'il me faut, c'est??
* JDokan
* une librairie de lecture IPTC un peu compl??te (comme par exemple celle de Tidalwave : Mistral)
* Une bonne dose d'huile de coude et de code Groovy/JavaEt avec ??a, je n'aurais plus ?? m'emb??ter ?? d??placer physiquement les fichiers, puisque ce sont leurs m??tadonn??es qui les rangeront automatiquement ! Et m??me ma femme sera contente. En fait, avant ??a, je pense que je vais prototyper le truc avec un disque virtuel posterous ...