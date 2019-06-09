package representations;

import java.util.Map;
import java.util.Set;

// Disjonction == OU Logique
public class Disjunction extends Rule {

    public Disjunction(Map<Variable, String> constraint){

        super(null, constraint);
 
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> ChoixClient) {

        return this.Conclusion(ChoixClient);
    }

    @Override
    public Set<Variable> getScope() {

        return this.getConclusion().keySet();
    }
}
