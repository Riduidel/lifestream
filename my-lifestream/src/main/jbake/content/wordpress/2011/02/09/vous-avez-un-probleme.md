type: post
status: published
title: Vous avez un probl??me ? ...
tags: euler, groovy, regexp
date: Wed Feb 09 19:06:42 CET 2011
~~~~~~
# Vous avez un probl??me ? ...

Je ne vais pas reprendre encore une fois [la fameuse phrase](http://regex.info/blog/2006-09-15/247) attribu??e ?? Jamie Zawinsky, mais vous voyez l'id??e.  
Dans [le probl??me 26](http://projecteuler.net/index.php?section=problems&id=26), on doit trouver le plus long cycle dans l'??criture d??cimale de 1/n.Et ??a, c'est typiquement un boulot pour une expression r??guli??re.Pour ??a, j'ai donc d??gain?? mon[ testeur d'expressions r??guli??res Java](http://www.regexplanet.com/simple/index.html) pr??f??r??, j'ai forg?? mon [expression r??guli??re](http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html) (vous avez vu comme ??a s'??crit bien en groovy [une expression r??guli??re](http://groovy.codehaus.org/Regular+Expressions) ?)Et pouf :



[https://gist.github.com/818913](https://gist.github.com/818913)

Bon, au passage, vous remarquerez l'appel ?? [Map#max(Closure)](http://groovy.codehaus.org/groovy-jdk/java/util/Map.html#max(groovy.lang.Closure)) qui est super m??ga cool et le for louche.Le tout pour un temps d'ex??cution de ... de ... de 14.523 sCourt, hein !