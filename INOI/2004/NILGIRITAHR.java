import java.util.*;
import java.io.*;
class NILGIRITAHR
{
    static int a[][];
    static int M,N;
    static int visited[][];
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        a=new int[M+1][N+1];
        visited=new int[M+1][N+1];
        for(int i=1;i<=M;i++)
        {
            for(int j=1;j<=N;j++)
            {
                a[i][j]=sc.nextInt();
            }
        }
        visited[1][1]=1;
        dfs("1 1",1, 1 );
        System.out.println("0");
    }

    static void dfs (String lol , int x , int y)
    {
        if(x==M && y==N)
        {
            System.out.println("1");
            System.out.println(lol);
            System.exit(0);
        }

        int right = y+1;
        int left =  y-1;
        int down = x+1;
        int up = x-1;
        if(up>=1 && Math.abs(a[x][y]-a[up][y])<=1)
        {
            if(visited[up][y]==0)
            {
                lol= lol+"\n"+Integer.toString(up)+" "+Integer.toString(y);
                visited[up][y]=1;
                dfs(lol , up , y);
            }

        }
        if(down<=M && Math.abs(a[x][y]-a[down][y])<=1)
        {
            if(visited[down][y]==0)
            {
                lol=lol+"\n"+Integer.toString(down)+" "+Integer.toString(y);
                visited[down][y]=1;
                dfs(lol,down,y);
            }
        }
        if(left>=1 && Math.abs(a[x][y]-a[x][left])<=1)
        {
            if(visited[x][left]==0)
            {
                lol=lol+"\n"+Integer.toString(x)+" "+Integer.toString(left);
                visited[x][left]=1;
                dfs(lol , x , left);
            }
        }
        if(right<=N && Math.abs(a[x][y]-a[x][right])<=1)
        {
            if(visited[x][right]==0)
            {
                lol=lol+"\n"+Integer.toString(x)+" "+Integer.toString(right);
                visited[x][right]=1;
                dfs(lol ,x ,right);
            }
        }
    }
}
