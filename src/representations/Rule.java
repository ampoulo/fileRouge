package representations;

import java.util.*;
import java.util.Map.Entry;
import tools.Tools;

public class Rule implements Constraint {

    protected final Map<Variable, String> premisse;
    protected final Map<Variable, String> conclusion;

    //  premisse :  règle toit_ouvrant = true && couleur_gauche = bleu ( avant flèche)
    // conclusion
    public Rule(Map<Variable, String> premisse, Map<Variable, String> conclusion) {

        this.premisse = premisse;
        this.conclusion = conclusion;
    }

    @Override
    public Set<Variable> getScope() {

        Set<Variable> monScope = new HashSet();

        monScope.addAll(getConclusion().keySet());
        monScope.addAll(getPremisse().keySet());

        return monScope;
    }

    // un && logique
    public boolean Premisse(Map<Variable, String> ChoixClient) { // premisse est un && 

        for (Entry<Variable, String> entry : this.getPremisse().entrySet()) {

            Variable pVariable = entry.getKey();
            String pValeur = entry.getValue();

            if (Tools.containsVariable(ChoixClient, pVariable)) {

                if (!Tools.getValue(ChoixClient, pVariable).equals(pValeur)) {

                    return false;
                }

            } else {

                return false;
            }
        }
        return true;
    }

    /**
     * Filtre de la premisse
     *
     * @param assigne
     * @param nonAssigne
     * @return
     */
    public boolean filterPrimesse(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {

        ArrayList<Variable> InNonAss = Tools.variableInConstraint(this.premisse, nonAssigne); // recuperation des varable de la premise qui ne sont par encore assigne 
        ArrayList<Variable> InAss = Tools.variableConstraintInAssigne(assigne, this.premisse); // recuperation des varable de la premise qui sont deja assigne 
       
        
        for (Variable v : this.getScope()) {
            // on verrification de la presente de tout la varriable scope
            if (!Tools.inArrayList(InAss, v) && !Tools.inArrayList(InNonAss, v)) {
                return false;
            }
        }

        if (InNonAss.isEmpty() || InAss.isEmpty()) { // cas on aussi variable ou tout les varriable son asigne 

            return false;
        }

        String valeurVariablePremise;

        for (int i = 0; i < InAss.size(); i++) {// on veriffie que les varaible de les variable deja assigne respect la contrainte 

            valeurVariablePremise = Tools.getValue(this.premisse, InAss.get(i));

            if (!Tools.getValue(assigne, InAss.get(i)).equals(valeurVariablePremise)) {

                Tools.cleanDomainFritrage(nonAssigne, InNonAss);

                return true;
            }

        }

        boolean filtrage = false;

        for (int j = 0; j < InNonAss.size(); j++) { // Reduction des domain des varaible si les non assigne respect la contrainte 

            valeurVariablePremise = Tools.getValue(this.premisse, InNonAss.get(j)); // recuperation de la valeur de variable non asigne dans la premisse 

            for (int k = 0; k < nonAssigne.get(InNonAss.get(j)).size(); k++) { // filttrage du domaine 

                if (!nonAssigne.get(InNonAss.get(j)).get(k).equals(valeurVariablePremise)) {

                    nonAssigne.get(InNonAss.get(j)).remove(k);

                    k--;

                    filtrage = true;

                }

            }

        }

        return filtrage;
    }

    // un || logique
    public boolean Conclusion(Map<Variable, String> ChoixClient) { // la conclusion un ou 

        for (Entry<Variable, String> entry : this.getConclusion().entrySet()) {

            Variable variable = entry.getKey();
            String valeur = entry.getValue();

            if (Tools.containsVariable(ChoixClient, variable)) {

                if (Tools.getValue(ChoixClient, variable).equals(valeur)) {

                    return true;
                }

            } else {

                return false;
            }

        }

        return false;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> ChoixClient) {

        return Premisse(ChoixClient) && Conclusion(ChoixClient);
    }

    /**
     * @return the premisse
     */
    protected Map<Variable, String> getPremisse() {

        return premisse;
    }

    /**
     * @return the conclusion
     */
    public Map<Variable, String> getConclusion() {
        return conclusion;
    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {

        return this.filterPrimesse(assigne, nonAssigne);
    }

}
