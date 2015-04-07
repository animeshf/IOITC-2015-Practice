import java.util.*;
import java.io.*;
class TRIATHLON
{
    public static void main(String[] args) throws IOException
    {
        List<pair> pairs =  new ArrayList<pair>();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        while(T-->0)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            int d=b+c; //PoleVault+Doughnut Time
            pairs.add(new pair(a,d)); // Tuple of Cobol and DoughNut+PoleVault
        }
        Collections.sort(pairs);
        int lolwut=0;
        int ans=0;
        for(pair s: pairs)
        {
            int poledoughnut=s.b;
            int cobol=s.a;
            lolwut+=cobol;
            if(lolwut+poledoughnut>ans)
                ans=lolwut+poledoughnut;
        }
        System.out.println(ans);
    }
}
class pair implements Comparable<pair>
{
    int a,b;
    public int compareTo(pair o)
    {
        if(o.b!=this.b)
            return(o.b-this.b); 
        else
            return(o.a-this.a);
    }
    public pair(int a,int b) 
    {
        this.a = a ;
        this.b = b;
    }
}
