import java.util.*;
import java.io.*;
class GANGSTERAUTHENTICATIONPROTOCOL
    {
        public static void main(String[]args)
        {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            int M = sc.nextInt();
            while(M-->0)
            {
                int n = sc.nextInt();
                int a[]=new int[n];
                for(int i=0;i<n;i++)
                {
                    a[i]=sc.nextInt();
                }
                Arrays.sort(a);
                int prev=0;
                for(int i=0;i<n;i++)
                {
                    int val=a[i];
                    val=val+1;
                    val+=prev;
                    System.out.print(val+" ");
                    prev++;
                }
                System.out.println();
            }
        }
    }
               