//Liav Burger 208277871
import java.util.Map;
/**
 * And class.
 */
public class And extends BinaryExpression {
    /**
     * Constructor.
     * @param expression1 can be any expression.
     * @param expression2 can be any expression.
     */
    public And(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * @return this And in string.
     */
    @Override
    public String toString() {
        return "(" + super.getExpression1() + " & " + super.getExpression2() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {

        return new And(
                super.getExpression1().assign(var, expression),
                super.getExpression2().assign(var, expression));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return super.getExpression1().evaluate(assignment) && super.getExpression2().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return super.getExpression1().evaluate() && super.getExpression2().evaluate();
    }

    @Override
    //Q = A AND B	= ( A NAND B ) NAND ( A NAND B )
    public Expression nandify() {
        return new Nand(new Nand(super.getExpression1().nandify(), super.getExpression2().nandify()),
                        new Nand(super.getExpression1().nandify(), super.getExpression2().nandify()));
    }

    @Override
    //Q = A AND B	= ( A NOR A ) NOR ( B NOR B )
    public Expression norify() {
        return new Nor(
                new Nor(super.getExpression1().norify(), super.getExpression1().norify()),
                new Nor(super.getExpression2().norify(), super.getExpression2().norify()));
    }

    @Override
    public Expression simplify() {
        //split into 2 expressions and simplify them recursively
        Expression expression1 = super.getExpression1().simplify();
        Expression expression2 = super.getExpression2().simplify();

        // x & T = x
        // if expression 1 is fully simplified, continue simplifying expression 2
        if (expression1.toString().equals("T")) {
            return expression2;
        }
        // x & T = x
        // if expression 2 is fully simplified, continue simplifying expression 1
        if (expression2.toString().equals("T")) {
            return expression1;
        }
        // x & F = F
        if (expression1.toString().equals("F")
                || expression2.toString().equals("F")) {
            return new Val(false);
        }
        //x & x = x
        if (expression1.toString().equals(expression2.toString())) {
            return expression1;
        }

        //if no simplify needed return original expression
        return new And(expression1, expression2);
    }
}
