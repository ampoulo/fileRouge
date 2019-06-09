/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import planning.example.AssemblyLine;
import java.util.Map;
import representations.Variable;

/**
 *
 * @author mbow
 */
public class SimpleHeuristic implements Heuristic{

    private PlanningProblem pb;
    //private 
    public SimpleHeuristic(PlanningProblem pb){
        this.pb=pb;
    }
    
    @Override
    public int heuristic(State state) {
        
        int nbreDePiece=0;//le nombre de piece de mauvaise couleur
        
        Map<Variable,String> etatfin = pb.etatFinal.getEnsemble();
        
        for(Map.Entry<Variable,String> conclusion : state.getEnsemble().entrySet()){
            if(etatfin.get(conclusion.getKey())!=conclusion.getValue())
                nbreDePiece++;
        }
        return nbreDePiece;
    }
    
    public PlanningProblem getPlanningProblem(){
        return this.pb;
    }
    
    
    public static void main(String[] args){
        AssemblyLine aline = new AssemblyLine();
        //System.out.println("coh :"+aline.genereEtatCoherent());
        
        State st2=aline.genereEtatCoherent();
        //System.out.println(st2.getEnsemble());
        
        PlanningProblem pb = new PlanningProblem(aline.conditionInitiale(),aline.genereEtatCoherent(),aline.actionListe);
        SimpleHeuristic h = new  SimpleHeuristic(pb);
        System.out.println(h.pb.getEtatFinal().toString());
        System.out.println(st2.toString());
        System.out.println(h.heuristic(st2));
    
    }
}
