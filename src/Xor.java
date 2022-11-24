//Liav Burger 208277871
import java.util.Map;
/**
 * Xor class.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructor.
     * @param expression1 can be any expression.
     * @param expression2 can be any expression.
     */
    public Xor(Expression expression1, Expression expression2)  {
        super(expression1, expression2);
    }
    /**
     * @return this Or in string.
     */
    public String toString() {
        return "(" + super.getExpression1() + " ^ " + super.getExpression2() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(
                super.getExpression1().assign(var, expression),
                super.getExpression2().assign(var, expression));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return (super.getExpression1().evaluate(assignment) && !super.getExpression2().evaluate(assignment))
                || (!super.getExpression1().evaluate(assignment) && super.getExpression2().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return (super.getExpression1().evaluate() && !super.getExpression2().evaluate())
                || (!super.getExpression1().evaluate() && super.getExpression2().evaluate());
    }

    @Override
    //Q = A XOR B	= [ A NAND ( A NAND B ) ] NAND [ B NAND ( A NAND B ) ]
    public Expression nandify() {
        return new Nand(
                    new Nand(this.getExpression1().nandify(), new Nand(this.getExpression1().nandify(), this.getExpression2().nandify())),
                    new Nand(this.getExpression2().nandify(), new Nand(this.getExpression1().nandify(), this.getExpression2().nandify())));
    }

    @Override
    //Q = A XOR B	= [ ( A NOR A ) NOR ( B NOR B ) ] NOR ( A NOR B )
    public Expression norify() {
        return new Nor(
                    new Nor(
                            new Nor(this.getExpression1().norify(), this.getExpression1().norify()),
                            new Nor(this.getExpression2().norify(), this.getExpression2().norify())),
                    new Nor(this.getExpression1().norify(), this.getExpression2().norify()));
    }

    @Override
    public Expression simplify() {
        //split into 2 expressions and simplify them recursively
        Expression expression1 = super.getExpression1().simplify();
        Expression expression2 = super.getExpression2().simplify();

        //T ^ x = ~(x)
        if (expression1.toString().equals("T")){
            return new Not(expression2).simplify();
        }
        //x ^ T = ~(x)
        if (expression2.toString().equals("T")){
            return new Not(expression1).simplify();
        }

        //F ^ x = x
        if (expression1.toString().equals("F")) {
            return expression2;
        }
        //x ^ F = x
        if (expression2.toString().equals("F")) {
            return expression1;
        }

        //x ^ x = 0
        if (expression1.toString().equals(expression2.toString())) {
            return new Val(false);
        }

        //if no simplify needed return original expression
        return new Xor(expression1, expression2);
    }
}
