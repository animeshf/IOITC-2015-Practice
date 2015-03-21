/*
 * Timus - Anansi Cobwebs
 * Union Find Implementation
 */
import java.util.*;
import java.io.*;
class AnansiCobwebs
{
    static int id[]=new int[500001];
    static int sz[]=new int[500001];
    static int buck[]=new int[500001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        for(int i=0;i<=N;i++)
        {
            id[i]=i;
            sz[i]=1;
        }
        
        int edge[][]=new int[M+1][2];   
        int number[]=new int[M+1];
        int ind=1;
        int size=M;
        
        while(M-->0)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            edge[ind][0]=a;
            edge[ind][1]=b;
            ind++;
        }
        
        ind=1;
        
        int del=sc.nextInt();
        int ll=del;
        
        while(del-->0)
        {
            int v = sc.nextInt();
            number[ind]=v;
            buck[v]=1;
            ind++;
        }
        
        int ans=N;
        
        
        for(int i=1;i<=size;i++)
        {
            if(buck[i]==0)
            {
                int a = edge[i][0];
                int b = edge[i][1];
                if(root(a)!=root(b))
                {
                    ans--;
                    union(a,b);
                }
            }
        }
        int qu[]=new int[ll];
        int c=0;
        qu[c++]=ans;
        for(int i = ind-1;i>=2;i--)
        {
            int no = number[i];
            int a = edge[no][0];
            int b = edge[no][1];
            if(root(a)!=root(b))
            {
                ans--;
                union(a,b);
            }
            qu[c++]=ans;
        }
        for(int i = ll-1;i>=0;i--)
        System.out.print(qu[i]+" ");
    }

    static int root(int x)
    {
        if(id[x]==x)
            return x;
        else
            return id[x]=root(id[x]);
    }

    static void union(int a , int b)
    {
        a=root(a);
        b=root(b);
        if(sz[a] < sz[b])
        {
            id[a]=b;
            sz[b]+=sz[a];
        }
        else
        {
            id[b]=a;
            sz[a]+=sz[b];
        }
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

