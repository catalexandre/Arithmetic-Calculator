import java.io.FileInputStream;
import java.util.Scanner;

public class App {

    static Stack<String> operatorStack;
    static Stack<String> valueStack;
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(new FileInputStream("input.txt"));

        System.out.println(input.nextLine());

        while(input.hasNextLine())
        {
            String expressionString = input.nextLine();

            evaluateExpression(expressionString);
        }

        System.out.println("done");

        input.close();
    }

    public static int precedence(String operator)
    {
        int p;

        if(operator.equals("(") || operator.equals(")"))
        {
            p = 1;
        }

        else if(operator.equals("^"))
        {
            p = 2;
        }

        else if(operator.equals("*") || operator.equals("/"))
        {
            p = 3;
        }

        else if(operator.equals("+") || operator.equals("-"))
        {
            p = 4;
        }

        else if(operator.equals("<") || operator.equals("<=") || operator.equals(">") || operator.equals(""))
        {
            p = 5;
        }

        else if(operator.equals("==") || operator.equals("!="))
        {
            p = 6;
        }

        else if(operator.equals("$"))
        {
            p = 7;
        }

        else p = 0;

        return p;
    }

    public static void evaluateExpression(String expression)
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

        System.out.println(valueStack.peek());

        expressionScanner.close();
    }

    public static boolean isNumber(String s)
    {
        boolean number = true;

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                number = false;
                break;
            }
        }

        return number;
    }

    public static void repeatOperations(String referenceOperator)
    {
        while(valueStack.size() > 1 && precedence(referenceOperator) <= precedence(operatorStack.peek()))
        {
            performOperation();
        }
    }

    public static void performOperation()
    {
        String x = valueStack.pop();
        String y = valueStack.pop();
        String operator = operatorStack.pop();
        valueStack.push(calculate(operator, x, y));
    }

    private static String calculate(String operator, String x, String y)
    {
        if(operator.equals("^"))
        {
            return String.valueOf(power(Integer.parseInt(x), Integer.parseInt(y)));
        }   

        else if(operator.equals("*"))
        {
            return String.valueOf(Integer.parseInt(x) * Integer.parseInt(y));
        }
        
        else if(operator.equals("/"))
        {
            return String.valueOf(Integer.parseInt(x) / Integer.parseInt(y));
        }
        
        else if(operator.equals("+"))
        {
            return String.valueOf(Integer.parseInt(x) + Integer.parseInt(y));
        }
        
        else if(operator.equals("-"))
        {
            return String.valueOf(Integer.parseInt(x) - Integer.parseInt(x));
        }
        
        else if(operator.equals(">"))
        {
            return (Integer.parseInt(x) > Integer.parseInt(y)) ? "T" : "F";
        }
        
        else if(operator.equals(">="))
        {
            return (Integer.parseInt(x) >= Integer.parseInt(y)) ? "T" : "F";
        }
        
        else if(operator.equals("<"))
        {
            return (Integer.parseInt(x) < Integer.parseInt(y)) ? "T" : "F";
        }
        
        else if(operator.equals("<="))
        {
            return (Integer.parseInt(x) <= Integer.parseInt(y)) ? "T" : "F";
        }

        else if(operator.equals("=="))
        {
            return (Integer.parseInt(x) == Integer.parseInt(y)) ? "T" : "F";
        }

        else if(operator.equals("!="))
        {
            return (Integer.parseInt(x) != Integer.parseInt(y)) ? "T" : "F";
        }

        else return "Invalid";
    }

    private static int power(int x, int y)
    {
        if(y == 1)
        {
            return x;
        }

        else return x * power(x, y - 1);
    }
}