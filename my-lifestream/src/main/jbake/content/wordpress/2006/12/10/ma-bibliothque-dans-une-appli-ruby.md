type: post
status: published
title: Ma biblioth??que dans une appli Ruby
tags: code, informatique, pimki, ruby
date: Sun Dec 10 20:14:00 CET 2006
~~~~~~
# Ma biblioth??que dans une appli Ruby

Comme vous le savez [peut-être](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2006/blikibidule/), j'ai décidé de passer mon site web dans une appli Ruby (en l'occurence [Pimki](pimki.rubyforge.org)), dans un avenir plus ou moins proche. L'un de mes besoins essentiels concerne ma bibliothèque et la capacité de générer facilement des fiches bibliographiques à partir de l'ISBN.

Dans Wordpress, j'utilise généreusement ce que m'offre [Noosfere](http://www.noosfere.org "http://www.noosfere.org"), mais ça me limite à la SF. Or je me suis rendu compte que j'aimerais bien pouvoir parler correctement d'une BD comme [Mutafukaz](http://www.mutafukaz.com/ "Mutafukaz - comics by Run / Ankama Editions") qui n'est pas référencée dans la noosfere, ce qui est somme toute normal. Et puis, pour mon amour-propre, ce serait sympa d'écrire un code facilement réutilisable par les gens. Je me suis ddonc mis en quête de différentes infos.

D'abord, comment l'intégrer dans Pimki. Ca, c'est facile et c'est Assaph qui me l'a expliqué.

Ensuite, comment et à qui demander les infos. Comment, c'est pas trop dur, c'est grâce aux web services ou, pour le dire plus vulgairement, en faisant une banale requête HTTP bien remplie (ce que je faisais déja chez la Noosfere). Mais à qui ? La BNF ? Amazon ? Ce sera le dernier, tout simplement parce que je n'ai **jamais** entendu parler d'un quelconque web service de la BNF, alors que ceux d'Amazon, je les connais. Reste maintenant à taper dedans en Ruby, ce qui est plutôt bien expliqué sur le web :
* [Ruby/Amazon & Amazon web services](http://www.ddj.com/showArticle.jhtml?articleID=184405959 "http://www.ddj.com/showArticle.jhtml?articleID=184405959")
* [Ruby and amazon web services](http://www.cocking.co.uk/blog/2006/11/ruby-and-amazon-web-services.html "Romilly Cocking's Review: Ruby and Amazon Web Services") qui me paraît plus léger et plus compréhensible.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)