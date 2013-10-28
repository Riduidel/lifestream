type: post
status: published
title: SqlBuilder, c'est bien !
tags: groovy, java, library, sql
date: Fri Sep 03 14:20:27 CEST 2010
~~~~~~
# SqlBuilder, c'est bien !

Bon, j'ai l'impression que ??a fait bien longtemps que je n'ai pas parl?? de [Java](http://riduidel.posterous.com/tag/java) ici ... (en l'occurence, ??a date du mois de juin), il est donc temps de r??parer cet oubli.  
Mais avant, un petit d??tour du c??t?? de groovy sera utile.

Petit voyage au pays des Builders

En Groovy, donc, il y a un concept qui est bien, et dont je me sers avec plaisir, c'est celui des [Builders](http://groovy.codehaus.org/Builders). Un Builder, c'est un objet dans lequel on d??clare ses ??l??ments, et qui les construit ?? la vol??e. Il y en a plein et, souvent, ils permettent d'??crire les choses d'une fa??on tr??s compacte. Ce concept, on le retrouve ??videment en [JavaFX](http://download.oracle.com/javafx/1.3/tutorials/ui/syntax/), o?? il s'agit l?? de la seule fa??on d'instancier les objets, en fait.C'est un concept tellement bien que, la plupart du temps (et m??me si ??a n'est malheureusement pas possible) j'essaye d'utiliser des APIs qui fournissent ce genre de constructions.Et cette semaine, je suis tomb?? sur deux candidats dans un domaine qui me para??t essentiel, mais pour lequel je vais faire un autre d??tour.

SQL, le faux ami

En fait, dans une application Java, bien trop souvent ([h??las](http://fr.wikipedia.org/wiki/NoSQL), pour ceux comme moi qui trouvent le SQL d??cidement trop archai??que), on a besoin d'??crire du [SQL](http://fr.wikipedia.org/wiki/Structured_Query_Language). le [SQL](http://sql.developpez.com/), c'est un peu la [lingua franca](http://fr.wikipedia.org/wiki/Lingua_franca) des bases de donn??es relationnelles. Elles le comprennent toutes ?? peu pr??s de la m??me mani??re (et ajoutent toutes leurs petites extensions plus ou moins bien pens??es), bien qu'il existe [une norme](http://en.wikipedia.org/wiki/SQL-92) le d??finissant totallement. Bon, ??a, c'est g??nant, mais pas trop. Ce qui est beaucoup plus g??nant, c'est que le SQL est un langage interpr??t??. C'est-??-dire qu'on sait qu'une instruction SQL est valide seulement en l'ex??cutant. Parce qu'?? part ??a, mis ?? part avec des[ extensions](http://en.wikipedia.org/wiki/SQLJ) au langage, ??a n'est qu'une cha??ne de caract??re qu'on stocke dans un fichier de propri??t??s ([certains](http://stackoverflow.com/questions/2026429/embedded-sql-in-oo-languages-like-java/2026490#2026490) mettent directement ce SQL dans une cha??ne de caract??re dans le code Java, mais c'est plut??t moyen).  
On stocke donc le SQL quelque part, en esp??rant que ce SQl est valide au sens de la syntaxe, mais en sachant qu'on ne pourra v??rifier ??a qu'avec un test unitaire, alors que bon, le v??rifier d??s la compilation serait quand m??me vachement plus pratique, non ?

SQLBuilder ?? la rescousse !

Ben oui, ??videment, c'est ?? ??a que sert le SQLBuilder ! A ??crire du code Java qui va produire comme r??sultat une requ??te SQL, certes dans une cha??ne de caract??re, mais dans une syntaxe garantie correcte au sens du langage ! Et coup de bol, il en existe deux (trouv??s ??videment sur [StackOverflow](http://stackoverflow.com/questions/623607/dynamic-sql-java-library)) :??  

* [Squiggle](http://code.google.com/p/squiggle-sql/)
* [SQLBuilder](http://openhms.sourceforge.net/sqlbuilder/)Evidement, vu le titre de l'article, vous vous doutez bien que j'ai choisi le second. Et vous avez raison. Mais pourquoi ? Eh bien tout simplement parce que Squiggle se limite ?? faire des SELECT quand SQLBuilder fournit toutes les op??rations. Et que je compte bien utiliser toutes ces op??rations pour un projet pas trop secret, mais [discret](https://www.ohloh.net/accounts/Riduidel/messages/113085) quand m??me.

My 2 cents

SQLBuilder est donc bien pratique, mis ?? part un petit point de "d??tail". Comme Squiggle, il ne peut travailler qu'avec des [Table](http://openhms.sourceforge.net/sqlbuilder/apidocs/com/healthmarketscience/sqlbuilder/dbspec/Table.html), [Column](http://openhms.sourceforge.net/sqlbuilder/apidocs/com/healthmarketscience/sqlbuilder/dbspec/Column.html) et [Schema](http://openhms.sourceforge.net/sqlbuilder/apidocs/com/healthmarketscience/sqlbuilder/dbspec/basic/DbSchema.html) d??ja d??clar??s. Et quand par exemple on veut injecter les informations d'une table d??ja existante, ??a peut ??tre un peu fastidieux de le faire ?? la main. J'ai donc ??crit une petite classe qui permet de tout charger depuis une [Connection JDBC](http://download.oracle.com/javase/6/docs/api/java/sql/Connection.html).  


Cette classe, la voici :



[https://gist.github.com/563808](https://gist.github.com/563808)

Et avec ??a, croyez-moi, travailler avec SQLBuilder devient terriblement facile, parce que SQLBuilder conna??t alors toutes les tables de la base de donn??e, ainsi que toutes ses colonnes avec leurs information de type complet.