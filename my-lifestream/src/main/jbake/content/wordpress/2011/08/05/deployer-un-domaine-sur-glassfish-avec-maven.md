type: post
status: published
title: Déployer un domaine sur Glassfish avec maven
tags: glassfish, java, maven
date: Fri Aug 05 13:47:50 CEST 2011
~~~~~~
# Déployer un domaine sur Glassfish avec maven

Supposons que vous ayez un projet [Java EE](http://fr.wikipedia.org/wiki/Java_EE).  
Supposons également que vous souhaitiez, en test tout au moins, le déployer sur une instance de [Glassfish](http://fr.wikipedia.org/wiki/GlassFish).Supposons toujours que, par habitude, ou par goût, ou même par masochisme, vous souhaitiez utiliser [maven](http://fr.wikipedia.org/wiki/Apache_Maven) pour ça.Supposons enfin que, pour des raisons diverses et variées, vous utilisiez des données stockées dans, mettons, une base de donnée [Neo4J](http://neo4j.org/), des queues [JMS](http://fr.wikipedia.org/wiki/Java_Message_Service), une base de donnée relationelle, et que sais-je encore.Vous allez naturellement vous diriger vers un plugin maven permettant le pilotage de glassfish. Et, comme vous étes un bon développeur, donc fainçant, vous allez chercher sur le web ce qui existe. Et pour question comme ça, le web, c'est [StackOverflow](http://stackoverflow.com/questions/1836317/which-maven-glassfish-plugin-to-use), ni plus, ni moins.Donc, vous allez vous diriger vers [maven-glassfish-plugin](http://maven-glassfish-plugin.java.net/). J'ai hélas déja dit ici qu'il comportait [quelques bugs](http://riduidel.posterous.com/meme-quand-cest-pas-bien-lopen-source-cest-mi). Ce que je n'ai pas dit, c'est qu'en plus de ces bugs, il est impossible de l'utiliser pour déployer le [connecteur Neo4J](http://alexsmirnov.wordpress.com/2011/05/18/neo4j-java-ee-connector/).Et là, il faut en revenir à la base.Enfin, la base, pas tout à fait.Vous savez que maven dispose d'un [exec-maven-plugin](http://mojo.codehaus.org/exec-maven-plugin/), permettant d'exécuter n'importe quel programme ? Et bien en l'utilisant dans mon projet, je crée maintenant le domaine avec toutes les informations utiles (enfin, [presque](http://stackoverflow.com/questions/6942759/batch-creation-of-domain-working-in-command-line-but-not-through-exec-maven-plug)), je déploie mon application (avec ce fameux flag "--force" qui manque au maven-glassfish-plugin), et je peux même détruire le domaine. Bref, c'est la féte avec maven.

Evidement, si vous voulez savoir comment je déploie ce domaine, je peux vous l'expliquer.

J'ai donc découvert la commande [multimode](http://download.oracle.com/docs/cd/E18930_01/html/821-2433/multimode-1.html#scrolltoc) de Glassfish, qui permet de lui envoyer des "scripts".

J'ai donc créé un script create-domain.txt, dans lequel j'ai mis toutes les instructions utiles (dont le déploiement du connecteur Neo4J, pour lequel je dois retrouver le chemin d'une dépendance maven - merci gmaven -, ce que j'explique dans [cette réponse sur StackOverflow](http://stackoverflow.com/questions/2359872/can-i-use-the-path-to-a-maven-dependency-as-a-property/6941647#6941647) enfin, disons qu'en plus de ça, je fais un peu de [filtrage de ressource](http://maven.apache.org/plugins/maven-resources-plugin/examples/copy-resources.html)).Puis, dans mon pom, j'ai créé un profil activé par la propriété domain=create, dans lequel, lors de la phase package, je lance ma commande multimode. Vous voulez voir cette commande ? D'accord, la voilà :



[https://gist.github.com/1127365](https://gist.github.com/1127365)

Enorme, non ?En fait, ça ne marche pas. Regardez cette question que je pose ([encore une fois sur StackOverflow](http://stackoverflow.com/questions/6942759/batch-creation-of-domain-working-in-command-line-but-not-through-exec-maven-plug)). Je soupçonne que ça a un rapport avec [ce bug Ant](https://issues.apache.org/bugzilla/show_bug.cgi?id=46805) correspondant peu ou prou au même cas. D'ailleurs, je vais tester sur autre chose que Windows XP, tiens.Notez qu'à cause de l'absence du flag force, j'utilise à peu prés la même méthode pour déployer mon EAR : un profil contenant un appel à exec-maven-plugin lançant asadmin avec les bons paramètres.

Comme quoi, parfois, Java EE, c'est la guerre, mais pas forcément à cause des raisons traditionnelles.