import java.util.*;
import java.io.*;
class Vacations
{
    static int BIT[]=new int[30001];
    static int a[]=new int[30001];
    static pair q[]=new pair[200001];
    static int lastIndex[]=new int[1000001];
    static int N,Q;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        for(int i=1;i<=N;i++)
            a[i]=sc.nextInt();
        Q = sc.nextInt();
        for(int i=1;i<=Q;i++)
        {
            int qStart = sc.nextInt() , qEnd = sc.nextInt();
            q[i]=new pair(qStart,qEnd,i,0);
        }
        Arrays.sort(q,1,Q+1,new queryEnd_sort());
        int idx=1;
        for(int i=1; i<=Q; i++)
        {
            if(idx<=q[i].j)
            {
                while(idx<=q[i].j)
                {
                    if(lastIndex[a[idx]]!=0)
                        update(lastIndex[a[idx]],-1);
                    update(idx,1); 
                    lastIndex[a[idx]]=idx++;
                }
            }
            q[i].ans=query(q[i].j)-query(q[i].i-1);
        } 
        Arrays.sort(q,1,1+Q,new index_sort());
        StringBuilder op = new StringBuilder();
        for(int i=1;i<=Q;i++)
            op.append(q[i].ans).append("\n");
        System.out.print(op);
    }
    static void update(int idx , int val)
    {
        for(int i=idx;i<=30000;i+=i&-i)
            BIT[i]+=val;
    }
    static int query(int idx)
    {
        int ans=0;
        for(int i=idx;i>0;i-=i&-i)
            ans+=BIT[i];
        return ans;    
    }
}
class queryEnd_sort implements Comparator<pair>
{
    public int compare(pair a , pair b)
    {
        if(a.j==b.j)
            return a.i - b.i;
        else
            return a.j - b.j;
    }
}
class index_sort implements Comparator<pair>
{
    public int compare(pair a , pair b)
    {
        return a.index - b.index;
    }
}    
class pair 
{
    int i,j,index,ans;
    public pair(int i , int j, int index , int ans)
    {
        this.i=i;
        this.j=j;
        this.index=index;
        this.ans=ans;
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