/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import representations.*;

/**
 *
 * @author 21208468
 */
public class State implements Cloneable {
    Map<Variable,String> ensemble;
    
	
	 public State(State stat)  {
	    	this.ensemble= clone(stat.getEnsemble());
	    }
	 
	public State(Map<Variable,String> ensemble){
	        this.ensemble=ensemble;
	    }   
        public Map<Variable, String> getEnsemble() {
		return ensemble;
	}

	public void setEnsemble(Map<Variable, String> ensemble) {
		this.ensemble = ensemble;
	}
        public boolean equals(State state){
            for(Map.Entry<Variable, String> me:state.getEnsemble().entrySet()){
                if(!(this.ensemble.get(me.getKey())==me.getValue()))
                    return false;
            }
            return true;
        }
        public boolean isContainedBy(Collection<State> state){
            for(State etat: state){
                if(this.equals(etat))
                    return true;
            }
            return false;
        }
        
	
	/*vérifie si un état donné satisfait les états finaux.*/
	public boolean satisfies(Map<Variable,String> etat_partiel){  
            if(this.ensemble==null && etat_partiel==null) 
               return true;
            if(!this.getEnsemble().keySet().containsAll(etat_partiel.keySet()) ){                
                return false;
            }
           
            else{                 
                for(Map.Entry<Variable,String> premisse : etat_partiel.entrySet()){
                    if(this.getEnsemble().isEmpty())
                        System.out.println("vide");
                    if(!(this.getEnsemble().get(premisse.getKey())==premisse.getValue())){                        
                        return false;
                    }
                }
            return true;
            }
        }	          
	
    @Override
	public String toString() {
		String str="{(";
                for(Map.Entry<Variable,String> etatPartiel: this.ensemble.entrySet()){
                    str = str+etatPartiel.toString()+"\n";
                }
                str=str+")}";
                return str;
	}
	
    @Override
	public Object clone() throws CloneNotSupportedException{
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	} 
       	
	public State apply(Action action){
            State statecp = new State(this);
            if(action.is_applicable( this)){
		for ( Map.Entry<Variable,String> entry : action.getConclusion().entrySet()){
                    statecp.getEnsemble().put(entry.getKey(),entry.getValue());		                
		}   		                
            }    return statecp;		
        }            
        public static<K,V> Map<K,V> clone(Map<K,V> original) {
            Map<K,V> copy = new HashMap<>();
            copy.putAll(original);
            return copy;
        }
			
}
