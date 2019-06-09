/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import representations.*;
import tools.Tools;

/**
 * Cette class permet de test les deux filtrage que j'ai reussi à faire sur la
 * premise de rule et AllequalsConstraint
 *
 * @author nokenn
 */
public class FiltrageTest {

    public static void main(String[] args) {

        Map<Variable, String> constrainte1 = new HashMap();

        Variable toit = new Variable("toit", new ArrayList<>());
        Variable capot = new Variable("capot", new ArrayList<>());
        Variable hayon = new Variable("hayon", new ArrayList<>());
        Variable droit = new Variable("droit", new ArrayList<>());
        Variable gauche = new Variable("gauche", new ArrayList<>());
        Variable sono = new Variable("sono", new ArrayList<>());

        constrainte1.put(toit, "rouge");
        constrainte1.put(capot, "gris");
        constrainte1.put(hayon, "jaune");
        constrainte1.put(droit, "noir");
        constrainte1.put(gauche, "noir");
        constrainte1.put(sono, "rouge");

        AllEqualConstraint all = new AllEqualConstraint(constrainte1);

        Rule rul = new Rule(constrainte1, constrainte1);

        ArrayList<String> dom = new ArrayList();
        dom.add("gris"); //
        dom.add("jaune");//--
        dom.add("bleu");//
        dom.add("rouge");//--
        dom.add("noir");

        ArrayList<String> dom1 = new ArrayList();
        dom1.add("gris");//
        dom1.add("bleu");//
        dom1.add("rouge");//--
        dom1.add("noir");//

        ArrayList<String> dom2 = new ArrayList();
        dom2.add("gris");//
        dom2.add("jaune");//--
        dom2.add("bleu");//
        dom2.add("noir");//

        Variable capot1 = new Variable("capot", dom2);
        Variable hayon1 = new Variable("hayon", dom);
        Variable droit1 = new Variable("droit", dom1);
        Variable gauche1 = new Variable("gauche", dom);
        Variable sono1 = new Variable("sono", dom2);

        Map<Variable, String> assigne = new HashMap();
        assigne.put(toit, "rouge");

        Map<Variable, ArrayList<String>> nonAssigne = new HashMap(); //NON ASSIGNATION POUR ALL EQUALS 

        nonAssigne.put(capot1, (ArrayList<String>) dom2.clone());
        nonAssigne.put(hayon1, (ArrayList<String>) dom.clone());
        nonAssigne.put(droit1, (ArrayList<String>) dom1.clone());
        nonAssigne.put(gauche1, (ArrayList<String>) dom.clone());
        nonAssigne.put(sono1, (ArrayList<String>) dom2.clone());

        System.out.println("POUR SE FILTRAGE ON VEUT LA CONTRAINTE QUE :\nLE CAPOT LE HAYON COTE GAUCHE ET DROIT PUIS SONO EST LA MEME COULEUR.");
        System.out.println();

        System.out.println("ICI ON ASSIGNE AU TOIT À COULEUR ROUGE ON LAISSE FILTRE ");
        System.out.println();

        System.out.println("RESULTAT DE FILTRAGE ALLEQUAL : " + all.filter(assigne, nonAssigne));
        System.out.println();

        System.out.println("AFFICHAGE VARIABLE APRES LE FILTRAGE  \n");
        
        Tools.afficheVariable(capot1);
        System.out.println(nonAssigne.get(capot1));
        System.out.println();

        Tools.afficheVariable(hayon1);
        System.out.println(nonAssigne.get(hayon1));
        System.out.println();

        Tools.afficheVariable(droit1);
        System.out.println(nonAssigne.get(droit1));
        System.out.println();

        Tools.afficheVariable(gauche1);
        System.out.println(nonAssigne.get(gauche1));
        System.out.println();

        Tools.afficheVariable(sono1);
        System.out.println(nonAssigne.get(sono1));
        System.out.println();

        Map<Variable, ArrayList<String>> nonAssigne2 = new HashMap(); //NON ASSIGNATION POUR LA PREMISE DE RULE

        nonAssigne2.put(capot1, (ArrayList<String>) dom2.clone());
        nonAssigne2.put(hayon1, (ArrayList<String>) dom1.clone());
        nonAssigne2.put(droit1, (ArrayList<String>) droit1.getDomaine2().clone());
        nonAssigne2.put(gauche1, (ArrayList<String>) gauche1.getDomaine2().clone());
        nonAssigne2.put(sono1, (ArrayList<String>) sono1.getDomaine2().clone());

        System.out.println("LA CONTRAINTE SUR LA PREMISE \n");

        Tools.afficheMapVariableString(constrainte1);

        System.out.println("ICI ON ASSIGNE AU TOIT À COULEUR ROUGE ON LAISSE FILTRE ");

        Map<Variable, String> assigne2 = new HashMap();

        assigne2.put(toit, "rouge");

        System.out.println("RESULTAT DU FILTRAGE DE LA PREMISE DE RULE : " + rul.filter(assigne2, nonAssigne2));

        System.out.println();

        
        System.out.println("AFFICHAGE VARIABLE APRES LE FILTRAGE  \n");
        
        Tools.afficheVariable(capot1);
        System.out.println(nonAssigne2.get(capot1));
        System.out.println();

        Tools.afficheVariable(hayon1);
        System.out.println(nonAssigne2.get(hayon1));
        System.out.println();

        Tools.afficheVariable(droit1);
        System.out.println(nonAssigne2.get(droit1));
        System.out.println();

        Tools.afficheVariable(gauche1);
        System.out.println(nonAssigne2.get(gauche1));
        System.out.println();

        Tools.afficheVariable(sono1);
        System.out.println(nonAssigne2.get(sono1));
        System.out.println();

    }
}
