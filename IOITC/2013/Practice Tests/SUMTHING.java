/*
 * Problem - SUMTHING
 * IOITC - 2013
 * 
 * Animesh Fatehpuria
 */
import java.io.*;
class SUMTHING
    {
        public static void main(String[]args)throws IOException
        {
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(isr);
            int len=Integer.parseInt(br.readLine());
            String s=br.readLine();
            int T=Integer.parseInt(br.readLine());
            int dp[][]=new int[len+1][T+1];
            dp[len][0]=0; 
            /** dp[i][s]= INF if it is not possible to get a sum 
            of s from i to len **/
            int INF  = 100000000;
            for(int i=1;i<=T;i++)
            dp[len][i]=INF; //  Empty String - NO sum greater than 0 possible
            for(int i=len-1;i>=0;i--)
            {
               for(int k=0;k<=T;k++)
               {
                   int digit=s.charAt(i)-48;
                   if(k<digit) // if k is not possible from i to len
                   {
                       dp[i][k]=INF;
                       continue;
                   }
                   if(i==len-1)
                   dp[i][k]=dp[i+1][k-digit]; // You dont add a plus at the end
                   else
                   dp[i][k]=dp[i+1][k-digit]+1;
                   for(int j=i+1;j<len;j++)//Iterating over All prefixes
                   {
                      digit*=10;
                      int digitJ=s.charAt(j)-48;
                      digit+=digitJ;
                      if(digit>k) // number cant be formed
                      break;
                      if(j==len-1)  // You dont add a plus to the end
                      dp[i][k]=Math.min(dp[i][k],dp[j+1][k-digit]);
                      else
                      dp[i][k]=Math.min(dp[i][k],dp[j+1][k-digit]+1);
                   }
               }
            }
            if(dp[0][T]>=INF) // Best way to get T starting from 0 to len of String
            System.out.println("-1");
            else
            System.out.println(dp[0][T]);
        }
    }
            
            