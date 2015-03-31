import java.util.*;
import java.io.*;
class HappyJourneyPartial
{
    static int N;
    static ArrayList<ArrayList<pair>> adj = new ArrayList<ArrayList<pair>>();
    static int distFromSource[]=new int[100001]; // Stores Shortest Distance from a Source to a Node
    static int bestParent[]=new int[100001]; // Stores Best Parent of Each Node
    static String bestPath[]=new String[100001]; // Stores Value of Best Path Till a Node 
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N=sc.nextInt();
        int M = sc.nextInt();
        int source = sc.nextInt();
        int dest = sc.nextInt();
        for(int i=0;i<=N+1;i++)
            adj.add(new ArrayList<pair>());
        for(int i = 0 ; i<M ; i++)
        {
            int f = sc.nextInt();
            String q = sc.next();
            int t = sc.nextInt();
            adj.get(f).add(new pair(t,q));
            adj.get(t).add(new pair(f,q));
        }
        bfs(source,dest);
        // Now we know the Shortest Path from source to a node and the bestParent of each node
        // Knowing these two we know which edge is the best edge to select!
        bfs2(source,dest);
    }

    static void bfs(int source , int dest) 
    {
        // Computes Shortest Paths from Source to all Nodes having the same level as destination node
        // Computes Best Parent of Each Node
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<String> paths = new LinkedList<String>();
        Arrays.fill(bestPath,"zzz");
        q.add(source); 
        paths.add("");
        distFromSource[source]=0;
        while(!q.isEmpty())
        {
            int node = q.poll();
            String curPath = paths.poll();
            if(node==dest)
                return;
            int sz = adj.get(node).size();
            for(int i=0;i<sz;i++)
            {
                int next = adj.get(node).get(i).to;
                String nextChar = adj.get(node).get(i).x;
                String newPath = curPath + nextChar ;
                if(distFromSource[next]==0) // Found shortest Path
                {
                    distFromSource[next]=distFromSource[node]+1;
                    q.add(next);
                    paths.add(newPath); 
                    bestParent[next]=node;
                    bestPath[next]=newPath;
                }
                else if(distFromSource[next] == distFromSource[node]+1)//If has same shortest path
                {
                    if(newPath.compareTo(bestPath[next])<0) // If LexicoGraphically Smaller Path then Better Node
                    {
                        bestPath[next]=newPath;
                        bestParent[next]=node;
                        // No need to add next to the queue cause it's already there!
                    }
                }
            }
        }
        return;
    }

    static void bfs2(int source , int dest)
    {
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> d = new LinkedList<Integer>();
        Queue<String> s = new LinkedList<String>();
        q.add(source); d.add(0);s.add(" ");
        while(!q.isEmpty())
        {
            int node = q.poll();
            int dist = d.poll();
            String curPath = s.poll();
            if(node==dest)
            {
                System.out.println(curPath);
                System.exit(0);
            }
            int sz = adj.get(node).size();
            for(int i = 0 ; i<sz ; i++)
            {
                int next = adj.get(node).get(i).to;
                String p = adj.get(node).get(i).x;
                String newPath = curPath + p;
                if(distFromSource[next] == dist+1 && bestParent[next]==node) // If Shortest Path and Best Parent
                // Hence we know that the edge from node ---> next is the best edge to select
                {
                    q.add(next);
                    d.add(dist+1);
                    s.add(newPath);
                }
            }
        }
    }
}
class pair
{
    int to;
    String x;
    public pair()
    {
        to=0;
        x="";
    }

    public pair(int a , String b)
    {
        to=a;
        x=b;
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