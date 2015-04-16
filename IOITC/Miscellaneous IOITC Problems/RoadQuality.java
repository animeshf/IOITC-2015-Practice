import java.util.*;
import java.io.*;
class RoadQuality
{
    static int N,M;
    static int parent[]=new int[5001];
    static int sz[]=new int[5001]; 
    static Edge edges[] = new Edge[100001]; 
    static int min = Integer.MAX_VALUE;
    static int V=0,j=0;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i=0;i<M;i++)
        {
            int from = sc.nextInt(),to=sc.nextInt(),qual=sc.nextInt(),len=sc.nextInt();
            edges[i]=new Edge(from,to,qual,len);
        }
        kruskal_quality_MST();
        if(V!=N-1)
        {
            System.out.println("-1");
            System.exit(0);
        }
        System.out.print(min+" ");
        while(edges[j].quality>=min)
        {
            j++;
        }
        System.out.println(kruskal_length_MST());
    }

    static void kruskal_quality_MST()
    {
        for(int i=0;i<N;i++)
        {
            parent[i]=i;
            sz[i]=1;
        }
        Arrays.sort(edges,0,M,new qualityCompare());
        for(int i=0;i<M;i++)
        {
            if(find(edges[i].from)!=find(edges[i].to))
            {
                union(edges[i].from,edges[i].to);
                min = edges[i].quality;
                V++;
            }
        }
    }

    static long kruskal_length_MST()
    {
        int i;
        long length = 0;
        for(i=0;i<N;i++)
        {
            parent[i]=i;
            sz[i]=1;
        }
        Arrays.sort(edges,0,j,new lengthCompare());
        for(i=0;i<j;i++)
        {
            if(find(edges[i].from) != find(edges[i].to))
            {
                union(edges[i].from,edges[i].to);
                length += edges[i].length;
            }
        }
        return length;
    }

    static int find(int x)
    {
        if(parent[x] != x)
            return parent[x] = find(parent[x]);
        else
            return x;
    }

    static void union(int x , int y)
    {
        int rootx = find(x);
        int rooty = find(y);
        if(sz[rootx] < sz[rooty])
        {
            parent[rootx]=rooty;
            sz[rooty]+=sz[rootx];
        }
        else
        {
            parent[rooty]=rootx;
            sz[rootx]+=sz[rooty];
        }
    }

}
class qualityCompare implements Comparator<Edge>
{
    public int compare(Edge u,Edge v)
    {
        return v.quality-u.quality;
    }
}
class lengthCompare implements Comparator<Edge>
{
    public int compare(Edge u , Edge v)
    {
        return u.length-v.length;
    }
}
class Edge
{
    int from ,to , quality , length;
    public Edge()
    {
        from=0;
        to=0;
        quality=0;
        length=0;
    }

    public Edge(int a,int b , int c , int d)
    {
        from=a;
        to=b;
        quality=c;
        length=d;
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
