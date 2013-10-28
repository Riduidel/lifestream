type: post
status: published
title: Polymorphisme ? Ou
tags: code, informatique, java
date: Fri Jan 07 15:46:27 CET 2005
~~~~~~
# Polymorphisme ? Ou

Lorsque j'ai lu la d??finition du polymorphisme sur le blog de [Jose](http://www.josesandoval.com/2005/01/polymorphic-overriding.html), j'ai ??t?? assez intrigu??. En effet, pour moi, ?? la base, le polymorphisme, c'est la possibilit?? dans une classe Java d'??crire :When I read the ploymorphism definition on [Jose](http://www.josesandoval.com/2005/01/polymorphic-overriding.html)'s blog, I was almost intrigued. Indeed, for me, polymorphism is the ability in a Java class to write :[java]public class MyClass {    // no constructor for this code sample    public void doThat(int _int) {
// do something    }    public void doThat(Integer _int) {
// do something    }    public void doThat(String _str) {
// do something    }}[/java]Le polymorphisme qu'il d??crit ne ressemble absolument pas au mien. Heureusement, [Comment ??a marche](http://www.commentcamarche.net/poo/polymorp.php3) me rassure et m'explique que nous avons tous les deux raison : Jose parle de **polymorphisme par h??ritage** et moi de **polymorphisme param??trique**.Je trouve personnellement cette confusion entre deux notions assez ??loign??es (dans son cas la surcharge et dans le mien la red??finition de m??thode) plut??t ??trange ...His polymorphism obviously don't looks like mine. Hopefully, [Wikipedia](http://en.wikipedia.org/wiki/Polymorphism_(computer_science)) reassures me and explains that we're both right : Jose talks about **subtyping polymorphism**, when i talk about **parametric polymorphism**.I find it strange that the same word is used about such notions, but it's once again mysteries of vocabulary.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)