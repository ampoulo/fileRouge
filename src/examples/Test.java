package examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import planning.Action;
import planning.PlanningProblem;



import planning.example.AssemblyLine;
import java.util.HashMap;
import java.util.Map;
import planning.State;
import representations.Variable;
/*Classe pour tous les tests*/
public class Test {

	/*Méthode à utiliser dans AllEqualConstraint*/
	public static boolean VerifieValeurs(List<String> liste){
		for (String s : liste){
			if (!s.equals(liste.get(0))){
				return false;
			}
		}
		return true;		
	}

	public static void main(String[] args) throws CloneNotSupportedException {
      
		Set<String> domaineBooleen = new HashSet();
		domaineBooleen.add("TRUE");
		domaineBooleen.add("FALSE");
			
		Variable HAS_CHASSIS = new Variable("HAS_CHASSIS",domaineBooleen);
		Variable HAS_FRONT_LEFT_WHEEL = new Variable("HAS_FRONT_LEFT_WHEEL",domaineBooleen);
		Variable HAS_FRONT_RIGHT_WHEEL = new Variable("HAS_FRONT_RIGHT_WHEEL",domaineBooleen);
		Variable HAS_REAR_LEFT_WHEEL = new Variable("HAS_REAR_LEFT_WHEEL",domaineBooleen);
		Variable HAS_REAR_RIGHT_WHEEL = new Variable("HAS_REAR_RIGHT_WHEEL",domaineBooleen);
		Variable HAS_BODY = new Variable("HAS_BODY",domaineBooleen);
		Variable HAS_WHEELS = new Variable("HAS_WHEELS",domaineBooleen);

		
		//domaine des variables prenant une couleur
		Set<String> COLOR = new HashSet();
		//COLOR.add("GRAY");
		COLOR.add("BLACK");
		COLOR.add("WHITE");
//		COLOR.add("RED");
//		COLOR.add("GREEN");
//		COLOR.add("BLUE");
//		COLOR.add("ORANGE");
//		COLOR.add("YELLOW");

		/*FRONT, LEFT, REAR, RIGHT, ROOF*/
		Variable FRONT = new Variable("FRONT",COLOR);
		Variable LEFT = new Variable("LEFT",COLOR);
		Variable REAR = new Variable("REAR",COLOR);
		Variable RIGHT = new Variable("RIGHT",COLOR);
		Variable ROOF = new Variable("ROOF",COLOR);                                
		
                //Ajout des variables dans une liste
		Set<Variable> variables = new HashSet<>();
		//variables.add(FRONT);
		variables.add(LEFT);
		variables.add(REAR);
		variables.add(RIGHT);
		variables.add(ROOF);
		variables.add(HAS_CHASSIS);
		variables.add(HAS_FRONT_LEFT_WHEEL);
		variables.add(HAS_FRONT_RIGHT_WHEEL);
		variables.add(HAS_REAR_LEFT_WHEEL);
		variables.add(HAS_REAR_RIGHT_WHEEL);
		variables.add(HAS_BODY);
		variables.add(HAS_WHEELS);               
                                                                              
		//Précondition1 
		Map<Variable,String> PRE1 = new HashMap<>();
		PRE1.put(HAS_CHASSIS,"TRUE");                            
                //effet1
		Map<Variable,String> EFF1 = new HashMap<>();
		EFF1.put(HAS_BODY,"TRUE");
                //effet11
		Map<Variable,String> EFF11 = new HashMap<>();
		EFF11.put(HAS_BODY,"FALSE");
                
                //Précondition2
		Map<Variable,String> PRE2 = new HashMap<>();		
		PRE2.put(HAS_CHASSIS,"TRUE");				
		//effet2
		Map<Variable,String> EFF2 = new HashMap<>();
		EFF2.put(HAS_FRONT_RIGHT_WHEEL, "TRUE");
                //effet22
		Map<Variable,String> EFF22 = new HashMap<>();
		EFF22.put(HAS_FRONT_RIGHT_WHEEL, "FALSE");
                
                //Précondition3
		Map<Variable,String> PRE3= new HashMap<>();		
		PRE3.put(HAS_CHASSIS,"TRUE");				                                
		//effet3
		Map<Variable,String> EFF3 = new HashMap<>();
		EFF3.put(HAS_FRONT_LEFT_WHEEL, "TRUE");
                //effet3
		Map<Variable,String> EFF33 = new HashMap<>();
		EFF33.put(HAS_FRONT_LEFT_WHEEL, "FALSE");
                
                //Précondition4
		Map<Variable,String> PRE4= new HashMap<>();		
		PRE4.put(HAS_CHASSIS,"TRUE");				                                
		//effet4
		Map<Variable,String> EFF4 = new HashMap<>();
		EFF4.put(HAS_REAR_LEFT_WHEEL, "TRUE");
                //effet44
		Map<Variable,String> EFF44 = new HashMap<>();
		EFF44.put(HAS_REAR_LEFT_WHEEL, "FALSE");
                
                 //Précondition5
		Map<Variable,String> PRE5= new HashMap<>();		
		PRE5.put(HAS_CHASSIS,"TRUE");				                                
		//effet5
		Map<Variable,String> EFF5 = new HashMap<>();
		EFF5.put(HAS_REAR_RIGHT_WHEEL, "TRUE");
                //effet55
		Map<Variable,String> EFF55 = new HashMap<>();
		EFF55.put(HAS_REAR_RIGHT_WHEEL, "FALSE");
                
                  //Précondition6
		Map<Variable,String> PRE6= new HashMap<>();		
		PRE6.put(HAS_CHASSIS,"TRUE");				                                
		//effet6
		Map<Variable,String> EFF6 = new HashMap<>();
		EFF6.put(HAS_WHEELS, "TRUE");
                //effet66
		Map<Variable,String> EFF66 = new HashMap<>();
		EFF66.put(HAS_WHEELS, "FALSE");
                
                  //Précondition7
		Map<Variable,String> PRE7= new HashMap<>();		
		PRE7.put(HAS_BODY,"TRUE");				                                
		//effet7
		Map<Variable,String> EFF7 = new HashMap<>();
		EFF7.put(LEFT, "WHITE");
                //effet77
		Map<Variable,String> EFF77 = new HashMap<>();
		EFF77.put(LEFT, "BLACK");
                
                  //Précondition8
		Map<Variable,String> PRE8= new HashMap<>();		
		PRE8.put(HAS_BODY,"TRUE");				                                
		//effet8
		Map<Variable,String> EFF8 = new HashMap<>();
		EFF8.put(REAR, "WHITE");
                //effet88
		Map<Variable,String> EFF88 = new HashMap<>();
		EFF88.put(REAR, "BLACK");
                
                  //Précondition9
		Map<Variable,String> PRE9= new HashMap<>();		
		PRE9.put(HAS_BODY,"TRUE");				                                
		//effet9
		Map<Variable,String> EFF9 = new HashMap<>();
		EFF9.put(RIGHT, "WHITE");
                //effet99
		Map<Variable,String> EFF99 = new HashMap<>();
		EFF99.put(RIGHT, "BLACK");
                
                  //Précondition10
		Map<Variable,String> PRE10= new HashMap<>();		
		PRE10.put(HAS_BODY,"TRUE");				                                
		//effet10
		Map<Variable,String> EFF10 = new HashMap<>();
		EFF10.put(ROOF, "WHITE");
                //effet1010
		Map<Variable,String> EFF1010 = new HashMap<>();
		EFF1010.put(ROOF, "BLACK");
                
                 //Précondition11
		Map<Variable,String> PRE121= new HashMap<>();		
		PRE121.put(HAS_BODY,"TRUE");				                                
		//effet11
		Map<Variable,String> EFF121 = new HashMap<>();
		EFF121.put(FRONT, "WHITE");
                //effet1221
		Map<Variable,String> EFF1221 = new HashMap<>();
		EFF1221.put(FRONT, "BLACK");
                
                //les différentes actions possibles
                Action act1 = new Action(PRE1,EFF1);
                Action act11 = new Action(PRE1,EFF11);
                
                Action act2 = new Action(PRE2,EFF2);
                Action act22 = new Action(PRE2,EFF22);                            
                
                Action act3 = new Action(PRE3,EFF3);                                 		                                                                             
                Action act33 = new Action(PRE3,EFF33);
                
                Action act4 = new Action(PRE4,EFF4);                               
                Action act44 = new Action(PRE4,EFF44);
              
                Action act5 = new Action(PRE5,EFF5);
                Action act55 = new Action(PRE5,EFF55);
                
                Action act6 = new Action(PRE6,EFF6);
                Action act66 = new Action(PRE6,EFF66);                            
                
                Action act7 = new Action(PRE7,EFF7);                                 		                                                                             
                Action act77 = new Action(PRE7,EFF77);
                
                Action act8 = new Action(PRE8,EFF8);                               
                Action act88 = new Action(PRE8,EFF88);
              
                Action act9 = new Action(PRE9,EFF9);                                 		                                                                             
                Action act99 = new Action(PRE9,EFF99);
                
                Action act10 = new Action(PRE10,EFF10);                               
                Action act1010 = new Action(PRE10,EFF1010);
                
                Action act121 = new Action(PRE121,EFF121);                               
                Action act1221 = new Action(PRE121,EFF1221);


//Decommenter pour tester dfs simple
    
                //les actions dans une liste
                Set<Action> listeAction = new HashSet<Action>();
                listeAction.add(act1);
                //listeAction.add(act11);
                listeAction.add(act2);
                //listeAction.add(act22);
                listeAction.add(act3);
                //listeAction.add(act33);
                listeAction.add(act4);
                //listeAction.add(act44);
                listeAction.add(act5);
                //listeAction.add(act55);
                listeAction.add(act6);
                //listeAction.add(act66);
                listeAction.add(act7);
                listeAction.add(act77);
                listeAction.add(act8);
                listeAction.add(act88);
                listeAction.add(act9);
                listeAction.add(act99);
                listeAction.add(act10);
                listeAction.add(act1010);
                listeAction.add(act121);
                listeAction.add(act1221);
                
                //System.out.println("listeAction : "+listeAction);
                //générer un état coherent                
                AssemblyLine chaineAssemblage = new AssemblyLine(variables);
                State etat_initial = chaineAssemblage.conditionInitiale();
                State etatCoherent = chaineAssemblage.genereEtatCoherent();
                        
                System.out.println("Etat initial :\n"+etat_initial);             
                System.out.println("Etat final :\n"+ etatCoherent+"----\n");                                
         
                //Planning problem: algo recherche en profondeur recursive
                PlanningProblem planProblem = new PlanningProblem(etat_initial,etatCoherent,listeAction);
                Set<State> etats= new HashSet<State>();
        
                //parcours en profondeur
                //Stack dfs = planProblem.dfs(etat_initial,new Stack(),new HashSet<State>());
                //System.out.println("dfs : "+dfs+"\n"+etatCoherent+" \n");
                
                //parcours en profondeur itératif
                /*Stack dfs_it = planProblem.dfs_iterative(etat_initial);
                System.out.println("dfs : "+dfs_it+"\n"+etatCoherent+" \n"); */
                    
                //parcours en profondeur limité
//               Stack dls = planProblem.dls(etat_initial, new Stack<Action>(),new HashSet<State>(),7);
//               System.out.println("dfs : "+dls+"\n"+etatCoherent+" \n");

                 //parcours en profondeur limité
               Stack bfs = planProblem.bfs();
               System.out.println("bfs : "+bfs+"\n"+etatCoherent+" \n");
    
        }
}


