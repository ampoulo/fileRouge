package representations.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import representations.*;

/**
 * Toutes vérifient qu'au moins l'un des deux côtés (gauche et droite) a la même
 * couleur que le toit
 *
 * @author nokenn
 */
public class Constraint2 implements Constraint {

    private final Variable toit;
    private final Variable droit;
    private final Variable gauche;

    private final AllEqualConstraint all_1;
    private final AllEqualConstraint all_2;
    private final HashMap<Variable, String> constrainte1;
    private final HashMap<Variable, String> constrainte2;
    private final ConstraintDisjunction constDis;

    public Constraint2() {

        this.toit = new Variable("toit", new ArrayList<>());
        this.gauche = new Variable("gauche", new ArrayList<>());

        this.constrainte1 = new HashMap();
        this.constrainte1.put(toit, "");
        this.constrainte1.put(gauche, "");

        this.droit = new Variable("droit", new ArrayList<>());

        this.constrainte2 = new HashMap();
        this.constrainte2.put(toit, "");
        this.constrainte2.put(droit, "");

        this.all_1 = new AllEqualConstraint(this.constrainte1);
        this.all_2 = new AllEqualConstraint(this.constrainte2);

        this.constDis = new ConstraintDisjunction();
        this.constDis.ajouter(this.all_1);
        this.constDis.ajouter(this.all_2);
    }

    @Override
    public Set<Variable> getScope() {

        Set<Variable> monScope = new HashSet();

        monScope.addAll(this.constrainte1.keySet());
        monScope.addAll(this.constrainte2.keySet());

        return monScope;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> choixclient) {

        return this.constDis.isSatisfiedBy(choixclient);
    }

    @Override
    public boolean filter(Map<Variable, String> assigne, Map<Variable, ArrayList<String>> nonAssigne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
