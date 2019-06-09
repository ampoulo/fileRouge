/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.*;
import representations.*;

/**
 *
 * @author nokenn
 */
public abstract class Tools {

    /**
     * Permet l'affichage d'un variable
     *
     * @param variable
     */
    public static void afficheVariable(Variable variable) {

        System.out.println(variable + " : " + variable.getDomaine2());
    }

    /**
     * AFFICHER UN MAP DE VARIABLE AVEC LEUR VALEUR ASSOCIER
     *
     * @param choixclient
     */
    public static void afficheMapVariableString(Map<Variable, String> choixclient) {

        for (Map.Entry<Variable, String> entry : choixclient.entrySet()) {

            Tools.afficheVariable(entry.getKey());
            System.out.println("Valeur Associer: " + entry.getValue());
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Affichage d'un arrayList de variable
     *
     * @param list
     */
    public static void afficheArrayVariable(ArrayList<Variable> list) {

        for (int i = 0; i < list.size(); i++) {

            Tools.afficheVariable(list.get(i));
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println();
    }

    /**
     * return la valeur associer a une variable dans un Map
     *
     * @param choixclient
     * @param conste
     * @return
     */
    public static String getValue(Map<Variable, String> choixclient, Variable conste) { // 

        for (Map.Entry<Variable, String> entry : choixclient.entrySet()) {

            if (entry.getKey().equals(conste)) {

                return entry.getValue();
            }

        }
        return null;
    }

    /**
     * Affihce un Diagnoctic au sens de l'inclusion
     *
     * @param diagnotic
     */
    public static void afficheDiagnostic(ArrayList<Map<Variable, String>> diagnotic) {

        for (int i = 0; i < diagnotic.size(); i++) {

            Tools.afficheMapVariableString(diagnotic.get(i));

        }

    }

    /**
     * Verrifie si la vatiable est dans la Map
     *
     * @param choixclient
     * @param conste
     * @return
     */
    public static boolean containsVariable(Map<Variable, String> choixclient, Variable conste) { // 

        for (Map.Entry<Variable, String> entry : choixclient.entrySet()) {

            if (entry.getKey().equals(conste)) {

                return true;
            }

        }
        return false;
    }

    /**
     * recupertation d'un valeur assige a une des variable de la contrainte
     *
     * @param assigne
     * @param constraint
     * @return
     */
    public static String getValueOfConstraintInAssigne(Map<Variable, String> constraint, Map<Variable, String> assigne) {

        for (Map.Entry<Variable, String> entry : constraint.entrySet()) {

            if (Tools.containsVariable(assigne, entry.getKey())) {

                return Tools.getValue(assigne, entry.getKey());
            }

        }

        return null;
    }

    /**
     * Permet de retourner la liste des variable nonAssigne appartenant à la
     * constrainte
     *
     * @param constraint
     * @param nonAssigne
     * @return
     */
    public static ArrayList<Variable> variableInConstraint(Map<Variable, String> constraint, Map<Variable, ArrayList<String>> nonAssigne) {

        ArrayList<Variable> In = new ArrayList<>();

        for (Map.Entry<Variable, ArrayList<String>> entry : nonAssigne.entrySet()) {

            if (Tools.containsVariable(constraint, entry.getKey())) {

                In.add(entry.getKey());
            }
        }

        return In;
    }

    /**
     * Permet de retourner un liste de variable de la assignation est contenu
     * dans l'a constraint
     *
     * @param assigne
     * @param constraint
     * @return
     */
    public static ArrayList<Variable> variableConstraintInAssigne(Map<Variable, String> assigne, Map<Variable, String> constraint) {

        ArrayList<Variable> In = new ArrayList<>();

        for (Map.Entry<Variable, String> entry : assigne.entrySet()) {

            if (Tools.containsVariable(constraint, entry.getKey())) {

                In.add(entry.getKey());
            }
        }
        return In;

    }

    /**
     * Va permetre de vider tout les domaine des variable d'un contraint non
     * assigne
     *
     *
     * @param nonAssigne
     * @param InNonAss
     */
    public static void cleanDomainFritrage(Map<Variable, ArrayList<String>> nonAssigne, ArrayList<Variable> InNonAss) {

        for (int i = 0; i < InNonAss.size(); i++) {

            nonAssigne.get(InNonAss.get(i)).clear();

        }

    }

    /**
     *
     * @param list
     * @param verif
     * @return
     */
    public static boolean inArrayList(ArrayList<Variable> list, Variable verif) {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).equals(verif)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Methode retourne tous les sous ensemble d'une Liste de variable (fonction
     * que j'ai recupere sur le site:
     * http://www.rosettacode.org/wiki/Power_set#java)
     *
     * @param list
     * @return
     */
    public static ArrayList<ArrayList<Variable>> powerset(ArrayList<Variable> list) {
        ArrayList<ArrayList<Variable>> ps = new ArrayList<>();

        ps.add(new ArrayList<>());   // add the empty set

        // for every item in the original list
        for (Variable item : list) {

            ArrayList<ArrayList<Variable>> newPs = new ArrayList<>();

            for (ArrayList<Variable> subset : ps) {
                // copy all of the current powerset's subsets

                newPs.add(subset);

                // plus the subsets appended with the current item
                ArrayList<Variable> newSubset = new ArrayList<>(subset);

                newSubset.add(item);

                newPs.add(newSubset);
            }

            // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
            ps = newPs;
        }

        for (int i = 0; i < ps.size(); i++) {

            if (ps.get(i).size() == list.size()) {
                ps.remove(i);

                break;
            }

        }
        return ps;
    }

}
