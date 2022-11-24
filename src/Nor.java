//Liav Burger 208277871
import java.util.Map;
/**
 * Nor class.
 */
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param expression1 can be any expression.
     * @param expression2 can be any expression.
     */
    public Nor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * @return this And in string.
     */
    @Override
    public String toString() {
        return "(" + super.getExpression1() + " V " + super.getExpression2() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {

        return new Nor(
                super.getExpression1().assign(var, expression),
                super.getExpression2().assign(var, expression));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !super.getExpression1().evaluate(assignment) && !super.getExpression2().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !super.getExpression1().evaluate() && !super.getExpression2().evaluate();
    }

    @Override
    //Q = A NOR B	= [ ( A NAND A ) NAND ( B NAND B ) ] NAND [ ( A NAND A ) NAND ( B NAND B ) ]
    public Expression nandify() {
        return new Nand(
                new Nand(
                        new Nand(super.getExpression1().nandify(),
                                super.getExpression1().nandify()),
                        new Nand(super.getExpression2().nandify(),
                                super.getExpression2().nandify())),
                new Nand(
                        new Nand(super.getExpression1().nandify(),
                                super.getExpression1().nandify()),
                        new Nand(super.getExpression2().nandify(),
                                super.getExpression2().nandify())));
    }

    @Override
    public Expression norify() {
        Expression expression1 = super.getExpression1().norify();
        Expression expression2 = super.getExpression2().norify();
        return new Nor(expression1, expression2);
    }

    @Override
    public Expression simplify() {
        //split into 2 expressions and simplify them recursively
        Expression expression1 = super.getExpression1().simplify();
        Expression expression2 = super.getExpression2().simplify();

        //T V x = F
        if (expression1.toString().equals("T")) {
            return new Val(false);
        }
        //x V T = F
        if (expression2.toString().equals("T")) {
            return new Val(false);
        }

        //F V x = ~(x)
        if (expression1.toString().equals("F")) {
            return new Not(expression2).simplify();
        }
        //x V F = ~(x)
        if (expression2.toString().equals("F")) {
            return new Not(expression1).simplify();
        }

        //x V x = ~(x)
        if (expression1.toString().equals(expression2.toString())) {
            return new Not(expression1).simplify();
        }

        //if no simplify needed return original expression
        return new Nor(expression1, expression2);
    }
}
