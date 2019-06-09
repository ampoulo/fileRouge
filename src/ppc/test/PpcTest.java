/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppc.test;

import ppc.Backtracking;
import representations.*;
import java.util.ArrayList;
import ppc.*;
import representations.examples.*;

/**
 *
 * @author nokenn
 */
public class PpcTest {

    public static void main(String[] args) {

        ArrayList<Constraint> constraint = new ArrayList<>();

        Constraint1 c1 = new Constraint1();
        Constraint2 c2 = new Constraint2();
        Constraint3 c3 = new Constraint3();
        Constraint4 c4 = new Constraint4();

        constraint.add(c1);
        constraint.add(c2);
        constraint.add(c3);
        constraint.add(c4);

        ArrayList<Variable> nonass = new ArrayList<>();
        
        ArrayList<String> dom = new ArrayList();
        dom.add("bleu");
        dom.add("rouge");
        dom.add("noir");
        

        ArrayList<String> dom2 = new ArrayList();
       
        dom2.add("false");
        dom2.add("true");

        Variable toit = new Variable("toit", dom);
        Variable capot = new Variable("capot", dom);
        Variable hayon = new Variable("hayon", dom);
        Variable droit = new Variable("droit", dom);
        Variable gauche = new Variable("gauche", dom);
        Variable sono = new Variable("sono", dom2);
        Variable toit_ouvrant = new Variable("toit_ouvrant", dom2);

        nonass.add(toit);
        nonass.add(capot);
        nonass.add(hayon);
        nonass.add(gauche);
        nonass.add(droit);
        nonass.add(sono);
        nonass.add(toit_ouvrant);

        Backtracking back = new Backtracking(constraint, new HeuristiqueOfDomaine());

        back.solution(nonass,1);
        back.moinunesolution();

    }
}
