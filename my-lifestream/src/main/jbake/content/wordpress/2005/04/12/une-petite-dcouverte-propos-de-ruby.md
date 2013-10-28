type: post
status: published
title: Une petite d??couverte ?? propos de Ruby
tags: bookmarcs, code, informatique, ruby
date: Tue Apr 12 10:37:00 CEST 2005
~~~~~~
# Une petite d??couverte ?? propos de Ruby

Je codais tranquillement hier soir (la lecture des bookmarks Opera, pour être précis. D'ailleurs, je lis aussi les favoris d'IE), quand j'ai commencé à subir un angoissant problème : j'ai une classe Bookmark qui contient ça :

<pre>def to_s(_deepness = 0)                 "t"*_deepness+attributes.inspect         end</pre>

et une classe Folder, qui hérite de Bookmark, qui contient ça

<pre>def to_s(_deepness = 0)                 returned = super.to_s(_deepness) # cette ligne plante                 @bookmarks.each do |bookmark|                         returned += "n"+bookmark.to_s(_deepness+1)                 end                 returned         end</pre>

Alors, pourquoi le super.to_s plante ? Parce qu'en Ruby, tout est objet. Y compris une méthode. Donc, d'après ce que je comprend, super ne se réfère pas à l'objet courant vu comme un Bookmark, mais à la méthode courante vue comme une méthode de Bookmark. Du coup, si je remplace la ligne incriminée par ` returned = super(_deepness) ` Ca marche. C'est fou, non ?

 via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)