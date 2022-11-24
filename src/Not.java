//Liav Burger 208277871
import java.util.Map;
/**
 * Not class.
 */
public class Not extends UnaryExpression {

    /**
     * Constructor.
     * @param e1 can be any expression.
     */
    public Not(Expression e1) {
        super(e1);
    }

    /**
     * @return this And in string.
     */
    @Override
    public String toString() {
        return "¬(" + super.getExpression1() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(super.getExpression1().assign(var, expression));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !super.getExpression1().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !super.getExpression1().evaluate();
    }

    @Override
    //Q = NOT( A )	= A NAND A
    public Expression nandify() {
        return new Nand(super.getExpression1().nandify(), super.getExpression1().nandify());
    }

    @Override
    //Q = NOT( A )	= A NOR A
    public Expression norify() {
        return new Nor(super.getExpression1().norify(), super.getExpression1().norify());
    }

    @Override
    public Expression simplify() {
        //split into 2 expressions and simplify them recursively
        Expression expression1 = super.getExpression1().simplify();

        //~(F) == T
        if (expression1.toString().equals("F")) {
            return new Val (true);
        }
        //~(T) == F
        if (expression1.toString().equals("T")) {
            return new Val (false);
        }

        //if no simplify needed return original expression
        return new Not(expression1);
    }
}
