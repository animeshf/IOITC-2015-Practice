import java.util.*;
class CULTURALPROGRAMME
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        List<pair> time = new ArrayList<pair>();
        for(int i=0;i<N;i++)
        {
            int start = sc.nextInt() , end =sc.nextInt();
            time.add(new pair(start,'S'));
            time.add(new pair(end,'E'));
        }
        Collections.sort(time);
        int max=0,cur=0;
        for(int i=0;i<time.size();i++)
        {
            int val = time.get(i).t;
            char x  = time.get(i).s;
            if(x=='S')
                cur++;
            else
                cur--;
            max = Math.max(max,cur);
        }
        System.out.println(max);
    }
}
class pair implements Comparable <pair>
{
    int t;
    char s;
    public pair(int t , char s)
    {
        this.t=t;
        this.s=s;
    }

    public int compareTo( pair o)
    {
        return this.t - o.t;
    }
}
