import java.util.*;
import java.io.*;
class FREETICKET
{
    static int V;
    public static void main(String [] args)throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sen=br.readLine();
        V=Integer.parseInt(sen.substring(0,sen.indexOf(" ")));
        int e=Integer.parseInt(sen.substring(sen.indexOf(" ")+1));
        int a[][]=new int[V][V];
        for(int i=0;i<V;i++)
        {
            for(int j=0;j<V;j++)
                a[i][j]=-1; //Initializing adjacency matrix to zero
        }
        //Setting Up Adjacency Matrix -- Undirected Graph = Weighted
        for(int i=0;i<e;i++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int lol=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            a[lol-1][b-1]=c; // Undirected so both have same weight
            a[b-1][lol-1]=c;// Building up an undirected graph
        }
        floydWarshall(a);
    }
    static void floydWarshall (int graph[][])
    {
        /* dist[][] will be the output matrix that will finally have the shortest 
        distances between every pair of vertices */
        int dist[][]=new int[V][V], i, j;
        /* Initialize the solution matrix same as input graph matrix. Or 
        we can say the initial values of shortest distances are based
        on shortest paths considering no intermediate vertex. */
        for (i = 0; i < V; i++)
        {
            for (j = 0; j < V; j++)
            {
                dist[i][j] = graph[i][j];
                if(graph[i][j]==-1)
                dist[i][j]=10000001;//INFINITY in this case
            }
        }
        /* Add all vertices one by one to the set of intermediate vertices.
        ---> Before start of a iteration, we have shortest distances between all
        pairs of vertices such that the shortest distances consider only the
        vertices in set {0, 1, 2, .. k-1} as intermediate vertices.
        ----> After the end of a iteration, vertex no. k is added to the set of
        intermediate vertices and the set becomes {0, 1, 2, .. k} */
        for (int intermediate = 0; intermediate <V; intermediate++)
        {
            for (int source = 0; source <V; source++)
            {
                for (int destination = 0; destination <V; destination++)
                {
                    if (dist[source][intermediate] + dist[intermediate][destination]
                         < dist[source][destination])
                        dist[source][destination] = dist[source][intermediate] 
                            + dist[intermediate][destination];
                }
            }
        }
        int max=-1;
        for(i=0;i<V;i++) // Finding max value of shortest routes
        {
            for(j=0;j<V;j++)
            {
                if(dist[i][j]>max&&i!=j)
                max=dist[i][j];
            }
        }
        System.out.println(max);
    }
}