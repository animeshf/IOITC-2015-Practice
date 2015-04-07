import java.util.*;
import java.io.*;
class CALVINSGAME
    {
        public static void main(String [] args)throws IOException
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            int a[]=new int[n+1];
            StringTokenizer str=new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++)
            a[i]=Integer.parseInt(str.nextToken());
            int dp1[]=new int[n+1];
            int dp2[]=new int[n+1];
            dp1[k]=0;
            if(k!=n)
            {
                dp1[k+1]=a[k+1];
                for(int i=k+2;i<=n;i++)
                dp1[i]=Math.max(dp1[i-1]+a[i],dp1[i-2]+a[i]);
            }
            //for(int i=k;i<=n;i++)
            //System.out.println(dp1[i]);
            dp2[0]=-1000000000;
            dp2[1]=a[1];
            for(int i=2;i<=n;i++)
            {
                dp2[i]=Math.max(dp2[i-1]+a[i],dp2[i-2]+a[i]);
            }
            int max=Integer.MIN_VALUE;
            //for(int i=1;i<=n;i++)
            //System.out.println(dp2[i]);
            if(k==1)
            {
            for(int i=k+1;i<=n;i++)
            {
                if(dp1[i]+(Math.max(dp2[i-1],dp2[i-2]))>max)
                max=dp1[i]+Math.max(dp2[i-1],dp2[i-2]); // From cur i you can move to i-2 or i-1
            }
            if(max<0)
            max=0;// No movements made
            }
            else
            {
            for(int i=k;i<=n;i++)
            {
                if(dp1[i]+(Math.max(dp2[i-1],dp2[i-2]))>max) 
                max=dp1[i]+Math.max(dp2[i-1],dp2[i-2]);
            }
            }
            System.out.println(max);
        }
    }
            
                
            