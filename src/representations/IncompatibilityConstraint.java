package representations;

import java.util.Map;
import java.util.Set;

public class IncompatibilityConstraint extends Rule {

    public IncompatibilityConstraint(Map<Variable, String> constraint) {

        super(constraint,null);
     
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> ChoixClient) {

        return !this.Premisse(ChoixClient);
    }

    @Override
    public Set<Variable> getScope() {

        return this.getPremisse().keySet();
    }


}
