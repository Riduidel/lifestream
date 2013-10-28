type: post
status: published
title: IPTC, XMP ? Ruby ?? la rescousse ! 
tags: iphoto, iptc, mac, ruby
date: Mon Aug 31 22:26:34 CEST 2009
~~~~~~
# IPTC, XMP ? Ruby ?? la rescousse ! 

[http://github.com/whymirror/hpricot/tree/master](http://github.com/whymirror/hpricot/tree/master)Bon, comme je le disais, de retour de vacances, iPhoto m'??nerve toujours autant avec sa non-gestion des tags IPTC/XMP. Alors cette fois-ci, ??a suffit !Je sais, depuis bien longtemps, que dans une biblioth??que iPhoto, il y a toujours un fichier [AlbumData.xml](http://www.fatcatsoftware.com/iplm/Documentation/iPLM/pgs/albumdata.html) qui contient toute la base de donn??es de photos. Je vais donc l'utiliser comme source pour lire les infos que je vais ajouter dans les tags IPTC/XMP des photos. Comme ??a, je serais tranquille.  
 Et franchement, l'algorithme est hyper-pipeau :

Lire le fichier XML???? ??Pour chaque tag, m??moriser son id???? ??Pour chaque photo, r??cup??rer les tags par leur id???? ??Ecrire chaque tag en IPTC ET en XMP dans la photo

Et ce sera tout.Pour ??a, j'aurais besoin d'Hpricot (RPI [_Why](http://en.wikipedia.org/wiki/Why_the_lucky_stiff), RIP) et d'une API pour ??crire l'IPTC et [l'XMP](http://www.chilkatsoft.com/ruby-xmp.asp) (et vu nos usages, je me demande si l'XMP n'est pas plus important que l'IPTC ...)Et avec ??a, "normalement", si tout se passe bien, j'aurais mes tags en IPTC et en XML, ce qui sera bien pratique pour exploiter correctement ces photos.