import java.io.*;
import java.util.*;
class FROGJUMPING
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int R[][] = new int [M] [N];
        int D[][] = new int [M] [N];
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                R[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<M;i++)
        {
            for(int j=0;j<N;j++)
            {
                D[i][j]=sc.nextInt();
            }
        }    
        
        Queue<pair> q = new LinkedList<pair>();
        Queue<Integer> d = new LinkedList<Integer>();
        d.add(0);
        q.add(new pair(0,0));
        int visited[][]=new int[M][N];
        while(!q.isEmpty())
        {
            pair p = q.poll();
            int dist = d.poll();
            int x = p.x;
            int y = p.y;
            if(x==M-1 && y==N-1)
            System.out.println(dist);
            int maxR=R[x][y];
            int maxD=D[x][y];
            // Travelling right -- maxR 
            // Row remains as x , column changes from y+1 to N 
            for(int i=y;i<=y+maxR;i++)
            {
                    if(i>=N)
                    break;
                    if(visited[x][i]==0)
                    {
                        q.add(new pair(x,i));
                        d.add(dist+1);
                        visited[x][i]=1;
                    }
            }
            //Travelling down --- maxD
            // Column remains same , row changes from x+1 to M
            for(int i=x;i<=x+maxD;i++)
            {
                    if(i>=M)
                    break;
                    if(visited[i][y] ==0) // If not visited earlier ( if never in queue)
                    {
                        q.add(new pair(i,y)); // Add to queue
                        d.add(dist+1); // Add distance
                        visited[i][y]=1;
                    }
            }
        }
    }
}
class pair 
{
    int x,y;
    public pair(int x , int y)
    {
        this.x=x;
        this.y=y;
    }
}
