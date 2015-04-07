import java.util.*;
import java.io.*;
class StealingPyramid
{
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static int indices[]=new int[100001];
    static int ind_val[]=new int[100001];
    static int values[]=new int[100001];
    static int visited[]=new int[100001];
    static int ans[]=new int[100001];
    static int N,ind_pointer=0;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N=sc.nextInt();
        for(int i=0;i<=N;i++)adjList.add(new ArrayList<Integer>());
        for(int i=1;i<=N;i++)values[i]=sc.nextInt();
        for(int i=1;i<=N;i++)
        {
            int temp = sc.nextInt();
            if(i-temp>0)
            {
                adjList.get(i-temp).add(i);
                adjList.get(i).add(i-temp);
            }
            if(i+temp<=N)
            {
                adjList.get(i).add(i+temp);
                adjList.get(i+temp).add(i);
            }
        }
        for(int i=1;i<=N;i++)
        {
            if(visited[i]==0)
            {
                dfs(i);
                Arrays.sort(indices,0,ind_pointer);
                Arrays.sort(ind_val,0,ind_pointer);
                for(int j=0;j<ind_pointer;j++)
                    ans[indices[j]]=ind_val[j];
                ind_pointer=0;    
            }
        }
        for(int i=1;i<=N;i++)
        {
            if(ans[i]!=i)
            {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");        
    }

    static void dfs(int node)
    {
        visited[node]=1;
        indices[ind_pointer]=node;
        ind_val[ind_pointer++]=values[node];
        int size = adjList.get(node).size();
        for(int i=0;i<size;i++)
        {
            int next = adjList.get(node).get(i);
            if(visited[next]==0)
                dfs(next);
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
