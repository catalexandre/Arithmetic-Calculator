import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileOutputStream;

public class App {

    static Stack<String> operatorStack;
    static Stack<String> valueStack;
    public static void main(String[] args) throws Exception {

        System.out.println(evaluateExpression("( 2 + 5 ) * -7"));
    }

    public static int precedence(String operator)
    {
        int p;

        if(operator.equals("(") || operator.equals(")"))
        {
            p = 7;
        }

        else if(operator.equals("^"))
        {
            p = 6;
        }

        else if(operator.equals("*") || operator.equals("/"))
        {
            p = 5;
        }

        else if(operator.equals("+") || operator.equals("-"))
        {
            p = 4;
        }

        else if(operator.equals("<") || operator.equals("<=") || operator.equals(">") || operator.equals(""))
        {
            p = 3;
        }

        else if(operator.equals("==") || operator.equals("!="))
        {
            p = 2;
        }

        else if(operator.equals("$"))
        {
            p = 1;
        }

        else p = 0;

        return p;
    }

    public static String evaluateExpression(String expression)
    {
        Scanner expressionScanner = new Scanner(expression);

        valueStack = new Stack<String>(1);
        operatorStack = new Stack<String>(1);

        while(expressionScanner.hasNext())
        {
            String token = expressionScanner.next();

            if(isNumber(token))
            {
                valueStack.push(token);
            }

            else 
            {
                repeatOperations(token);
                operatorStack.push(token);
            }
        }

        repeatOperations("$");

        expressionScanner.close();

        return valueStack.peek();
    }

    public static boolean isNumber(String s)
    {
        boolean number = true;

        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-')
            {
                continue;
            }

            if (!Character.isDigit(s.charAt(i))) 
            {
                number = false;
                break;
            }
        }

        return number;
    }

    public static void repeatOperations(String referenceOperator)
    {
        while((valueStack.size() > 1 && precedence(referenceOperator) <= precedence(operatorStack.peek())) || (referenceOperator.equals(")") && !operatorStack.peek().equals("(")))
        {
            performOperation();
        }
    }

    public static void performOperation()
    {
        String y = valueStack.pop();
        String x = valueStack.pop();
        String operator = operatorStack.pop();
        valueStack.push(calculate(operator, x, y));
    }

    private static String calculate(String operator, String x, String y)
    {
        if(operator.equals("^"))
        {
            return String.valueOf(power(Double.parseDouble(x), Integer.parseInt(y)));
        }   

        else if(operator.equals("*"))
        {
            return String.valueOf(Double.parseDouble(x) * Double.parseDouble(y));
        }
        
        else if(operator.equals("/"))
        {
            return String.valueOf(Double.parseDouble(x) / Double.parseDouble(y));
        }
        
        else if(operator.equals("+"))
        {
            return String.valueOf(Double.parseDouble(x) + Double.parseDouble(y));
        }
        
        else if(operator.equals("-"))
        {
            return String.valueOf(Double.parseDouble(x) - Double.parseDouble(y));
        }
        
        else if(operator.equals(">"))
        {
            return (Double.parseDouble(x) > Double.parseDouble(y)) ? "T" : "F";
        }
        
        else if(operator.equals(">="))
        {
            return (Double.parseDouble(x) >= Double.parseDouble(y)) ? "T" : "F";
        }
        
        else if(operator.equals("<"))
        {
            return (Double.parseDouble(x) < Double.parseDouble(y)) ? "T" : "F";
        }
        
        else if(operator.equals("<="))
        {
            return (Double.parseDouble(x) <= Double.parseDouble(y)) ? "T" : "F";
        }

        else if(operator.equals("=="))
        {
            return (Double.parseDouble(x) == Double.parseDouble(y)) ? "T" : "F";
        }

        else if(operator.equals("!="))
        {
            return (Double.parseDouble(x) != Double.parseDouble(y)) ? "T" : "F";
        }

        else return "Invalid";
    }

    private static double power(double x, int y)
    {
        if(y == 1)
        {
            return x;
        }

        else return x * power(x, y - 1);
    }
}