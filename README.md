# Android-Sequence1 TODO List

## Présentation succinte

L'objectif de cette app était pour nous de se familiaruser avec les différents concepts sans perdre trop de temps dans des considérations d'UX design ou d'ergonomie.
Nous avons donc essayé beaoucoup de choses parmi lesquelles certaines n'étaient pas demandés.

Finalement, nous avons 5 activitiés :
* Une activité principale qui permet d'enregistrer des utilisateurs
* Une fois l'utilisateur enregistré, on est dirigé vers un écran qui permet d'ajouter des listes
* Une fois une liste créé, on est dirigé vers un écran qui permet de créer différents items
* Il y a également une activité de paramètres
* Une activité à propos atteignable depuis le menu permet de montrer la licence du code

Nous avons également fait le choix d'une activité Basic duquel hérite toutes les autres. Nosu avons aussi utiliser le plus possible androidx (à savoir la toolbar ici)
Pour l'écran de settings nous avons dans un premier temps testé la veriosn androidx avec des fragment et la classe `PreferenceFragmentCompat`, mais pour s'en tenir au sujet, nous avons finalement utilisé la classe très fortement dépréciée de Java `PreferenceActvity`

## Base de données
Nous avons fait le choix d'utiliser `room` et sa gestion de la persistence des données afin d'enregistrer les listes et les utilisateurs en mémoires.
Pour cela nous avons créé des modèles de données ainsi que des DAOs.
Nosu sommes bien conscient que la structure adoptée est loin d'être optimale et nous avons testé différentes possibilités de `room`
(il nous aurait fallu utiliser la librairie `Gson` avec des converters pour gérer les tables plus finement, en effet Room est basé su Sqlite et nee permet l'enregistrement que de types très simples)

Les requêtes sur la BDD doivent se faire dans un thread séparé et la mise à jour de l'UI dans le thread principal ( pour cela nous avon bricolé qqch afin que la mise à jour soit en temps réel, mais la bonne solution est d'utiliser des `LivingData` bien plus perforamantes

## RecyclerView
Pour les `recyclerview` nous avons implémenté des adapers nous permettant de créer dynamiquement les listes à partir de base de donnée.
