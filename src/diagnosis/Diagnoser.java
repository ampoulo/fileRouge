package diagnosis;

import ppc.*;
import java.util.*;
import tools.Tools;
import representations.*;

/**
 *
 * @author nokenn
 */
public class Diagnoser {

    ArrayList<Variable> variables; // liste de varriable de la contriante avce leur donaine 
    ArrayList<Constraint> contraintes; // L'essemble des contrainte sur les variable
    private Map<Variable, String> C; // Grand c une explication non minima au sens  de l'inclusion 

    Backtracking back;

    /**
     *
     * @param vars
     * @param contr
     */
    public Diagnoser(ArrayList<Variable> vars, ArrayList<Constraint> contr) {

        this.variables = vars;
        this.contraintes = contr;
        this.C = new HashMap<>();
        back = new Backtracking(this.contraintes, new HeuristiqueRandom());
    }

    /**
     * ajouter/retirer un couple variable/valeur
     *
     * @param v
     * @param s
     */
    public void ajouter(Variable v, String s) {

        this.reduireDomaine(v, s);
        
        getC().put(v, s);
    }

    /**
     *
     * @param v
     * @param s
     */
    public void retirer(Variable v, String s) {

        getC().remove(v, s);
    }

    /**
     * reduit le domaine seulement à la valeur au quelle la variable va être
     * associer
     *
     * @param v
     * @param s
     */
    private void reduireDomaine(Variable v, String s) {

        for (int i = 0; i < v.getDomaine2().size(); i++) {

            if (!v.getDomaine2().get(i).equals(s)) {

                v.getDomaine2().remove(i);
                i--;
            }
        }

    }

    /**
     * Cette methode va permetre d'ajout l'ensemble de variable de la contrainte
     * qui ne sont pas dans la Map d'un sous ensemble de grand C plus le choix
     * interdit
     *
     * @param CplusChoixInterdit
     */
    private void addVariableContrainte(Map<Variable, String> CplusChoixInterdit) {

        for (int i = 0; i < this.variables.size(); i++) {

            if (!Tools.containsVariable(CplusChoixInterdit, this.variables.get(i))) {

                CplusChoixInterdit.put(this.variables.get(i), "");
            }

        }
    }

    /**
     * VA me retoutn un essemble de variable non assigne en partant d'un map
     * pour la Method solution de backtracking
     *
     * @param c
     * @return
     */
    private ArrayList<Variable> mapToArrayList(Map<Variable, String> c) {

        ArrayList<Variable> nonAss = new ArrayList<>();

        for (Map.Entry<Variable, String> variable : c.entrySet()) {

            nonAss.add(variable.getKey());

        }
        return nonAss;
    }

    /**
     * Method qui prend un arrayList de variable aev un domaine contenant une
     * variable
     *
     * @param list
     * @return
     */
    private Map<Variable, String> ArrayListToMap(ArrayList<Variable> list) {

        Map<Variable, String> map = new HashMap();

        for (int i = 0; i < list.size(); i++) {

            map.put(list.get(i), list.get(i).getDomaine2().get(0));
        }

        return map;
    }

    /**
     * Cette fonction me permet de clone un map
     *
     * @param c
     * @return
     */
    private Map<Variable, String> cloneMap(Map<Variable, String> c) {

        Map<Variable, String> clone = new HashMap<>();

        for (Map.Entry<Variable, String> v : c.entrySet()) {

            clone.put(new Variable(v.getKey().getNom(), (ArrayList<String>) v.getKey().getDomaine2().clone()), v.getValue());
        }
        return clone;
    }

    /**
     * Retourne si une sous ensemble est une explication
     *
     * @param v
     * @param s
     * @param c
     * @return
     */
    public boolean isExplication(Map<Variable, String> c, Variable v, String s) {

        ArrayList<Variable> nonAss;

        Map<Variable, String> clone = cloneMap(c);

        this.reduireDomaine(v, s);

        clone.put(v, s);

        this.addVariableContrainte(clone);

        nonAss = this.mapToArrayList(clone);

        back.solution(nonAss, 0);

        return !back.moinunesolution();
    }

    /**
     * Permet de savoir si dans un liste de sous Ensemble de c contient au moin
     * une explication
     *
     * @param desSousEnsemble
     * @param v
     * @param s
     * @return
     */
    private boolean explicationIn(ArrayList<ArrayList<Variable>> SousEnsemble, Variable v, String s) {

        for (int i = 0; i < SousEnsemble.size(); i++) {

            if (this.isExplication(this.ArrayListToMap(SousEnsemble.get(i)), v, s)) {

                return true;
            }
        }

        return false;
    }

    /**
     * @param v
     * @param s
     * @return
     */
    public ArrayList<Map<Variable, String>> dignoticInclusion(Variable v, String s) {

        Map<Variable, String> tmpMap = null;

        ArrayList<Map<Variable, String>> diagnostic = new ArrayList<>();

        ArrayList<ArrayList<Variable>> sousC = Tools.powerset(this.mapToArrayList(this.getC()));

        for (int i = 0; i < sousC.size(); i++) {

            tmpMap = this.ArrayListToMap(sousC.get(i));

            if (this.isExplication(tmpMap, v, s)) {

                if (!this.explicationIn(Tools.powerset(sousC.get(i)), v, s)) {

                    diagnostic.add(tmpMap);
                }
            }

        }

        return diagnostic;
    }

    public Map<Variable, String> diagnoticCadinalite(Variable v, String s) {

        int taille = 1;

        Map<Variable, String> diagnoC = new HashMap();

        ArrayList<Map<Variable, String>> diagno = this.dignoticInclusion(v, s);

        for (int i = 0; i < diagno.size(); i++) {

            if (diagno.get(i).size() <= taille) {

                taille = diagno.get(i).size();
                diagnoC = diagno.get(i);

            }

        }

        return diagnoC;
    }

    /**
     * @return the C
     */
    public Map<Variable, String> getC() {
        return C;
    }

}
