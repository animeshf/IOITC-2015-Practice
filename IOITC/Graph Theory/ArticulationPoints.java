import java.util.*;
import java.io.*;
class ArticulationPoints
{ 
    static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
    static int parent[]=new int[10100];
    static int visited[]=new int[10100];
    static int disc[]=new int[10100];
    static int low[]=new int[10100];
    static int ap[]=new int[10100];
    static int time=0;
    public static void main(String[]args) throws IOException
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inLine[];
        StringBuilder op = new StringBuilder();
        for(int i=0;i<=10100;i++)
            map.add(new ArrayList<Integer>());
        while(true)
        {
            inLine = input.readLine().split(" ");
            int N = Integer.parseInt(inLine[0]);
            int M = Integer.parseInt(inLine[1]);
            if(N==0&&M==0)break;
            //Reset Everything!
            for(int i=0;i<=N;i++)
            { map.get(i).clear();parent[i]=-1;visited[i]=-1;ap[i]=-1;disc[i]=0;low[i]=0;}
            for(int i=0;i<M;i++)
            {
                inLine = input.readLine().split(" ");
                int u = Integer.parseInt(inLine[0]);
                int v = Integer.parseInt(inLine[1]);
                map.get(u).add(v);
                map.get(v).add(u);
            }
            time=0;
            dfs(1,true);
            int ans=0;
            for(int i=1;i<=N;i++)
            if(ap[i]==1)ans++;
            op.append(ans).append("\n");
        }
        System.out.println(op);
    }

    static void dfs (int u , boolean isRoot)
    {
        int child=0;
        visited[u]=1;
        disc[u]=++time;
        low[u]=disc[u];
        int sz = map.get(u).size();
        for(int i=0;i<sz;i++)
        {
            int v = map.get(u).get(i);
            if(visited[v]!=1)
            {
                child++;
                parent[v]=u;
                dfs(v,false);
                // Check if the subtree rooted with v has a connection to one of the ancestors of u
                low[u]  = Math.min(low[u],low[v]);
                // u is an articulation point in following cases
                // (1) u is root of DFS tree and has two or more chilren.
                if (isRoot&& child>=2)
                    ap[u]=1;
                // (2) If u is not root and low value of one of its child is more
                // than discovery value of u.
                if ( !isRoot && low[v] >= disc[u])
                    ap[u]=1;
            }
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }
}