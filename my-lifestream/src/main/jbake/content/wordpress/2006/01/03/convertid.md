type: post
status: published
title: CONVERT(ID)
tags: informatique, leblog, monwordpress, web
date: Tue Jan 03 09:12:00 CET 2006
~~~~~~
# CONVERT(ID)

Suite au précédent message, voici ma méthode très très personnelle pour déplacer des messages

####Mise en garde préalable

Avant tout, ce n'est pas un tutorial complet, mais plutôt une version alpha de ce qui pourrait être utile, quand toutes les autres méthodes ont échoué.

####Contexte

Voici mon contexte : j'ai un blog utilisant Wordpress 1.2 que j'aimerais recycler dans un autre (celui que vous lisez actuellement :-)) utilisant un plus moderne Wordpress 1.5. Je voulais en fait récupérer tous mes vieux posts sur ce nouveau blog, lequel a été créé vite (quelle erreur). Donc, j'ai un blog A souhaitant migrer ses posts dans un blog B où, hélas, des posts existent déja.   Following the previous message, here is my very very own post move procedure.

####A foreword precaution

Before all, this is not a complete tutorial, but rather the very alpha version of what could become a useful message, when all other attempts fail.

####Context

Here is my context : I have one blog running under Wordpress 1.2 for which I wish a full bit recycle in a second one (this blog you're currently reading :-)) running a little more modern Wordpress 1.5. I wanted in fact to retrieve all my old posts to this new blog, which has been created empty (a pity). So, I have blog A wishing to migrate its posts to blog B where, alas, entries already exists.

####La méthode

Cette méthode n'est pas complète, mais pourrait guider les meilleurs hackers Wordpress dans la voie que j'ai trouvé, à moitié par hasard, et à moitié parce que j'étais perdu sans espoir d'être retrouvé par qui que ce soit.

#####Sauvegardez votre ancien blog

Jetez un oeil, par exemple, au [Codex Wordpress](http://codex.wordpress.org/User:SebErard/fr:Sauvegarder_sa_Base_de_Donnees). C'est plutôt simple, et devrait vous permettre d'obtenir un dump de la table `wp_posts` (notez bien que je suis seulement intéressé par cette table, puisque je veux **seulement** récupérer mes posts). N'oubliez pas de récupérer la structure de la table.

####The method

This method is not complete, but could guide the best Wordpress hackers to the path I found, half by luck, and half being lost and having no hope to be found by anyone.

#####Backup your old blog

Take a look, for example, at [Wordpress codex](http://codex.wordpress.org/Backing_Up_Your_Database). This is quite simple, and could allow you to obtain a dump of your `wp_posts` table (notice I'm only interested by this table, since I **only** want to get my posts back). Do not forget to backup the table structure.

#####Renommez cette table des posts

Maintenant, conformément aux recommandations de Wordpress, ouvrez un éditeur de code pour rechercher/remplacer toutes les occurences de `wp_posts` par `temp_posts` (vous pouvez utiliser un autre nom, mais ne l'oubliez pas ;-)).

#####Fusionnez les structures

Modifiez la structure de `temp_posts` pour supprimer toutes les colonnes inutiles (comme par exemple, `post_lat` et `post_lon`, de Wordpress 1.2). C'est la partie facile, parce qu'après, vous devez ajouter les colonnes correspondant aux nouveaux champs (comme la colonne `guid` de Wordpress 1.5). Ce qui n'est pas mortel; puisqu'il vous suffit d'ajouter à la structure de `temp_posts` toutes les colonnes présentes seulement dans `wp_posts`.

#####Rename the posts table

Now, according to Wordpress recommandations, use  a code editor to find and rename all occurences of `wp_posts` to `temp_posts` (you can use another name, but do not forget it ;-)).

#####Merge both structures

Modify your `temp_posts` structure to remove all unneeded columns (as an example, `post_lat` and `post_lon` of Wordpress 1.2). This is the easy part. Because after, you'll have to add columns corresponding to new fields (as an example, the `guid` of Wordpress 1.5) ...

#####Changez les index et les valeurs obligatoires

Cette partie implique du code SQL spécifique. Par exemple, j'ai dû mettre à jour tous les index (pour une référence du langage, jetez un oeil à [MySQL reference manual](http://dev.mysql.com/doc/refman/5.0)) en utilisant cette requête :

#####Change indexes and required values

This part involves specific SQL code. As an example, I've had to update all indexes (for language reference, check [MySQL reference manual](http://dev.mysql.com/doc/refman/5.0)) using this query :   [MYSQL] UPDATE temp_posts SET ID=ID+250 [/MYSQL]   The +250 is due to the fact that I already have 230 posts in my `temp_posts` table, and 110 in my `wp_posts` table. You should take the maximum index between bothg and, for security, add 10. You can also add 1000, or even 100000, but, well, I find that not so ecologically correct ;-) And guid using this query :   Le +250 est dû au fait que j'ai déja 230 posts dans ma table `temp_posts`, et 110 dans la table `wp_posts`. Vous devez prendre l'indice maximal entre ces deux tables et, par sécurité, ajouter 10. Vous pouvez aussi ajouter 1000, ou même 100000, mais bon, je ne trouve pas ça si écologiquement correct ;-) Et le guid en utilisant celle-ci :   [MYSQL] UPDATE temp_posts SET guid=CONCAT('http://nicolas-delsaux.is-a-geek.net/wordpress/?p=via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)