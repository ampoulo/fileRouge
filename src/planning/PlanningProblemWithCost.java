/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import planning.example.AssemblyLine;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author 21612706
 */
public class PlanningProblemWithCost extends PlanningProblem{
    //compte le nombre de noeuds dans l'algorithme Djikstra
    private int sondeDjikstra=0;
   
    //compte le nombre de noeud dans l'algorithme Astar
    private int sondeAstar=0;
    //utilise cette classe pour acceder à l'ensemble des actions, l'etat initial et l'etat final
    private AssemblyLine assemblyLine;    
    private Heuristic h;
    
    public PlanningProblemWithCost(State EtatInitial, State EtatFinal, Set<Action> ActionsListe) {
        super(EtatInitial, EtatFinal, ActionsListe);
        this.assemblyLine = new AssemblyLine();        
    }
    
    public int getSondeDjikstra() {
        return sondeDjikstra;
    }

    public int getSondeAstar() {
        return sondeAstar;
    }
    //calcul le coût de l'action donnée en parametre
    public int cost(Action action){        
        for(Action act : assemblyLine.INSTALL_PIECES()){            
            if(act.equals(action)){
                return 2;
            }                     
        }
        for(Action act : assemblyLine.PAINT_ROOF()){            
            if(act.equals(action)){
                return 2;
            }
        }
        return 1;                    
    }
    
    //algorithme de djikstra
    public Stack<Action>  djikstra(PlanningProblem pb){
        Map<State,Integer> distance = new HashMap<State,Integer>();                       
        Map<State,State> father= new HashMap<State,State>();        
        Map<State,Action> plan = new HashMap<>();        
        Set<State> goals = new HashSet<>();
        Set<State> open = new HashSet<State>();
        Set<State> closed = new HashSet<>();
        
        open.add(pb.getEtatInitial());
        distance.put(pb.getEtatInitial(),0); 
        father.put(pb.getEtatInitial(),null); 
        
        
        while(!open.isEmpty()){
            State state  = argmin(open,distance);
            open.remove(state);
            sondeDjikstra++;
            if(state.satisfies(pb.getEtatFinal().getEnsemble()))
                goals.add(state);
            for(Action act : pb.getActionsListe()){
                if(act.is_applicable(state)){                    
                    State next = state.apply(act);                    
                    Set<State> stateDist = distance.keySet();
                    if(!next.isContainedBy(stateDist)){
                        distance.put(next, Integer.MAX_VALUE);                                            
                        if(distance.get(next) > distance.get(state)+cost(act)){
                            distance.put(next,distance.get(state)+cost(act));
                            father.put(next,state);
                            plan.put(next, act);
                            open.add(next);
                        }
                    }             
                }                             
            }
        }   
            return get_djikstra_plan(father,plan, goals, distance);
    }
    
    
    public Stack<Action> get_djikstra_plan(Map<State,State> father, Map<State,Action> actions,Set<State> goals,Map<State,Integer> distance){
        Stack<Action> plan = new Stack<>();
        State goal = argmin(goals,distance);
        while(goal!=null){
            plan.push(actions.get(goal));
            goal = father.get(goal);
        }
        return reverse(plan);
    }
    
    
    //l'etat dans open qui a la plus petite distance
    public State argmin(Set<State> open,Map<State,Integer> distance){        
        State stateMin = (State) open.toArray()[0];
        int min = (int) distance.get(stateMin);        
        for(State etat : open){
            if(distance.get(etat)<=min)
                stateMin = etat;
                min=distance.get(stateMin);            
        }
        return stateMin;
    }
    //algorithme A*
    public Stack<Action> aStar(PlanningProblem pb,Heuristic h){
        Map<State,State> father = new HashMap<State,State>(){{put(pb.etatInitial,null);}};
        Set<State> open=new HashSet<State>(){{add(pb.etatInitial);}};
        Map<State,Integer> distance = new HashMap<State,Integer>(){{put(pb.etatInitial,0);}};
        Map<State,Integer> value = new HashMap<State,Integer>(){{put(pb.etatInitial,h.heuristic(etatInitial));}};        
        Map<State,Action> plan = new HashMap<>();        
        
        while(!open.isEmpty()){
            State state = argmin(open,distance);
            open.remove(state);
            sondeAstar++;
            if(state.satisfies(etatFinal.getEnsemble())){
                System.out.println("final : "+state);
                return get_bfs_plan(father,plan,state);
            }
            for(Action act:pb.actionsListe){
                if(act.is_applicable(state)){
                    State next = state.apply(act);
                    if(!next.isContainedBy(distance.keySet())){
                        distance.put(next, Integer.MAX_VALUE);
                        if(distance.get(next)>distance.get(state)+cost(act)){
                            distance.put(next,distance.get(state)+cost(act));
                            value.put(next, distance.get(next)+h.heuristic(next));
                            father.put(next, state);
                            plan.put(next, act);
                            open.add(next);
                        }
                            
                    }
                }
            }
        }
        return null;
    }      
    
    
}
