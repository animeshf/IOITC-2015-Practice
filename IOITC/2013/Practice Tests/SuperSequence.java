import java.io.*;
class SuperSequence
{        
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String idc = br.readLine();
        char a[] = br.readLine().toCharArray(),b[] = br.readLine().toCharArray();
        int l = a.length, l1 = b.length;
        int dp[][]=new int[l+2][l1+2];
        char pr[][]=new char[l+2][l1+2];
        pair next[][]=new pair[l+2][l1+2];
        for(int i=0;i<=l+1;i++)
            for(int j=0;j<=l1+1;j++)
                next[i][j] = new pair(0,0);
        for(int i=l ; i>0 ; i--)
        {
            for(int j=l1;j>0;j--)
            {
                if(a[i-1] == b[j-1])
                {
                    dp[i][j] = dp[i+1][j+1] + 1;
                    next[i][j].x = i+1;
                    next[i][j].y = j+1;
                    pr[i][j] = a[i-1];
                }
                else
                {
                    if(dp[i+1][j] > dp[i][j+1])
                    {
                        dp[i][j] = dp[i+1][j];
                        next[i][j].x = i+1;
                        next[i][j].y = j;
                        pr[i][j] = a[i-1];
                    }
                    
                    else if(dp[i+1][j] < dp[i][j+1])
                    {
                        dp[i][j] = dp[i][j+1];
                        next[i][j].x = i;
                        next[i][j].y = j+1;
                        pr[i][j] = b[j-1];
                    }
                    else
                    {
                        dp[i][j] = dp[i+1][j];
                        if(a[i-1] < b[j-1])
                        {
                            pr[i][j] = a[i-1];
                            next[i][j].x = i+1;
                            next[i][j].y = j;
                        }
                        else
                        {
                            pr[i][j] = b[j-1];
                            next[i][j].x = i;
                            next[i][j].y = j+1;
                        }
                    }
                }
            }
        }
        int i=1 , j=1;
        StringBuilder op = new StringBuilder();
        while(i<=l && j<=l1)
        {
            op.append(pr[i][j]);
            int temp = next[i][j].x;
            j = next[i][j].y;
            i = temp;
        }
        while(j<=l1) {op.append(b[j-1]);j++;};
        while(i<=l)  {op.append(a[i-1]);i++;};
        System.out.println(op);
    }
}
class pair
{
    int x , y;
    public pair(int x , int y)
    {
        this.x = x;
        this.y = y;
    }	
}    