//Liav Burger 208277871
import java.util.Map;
import java.util.TreeMap;
/**
 * test of code.
 */
public class ExpressionsTest {
    /**
     * Main method.
     * @param args input
     */
    public static void main(String[] args) {
        Expression expression = new Xor(new Or(new Var("x"), new Var("y")),
                new Not(new Or(new Var("z"), new Val(true))));
        System.out.println(expression.toString());
        Map<String, Boolean> assign = new TreeMap<>();
        assign.put("x", true);
        assign.put("y", false);
        assign.put("z", true);
        try {
            System.out.println(expression.evaluate(assign));
        } catch (Exception exception) {
            System.out.println(exception);
        }
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());
    }
}
