package representations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import tools.Tools;

public class AllEqualConstraint implements Constraint {

    private Map<Variable, String> contrainte;

    public AllEqualConstraint(Map<Variable, String> variable) {

        this.contrainte = variable;

    }

    @Override
    public Set<Variable> getScope() {

        return contrainte.keySet();
    }

    @Override
    // On teste si les valeurs sont les mêmes 
    public boolean isSatisfiedBy(Map<Variable, String> choixclient) {

        int unefoi = 0;

        String equal = "";

        for (Entry<Variable, String> entry : this.contrainte.entrySet()) {

            Variable cVariable = entry.getKey();// Recuperation de la clef de ma contrainte

            if (Tools.containsVariable(choixclient, cVariable)) { // Verification si cette clef se trouve dans le choixClient

                if (unefoi == 0) { // la premier fois on recupere la valeur de associe a la premier clef dans le choixclient 

                    equal = Tools.getValue(choixclient, cVariable); //recuperation 

                    unefoi = 1; // on mes unefoi a 1  

                } else {

                    if (!equal.equals(Tools.getValue(choixclient, cVariable))) { // comparaison avec les autre valeur asssocier au choixclient  

                        return false;
                    }
                }

            } else { // cas ou une varariable du choix clien n'est pas dans la contrainte

                return false;
            }

        }
        return true;
    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {

        ArrayList<Variable> InNonAss = Tools.variableInConstraint(this.contrainte, nonAssigne); // recuperation des varable de la premise qui ne sont par encore assigne 
        ArrayList<Variable> InAss = Tools.variableConstraintInAssigne(assigne, this.contrainte); // recuperation des varable de la premise qui sont deja assigne 
        
        for (Variable v : this.getScope()) {
            // verfication que tout les variable du scope soit present dans InNonAss union InAss
            if (!Tools.inArrayList(InAss, v) && !Tools.inArrayList(InNonAss, v)) {
                return false;
            }
        }

        if (InNonAss.isEmpty() || InAss.isEmpty()) {

            return false;
        }

        String val = Tools.getValueOfConstraintInAssigne(assigne, contrainte); // recupertation d'un valeur assige a une des variable de la contrainte 

        for (int k = 0; k < InAss.size(); k++) {

            if (!Tools.getValue(assigne, InAss.get(k)).equals(val)) {

                Tools.cleanDomainFritrage(nonAssigne, InNonAss);

                return true;
            }

        }

        boolean filtrage = false;

        for (int i = 0; i < InNonAss.size(); i++) {

            for (int j = 0; j < nonAssigne.get(InNonAss.get(i)).size(); j++) {

                if (!nonAssigne.get(InNonAss.get(i)).get(j).equals(val)) {

                    nonAssigne.get(InNonAss.get(i)).remove(j);

                    j--;
                    filtrage = true;

                }

            }

        }

        return filtrage;
    }

}
