
/**
 * SPOJ 
 * Find Number of Distinct Subsequences in a String!
 */
import java.io.*;
import java.util.*;
class Distinct_Subsequences_In_A_String
{
    static long dp[]=new long[100010];
    // dp[i] stores number of distinct subsequences of the string from [1...i]
    static int last[]=new int[26]; // Last occurence of i'th character in a string
    static long MOD = 1000000007;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder outp=new StringBuilder();
        while(T-->0)
        {
            String s = br.readLine();
            Arrays.fill(last,0); // Reset
            int len = s.length();
            dp[0]=1; // Null String is Valid!
            for(int i=1;i<=len;i++)
            {
                dp[i]=dp[i-1]*2;
                dp[i]%=MOD;
                char x = s.charAt(i-1);
                int sub = last[x-'A']; // Last Occurence of Character 'x' in String
                // Number of subsequences with 'i' as last character must be subtracted as thet are counted twice!
                if(sub!=0)
                    dp[i]=(dp[i]-dp[sub-1]+MOD)%MOD; // Subtract Extras!
                last[x-'A']=i;    // Update Last occurence of current character
            }
            outp.append(dp[len]).append("\n");
        }
        System.out.println(outp);
    }
}