type: post
status: published
title: bind vs PropertyChangeListener
tags: event, java, javafx
date: Thu Jun 03 15:27:08 CEST 2010
~~~~~~
# bind vs PropertyChangeListener

Depuis quelques mois, [mes petits amis de dooapp](http://blog.dooapp.com/) (j'adore dire ??a, ??a me donne l'impression d'??tre animateur [Chez Marcus](http://marcus.canalserveur.com/home.php)) me disent que JavaFX, c'est la meilleure chose qui soit arriv??e depuis le pain en tranche. Du coup, je m'interroge, m'interp??le. C'est vrai que certaines interfaces faites avec ce langage semblent assez jolies. Ils me disent aussi, et surtout m??me, que le [bind](http://java.sun.com/javafx/1/tutorials/core/dataBinding/index.html), c'est un truc incroyable. Du coup, j'ai d??cid?? de tester un peu JavaFX.  
Enfin, tester, c'est un bien grand mot. J'ai plut??t d??cid?? de tenter de suivre un ou deux tutoriels voir si c'est un langage qui parle ?? mon moi profond.Bon, je sais pas si vous le savez, mais avant de faire du Groovy, avant de faire du Ruby, mon langage de r??f??rence, c'est le Java. Bien s??r, c'est un langage qui commence ?? avoir un certain ??ge (je pense personnellement qu'il devient maintenant mature), mais bon, ??a n'est qu'un point de vue personnel). Et bien s??r, le fait que je pratique le Java depuis dix ans me donne un certain point de vue sur les langages qui essayent d'utiliser la machine virtuelle pour en tirer des choses "mieux" que ce que pourrait faire le Java. Bon, j'ai fait mienne depuis longtemps la devise de mon vieux complice [Charles Lehalle](http://lehalle.net/) "en informatique, tout est dans tout". Qui signifie ?? peu pr??s qu'il est toujours possible de tout faire dans tout langage. La seule diff??rence, ce sera l'expressivit??, c'est-??-dire la facilit?? avec laquelle un bout de code sera ??crit. Et l??, pour le coup, le bind, effectivement, est nettement plus expressif que le [PropertyChangeSupport](http://java.sun.com/j2se/1.5.0/docs/api/java/beans/PropertyChangeSupport.html)/[PropertyChangeEvent](http://java.sun.com/j2se/1.5.0/docs/api/java/beans/PropertyChangeEvent.html)/[PropertyChangeListener](http://java.sun.com/j2se/1.5.0/docs/api/java/beans/PropertyChangeListener.html) :



[https://gist.github.com/423873](https://gist.github.com/423873)

Du coup, je crois que je vais revoir mon avis sur le JavaFX, m??me si certains ??l??ments me paraissent curieux (les [s??quences](http://java.sun.com/javafx/1/tutorials/core/sequences/index.html) qui font comme si elles fournissaient toutes les collections existantes avec une syntaxe franchement ??trange) voire stupide (le remplacement de ??par AND et || par OR). Disons en fait que j'attends surtout d'atteindre le point o?? je ferais de l'interface graphique, parce que pour l'instant, la ligne de commande, c'est gentil, mais on peut en faire facilement dans l'importe quel langage, m??me en [Brainf*ck](http://fr.wikipedia.org/wiki/Brainfuck)).