type: post
status: published
title: C'est mal fait, ??a tiens
tags: groovy, posterous, posterousbackup
date: Mon Feb 07 14:14:00 CET 2011
~~~~~~
# C'est mal fait, ??a tiens

Ce matin (bon, ce midi, en fait), j'ai pris une grande décision.J'ai décidé d'arrêter de passer mes midis à jouer à [Urban Terror](http://www.urbanterror.info/news/home/) pour faire des trucs plus constructifs.En fait, j'avais déja fortement diminué mon temps de jeu il y a quelques mois, pour pouvoir blogger d'une façon plus régulière. Je pourrais bien entendu vous sortir une [timeline](http://beta.memolane.com/riduidel) de derrière les fagots, mais je ne suis pas sûr que ça en vaille la peine.Donc, j'ai décidé de diminuer encore mon temps de jeu (poussé par le fait que mon niveau commence à stagner), pour reprendre des occupations utiles.Au premier lieu de ces occupations, il y a la mise à jour de mes différents scripts. Et évidement, en premier, l'export posterous sur lequel je dois pratiquer deux opérations
1. Remplacer le format de sortie personnalisé que j'avais créé (vaguement inspiré, il faut le dire, par Docbook) par [un micro-format](http://microformats.org/wiki/blog-post-formats-fr) très adapté au contenu.
2. utiliser l'[API v2](http://apidocs.posterous.com/blog) de posterousEt sur ce deuxième point, je cale déja un peu (oui, je commence par le deuxième point parce que, tant qu'à faire, autant se faciliter la vie et commencer par lire les données avant de les écrire, ce qui me donne d'ailleurs une diée foudryante de puissance quant à l'utilisation de [GPars](http://gpars.codehaus.org/) et du [Groovy fonctionnel](http://www.jroller.com/vaclav/entry/add_one_function_to_my) pour accélérer le bouzin).En effet, les gens de Posterous disent de récupérer simplement ce token d'identification à une url, et évidement ça ne marche pas.Bon, je détaille, sinon personne n'y comprendra rien.Dans mon code Groovy, pour me connecter, j'utilise simplement





[https://gist.github.com/814351](https://gist.github.com/814351)

<span>Et devinez ce que me dit Groovy quand j'exécute ça ?</span><span><br></br><p><a href="https://gist.github.com/814353">https://gist.github.com/814353</a></p><br></br></span>

Ben oui, ça ne marche pas.Et apparement, c'est dû au fait que posterous utilise une authentification basique préemptive, alors que [HttpClient ](http://hc.apache.org/httpcomponents-client-ga/tutorial/html/authentication.html#d4e1023)(et donc [HttpBuilder](http://groovy.codehaus.org/modules/http-builder/doc/auth.html)) [n'aime pas ça](http://markmail.org/thread/gbky5leiivngglck#query:+page:1+mid:3hraq5ufip2mlpq5+state:results).

<span>Ce qui me pousse tout de suite à changer mon plan pour faire d'abord en premier la première partie de mon plan : exporter dans un joli HTMl réutilisable facilement.</span><span>Ensuite, je migrerai en API v2 (et oui, sur les petits projets, je n'hésite pas à modifier l'ordre des tâches si ça peut me permettre de réussir plus de trucs plus vite).</span>