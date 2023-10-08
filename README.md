# RAPPORT Voting-System

## Maxime BILLY, Timothée JUILLET

### Nos choix
- Utilisation de .csv pour les listes de user et de candidats
- Le vote se fait dans la console, candidat par candidat
- Les résultats sont affichés lorsqu'on les demande et non pas à la fin du vote

### Bonus
- Tous les bonus ont été fait

### Difficultés
- Pas de difficultés rencontrées.

### Fonctionnement

#### Voter
- Lancer la classe "Server" du package server
- Rentrer en dur le lien vers le fichier "candidates.csv" situé dans data
- Rentrer en dur le lien vers le fichier "users.csv" situé dans data
- Rentrer les dates de début et de fin du vote au format jj/mm/aaaa

Le serveur est maintenant opérationnel. Vous pouvez afficher/ajouter des utilisateurs/users en écrivant le numéro correspondant.

Le serveur peut également afficher les résultats pendant et après le vote.

- Lancer la classe Client du package client
- Rentrer un numéro d'étudiant ajouté ou déjà existant comme "ozeliurs"
- Rentrer le mot de passe, qui est "password" pour ozeliurs
- Rentrer 1 pour voter
- La liste de tous les candidats apparait, puis les candidats apparaissent 1 à 1 pour le vote. Rentrer un chiffre entre 0 et 2 puis appuyer sur entrée pour passer au candidat suivant.
- Sélectionner voter à nouveau l'option voter pour voter à nouveau.

Le client peut également voir les résultats.
