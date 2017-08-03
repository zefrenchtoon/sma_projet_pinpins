
Hi ! Here is the "Pinpins" story !

Here you will find a program I made with one of my friend many years ago while we were studying computer science.
This program was writen as a sort of exam in a course named "Multi-Agent System" ("Systèmes Multi-Agents" in french).

The main goal was to simulate population of people (named "pinpins") that have the mission to "convert" the others to their own "thought" when they encounter a "pinpin" from another thought.

There are some parameters to set before to start a simulation. Default values are set to be able to start a new simulation easily:

- Total number of "pinpin"
- Naïve level of each of them
- Number of population
- If they have to move slowly or not
- Random distribution of "pinpins" across different populations
- Frequency of logging (saving stats)

Be careful that using a quite high value for the number of "pinpin" will slow down your computer. Values over than 1000 will have to be used with cautions. Of course, this depends on others parameters too.
Imagine that each "pinpin" is a thread … How cores do you have in your CPU ?  ;)

No code modifications were made since 2007 other than updating build script to be able to build the program using JDK 1.8 instead of 1.6.

Here after, the original french explanation of the concept.

Have fun !

---

Voici venu l'histoire des pinpins !!!

Ce programme a été réalisé dans le cadre d'un mini-projet pour mes études au niveau Master 1ère année dans une matière nommée "Interactions et Systèmes Multi-Agents".

Le principe de ce programme est de simuler le comportement d'une population d'individus ayant chacun s propre opinion sur un sujet. Ces individus se promènent tranquillement sur la zone de travail et lorsqu'un individu rencontre un autre individu, celui ci sonde l'autre de façon à voir son opinion. Si celle-ci est assez proche de la sienne (selon le degré de naïveté choisi) alors celui-ci converti l'autre individu à sa propre opinion puis repart gambader.

Explication avec exemple:

On lance le programme avec 10 individus (ou pinpins), 3 opinions et un degré de naïveté égal à 1. Cela signifie que nous aurons 3 populations ayant respectivement 4, 3 et 3 individus. À cause du degré de naïveté, un individu de la famille 1 ne pourra convaincre un individu de la famille 3 (1+1 < 3).
Le dernier paramètre sert à fixer le temps qui s'écoule entre chaque sauvegarde des stats dans le fichier de Log qui servira ensuite à générer de zolis graphes sous Excel pour la constitution du rapport à rendre. De plus, cela permet de remarquer certains phénomènes assez étranges.

Bien entendu, le but de l'opération est de tester la chose avec différents paramètres mais surtout de le laisser tourner jusqu'à stabilisation des statistiques. En effet, d'expérience, il peut y avoir un retournement de situation n'importe quand ...

Bon "amusement"  ;-)
