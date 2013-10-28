type: post
status: published
title: Commons-VFS
tags: ca sert à presque rien, file, http, java, webdav, zip
date: Tue Feb 12 20:53:11 CET 2013
~~~~~~
# Commons-VFS

Admettons que vous soyez un développeur Java, qui veut utiliser des fichiers de différents systèmes de stockage (du ZIP, du FTP, du HTTP(S), voire même du [WebDAV](http://fr.wikipedia.org/wiki/WebDAV)). Bon, ben c'est pas compliqué, ne vous fatiguez pas à chercher ... Allez voir tout de suite [Commons VFS 2](http://commons.apache.org/vfs/). C'est franchment très très pratique.

Pour copier un fichier d'un endroit à un autre (du moment qu'il existe un support pour le [système de fichier](http://commons.apache.org/vfs/filesystems.html)), ou pour lire le contenu du fichier (éventuellement à l'aide de [commons-io](http://commons.apache.org/io/)), voire même encore pour ... en fait tout le reste, Commons VFS sera toujours là pour vous filer un coup de main.

Par contre, si à un moment vous voulez vous connecter à un serveur WebDAV, évitez l'implémentation par défaut, qui fait du bête parsing DTD, et écrivez votre [FileProvider](http://commons.apache.org/vfs/apidocs/org/apache/commons/vfs2/provider/FileProvider.html) reposant (par exemple) sur [Sardine ](https://code.google.com/p/sardine/)(dont le seul aspect ridicule est le nom) qui utilise la DTD de WebDAV compilée avec JAXB pour offrir une implémentation qui déchire son macareux.