package planning.example;
import planning.Action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import planning.State;


import representations.Variable;


public class AssemblyLine {

    //initialisation du domaine des variables booleens
    public static final Set<String> BOOLEAN_DOMAIN = new HashSet<String>(){{
        add("TRUE");
        add("FALSE");
    }};

    //initialisation du domaine des variables prenant une couleur
    public static final Set<String> ALL_COLORS= new HashSet<String>(){{
        //add("GRAY");
	add("BLACK");
	add("WHITE");
	add("RED");
	add("GREEN");
	//add("BLUE");
	//add("ORANGE");
	//add("YELLOW");
    }};
    //déclarations des variables
    public static final Variable HAS_CHASSIS = new Variable("HAS_CHASSIS",BOOLEAN_DOMAIN);
    public static final Variable HAS_FRONT_LEFT_WHEEL = new Variable("HAS_FRONT_LEFT_WHEEL",BOOLEAN_DOMAIN);
    public static final Variable HAS_FRONT_RIGHT_WHEEL = new Variable("HAS_FRONT_RIGHT_WHEEL",BOOLEAN_DOMAIN);
    public static final Variable HAS_REAR_LEFT_WHEEL= new Variable("HAS_REAR_LEFT_WHEEL",BOOLEAN_DOMAIN);
    public static final Variable HAS_REAR_RIGHT_WHEEL= new Variable("HAS_REAR_RIGHT_WHEEL",BOOLEAN_DOMAIN);
    public static final Variable HAS_BODY =new Variable("HAS_BODY",BOOLEAN_DOMAIN);

    public static final Variable FRONT_LEFT_WHEEL_COLOR = new Variable("FRONT_LEFT_WHEEL_COLOR",ALL_COLORS);
    public static final Variable FRONT_RIGHT_WHEEL_COLOR = new Variable("FRONT_RIGHT_WHEEL_COLOR",ALL_COLORS);
    public static final Variable REAR_LEFT_WHEEL_COLOR = new Variable("REAR_LEFT_WHEEL_COLOR",ALL_COLORS);
    public static final Variable REAR_RIGHT_WHEEL_COLOR = new Variable("REAR_RIGHT_WHEEL_COLOR",ALL_COLORS);

    public static final Variable FRONT = new Variable("FRONT",ALL_COLORS);
    public static final Variable LEFT = new Variable("LEFT",ALL_COLORS);
    public static final Variable REAR =new Variable("REAR",ALL_COLORS);
    public static final Variable RIGHT =new Variable("RIGHT",ALL_COLORS);
    public static final Variable ROOF =new Variable("ROOF",ALL_COLORS);

    //Variables indiquant la présence ou l'absence de pieces
    public static final Set<Variable> HAS_PIECES = new HashSet<Variable>(){{
        add(HAS_CHASSIS);
        add(HAS_FRONT_LEFT_WHEEL);
        add(HAS_FRONT_RIGHT_WHEEL);
        add(HAS_REAR_LEFT_WHEEL);
        add(HAS_REAR_RIGHT_WHEEL);
        add(HAS_BODY);
    }};

    //Variables indiquant les couleur des pneus
    public static final Set<Variable> X_Y_WHEEL_COLOR= new HashSet<Variable>(){{
        add(FRONT_LEFT_WHEEL_COLOR);
        add(FRONT_RIGHT_WHEEL_COLOR);
        add(REAR_LEFT_WHEEL_COLOR);
        add(REAR_RIGHT_WHEEL_COLOR);
    }};

    //Variables indiquant les couleurs des côtés de la voiture
    public static final Set<Variable> X_COLOR= new HashSet<Variable>(){{
        add(FRONT);
	add(LEFT);
	add(REAR);
	add(RIGHT);
	add(ROOF);
    }};

    //ajout de toutes les variables
    public static final Set<Variable> variables = new HashSet<Variable>(){{
        addAll(HAS_PIECES);
        addAll(X_Y_WHEEL_COLOR);
        addAll(X_COLOR);
    }};

    //Actions installant une seule piece
    public static final Set<Action> INSTALL_PIECES(){
        Set<Action> install_pieces = new HashSet<Action>();
        install_pieces.add(new Action(null,IHAS_CHASSIS));

        for(Variable xy : HAS_PIECES){
            if(xy.getNom()!="HAS_CHASSIS"){
            Map<Variable,String> xycol = new HashMap<Variable,String>(){{put(xy,"TRUE");}};
            Action act = new Action(IHAS_CHASSIS,xycol);
            install_pieces.add(act);                    }
        }
        return install_pieces;
    }

    //Actions installant parallement deux pneus
    public static final Set<Action> WHEEL_PARALLEL_INSTALL(){
        Set<Action> install_wheels = new HashSet<>();
        Map<Variable,String> LEFT_WHEELS = new HashMap<Variable,String>(){{
            put(HAS_FRONT_LEFT_WHEEL,"TRUE");
            put(HAS_REAR_LEFT_WHEEL,"TRUE");
        }};
        Action INSTALL_LEFT_WHEELS = new Action(IHAS_CHASSIS,LEFT_WHEELS);

        Map<Variable,String> RIGHT_WHEELS = new HashMap<Variable,String>(){{
            put(HAS_FRONT_RIGHT_WHEEL,"TRUE");
            put(HAS_REAR_RIGHT_WHEEL,"TRUE");
        }};
        Action INSTALL_RIGHT_WHEELS = new Action(IHAS_CHASSIS,RIGHT_WHEELS);

        Map<Variable,String> REAR_WHEELS = new HashMap<Variable,String>(){{
            put(HAS_REAR_RIGHT_WHEEL,"TRUE");
            put(HAS_REAR_LEFT_WHEEL,"TRUE");
        }};
        Action INSTALL_REAR_WHEELS = new Action(IHAS_CHASSIS,REAR_WHEELS);

        Map<Variable,String> FRONT_WHEELS = new HashMap<Variable,String>(){{
            put(HAS_FRONT_LEFT_WHEEL,"TRUE");
            put(HAS_FRONT_RIGHT_WHEEL,"TRUE");
        }};
        Action INSTALL_FRONT_WHEELS = new Action(IHAS_CHASSIS,FRONT_WHEELS);

        install_wheels.add(INSTALL_FRONT_WHEELS);
        install_wheels.add(INSTALL_REAR_WHEELS);
        install_wheels.add(INSTALL_RIGHT_WHEELS);
        install_wheels.add(INSTALL_LEFT_WHEELS);

        return install_wheels;
    }
    //actions pour une
    public static final Set<Action> PAINT_ROOF(){
        Set<Action> paint_body = new HashSet<Action>();
        //for(Variable var : X_COLOR){
            for(String str: ROOF.getDomaine()){
                if(str!="GRAY"){//la couleur GRAY est exclue car elle represente absence coloriée
                    HashMap<Variable,String> paint_roof = new HashMap<Variable,String>(){{ put(ROOF,str);}};
                    paint_body.add(new Action(IHAS_BODY,paint_roof));
                }
            }
        //}
        return paint_body;
    }

    //actions de peinture d'un seul pneu dans X_Y_WHEEL avec preconditions
    public static final Set<Action> X_Y_WHEEL_PAINT(){
        Set<Action> paint_x_y_wheel = new HashSet<>();
        Map<Variable,String> precondition =null;
        for(Variable xy : X_Y_WHEEL_COLOR){
            if(xy.getNom()=="FRONT_LEFT_WHEEL_COLOR")
                precondition = IHAS_FRONT_LEFT_WHEEL;
            else if((xy.getNom()=="FRONT_RIGHT_WHEEL_COLOR"))
                    precondition = IHAS_FRONT_RIGHT_WHEEL;
                else if((xy.getNom()=="REAR_LEFT_WHEEL_COLOR"))
                        precondition = IHAS_REAR_LEFT_WHEEL;
                    else
                        precondition = IHAS_REAR_RIGHT_WHEEL;
            for(String str : xy.getDomaine()){
                if(str!="GRAY"){//GRAY est exclue car elle represente l'absence de couleur
                    Map<Variable,String> xycol = new HashMap<Variable,String>(){{put(xy,str);}};
                    Action act = new Action(precondition,xycol);
                    paint_x_y_wheel.add(act);
                }
            }
        }
        return paint_x_y_wheel;
    }

    //Actions de peinture à large effet
    public static Set<Action> LARGE_EFFECT_PAINT(){
        Set<Action> large_effect_paint = new HashSet<Action>();
        //peindre le côté gauche(LEFT) et le toit
        Map<Variable,String> PAINT_LEFT_PRECONDITIONS = new HashMap<Variable,String>(){{
            put(HAS_FRONT_LEFT_WHEEL,"TRUE");
            put(HAS_REAR_LEFT_WHEEL,"TRUE");
            put(HAS_BODY,"TRUE");
        }};
        for(String color:ALL_COLORS){
            if(color!="GRAY"){
                Map<Variable,String> PAINT_LEFT_EFFECTS = new HashMap<Variable,String>(){{
                    put(FRONT_LEFT_WHEEL_COLOR,color);
                    put(REAR_LEFT_WHEEL_COLOR,color);
                    put(LEFT,color);
                    put(ROOF,color);
                }};
                large_effect_paint.add(new Action(PAINT_LEFT_PRECONDITIONS, PAINT_LEFT_EFFECTS));
            }
        }

        //peindre le côté droit et le toit
        Map<Variable,String> PAINT_RIGHT_PRECONDITIONS = new HashMap<Variable,String>(){{
            put(HAS_FRONT_RIGHT_WHEEL,"TRUE");
            put(HAS_REAR_RIGHT_WHEEL,"TRUE");
            put(HAS_BODY,"TRUE");
        }};
        for(String color:ALL_COLORS){
            if(color!="GRAY"){
                Map<Variable,String> PAINT_RIGHT_EFFECTS = new HashMap<Variable,String>(){{
                    put(FRONT_RIGHT_WHEEL_COLOR,color);
                    put(REAR_RIGHT_WHEEL_COLOR,color);
                    put(RIGHT,color);
                    put(ROOF,color);
                }};
                large_effect_paint.add(new Action(PAINT_RIGHT_PRECONDITIONS, PAINT_RIGHT_EFFECTS));
            }
        }
        //peindre devant et le toit
        Map<Variable,String> PAINT_REAR_PRECONDITIONS = new HashMap<Variable,String>(){{
            put(HAS_REAR_RIGHT_WHEEL,"TRUE");
            put(HAS_REAR_LEFT_WHEEL,"TRUE");
            put(HAS_BODY,"TRUE");
        }};
        for(String color:ALL_COLORS){
            if(color!="GRAY"){
                Map<Variable,String> PAINT_REAR_EFFECTS = new HashMap<Variable,String>(){{
                    put(REAR_RIGHT_WHEEL_COLOR,color);
                    put(REAR_LEFT_WHEEL_COLOR,color);
                    put(REAR,color);
                    put(ROOF,color);
                }};
                large_effect_paint.add(new Action(PAINT_REAR_PRECONDITIONS, PAINT_REAR_EFFECTS));
            }
        }
         //peindre devant et le toit
        Map<Variable,String> PAINT_FRONT_PRECONDITIONS = new HashMap<Variable,String>(){{
            put(HAS_FRONT_RIGHT_WHEEL,"TRUE");
            put(HAS_FRONT_LEFT_WHEEL,"TRUE");
            put(HAS_BODY,"TRUE");
        }};
        for(String color:ALL_COLORS){
            if(color!="GRAY"){
                Map<Variable,String> PAINT_FRONT_EFFECTS = new HashMap<Variable,String>(){{
                    put(FRONT_RIGHT_WHEEL_COLOR,color);
                    put(FRONT_LEFT_WHEEL_COLOR,color);
                    put(FRONT,color);
                    put(ROOF,color);
                }};
                large_effect_paint.add(new Action(PAINT_FRONT_PRECONDITIONS, PAINT_FRONT_EFFECTS));
            }
        }

        return large_effect_paint;

    }

    //ajout de toutes les actions possible
   public Set<Action> actionListe = new HashSet<Action>(){{
       addAll(INSTALL_PIECES());
       addAll(WHEEL_PARALLEL_INSTALL());
       addAll(PAINT_ROOF());
       addAll(LARGE_EFFECT_PAINT());
       //addAll(X_Y_WHEEL_PAINT());

   }};

    /*Assignation des variables d'installation*/
    public static final Map<Variable,String> IHAS_CHASSIS = new HashMap<Variable,String>(){{
        for(Variable var : HAS_PIECES){
            if(var.getNom()=="HAS_CHASSIS")
                put(var,"TRUE");
        }
    }};

    public static final Map<Variable,String> IHAS_BODY = new HashMap<Variable,String>(){{
        for(Variable var : HAS_PIECES){
            if(var.getNom()=="HAS_BODY")
                put(var,"TRUE");
        }
    }};

    public static final Map<Variable,String> IHAS_FRONT_LEFT_WHEEL = new HashMap<Variable,String>(){{
        for(Variable var : HAS_PIECES){
            if(var.getNom()=="HAS_FRONT_LEFT_WHEEL")
                put(var,"TRUE");
        }
    }};

    public static final Map<Variable,String> IHAS_FRONT_RIGHT_WHEEL = new HashMap<Variable,String>(){{
        for(Variable var :HAS_PIECES){
            if(var.getNom()=="HAS_FRONT_RIGHT_WHEEL")
                put(var,"TRUE");
        }
    }};

    public static final Map<Variable,String> IHAS_REAR_RIGHT_WHEEL = new HashMap<Variable,String>(){{
        for(Variable var : HAS_PIECES){
            if(var.getNom()=="HAS_REAR_RIGHT_WHEEL")
                put(var,"TRUE");
        }
    }};

    public static final Map<Variable,String> IHAS_REAR_LEFT_WHEEL = new HashMap<Variable,String>(){{
        for(Variable var : HAS_PIECES){
            if(var.getNom()=="HAS_REAR_LEFT_WHEEL")
                put(var,"TRUE");
        }
    }};

    //Condition initiale d'une voiture
    public State conditionInitiale(){
        Map<Variable, String> ensemble = new HashMap<Variable, String>();
            for(Variable var : HAS_PIECES)
                ensemble.put(var, "FALSE");
            for(Variable var : X_Y_WHEEL_COLOR)
                ensemble.put(var,"GRAY");
            for(Variable var : X_COLOR)
                ensemble.put(var,"GRAY");

	return new State(ensemble);
    }



    //afficher la liste des actions disponibles
    public String toStringActions(Collection<Action> actionListe){
            String str="";
            for(Action act: actionListe){
                str+=(act!=null)?act+"\n":"";
            }
            return str;
    }

	//generateur d'un etat coherent
	public State genereEtatCoherent(){
            State etat = conditionInitiale();
            while(!isEtatCoherent(etat)){
                Action action_prochain = (Action)actionListe.toArray()[new Random().nextInt(actionListe.size())];
                etat = etat.apply(action_prochain);
            }
            return etat;
        }

        //cette methode est utilisée sert à tester la coherence d'un etat
	public boolean isEtatCoherent(State state){
            for(Variable var : state.getEnsemble().keySet()){
                if(state.getEnsemble().get(var)==("false") || state.getEnsemble().get(var)==("GRAY"))
                    return false;
            }
            return true;
        }


        }
