type: post
status: published
title: Triangle de Pascal - suite et fin temporaire
tags: code, informatique, ruby
date: Thu Aug 17 15:47:00 CEST 2006
~~~~~~
# Triangle de Pascal - suite et fin temporaire

Encore une fois, Frederick m'a donné la solution, que j'ai lâchement recopié (et un peu modifié, après l'avoir comprise, surtout la partie [inject](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2006/inject-in-java/)), et qui ne fait plus que 68 o :

<pre>p=[1];34.times{puts p.join" ";p=p.inject([0]){|m,c|m[-1]+=c;m<<c}}</pre>

Bon, évidement, on a depuis longtemps franchi les limites du lisible, mais ça compile, et, surtout, ça passe le test de codegolf.  Pour l'implémentation Java, vous reviendrez ;-)

via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)

 