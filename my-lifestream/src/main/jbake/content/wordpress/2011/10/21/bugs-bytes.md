type: post
status: published
title: Bugs bytes
tags: flex, java, maven
date: Fri Oct 21 15:25:39 CEST 2011
~~~~~~
# Bugs bytes

Pendant que ma machine remet ?? neuf mon repository maven local, j'en profite pour vous faire part de mon exp??rience de d??veloppement r??cente.  
Parce que ??a fait un moment que je n'ai pas parl?? de d??veloppement, quand m??me, sauf que l??, ce sera d'une fa??on un peu plus g??n??rale.  
Dans une entreprise pr??c??dente et pas vraiment appr??ci??e, nous avions des instructions strictes nous interdisant d'utiliser des biblioth??ques Java externes. C'??tait frustrant, car on voyait passer des tonnes de bonnes id??es sans pouvoir s'en servir. Cela dit, ??a servait "cens??ment" l'objectif de maintenir la taille de notre logiciel ?? son minimum (m??me si on aurait pu utiliser des solutions bien plus intelligentes pour servir ce but). Si vous vous demandez pourquoi, la raison est double :
1. Le logiciel ??tait install?? via Java web Start, et ses mises ?? jour par le m??me moyen. Du coup, pour ??viter de les faire sentir ?? l'utilisateur, il vallait mieux limiter la taille du jar.
2. Vous savez combien ??a co??te ?? envoyer sur internet, un octet ? Ben pas moi ! N??anmoins, le chef consid??rait que chaque octet que nous envoyions lui co??tait de l'argent sorti de **sa** poche, ce qui ??tait inacceptable.Donc, nous n'utilisons aucune biblioth??que externe, mais connaissions pourtant le corollaire ?? la loi des bugs : chaque code de ligne qu'on n'??crit pas, c'est des bugs en moins.

Aujourd'hui, je travaille sur un logiciel mettant en oeuvre une pile plut??t complexe :
* un serveur [Glassfish](http://glassfish.java.net/fr/)
* un client Glassfish
* une pile [Neo4J](http://neo4j.org/)/RDF ...
* un [Weld](http://seamframework.org/Weld) c??t?? client
* du Flex avec [GraniteDS](http://www.graniteds.org/confluence/pages/viewpage.action?pageId=229378) largement customis?? pour le remotingJ'en passe, et des meilleurs. Et je ne compte m??me pas notre forge logicielle.Et une constatation s'impose maintenant : si le code que je n'??cris pas ne contient pas de bugs, celui que j'utilse peut lui aussi en contenir. Et cette semaine, c'est arriv?? ... ?? peu pr??s une fois chaque jouur :


* Un bug [ultra-gal??re](https://twitter.com/#!/riduidel/status/124822166448508928) de Glassfish concernant les [contextes JNDI qui ne sont pas navigables](http://java.net/jira/browse/GLASSFISH-17219)  

* Un [autre](https://twitter.com/#!/riduidel/status/126212190838595585) bug p??nible de [SerialInitContextFactory invisible](http://stackoverflow.com/questions/7804357/serialinitcontextfactory-not-found-in-glassfish-naming)
* Un [pataqu??s](https://twitter.com/#!/riduidel/status/126273095999823872) pas possible entre [maven, jenkins, et les slaves](http://stackoverflow.com/q/7807171/15619)
* Une merdouillle invraissemblable entre [Eclipse WTP et m2eclipse](https://issues.sonatype.org/browse/MECLIPSEWTP-73) qui emp??che d'utiliser un ejb-client comme d??pendance dans un projet (genre, le truc, c'est fait pour, mais chez EEclipse, ils sont pas fans).
* Et le dernier, qui date d'aujourd'hui : weld-se 1.1.2-Final qui g??re pal l'emplacement du fichier beans.xml (mais l??-dessus, je n'ai trouv?? aucune doc, et, je dois 'lavouer, j'ai la flemme de faire le bug report)Bref, assembler tout ??a, ??a a un c??t?? Behemoth assez d??plaisant : d??s qu'on passe un bug, on en prend un ou deux autres dans les dents. Quelque part, je comprend les partisans de Play! (m??me si l'application sur laquelle je bosse m'interdit d'utiliser une solution simpliste comme celle-l??).

