//Liav Burger 208277871
import java.util.Map;
/**
 * Nand class.
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor.
     * @param expression1 can be any expression.
     * @param expression2 can be any expression.
     */
    public Nand(Expression expression1, Expression expression2)  {
        super(expression1, expression2);
    }

    /**
     * @return this And in string.
     */
    @Override
    public String toString() {
        return "(" + super.getExpression1() + " A " + super.getExpression2() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {

        return new Nand(
                super.getExpression1().assign(var, expression),
                super.getExpression2().assign(var, expression));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !super.getExpression1().evaluate(assignment) || !super.getExpression2().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !super.getExpression1().evaluate() || !super.getExpression2().evaluate();
    }

    @Override
    public Expression nandify() {
        Expression expression1 = super.getExpression1().nandify();
        Expression expression2 = super.getExpression2().nandify();
        return new Nand(expression1,expression2);
    }

    @Override
    //Q = A NAND B	= [ ( A NOR A ) NOR ( B NOR B ) ] NOR [ ( A NOR A ) NOR ( B NOR B ) ]
    public Expression norify() {
        return new Nor(
                new Nor(
                        new Nor(super.getExpression1().norify(),
                                super.getExpression1().norify()),
                        new Nor(super.getExpression2().norify(),
                                super.getExpression2().norify())),
                new Nor(
                        new Nor(super.getExpression1().norify(),
                                super.getExpression1().norify()),
                        new Nor(super.getExpression2().norify(),
                                super.getExpression2().norify())));
    }

    @Override
    public Expression simplify() {
        //split into 2 expressions and simplify them recursively
        Expression expression1 = super.getExpression1().simplify();
        Expression expression2 = super.getExpression2().simplify();

        //T A x = ~(x)
        if (expression1.toString().equals("T")) {
            return new Not(expression2).simplify();
        }
        //x A T = ~(x)
        if (expression2.toString().equals("T")) {
            return new Not(expression1).simplify();
        }

        //0 A x = 1
        if (expression1.toString().equals("F")) {
            return new Val(true);
        }
        //x A 0 = 1
        if (expression2.toString().equals("F")) {
            return new Val(true);
        }

        //x A x = ~(x)
        if (expression1.toString().equals(expression2.toString())) {
            return new Not(expression1).simplify();
        }

        //if no simplify needed return original expression
        return new Nand(expression1, expression2);
    }
}
