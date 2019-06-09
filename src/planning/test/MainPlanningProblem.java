/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.test;

import java.util.HashSet;
import java.util.Stack;
import planning.Action;
import planning.PlanningProblem;
import planning.State;
import planning.example.AssemblyLine;

/**
 *
 * @author nokenn
 */
public class MainPlanningProblem {

    public static void main(String[] args){
        AssemblyLine assemblyLine = new AssemblyLine();
        PlanningProblem pb = new PlanningProblem(assemblyLine.conditionInitiale(),assemblyLine.genereEtatCoherent(),assemblyLine.actionListe);
        State etatfinal = pb.getEtatFinal();
        //l'etat final attendu
        System.out.println("etat fin: "+pb.getEtatFinal().toString());
        //BFS
        System.out.println("bfs :\n"+assemblyLine.toStringActions(pb.bfs(pb.getEtatInitial(),etatfinal,assemblyLine.actionListe)));
        //DFS D'ABORD
        System.out.println("dfs_dabord :\n"+assemblyLine.toStringActions(pb.dfs(pb.getEtatInitial(),new Stack<Action>(),new HashSet<State>())));
        //DFS itérative
        System.out.println("dfs_iterative :\n"+assemblyLine.toStringActions(pb.dfs_iterative(pb.getEtatInitial())));
        //les sondes des différents algorithmes
        System.out.println("noeuds explorés de bfs :"+pb.getSonde_bfs());
        System.out.println("noeuds explorés de dfs_d'abord :"+pb.getSonde_dfs_dabord());
        System.out.println("noeuds explorés de dfs itérative :"+pb.getSonde_dfs_itérative());

    }
}
