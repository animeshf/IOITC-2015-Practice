import java.util.*;
import java.io.*;
class ODDSORT
{
    public static void main(String [] args)throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        int a[]=new int[N];
        int sum=0;
        for(int i=0;i<N;i++)
        {
            a[i]=Integer.parseInt(st.nextToken());
            sum+=a[i];
        }
        int ans=sum-lisSum(a,N);
        System.out.println(ans);
    }

    static int lisSum(int a[],int N)
    {
        int tempMax[]=new int[N];
        int Max=0;
        for(int i=0;i<N;i++)
        {
            // We check whether we want to include the i'th element in every
            // iteration
            // if the i'th element increases the sum of the total then pick it
            // else if i'th element + ( sum of few/more numbers of 0 to i-1 elements)
            // is more then pick the sum
            // Basically we want to calculate  Largest Increasing Sum
            tempMax[i]=a[i];
            for(int j=0;j<i;j++)
            {
                if(a[j]>a[i])
                continue;
                else
                tempMax[i]=Math.max(tempMax[i],tempMax[j]+a[i]);
            }
            Max=Math.max(Max,tempMax[i]);
        }
        return Max;
    }
}

        