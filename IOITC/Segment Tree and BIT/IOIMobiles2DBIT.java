import java.util.*;
import java.io.*;
class IOIMobiles2DBIT
{
    static int N;
    static int BIT[][]=new int[1025][1025];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int ins = sc.nextInt();
        N = sc.nextInt();
        StringBuilder op = new StringBuilder();
        while(true)
        {
            int type=sc.nextInt();
            if(type==1)
            {
                int x=sc.nextInt(),y=sc.nextInt(),a=sc.nextInt();
                update(x+1,y+1,a);
            }
            else if(type==2){
                int x1=sc.nextInt(),y1=sc.nextInt(),x2=sc.nextInt(),y2=sc.nextInt();
                int res=query(x2+1,y2+1);
                res-=query(x1,y2+1);
                res-=query(x2+1,y1);
                res+=query(x1,y1);
                op.append(res).append("\n");
            }
            else
                break;
        }
        System.out.println(op);
    }

    static void update(int x , int y , int val)
    {

        while(x<=N)
        {
            int y1=y;
            while(y1<=N)
            {
                BIT[x][y1]+=val;
                y1+=y1&-y1;
            }
            x+=x&-x;
        }
    }

    static int query(int x , int y)
    {
        int ans=0;
        while(x>0)
        {
            int y1=y;
            while(y1>0)
            {
                ans+=BIT[x][y1];
                y1-=y1&-y1;
            }
            x-=x&-x;
        }
        return ans;
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

