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
import representations.examples.*;
import tools.Tools;

/**
 *
 * @author nokenn
 */
public class ExampleTest {

    public static void main(String[] args) {

        
        Constraint1 c1 = new Constraint1();
        Constraint2 c2 = new Constraint2();
        Constraint3 c3 = new Constraint3();
        Constraint4 c4 = new Constraint4();

        Variable toit = new Variable("toit", new ArrayList<>());
        Variable capot = new Variable("capot", new ArrayList<>());
        Variable hayon = new Variable("hayon", new ArrayList<>());
        Variable droit = new Variable("droit", new ArrayList<>());
        Variable gauche = new Variable("gauche", new ArrayList<>());
        Variable sono = new Variable("sono", new ArrayList<>());
        Variable toit_ouvrant = new Variable("toit_ouvrant", new ArrayList<>());

        Map<Variable, String> choixclient1 = new HashMap();

        choixclient1.put(toit, "noir");
        choixclient1.put(capot, "rouge");
        choixclient1.put(hayon, "rouge");
        choixclient1.put(gauche, "noir");
        choixclient1.put(droit, "noir");
        choixclient1.put(sono, "false");
        choixclient1.put(toit_ouvrant, "true");

        System.out.println();
        System.out.println("--------------ChoixClien 1-------------");
        System.out.println();

        Tools.afficheMapVariableString(choixclient1);

        System.out.println("Constraint 1 ---> " + c1.isSatisfiedBy(choixclient1));
        System.out.println("Constraint 2 ---> " + c2.isSatisfiedBy(choixclient1));
        System.out.println("Constraint 3 ---> " + c3.isSatisfiedBy(choixclient1));
        System.out.println("Constraint 4 ---> " + c4.isSatisfiedBy(choixclient1));

        Map<Variable, String> choixclient2 = new HashMap();

        choixclient2.put(toit, "rouge");
        choixclient2.put(capot, "rouge");
        choixclient2.put(hayon, "rouge");
        choixclient2.put(gauche, "rouge");
        choixclient2.put(droit, "noir");
        choixclient2.put(sono, "true");
        choixclient2.put(toit_ouvrant, "false");

        System.out.println();
        System.out.println("--------------ChoixClien 2-------------");
        System.out.println();

        Tools.afficheMapVariableString(choixclient2);

        System.out.println("Constraint 1 ---> " + c1.isSatisfiedBy(choixclient2));
        System.out.println("Constraint 2 ---> " + c2.isSatisfiedBy(choixclient2));
        System.out.println("Constraint 3 ---> " + c3.isSatisfiedBy(choixclient2));
        System.out.println("Constraint 4 ---> " + c4.isSatisfiedBy(choixclient2));
        System.out.println();

    }
}
