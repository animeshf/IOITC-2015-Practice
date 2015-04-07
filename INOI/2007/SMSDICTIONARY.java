import java.io.*;
import java.util.*;
class SMSDICTIONARY
{
    static int look_up[] = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
    public static void main(String [] args)throws IOException
    {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        int id[]=new int[N+1];
        for(int i=0;i<N;i++)
            id[i]=get_id(br.readLine());
        Arrays.sort(id);
        int max=0;
        int i=0;
        int max_id=0;
        while(i<N)
        {
            int cur=1;
            while(i<N-1&&id[i+1]==id[i])
            {
                i++;
                cur++;
            }
            if(cur>max)
            {
                max=cur;
                max_id=i;
            }
            i++;
        }
        System.out.println(id[max_id]);
    }

    public static int get_id(String s)
    {
        int len=s.length();
        int total=0;
        for(int i=0;i<len;i++)
        {
            char x=s.charAt(i);
            int val=x-97;
            val=look_up[val];
            total=total*10+val;
        }
        return total;
    }
}