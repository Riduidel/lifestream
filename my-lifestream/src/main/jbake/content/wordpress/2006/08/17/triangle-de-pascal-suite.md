type: post
status: published
title: Triangle de Pascal - suite
tags: code, informatique, ruby
date: Thu Aug 17 10:19:25 CEST 2006
~~~~~~
# Triangle de Pascal - suite

Puisque Frederick en a parl?? [en commentaires](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2006/triangle-de-pascal/), j'ai r????crit mon triangle de Pascal pour arriver ?? ??a :[ruby]p=[1]while(p.length<35)
puts p.join(" ")
p=Array.new(p.length+1) {|i|

a=i

0 ? p[i-1] : 0

a+b}end[/ruby]pour une taille totale de 140 octets (j'ai d'ailleurs mis ce code sur [le wiki de litterateprogramming](http://en.literateprograms.org/Pascal%27s_Triangle_%28Ruby%29 "Pascal's Triangle (Ruby) - LiteratePrograms")).D'un autre c??t??, Frederick, lui, avec ma pr??c??dente version, arrive ?? ??a :[ruby]def extendCoefs(a);p=0;r=a.inject([]){|s,e|svia [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)