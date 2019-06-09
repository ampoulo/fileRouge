/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.test;

import planning.PlanningProblemWithCost;
import planning.SimpleHeuristic;
import planning.example.AssemblyLine;

/**
 *
 * @author nokenn
 */
public class MainPlanningProblemWithCost {


    public static void main(String[] args){
        AssemblyLine assLine = new AssemblyLine();

        PlanningProblemWithCost ppwc = new PlanningProblemWithCost(assLine.conditionInitiale(),assLine.genereEtatCoherent(),assLine.actionListe);
        //liste des actions
        //ystem.out.println(assLine.toStringActions(assLine.actionListe));
        //l'etat final recherché
        System.out.println("etat final attendu"+ppwc.getEtatFinal().toString());
        //les actions retournées par djikstra
        System.out.println("djikstra :\n"+ppwc.toStringStack(ppwc.djikstra(ppwc)));
        System.out.println("astar :\n"+ppwc.toStringStack(ppwc.aStar(ppwc,new SimpleHeuristic(ppwc))));

        //System.out.println("noeuds explorés de Djikstra:"+ppwc.getSondeDjikstra());
        System.out.println("noeuds explorés de Astar :"+ppwc.getSondeAstar());
    }

}
