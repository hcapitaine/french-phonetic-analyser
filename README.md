# French-phonetic-analyser plugin (token filter)

## Que fait ce plugin / token filter?

Ce token filter permet d'encoder le texte à partir des phonèmes prononcés dans la langue française.

## Pourquoi ce plugin plutôt que ceux qui existent déjà?

Ce plugin a été créé car l'utilisation des plugins existants (soundex...)
ramenaient plus de résultats que ce qui était recherché. cf: https://blog.ippon.fr/2016/03/02/elasticsearch-tu-tentends-quand-tu-analyses/

## Comment le plugin fonctionne-t-il?

Le plugin fonctionne de la même façon qu'un enfant lirait un texte ou l'écrirait sans connaître l'orthographe.

Le texte est décomposé de gauche à droite avec une lecture de quelques caractères qui suivent la lettre courante.

La transcription de certains phonèmes est codé de la façon suivante:

|Valeur encodée   | Son à encoder   |
|-----------------|-----------------|
|        1        |          in     |
|        2        |           é     |
|        3        |          an     |
|        4        |          on     |
|        5        |           s     |
|        8        |      oeu/eu     |

A voir <a href="https://fr.wikipedia.org/wiki/Prononciation_du_fran%C3%A7ais#Prononciation_des_graph.C3.A8mes"/> Prononciation des graphèmes sur Wikipedia</a>


## Comment obtenir la version du plugin pour la version de l'elasticsearch que j'utilise:

Le plugin est décliné pour chaque version majeure et mineure d'elasticsearch.
 
Ce sont lors de ces versions que les montées de versions de lucène sont effectuées, ce plugin utilise aussi lucène, il est donc versionné de cette façon 5.6.X.

**ATTENTION**: Un plugin ne fonctionne que s'il a été buildé pour la version cible d'elasticsearch. La version est inscrite dans le fichier plugin-descriptor.properties

### Compiler la version désirée:


```shell
mvn clean install -Prun-its -DesYYX.version=Z
```
Remplacer YY par la version majeure et mineure d'elasticsearch et laisser le X tel quel. Remplacer le Z par la sous mineure désirée.

Par défaut des tests de performances JMH sont lancés ainsi qu'un test du plugin généré sur l'elasticsearch correspondant.

Si vous ne mettez pas le paramètre esYYX.version alors une version est prise par défaut et elle ne correspondra sûrement pas à la vôtre. Voir le pom du module correspondant pour la verison par défaut.

Le plugin se trouve ensuite dans le module maven pour la version d'elasticsearch désirée et dans ce module sous l'arborescence suivante: ***/target/xxxxx.zip***

### Release note:

| Version         | Contenu                                                                                                |
|-----------------|--------------------------------------------------------------------------------------------------------|
|        1.0.0    | Le plugin encode phonétiquement. 1 token <=> 1 token encodé                                            |
|        2.0.0    | Le plugin encode phonétiquement de différentes manières un même token. 1 token <=> 1...X tokens encodés|


## Contributeurs:
Merci aux ***Galeries Lafayette*** d'avoir permis de rendre le code open-source et à ces différents contributeurs:

* Harold Capitaine
* Yves Mathieu Rideau Baudin
* Alexandre Pocheau  
* Jonathan Baranzini
