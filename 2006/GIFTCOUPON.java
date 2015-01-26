import java.util.*;
import java.io.*;
class GIFTCOUPON
{
    static int N,sum;
    public static void main(String[]args)throws IOException
    {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sum = sc.nextInt();
        int a[]=new int[N];
        for(int i=0 ; i<N ; i++)
            a[i]=sc.nextInt();

        Arrays.sort(a);
        boolean flag=false;
        for(int i=0;i<N-2;i++)
        {
            int chosen = a[i];
            int reqSum = sum - chosen ;
            int start = i+1;
            int end = N-1;
            while(start<end)
            {
                if(a[start]+a[end] == reqSum)
                {
                    flag=true;
                    break;
                }
                else if(a[start]+a[end] < reqSum)
                    start++;
                else
                    end--;
            }
            if(flag)
            {
                    System.out.println(chosen+"\n"+a[start]+"\n"+a[end]);
                    break;
            }
        }
        if(!flag)
        System.out.println("0\n0\n0");
    }
}
