import java.io.*;
import java.util.*;
class Mushroom_Monsters_GCJ15P1
{
    static int a[]=new int [1000];
    static int N;
    static int maxDif = 0;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("E:\\input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("E:\\output.txt"));
        int T = Integer.parseInt(br.readLine());
        for(int i=1;i<=T;i++)
        {
            pw.print("Case #"+i+": ");
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            a[j]= Integer.parseInt(st.nextToken());
            int x = call();
            int y = call_2();
            pw.println(x+" "+y);
            maxDif=0;
        }
        pw.close();
    }
    static int call()
    {
        int ans=0;
        int i=0,j=1;
        while(j<=N-1)
        {
            if(a[j] < a[i])
            {
                ans+=a[i]-a[j];
                maxDif = Math.max(maxDif,a[i]-a[j]);
            }
            i++;
            j++;
        }
        return ans;
    }
    static int call_2()
    {
        int ans = 0;
        for(int i=0;i<N-1;i++)
        {
            ans+=Math.min(a[i],maxDif);
        }
        return ans;
    }
}