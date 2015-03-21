import java.util.*;
import java.io.*;
class RadioTelescopeIOITC // Segment Tree Implementation with Point Updates
{ 
    static node segTree[]=new node[160001]; // Segment Tree
    public static void main(String[]args)
    {
        for(int i=0;i<160001;i++)
            segTree[i]=new node(); // Initializing all the nodes of the Segment Tree
        InputReader1 sc = new InputReader1(System.in);
        int N= sc.nextInt();
        int K = sc.nextInt();
        int R = sc.nextInt();
        Queue<Integer>q  = new LinkedList<Integer>();
        for(int i=1;i<=K;i++)
        {
            int t = sc.nextInt();
            q.add(t);
            update(0,40000,1,t,1); // Update value inputted in SegTree , i.e. increase frequency by 1
        }
        StringBuilder ans = new StringBuilder(); // For Fast OUTPUT
        ans.append(segTree[1].val).append(" ").append(segTree[1].count).append("\n");          
        for(int i=K+1;i<=N;i++)
        {
            int add = sc.nextInt();
            int rem = q.poll();
            q.add(add);
            if(add!=rem) // No update necessary if element removed from window = element added to window
            {
                update(0,40000,1,add,1);
                update(0,40000,1,rem,-1);
            }
            ans.append(segTree[1].val).append(" ").append(segTree[1].count).append("\n");          
        }
        System.out.println(ans); // Print the entire output at the end to save on Output Time
    }

    static void update(int l , int r , int node , int pos , int val)
    {
        if(l==r) 
        {
            segTree[node].count+=val; // Increasing or Decreasing Frequency as required
            segTree[node].val=pos;
            return;
        }
        int mid = (l+r)/2;
        int leftChild=node*2;
        int rightChild=node*2+1;
        if(mid>=pos) // Move to the left subtree
        update(l,mid,leftChild,pos,val);
        else // Move to the right subtree
        update(mid+1,r,rightChild,pos,val);
        segTree[node]=merge(segTree[leftChild] , segTree[rightChild]); // Merge Left and Right SubTree
    } 

    static node merge ( node l , node r)
    {
        node temp = new node(); // The node with greater frequency gets the higher preference!
        if(l.count > r.count)
        {
            temp.count=l.count;
            temp.val=l.val;
        }
        else
        {
            temp.count=r.count;
            temp.val=r.val;
        }
        return temp;
    }
}
class node  // Node class with two attributes --> Value and Frequency
{
    int val , count;
    public node()
    {
        val=0;
        count=0;
    }
}
class InputReader1 
{

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader1(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public String nextLine() {
        int c = read();
        //while (c != '\n' && c != '\r' && c != '\t' && c != -1)
        //c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (c != '\n' && c != '\r' && c != '\t' && c != -1);
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

}

