package representations;

import java.util.*;

public class Variable {

    private int index;
    private String nom;
    private Set<String> domaine;
    private ArrayList<String> domaine2;

    public Variable(String nom, Set<String> domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }

    public Variable(String nom, ArrayList<String> domaine) {

        this.nom = nom;
        this.domaine2 = domaine;
        this.index = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<String> getDomaine() {
        return domaine;
    }

    /**
     * 
     * @param domaine 
     */
    public void setDomaine(Set<String> domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    /*---------------------------------------------------------*/
    public ArrayList<String> getDomaine2() {

        return domaine2;
    }
    
    /**
     * 
     * @param comp
     * @return 
     */
    public boolean equals(Variable comp) {

        return this.nom.equals(comp.getNom());
    }

    public boolean limDomaine() {

        return this.index == this.domaine2.size();
    }

    public int getindex() {

        return this.index;
    }

    /**
     * Cette fonction permet faire un parcourt du domaine de string en
     * retournant un string du domaine à chaque fois que on l'appelle
     *
     * @return
     */
    public String nextDomValue() {

        if (this.limDomaine()) {

            this.index = 0;

            return null;
        }

        return this.domaine2.get(this.index++);
    }

}
