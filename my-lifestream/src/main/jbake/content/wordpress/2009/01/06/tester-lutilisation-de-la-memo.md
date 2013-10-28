type: post
status: published
title: Tester l'utilisation de la m??moire 
tags: code, java, majickproperties, memory, test
date: Tue Jan 06 10:15:22 CET 2009
~~~~~~
# Tester l'utilisation de la m??moire 

Dans majick-properties, je suspecte le code que j'ai ??crit d'??tre [potentiellement](http://code.google.com/p/majick-properties/issues/detail?id=8) ?? l'origine de [fuites de m??moire](http://louis.cova.neuf.fr/blocs-notes/page2.html). Et ??a, c'est plus qu'emb??tant quand on fait une biblioth??que dont le but est de faciliter la vie.

Surtout que, traditionnellement, la chasse aux fuites m??moires en Java est compliqu??e : il faut un [profiler](http://en.wikipedia.org/wiki/Performance_analysis) (en Java, le meilleur rapport qualit??/prix est obtenu avec le profiler de NetBeans), trouver le truc qui foire, le corriger, et esp??rer que ??a tienne <img alt="360" height="15" src="http://getfile3.posterous.com/getfile/files.posterous.com/riduidel/GsRcsxQdKKUqxCdVdsHMbBQZZkT8bh6iruPRMa98ad3quW3WV92PK4z2s2cb/360.gif" width="12"></img>

  


Heureusement, la communaut?? Java dispose maintenant de m??thodes permettant de v??rifier que ces fuites de m??moire ne reviennent pas. [La premi??re](http://weblogs.java.net/blog/timboudreau/archive/2005/04/writing_memory.html) que j'ai trouv?? utilise une librairie dont le nom seul est un hommage au bont go??t : [insane](http://performance.netbeans.org/insane/index.html). Et [la deuxi??me](http://blog.palantirtech.com/2007/11/06/writing-junit-tests-for-memory-leaks/) utilise jUnit pour parvenir au m??me r??sultat, ce qui fait que je vais pouvoir utiliser les concepts dans mes tests. Je me demande quand m??me s'il ne va pas falloir que je regarde insane de pr??s pour am??liorer ??a, parce qu'en fait, cette deuxi??me solution ne semble pratique que quand on sait pr??cis??ment ce qu'on cherche, alors que la premi??re est plus efficace en terme d'exploration.

Tiens, au fait puisqu'on parle de m??moire en Java, il semble que cet article : [understanding weak references](http://weblogs.java.net/blog/enicholas/archive/2006/05/understanding_w.html), soit une mine d'infos sur les distinctions subtiles entre weak, soft et phantom references.