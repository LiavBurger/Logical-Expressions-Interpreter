//Liav Burger 208277871
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Var class.
 */
public class Var implements Expression{

    private String variable;

    /**
     * constructor.
     * @param variable the variable
     */
    public Var(String variable) {
        this.variable = variable;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        }
        throw new Exception("The variable " + this.variable + " doesn't exist");
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("The variable " + this.variable + " doesn't exist");
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.add(this.variable);
        return list;
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
