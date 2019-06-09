package ppc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import representations.*;
import tools.Tools;

/**
 *
 * @author nokenn
 */
public class Backtracking {

    private final ArrayList<Constraint> constraint; // liste de tout les contrainte 
    private final Map<Variable, String> assigne; //
    private final Heuristique heuristique;
    private final static int AFFICHE_SOLUTION = 1;
    private int numberSolution = 0;

    public Backtracking(ArrayList<Constraint> constraint, Heuristique heuristique) {

        this.constraint = constraint;
        this.heuristique = heuristique;
        assigne = new HashMap();
    }

    /**
     *
     * @param scope
     * @return
     */
    private boolean compareScope(Set<Variable> scope) {

        Iterator<Variable> itScope = scope.iterator();

        while (itScope.hasNext()) {

            if (!Tools.containsVariable(this.assigne, itScope.next())) {

                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    private Boolean consistent() {

        for (int i = 0; i < this.constraint.size(); i++) {

            if (compareScope(this.constraint.get(i).getScope())) {

                if (!this.constraint.get(i).isSatisfiedBy(this.assigne)) {

                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Afiche tout les solution et retoutn nul si il ya pas de solution
     *
     * @param nonAss
     * @param affiche
     * @return
     */
    public Map<Variable, String> solution(ArrayList<Variable> nonAss, int affiche) {

        if (nonAss.isEmpty()) {

            this.numberSolution++;

            if (affiche == AFFICHE_SOLUTION) {

                Tools.afficheMapVariableString(assigne);
            }

        } else {

            Variable x = this.heuristique.getNextAssignation(nonAss); // choix d'un variable a assigne par l'heuristique 
            nonAss.remove(x);

            for (String i = x.nextDomValue(); i != null; i = x.nextDomValue()) { // parcour du domaine de la variable courante

                this.assigne.put(x, i);

                if (this.consistent()) {

                    Map<Variable, String> r = new HashMap();

                    r = solution(nonAss, affiche);

                    if (r != null) {

                        return r;
                    }

                }
            }

            this.assigne.remove(x);
            nonAss.add(x);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public boolean moinunesolution() {

        System.out.println("NOMBRE DE SOLUTION " + this.numberSolution);
        
        if (this.numberSolution == 0) {

            return false;
        }
        this.numberSolution = 0;
        return true;
    }

}
