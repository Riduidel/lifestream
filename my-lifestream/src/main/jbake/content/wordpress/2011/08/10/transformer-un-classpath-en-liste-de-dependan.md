type: post
status: published
title: Transformer un CLASSPATH en liste de d??pendances maven ?
tags: classpath, groovy, maven
date: Wed Aug 10 17:42:08 CEST 2011
~~~~~~
# Transformer un CLASSPATH en liste de d??pendances maven ?

Aujourd'hui, j'ai eu un petit truc p??nible ?? faire.  
J'avais un jar venu ... de l'ext??rieur ... que je souhaitais mettre dans mon repository maven d'entreprise. Bien s??r, les choses auraient ??t?? bien plus simple si un artefact avait ??t?? disponible, mais h??las, ??a n'??tait pas le cas (apparement pour des raisons commerciales).Donc, j'avais ce jar , dans un dossier lib, accompagn?? d'une palanqu??e d'autres jars de provenances diverses (jetty, log4j, jUnit, Jena, ...).Coup de bol, comme il s'agit d'un [](http://www.rgagnon.com/javadetails/java-0166.html) jar [ex??cutable](http://www.rgagnon.com/javadetails/java-0166.html) (ou ?? peu pr??s), celui-cui disposait d'un [MANIFEST.MF](http://java.sun.com/developer/Books/javaprogramming/JAR/basics/manifest.html) complet. En particulier, il disposait d'une entr??e [Class-Path](http://blogs.oracle.com/olaf/entry/jdev_including_a_classpath_in), listant donc les d??pendances runtime de ce jar. Il me fallait donc les extraire pour les transformer en d??pendances maven. Bon, comme, en fait, je devais faire ??a pour un paquet de jars, j'ai ??crit un petit script Groovy pour automatiser tout ??a :



[https://gist.github.com/1137165](https://gist.github.com/1137165)

En bonus, pour les cas o?? on sait ?? quoi correspond un jar (par exemple, si je sais que jena/jena.jar correspond ?? l'artefact [com.hp.hpl.jena:jena:2.6.3](http://grepcode.com/snapshot/repo1.maven.org/maven2/com.hp.hpl.jena/jena/2.6.3), on peut ajouter ??a dans un fichier de propri??t??s Java (mais en XML, parce que les caract??res ":" sont interpr??t??s - curieusement - comme des s??parateurs cl??/valeur). Un fichier qui aura donc cette t??te-l?? :



[https://gist.github.com/1137176](https://gist.github.com/1137176)

Vous allez bien s??r me demander si il ya ??des feintes dans ce paquet ??pais de code Groovy. Pas trop, mis ?? part une bon sang d'expression r??guli??re. Oui, je sais, [maintenant, j'ai deux probl??mes](http://regex.info/blog/2006-09-15/247). Cela dit, l'avantage avec ce genre de truc, c'est que le temps de d??veloppement de l'expression r??guli??re (gr??ce ?? [un testeur en ligne](http://www.fileformat.info/tool/regex.htm)) est en fait bien plus long que son temps de mise en oeuvre dans le code.