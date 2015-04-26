import java.util.*;
import java.io.*;
class Kindergarten
{
    static int MOD = 1000000007;
    static long BITMASK[] = new long[(1<<18) + 50];
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt() , K = sc.nextInt();
        long ans = compute(N-1,K);
        ans = ans*(long)N;
        ans%=MOD;
        System.out.println(ans);
    }

    static long compute(int N , int K)
    {
        BITMASK[0] = 1;
        for(int i=1;i<(1<<N) ;i++)
        {
            BITMASK[i]=0;
            int groups =  i&1;
            for(int j=1;j<N;j++)
                if(((i&(1<<j))>0) && ( (i&((1<<(j-1)) )) ==0)) // If sitting in cur seat and not sitting in prev seat
                    groups++;   

            if(groups>K)continue;

            for(int j=0;j<N;j++)
            {
                if((i & (1<<j)) >0) 
                {
                    int T = i - (1<<j); // BITMASK[T] = Number of Ways a student can sit in cur seat
                    BITMASK[i] += BITMASK[T];
                    BITMASK[i] %=MOD;
                }
            }
        } 
        return BITMASK[(1<<N)-1];
    }
}