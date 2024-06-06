import java.io.FileInputStream;
import java.util.Scanner;

public class App {

    Stack operationStack = new Stack<String>(1);
    Stack valueStack = new Stack<String>(1);
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(new FileInputStream("input.txt"));

        System.out.println(input.nextLine());

        while(input.hasNextLine())
        {
            String expressionString = input.nextLine();
        }
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

        else p = 0;

        return p;
    }

    public static String evaluateExpression(String expression)
    {
        String result;
        Scanner expressionScanner = new Scanner(expression);

        while(expressionScanner.hasNext())
        {
            String token = expressionScanner.next();

            if(token.ch)
        }

        return result;
    }

    public static isNumber()
    {
        
    }
}