
import java.io.*;
import java.util.*;
class Edit_Distance_Advanced
{
    static int edit[][] = new int[1010][1010];
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder op = new StringBuilder();
        while(true)
        {
            String in = br.readLine();
            if(in.charAt(0)=='*')break;
            String a = in.substring(0,in.indexOf(" "));
            String b = in.substring(in.indexOf(" ")+1);
            int l = a.length() , l1 = b.length();
            for(int i=0;i<=l;i++)
                for(int j=0;j<=l1;j++)
                    edit[i][j] = 0;
            for(int i=1;i<=l1;i++) edit[0][i] = i;
            for(int i=0;i<=l;i++)  edit[i][0] = i;
            for(int i=1;i<=l;i++)
            {
                for(int j=1;j<=l1;j++)
                {
                    int cost = a.charAt(i-1) == b.charAt(j-1)? 0:1;
                    edit[i][j] = Math.min(edit[i-1][j]+1 , Math.min(edit[i][j-1]+1,edit[i-1][j-1]+cost));
                    // Insertion , Deletion , Replacement.
                    for(int k=i-1;k>=1;k--) // For adjacent character substitution
                    {
                        if((j>1)&& (a.charAt(i-1)==b.charAt(j-2)) && (a.charAt(k-1) == b.charAt(j-1)))
                            edit[i][j] = Math.min(edit[i][j] , edit[k-1][j-2]+1+(i-k-1));
                        // Check for substitution of every adjacent char pair.
                        /*
                         * this is the case when all chars from k+1 to i-1 are deleted and 
                         * these 2 are swapped..
                         */
                    }
                }
            }
            op.append(edit[l][l1]).append("\n");
        }
        System.out.println(op);
    }
}