import java.util.EmptyStackException;

public class Stack<E> {
    
    private E S[];
    private int t = -1;

    public int size()
    {
        return t + 1;
    }

    public boolean isEmpty()
    {
        if(t == -1) return true;

        else return false;
    }

    public void push(E item)
    {
        S[t + 1] = item;
    }

    public E pop() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        else
        {
            t--;
            return S[t + 1];
        }
    }

}
