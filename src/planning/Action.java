/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;


import java.util.HashMap;
import java.util.Map;

import representations.Rule;
import representations.Variable;

/**
 *
 * @author 21208468
 */
public class Action extends Rule {


	public Action(Map<Variable, String> premisse, Map<Variable, String> conclusion) {
		super(premisse,conclusion);		
	}
//        public Action(Map<Variable,String> conclusion){
//            super(null,conclusion);
//        }
	
	public boolean is_applicable(State etat) {
            if(this.getPremisse()==null){
                return true;}
            else if(this.premisse!=null){
                for(int i=0; i<this.premisse.size();i++){
                    if(etat.satisfies(this.premisse))
                        return true;            
                }
                
            }  return false;  
        }
		
   @Override
   public String toString(){
	   return " PRE ("+ this.premisse +") , " + "EFF (" + this.conclusion +")";	  
   }
   
   public boolean equals(Action action){
       boolean bool=false;
            if(action.getPremisse()!=null && this.premisse!=null){
                if(action.getPremisse().equals(this.premisse)
                   && action.getConclusion().equals(this.conclusion))
                return true;
            }

       return false;
   }
	
}
	
	

