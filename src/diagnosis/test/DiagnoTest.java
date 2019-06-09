/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosis.test;

import diagnosis.Diagnoser;
import java.util.ArrayList;
import representations.*;
import tools.Tools;

/**
 * Test du Diagnoser
 *
 * @author nokenn
 */
public class DiagnoTest {

    public static void main(String[] args) {

        C1 c1 = new C1();
        C2 c2 = new C2();

        ArrayList<Constraint> contr = new ArrayList<>();
        contr.add(c1);
        contr.add(c2);

        ArrayList<String> domaine1 = new ArrayList<>(); // choix 1
        domaine1.add("true");
        domaine1.add("false");

        ArrayList<String> domaine2 = new ArrayList<>(); // choix 2
        domaine2.add("true");
        domaine2.add("false");

        ArrayList<String> domaine3 = new ArrayList<>(); // choix interdit 
        domaine3.add("true");
        domaine3.add("false");

        ArrayList<String> domaine4 = new ArrayList<>(); // choix 3
        domaine4.add("true");
        domaine4.add("false");

        Variable x1 = new Variable("x1", domaine1);
        Variable x2 = new Variable("x2", domaine2);
        Variable x3 = new Variable("x3", domaine3);
        Variable x4 = new Variable("x4", domaine4);

        ArrayList<Variable> variables = new ArrayList<>();
        variables.add(x1);
        variables.add(x2);
        variables.add(x3);
        variables.add(x4);

        Diagnoser diagnostic = new Diagnoser(variables, contr);

        /*------------------------------------------------------------------------
            Les variable  des sous ensemble de GRAND C 
        -------------------------------------------------------------------------*/
        ArrayList<String> domaine11 = new ArrayList<>();
        domaine11.add("true");
        domaine11.add("false");

        Variable x11 = new Variable("x1", domaine11);

        //------------------------------------------------------
        ArrayList<String> domaine21 = new ArrayList<>();
        domaine21.add("true");
        domaine21.add("false");

        Variable x21 = new Variable("x2", domaine21);

        //------------------------------------------------------
        ArrayList<String> domaine41 = new ArrayList<>();
        domaine41.add("true");
        domaine41.add("false");

        Variable x41 = new Variable("x4", domaine41);
        //-----------------------------------------------------

        diagnostic.ajouter(x11, "true"); // choix 1
        diagnostic.ajouter(x21, "true"); // choix 2
        diagnostic.ajouter(x41, "false"); // choix 3

        ArrayList<String> domaine31 = new ArrayList<>();
        domaine31.add("true");
        domaine31.add("false");

        Variable x31 = new Variable("x3", domaine31);

        System.out.println("LES CONTRAINTES ICI SONT C1= x1 && x2 --> x3 et c2= ¬x3 --> x4");
        System.out.println();
        System.out.println("LES CHOIX FAITENT PAR L'UTILISATEUR SONT : ");
        System.out.println();

        Tools.afficheMapVariableString(diagnostic.getC());

        System.out.println("LE DIAGNOTIQUE AU SENS DE L'INCLUSION EST : ");
        System.out.println();

        Tools.afficheDiagnostic(diagnostic.dignoticInclusion(x31, "false"));

        System.out.println("AVEC COMME CHOIX INTERDI ¬x3");
        System.out.println();

        System.out.println("LE DIAGNOTIQUE AU SENS DE LA CARDINALITE EST : ");
        System.out.println();

        Tools.afficheMapVariableString(diagnostic.diagnoticCadinalite(x31, "false"));

        System.out.println("AVEC COMME CHOIX INTERDI ¬x3");

    }

}
