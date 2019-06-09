package extraction;

import java.util.HashMap;
import java.util.Set;

public class BooleanDataBase {

    private Set<String> variable;

    private HashMap<String, HashMap<String, String>> ListeTransactions;

    public BooleanDataBase(Set<String> variable, HashMap<String, HashMap<String, String>> ListeTransactions) {
        this.variable = variable;
        this.ListeTransactions = ListeTransactions;

    }

    public Set<String> getVariable() {
        return variable;
    }

    public void setVariable(Set<String> variable) {
        this.variable = variable;
    }

    public HashMap<String, HashMap<String, String>> getListeTransactions() {
        return ListeTransactions;
    }

    public void setListeTransactions(HashMap<String, HashMap<String, String>> listet1) {
        this.ListeTransactions = ListeTransactions;
    }

    @Override
    public String toString() {

        return "Variables : " + this.getVariable()
                + "\n" + "Transactions :" + this.getListeTransactions();
    }
}
