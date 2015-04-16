import java.util.*;
import java.io.*;
class PreorderTree
{
    static long NBST[] = new long[20];
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt() ;
        long K = sc.nextLong();
        compute();
        if(K>NBST[N])
            System.out.println("-1");
        else
        {
            query('a',N,K);
        }
    }

    static void query(char st , int n ,long K)
    {
        if(n==0)
            return;
        int i;
        long tmp;
        for(i = 0; i < n; ++i)
        {
            tmp = (long)1*NBST[i]*NBST[n-i-1];
            if(tmp < K)
            {
                K -= tmp;
                continue;
            }
            break;
        }
        long r = NBST[n-i-1];
        System.out.print((char)(st+i));
        query(st,i,(K-1)/r+1);
        query((char)(st+i+1),n-i-1,(K-1)%r+1);
    }

    static void compute() // Number of Said BSTs of Nodes N
    {
        NBST[0]=NBST[1]=1;
        NBST[2]=2;
        for(int i=3; i<=19; ++i)
        {
            long Q=0;
            for(int j=1; j<=i; ++j)
                Q+=(NBST[j-1]*NBST[i-j]);
            NBST[i]=Q;
        }
    }
}