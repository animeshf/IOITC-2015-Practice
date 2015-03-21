import java.util.*;
import java.io.*;
class MatrixUnionFind
{
    static int id[]=new int[1000051];//Parents of Nodes
    static long size[]=new long[1000051];//Size of trees
    static int wallx[][]=new int[1011][1011];//Vertical Wall
    static int wally[][]=new int[1011][1011];//Horizontal Wall
    static String queries[]=new String[1000051];//Query Offline Processing
    static long max=1;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        while(T-->0)
        {
            max=1;
            for(int i=1;i<=1000000;i++)
            {
                id[i]=i;
                size[i]=1;//Initialize size and parent to 1 and i 
                queries[i]="";
            }
            for(int i=1;i<=1000;i++)
            {
                for(int j=1;j<=1000;j++)
                {
                    wallx[i][j]=0;//Initialize walls to not present i.e. 0
                    wally[i][j]=0;
                }
            }

            int N =sc.nextInt();
            int M =sc.nextInt();
            int Q =sc.nextInt();
            long sum=0;
            long sz=N*M;

            int co=1;// Query Indexing
            while(Q-->0) // Offline Processing
            {
                int type=sc.nextInt();
                if(type==1)
                {
                    int x =sc.nextInt();
                    int y =sc.nextInt();
                    wallx[x][y]++; // Increment number of walls
                    int from = (x-1)*M + y;
                    int to=from+1;
                    queries[co++]="1 "+from+" "+to+" "+x+" "+y; // Store Query Details
                }
                else if(type==2)
                {
                    int x =sc.nextInt();
                    int y =sc.nextInt();
                    wally[x][y]++;
                    int from = (x-1)*M + y;
                    int to=from+M;
                    queries[co++]="2 "+from+" "+to+" "+x+" "+y;
                }
                else if(type==3)
                {
                    int x1=sc.nextInt();
                    int y1=sc.nextInt();
                    int x2=sc.nextInt();
                    int y2=sc.nextInt();
                    int from=(x1-1)*M +y1;
                    int to =(x2-1)*M+y2;
                    queries[co++]="3 "+from+" "+to;
                }
                else
                {
                    queries[co++]="4";
                }
            }

            for(int i=1;i<=N;i++)//Building Graph
            {
                for(int j=1;j<=M;j++)
                {
                    if(wallx[i][j]==0) // Vertical Wall not present
                    {
                        int from = (i-1)*M + j;
                        int to=from+1;
                        if(j!=M)
                            union(from,to);
                    }
                    if(wally[i][j]==0)//Horizontal Wall not present
                    {
                        int from = (i-1)*M + j;
                        int to=from+M;
                        if(i!=N)
                            union(from,to);
                    }
                }
            }

            for(int i=co-1;i>=1;i--)
            {
                String lol[]=queries[i].split(" ");
                int type=Integer.parseInt(lol[0]);
                if(type==1)
                {
                    int from =Integer.parseInt(lol[1]);
                    int to =Integer.parseInt(lol[2]);
                    int x=Integer.parseInt(lol[3]);
                    int y=Integer.parseInt(lol[4]);
                    wallx[x][y]--;
                    if(wallx[x][y]==0 && y!=M&&to<=sz)
                        union(from,to);
                }
                else if(type==2)
                {
                    int from =Integer.parseInt(lol[1]);
                    int to =Integer.parseInt(lol[2]);
                    int x=Integer.parseInt(lol[3]);
                    int y=Integer.parseInt(lol[4]);
                    wally[x][y]--;
                    if(wally[x][y]==0 && x!=N &&to<=sz)
                        union(from,to);
                }
                else if(type==3)
                {
                    int from=Integer.parseInt(lol[1]);
                    int to=Integer.parseInt(lol[2]);
                    if(root(from)==root(to))
                        sum++;
                }
                else
                {
                    sum+=max;
                }
                //System.out.println("Type of query  "+type+ "  "+sum);
            }
            System.out.println(sum);
        }
    }

    static int root(int a)
    {
        if(id[a]==a)
            return a;
        else
            return id[a]=root(id[a]);
    }

    static void union(int a , int b)
    {
        int i=root(a);
        int j=root(b);
        if(i==j)
            return;
        if(size[i]<size[j])
        {
            id[i]=j;
            size[j]+=size[i];
        }
        else
        {
            id[j]=i;
            size[i]+=size[j];
        }
        max=Math.max(max,Math.max(size[j],size[i]));
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

