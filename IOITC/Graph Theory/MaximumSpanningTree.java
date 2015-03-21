/**
Animesh Fatehpuria (animeshf)
USACO February Contest - SuperBull
 **/
 // Kruskal's Algorithm for Minimum Spanning Tree
import java.util.*;
import java.io.*;
class MaximumSpanningTree
{
    static int N,M;
    static List<edge> E = new ArrayList<edge>();
    static int parent[];
    public static void main(String[]args)throws IOException
    {
        //BufferedReader br = new BufferedReader(new FileReader("superbull.in"));
        //PrintWriter pw = new PrintWriter( new FileWriter("superbull.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int size=N*(N-1)/2;
        int v[]=new int[N];
        for(int i=0;i<N;i++)
            v[i]=Integer.parseInt(br.readLine());
        int ind=0;
        for(int i=0;i<N;i++)
        {
            for(int j=i+1;j<N;j++)
            {
                int w=v[i]^v[j];
                E.add(new edge(i,j,w));
                ind++;
            }
        }
        Collections.sort(E);
        parent=new int[N+1];
        for(int i=0;i<=N;i++)
            parent[i]=i;
        long ans=0;
        for(int i=0;i<ind;i++)
        {
            int u=find(E.get(i).f),z=find(E.get(i).t);
            if (u != z) 
            {
                ans += E.get(i).w;
                uni(u, z);
            }
        }
        System.out.println(ans);
        //pw.println(ans);
        //pw.close();
    }

    static int find (int x)
    {
        if (parent[x] == x)
        return x;
        else 
        return parent[x] = find(parent[x]);
    }

    static void uni (int u, int v)
    {
        u = find(u);
        v = find(v);
        parent[u] = v;
    }	
}
class edge implements Comparable<edge>
{
    int f,t,w;
    public edge(int a ,int b,int c)
    {
        f=a;
        t=b;
        w=c;
    }

    public int compareTo(edge e)
    {
        return e.w-this.w;
    }
}
