/*--------------------------------------------------------------------------------------------------------------------------------------------------
    Mouhamadou Ousmane Mbaye [21612706] 
    NDZAMBO OKMEBA KENNELVY [21810907] L3INFO
    Zafimamy Jary Avitsara [21208468]  
    Romuald Pradal [21502222]

-----------------------------------------------------------------------------------------------------------------------------------------------------*/


Partie 1 REPRÉSENTATION DES OBJETS ET CONSTRAINTES---------------------------------------------------------------------------------------------------------------------------------------------------------------

Les package Pricipal:

Reprensentation:

    class:
        -AllEqualConstraint
        -IncompatibilityConstrainte
        -Disjuction 
        -Rule
        -Variable
        -Constraint (Interface)

Sous Package:

Examples: Qui contient les 4 class qui reprensente les 4 contrainte de l'exemple de l'ennonce 
    
    class:
        -Constraint:
            CONTRINTE REPRENSENTE:
                    *Les voitures du catalogue ont toutes le toit, le capot et le hayon de la même couleur
        -Constraint2:
                CONTRINTE REPRENSENTE:
                    *Les voitures vérifient qu'au moins l'un des deux côtés (gauche et droite) a la même couleur que le toit      
        -Constraint3:
                CONTRINTE REPRENSENTE:
                    *Aucune voitures n'a les deux côtés noirs     
        -Constraint4:
                CONTRINTE REPRENSENTE:
                    *Aucune voiture n'a à la fois un toit ouvrant et une sono
                    
        -ConstraintDisjunction: Permettant de représenter des contraintes consistant en la disjonction de plusieurs contraintes
     
     
Test: ou se trouve des classes exécutables qui effectuent  des tests class du Package representation
     
        class
            -ExampleTest: Qui contient le test sur le sous package Example
            
            
            -FiltrageTest: Test de la methode de filtrage des constraintes 


PARTIE 2 BACKTRAKING --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

PACKAGE Pricipal:

PPC:
    class:
        -Backtracking
        
        -Heuristique(Interface)
        
        -HeuristiqueOfConstraint: Choix de la variable plus contrainte
            
        -HeuristiqueOfDomaine: Choix de la variable avec le domaine le plus grand

        -HeuristiqueRandom: Choix de valeur au hasard

sous Package:

Test:   
    class:
        PpcTest: (classe exécutable) Test de la class Backtracking Avec les 4 contrainte du sous Package Examples de Representation
    
PARTIE 3 PLANNIFICATION -----------------------------------------------------------------------------------------------------------------------------------------------------
planning:
    class:
        -State
        -Action
        -PlanningProblem
        -PlanningProblemWithCost
        -SimpleHeuristic
        -InformedHeuristic

sous package : planning.test
  Dans ce package sont testés les classes ci-dessus.
        -MainAssemblyLine : permet de voir toutes les actions, l'etat initial et l'etat final généré aléatoirement
        -MainPlanningProblem: teste le dfs, dfs_itérative et bfs et les sondes
        -MainPlanningProblemWithCost : teste l'algorithme de Djikstra et Astar
        
        Execution :
        lancer :
             -MainAssemblyLine.sh pour executer MainAssemblyLine
             -planningProblem.sh pour executer MainPlanningProblem
             -planningProblemWithCost.sh pour executer MainMainPlanningProblemWithCost             
        

PACKAGE principal:

Diagnosis:
    
    class:
        Diagnoser: Class implemant les different type de Diagnostic
        
SOUS PACKAGE:
    Test:
        class:
            C1: Qui represente la contriantre( x1 && x2 --> x3) donne en exemple CM 
            C2: Qui represente la contrainte (¬x3 --> x4) donner ausssi en exemple en CM 
            DiagnoTest: (classe exécutable) Qui faire un test de la class Diagnoser sur l'exemple vu en cours avec les contraintre C1 ET C2 




Partie 4 : Extraction 
Le package extraction contient la partie data mining du TP fil rouge. il contient une classe Test permettant de faire le test pour toutes les fonctions 

























