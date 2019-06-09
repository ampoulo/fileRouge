package representations;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface Constraint {

    /**
     * 
     * @return 
     */
    public Set<Variable> getScope();
    /**
     * 
     * @param choiclient
     * @return 
     */
    public boolean isSatisfiedBy(Map<Variable, String> choiclient);
    /**
     * 
     * @param assigne
     * @param nonAssigne
     * @return 
     */
   public boolean filter(Map<Variable, String> assigne , Map<Variable, ArrayList<String>> nonAssigne);
}