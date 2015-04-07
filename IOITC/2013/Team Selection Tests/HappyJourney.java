import java.util.*;
import java.io.*;
class HappyJourney
{
    static int N = 100005;
    static int visited[]=new int[100005];
    static ArrayList<ArrayList<Integer>>V = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<pair>> adj = new ArrayList<ArrayList<pair>>();
    static int dist[]=new int[100005]; 
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N=sc.nextInt();
        int M = sc.nextInt();
        int source = sc.nextInt();
        int dest = sc.nextInt();
        V.add(new ArrayList<Integer>());
        V.add(new ArrayList<Integer>());
        for(int i=0;i<=N+1;i++)
        {
            adj.add(new ArrayList<pair>());
        }

        for(int i = 0 ; i<M ; i++)
        {
            int f = sc.nextInt();
            char q = sc.next().charAt(0);
            int t = sc.nextInt();
            adj.get(f).add(new pair(t,q));
            adj.get(t).add(new pair(f,q));
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(dest);
        dist[dest]=1;
        while(!q.isEmpty()) // Reverse Breadth First Search From Destination to the Source!
        {
            int u = q.poll();
            int sz = adj.get(u).size();
            for(int i=0;i<sz;i++)
            {
                int v = adj.get(u).get(i).to;
                if(dist[v]==0)
                {
                    q.add(v);
                    dist[v]=dist[u]+1;
                }
            }
        }
        // Dist [x] stores distance between x and destination 
        // Dist [source] stores distance between source and destination
        V.get(0).add(source);// Add source as the initial candidate to Arraylist V
        int distance = dist[source] , idx=0; // distance = Distance between Source and Destination
        char already;
        StringBuilder ans = new StringBuilder();
        while(distance!=1) // Loop Till You Reach Destination i.e. Distance = 0
        {
            // At every step we think about one move towards the destination.
            already = 'z'; // Stores Minimum Character
            int sz = V.get(idx).size(); // Stores the Size of ArrayList V which contains Possible Candidates
            /**
             * 1 is connected to 2, 3, 4 and 5 out of which both 1-->2(edge cost is 'a') and 1-->3(is also 'a') 
             * then We need to keep both (a and b) as possible candidates, 
             * so we put both in ArrayList V, and process their neighbours and minm. edge costs.
             */
            for(int i=0;i<sz;i++) // Loop Through Possible Candidates and the neighbours of it.
            {
                int u = V.get(idx).get(i); // Each candidate
                int su= adj.get(u).size();
                for(int j=0 ; j<su ; j++) // Neighbours of Each Candidate
                {
                    // Considering Edges of Each Candidate!
                    int v = adj.get(u).get(j).to; // Neighbour of u
                    if(visited[v]==0 && dist[v] == distance-1) //if v is not visited AND it is connected to destination  
                        already=already<adj.get(u).get(j).x?already:adj.get(u).get(j).x;
                    // already would contain the minimum character of all edges we can possibly traverse    
                }
            }
            /* Now, as described above , there may be multiple edges having the same "Minimum" Character 
            In such a case we need to update V to store all the destination of such edges as possible candidates for
            Next Iteration*/
            for(int i=0;i<sz;i++)
            {
                int u = V.get(idx).get(i); // Current Candidate
                int su= adj.get(u).size();
                for(int j=0 ; j<su ; j++)
                {
                    int v = adj.get(u).get(j).to;
                    if(visited[v]==0 && dist[v] == distance-1 && adj.get(u).get(j).x==already)
                    {
                        V.get(1-idx).add(v); // Basically Add v to possible candidates for next iteration
                        visited[v]=1; // Marking v as Visited!
                    }
                }
            }
            
            ans.append(already);// We know the minimum character to be printed so append it 
            distance--;// Update distance as we have moved one step closer to destination 
            V.get(idx).clear(); // Clear Current Vector Index
            idx=1-idx; // Update Vector Index
            // At the end of each iteration in this loop , we have achieved the following:
            /**
             *  1: Printed the Minimum Edge(Lexicographically)which is connected to the destination
             *  2: Stored all possible candidates for the following iteration. In a case where there may 
             *  be multiple nodes which have the same "cost" , we add them to candidate list for next 
             *  iteration. If 1-->4 and 1-->2 and both edges have cost "a" , then 2 and 4 are possible candidates
             *  for the next iteration. The candidates are stored alternately in position 1 and 0 of Arraylist V
             *  All the possible candidates are marked visited.
             *  In Brief , In each iteration we compute the best edge we can traverse , print its value and 
             *  store all the nodes with similar values in V for next iteration!
             */
        }
        System.out.println(ans);
    }
}
class pair
{
    int to;
    char x;
    public pair()
    {
        to=0;
        x=' ';
    }

    public pair(int a , char b)
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