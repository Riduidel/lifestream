type: post
status: published
title: Le nomic des chameliers
tags: culture, nomic
date: Wed Oct 13 17:03:12 CEST 2004
~~~~~~
# Le nomic des chameliers

Cette page présente une tentative de nomic pas facile créée dans le cadre de socionomic, une expérience de metanomic sur frjm
##Règle 0Chaque membre du groupe des chameliers est appelé un ... chamelier. L'adésion au groupe est faite dès lors que le nouveau chamelier répond à un message comprenant l'entête définie en 0.3 par un message conservant ces entêtes.Un chamelier est considéré comme tel à chaque fois que son message porte l'entête définie en 0.3. La radiation a automatiquement lieu lorsque le potentiel de vote (défini dans la règle 0.1) du chamelier est inférieur à la limite minimale, définie dans la règle 0.2


###règle 0.1Le potentiel de vote d'un chamelier est déterminé par le dernier potentiel de vote déterminé (ou le potentiel de vote initial déterminé en 0.11) multiplié par le coefficient multiplicateur déterminé en 0.12.


####règle 0.11Le potentiel de vote initial d'un chamellier est de 1.


####règle 0.12Le coefficient multiplicateur est 1 plus le rapport entre le nombre de votes du chamelier moins le nombre de messages total du chamelier divisé par le nombre de messages total du chamelier.


###règle 0.2La limite minimale est actuellement fixée à 0.1


###règle 0.3L'entête actuelle est définie par le titre du message. Celui-ci doit contenir, dans cet ordre et avec cette syntaxe, les balises "[SOCIO][7][Chameliers]".


##règle 1.0Pour qu'une nouvelle règle soit adoptée, elle doit d'abord remplir les conditions définies en 1.1 et être proposée à la discussion pour un temps déterminé en 1.2, (durant sa phase de discussion, elle peut être remaniée, sans pour autant que l'esprit en soit changé) avant d'être soumise au vote selon 1.3 pour un temps déterminé en 1.4. Elle est adoptée selon les conditions définies en 1.5. Les différents chameliers voient leur potentiel de vote mis à jour selon les termes définis en 1.6.


###règle 1.1Une proposition de règle ne peut être soumise que par un chamelier.Elle est soumise dans un message respectant 1.11. Une proposition de règle peut être l'ajout d'une règle, l'abrogation d'une règle, la modification d'une règle ou l'échange des numéros de deux règles. Seules les règles portant des numéros non entiers peuvent être abrogées.Chaque nouvelle proposition porte un numéro défini selon 1.12. Une proposition peut contenir des sous-niveaux de règles. ils sont alors numérotés x.1, x.2, ... Ou x sera le numéro attribué à la proposition principale.Il ne peut y avoir de proposition rétroactive.


####règle 1.11Le message d'une nouvelle proposition inclut l'entête définie en 0.3, et son titre contient en plus les termes [discussion].


####règle 1.12Chaque nouvelle proposition reçoit un numéro définit comme suit : Si la proposition est un ajout, elle recevra un numéro entier non attribué choisi par le proposant.


###règle 1.2 Le délai nécessaire à la discussion d'une proposition est de 1/(potentiel de vote du proposant) jours.


###règle 1.3Le message lançant le vote doit contenir l'entête définie en 0.3, et contient en plus dans le titre les termes [vote]. Ce message est une réponse directe au message définit en 1.11. Chaque chamelier doit, pour voter, répondre à ce vote dans un message qui contiendra les termes "[+1]" (pour un vote positif), "[0]" (pour un vote blanc)  ou "[-1]" (pour un vote négatif) dans le corps de texte, et donc en-dehors de la signature.


###règle 1.4Le délai nécessaire au vote d'une proposition est de (potentiel de vote du proposant) jours.


###règle 1.5Pour définir si une proposition est adoptée, le potentiel de vote de chaque joueur est d'abord déterminé selon 0.1 par le chamelier défini selon 1.51. Les votes de tous les chameliers sont alors comptabilisés par le chamelier défini en 1.52. Chaque vote de chamelier est pondéré selon son potentiel de vote afin de former la somme pondérée des votes.Une proposition est adoptée selon les conditions de 1.52. Les potentiels de vote sont ensuite recalculés selon 1.53 par le chamelier défini selon 1.51


####règle 1.51Le chamelier calculant les potentiels de vote est le proposant, sauf en cas de contestation définie en 1.511


#####règle 1.511Si un chamelier n'est pas d'accord avec le choix du chamelier calculant les potentiels, il doit ajouter dans le corps de son message les termes "[contestation du chamelier calculant]". En ce cas, le chamelier calculant est alors déterminé selon la règle 1.5111


######règle 1.5111Le nouveau chamelier calculant est le premier chamelier votant aprés la contestation qui n'est pas le chamelier proposant.


####règle 1.52Le chamelier décomptant les votes est le premier chamelier à répondre [-1] ou le proposant si tous les chameliers ont voté [+1]


####règle 1.53Une proposition est adoptée si la somme des votes des proposants est supérieure au poids de la règle. Celui-ci est défini en 1.521


#####règle 1.531Si le numéro de règle proposée est un entier, le poids de la règle est son numéro.Sinon, le poids de la règle est le dernier chiffre significatif de celle-ci divisé par le nombre de chiffres de la règle arrondi à l'entier supérieur.(ici, ce poids est de 1).


###règle 1.6Le chamelier ayant proposé voit son potentiel de vote augmenté de la différence entre la somme pondérée des votes et le poids de la règle.Chaque chamelier ayant voté voit son potentiel de vote augmenté du rapport entre la valeur de son vote et la somme des valeurs absolues des votes.Tous ces calculs sont faits par le chamelier calculant les potentiels, défini en 1.51


## règle 2Une règle, pour s'appliquer, doit toujours être référencée, directement ou non, par une règle dont le numéro est entier.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)