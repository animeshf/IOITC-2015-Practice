
import java.util.*;
import java.io.*;

class COUNTINGTRIANGLES
{
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		List<pair> point = new ArrayList<pair>();
		int X[]=new int[10001];
		int Y[]=new int[10001];
		while(T-->0)
		{
		    int x=sc.nextInt();
		    int y=sc.nextInt();
		    X[x]++; // Increment number of points with same x
		    Y[y]++; // Increment number of points with same y
		    point.add(new pair(x,y)); // Store point itself
		}
		long ans=0;
		for(int i=0;i<point.size();i++)
		{
		    int x=point.get(i).x;
		    int y=point.get(i).y;
		    int numX=X[x]-1; // Number of points with same x as chosen point
		    int numY=Y[y]-1; // Number of points with same y as chosen point
		    ans+=numX*numY;
		}
		System.out.println(ans);
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
