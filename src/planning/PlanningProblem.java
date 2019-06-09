/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;
import planning.example.AssemblyLine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


/**
 *
 * @author 21612706
 */
public class PlanningProblem {
    //cette sonde renvoie 364 noeuds en moyenne avec un domaine de deux couleurs
    private int sonde_dfs_dabord=0;
    //cette sonde renvoie 67 noeuds en moyenne avec un domaine de deux couleurs
    private int sonde_dfs_iterative=0;
    //cette sonde renvoie 1489 noeuds en moyenne avec un domaine de deux couleurs
    private int sonde_bfs=0;
    
    
    protected State etatInitial;
    protected State etatFinal;
    protected Set<Action> actionsListe;
    private final Set<State> closed;
    private Stack pile;
    Set<Action>subplan;
    

    public int getSonde_dfs_dabord() {
        return sonde_dfs_dabord;
    }

    public int getSonde_dfs_itérative() {
        return sonde_dfs_iterative;
    }

    public int getSonde_bfs() {
        return sonde_bfs;
    }
       
    public PlanningProblem( State EtatInitial, State EtatFinal,Set<Action> ActionsListe){
        this.etatInitial=EtatInitial;
        this.etatFinal=EtatFinal;
        this.closed=new HashSet<State>();
        this.actionsListe=ActionsListe;       
    }
    //recherche en profondeurs d'abord
    public Stack<Action> dfs(State initial_state, Stack<Action> plan, HashSet<State> closed){        
        sonde_dfs_dabord++;
        if(initial_state.satisfies(etatFinal.getEnsemble()))
            return plan;        
        else{
            for( Action act : actionsListe ){
                if(act.is_applicable(initial_state)){
                    State next = initial_state.apply(act);                                       
                    if(!next.isContainedBy(closed)){
                        plan.push(act); 
                        closed.add(next);                                                                                               
                        Stack<Action> subplan = dfs(next,plan,closed);                        
                        if(!(subplan.isEmpty()))
                            return subplan;
                        else{
                            plan.pop();                        
                        }
                    }                                                                                   
                }
            }                        
        }
        return new Stack<Action>();
    }                 
    //recherche en profondeur itérative
    public Stack<Action> dfs_iterative(State initial_state){                
        for(int profondeur=1;profondeur<Double.POSITIVE_INFINITY; profondeur++){            
            Stack<Action> resultat = dls(initial_state,new Stack<Action>(),new HashSet<State>(), profondeur);
            if(!resultat.isEmpty()){                 
                return resultat;
            }
        }
        return null;
    }       
    // recherche en profondeur limitée 
    public Stack<Action> dls(State initial_state, Stack<Action> plan,Set<State> closed, int limit){                        
        sonde_dfs_iterative++;
        if(initial_state.satisfies(etatFinal.getEnsemble())){
            return plan;
        }
        else if(limit==0) return null;
            else{
                for( Action act : actionsListe ){                
                    if(act.is_applicable(initial_state)){                                       
                        State next = initial_state.apply(act);                                       
                        if(!next.isContainedBy(closed)){                        
                            plan.push(act);  
                            closed.add(next);                                                                                                 
                            Stack<Action> subplan = dls(next,plan,closed,limit-1);                        
                            if(!(subplan==null))
                                return subplan;                                                
                            else
                                plan.pop();                            
                        }
                    }                                                                                   
                }
            }                                        
        return new Stack<Action>();
    }
    
    public Stack<Action> bfs(State initial_state,State final_state,Set<Action> actionListe){
        
        Map<State,State> father = new HashMap<>();
        Map<State,Action> plan = new HashMap<>();
        Set<State> closed = new HashSet<>();
        List<State> opened = new ArrayList<>();
        
        opened.add(initial_state);
        father.put(initial_state,null);
        
        while(!opened.isEmpty()){
            State state = opened.get(0);
            opened.remove(0);
            closed.add(state);
            for(Action act: actionListe){                                                
                State next = state.apply(act);                
                if(!next.isContainedBy(closed) && !next.isContainedBy(opened)){
                    father.put(next,state);
                    plan.put(next, act);
                    sonde_bfs++;
                    if(next.satisfies(final_state.getEnsemble()))
                        return get_bfs_plan(father,plan,next);                    
                    else
                        opened.add(next);
                }
            }
        }
        return null;
    }
    
   public Stack<Action> get_bfs_plan(Map<State,State> father, Map<State,Action> action, State goal){
        Stack<Action> plan = new Stack();        
        while(goal!=null){
            plan.push(action.get(goal));
            goal=father.get(goal);
            
        }
        return reverse(plan);
    }
    
    public Stack<Action> reverse(Stack<Action> plan){
        Stack<Action> planReversed = new Stack<Action>();
        for(int i=plan.size()-2; i>=0;i--){
            planReversed.push(plan.get(i));
        }
        return planReversed;
    }
    
    //affiche les elements d'un Stack
    public String toStringStack(Stack<Action> stack){
        String str="";
        for(Action st:stack){
            str+=st+"\n";
        }
        return str;
    }
    public State getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(State EtatInitial) {
        this.etatInitial = EtatInitial;
    }

    public State getEtatFinal() {
        return etatFinal;
    }

       public Set<Action> getActionsListe() {
        return actionsListe;
    }

    public void setActionsListe(Set<Action> ActionsListe) {
        this.actionsListe = ActionsListe;
    }
    
    public boolean estSolution(Stack<Action> st){
        State stat=this.etatInitial;
        for(int i=0;i<st.size();i++){
            if(st.get(i)!=null)
                stat = stat.apply(st.get(i));
        }
        return stat.equals(etatFinal);
    }
   
}

    
    

