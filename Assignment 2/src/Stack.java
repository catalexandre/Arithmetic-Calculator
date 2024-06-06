public class Stack<T> {
    private T[] s;
    private int t;

    public Stack(int capacity)
    {
        t = -1;
        s = (T[]) (new Object[capacity]);
    }

    public int size()
    {
        return t + 1;
    }

    public boolean isEmpty()
    {
        if(t == -1) return true;

        else return false;
    }

    public void push(T item)
    {
        if(s.length - 1 < t + 1)
        {
            expand();
        }

        s[t + 1] = item;
    }

    public T peek()
    {
        if(isEmpty())
        {
            System.out.println("Empty stack");
            return null;
        }

        else
        {
            return s[t];
        }
    }

    public T pop()
    {
        if(isEmpty())
        {
            System.out.println("Empty stack");
            return null;
        }

        else
        {
            return s[t--];
        }
    }

    private void expand()
    {
        T[] largerStack = (T[]) (new Object[s.length * 2]);
        
        for(int i = 0; i < s.length; i++)
        {
            largerStack[i] = s[i];
        }

        s = largerStack;
    }

}