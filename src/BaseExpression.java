//Liav Burger 208277871
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Classname: BaseExpression.
 */
public abstract class BaseExpression implements Expression{
    private Expression expression1;
    private Expression expression2;

    /**
     * Constructor.
     * @param expression1 can be any expression.
     * @param expression2 can be any expression.
     */
    public BaseExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    /**
     * Constructor.
     * @param expression1 can be any expression.
     */
    public BaseExpression(Expression expression1) {
        this.expression1 = expression1;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return null;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return null;
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>(this.expression1.getVariables());
        List<String> listWithoutDuplicates = new ArrayList<>();

        if (this.expression2 != null) {
            list.addAll(this.expression2.getVariables());
        }

        //add to the other list without duplicates
        for (String item: list) {
            if (!listWithoutDuplicates.contains(item)) {
                listWithoutDuplicates.add(item);
            }
        }
        return listWithoutDuplicates;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

    /**
     * get Expression1.
     * @return this expression1
     */
    protected Expression getExpression1() {
        return this.expression1;
    }
    /**
     * get Expression2.
     * @return this expression2
     */
    protected Expression getExpression2() {
        return this.expression2;
    }
}
