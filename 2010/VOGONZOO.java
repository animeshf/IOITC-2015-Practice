
import java.util.*;
import java.lang.*;
import java.io.*;

class VOGONZOO
{
	public static void main (String[] args) throws java.lang.Exception
	{
          Scanner sc = new Scanner(System.in);
          int N=sc.nextInt() , K=sc.nextInt();
          int a[]=new int[N];
          for(int i=0;i<N;i++)
          a[i]=sc.nextInt();
          Arrays.sort(a);
          int ans=1 , cur=a[0];
          
          for(int i=1;i<N;i++)
          {
              if(a[i] - cur >=K)
              {
                  ans++;
                  cur=a[i];
              }
          }
          System.out.println(ans);
          
	}
}
