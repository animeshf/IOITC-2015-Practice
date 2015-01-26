import java.io.*;
import java.util.*;
class CHAMBERSINACASTLE
    {
        static int a[][]= new int[501][501];
        static int filled=0;
        static int M,N;
        public static void main(String [] args)
        {
            Scanner sc = new Scanner(System.in);
            int M=sc.nextInt();
            int N=sc.nextInt();
            for(int i=0; i<M; i++)
            {
                for(int j=0; j<N; j++)
                {
                    a[i][j]=sc.nextInt();
                }
            }
            int chambers=0,max=-1;
            for(int i=0;i<M;i++)
            {
                for(int j=0;j<N;j++)
                {
                    if(fill(i,j)==1)
                    {
                        chambers++;
                        if(filled>max)
                        max=filled;
                        filled=0;
                    }
                }
            }
            System.out.println(chambers+"\n"+max);
        }
        static int  fill (int x , int y)
        {
            if(a[x][y]==1)
            return 0;
            a[x][y]=1;
            filled++;
            fill(x-1,y);
            fill(x+1,y);
            fill(x,y-1);
            fill(x,y+1);
            return 1;
        }
    }
            