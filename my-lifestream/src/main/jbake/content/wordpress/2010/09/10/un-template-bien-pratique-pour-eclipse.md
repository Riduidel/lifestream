type: post
status: published
title: Un template bien pratique pour Eclipse
tags: eclipse, exception, java
date: Fri Sep 10 11:18:00 CEST 2010
~~~~~~
# Un template bien pratique pour Eclipse

Vous connaissez déja depuis longtemps le template pour [créer un logger facilement dans Eclipse](http://riduidel.posterous.com/template-log4j-pour-insertion-rapide-dans-ecl). J'en ai quelques autres, mais celui dont je voudrais vous parler aujourd'hui, c'est celui pour le code des méthodes.Allez dans "Preferences .../Java/Code Style/Code Templates", sélectionnez "method body", et remplacez le code existant par

<pre>// ${todo} Auto-generated method stubthrow new UnsupportedOperationException("method "+${enclosing_type}.class.getName()+"#${enclosing_method} has not yet been implemented AT ALL");</pre>

<span style="font-family:arial, helvetica, sans-serif;">De cette manière, quand vous créez une méthode, soit en implémentant une interface, soit  en surchargeant une méthode parente, vous vous retrovuez, si vous l'appelez, avec une belle exception qui vous rappelera à cahque exécution qu'une partie de votre code n'a pas encore été implémentée, plutôt que d'avoir simplement une méthode vide qui ne vous poussera qu'à vous creuser la tête.</span><span style="font-family:courier new, monospace;"><br></br></span>

<span style="font-family:arial, helvetica, sans-serif;">C'est peut-être pas le standard dans Eclipse, mais si vous cherchez un peu, vous verrez que c'est assez courant dans le monde Java.<br></br></span>