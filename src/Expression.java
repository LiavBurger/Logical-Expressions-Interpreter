//Liav Burger 208277871
import java.util.List;
import java.util.Map;

public interface Expression {

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment the given map.
     * @return the evaluated variable.
     * @throws Exception if there is no assignment to compare with.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Convenience method.
     * This method throws exception if it's called.
     * because it can't evaluate a variable without being given a map.
     * @return the evaluated variable.
     * @throws Exception if there is no assignment to compare with.
     */
    Boolean evaluate() throws Exception;

    /**
     * This method returns a list with the variables in it.
     * @return a list with the variables in it.
     */
    List<String> getVariables();

    /**
     * This method returns a nice string representation of the expression.
     * @return the variable's name.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var the string that represents the variable.
     * @param expression the expression that is being assigned.
     * @return the assigned variable or the variable without assigning it.
     */
    Expression assign(String var, Expression expression);

    /**
     * This function converts all the operations to the logical Nand operation.
     * @return Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * This function converts all the operations to the logical Nor operation.
     * @return Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    // Returned a simplified version of the current expression.
    /**
     * This function simplifies the operations by removing redundant parts.
     * @return Returns a simplified version of the current expression.
     */
    Expression simplify();
}