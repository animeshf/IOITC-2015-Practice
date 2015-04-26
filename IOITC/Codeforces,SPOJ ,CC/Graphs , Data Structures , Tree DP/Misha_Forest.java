import java.util.*;
class Misha_Forest
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int DEG[]=new int[N] , XORSUM[]=new int[N];
        Queue<Integer>Q = new LinkedList<Integer>();
        for(int i = 0 ; i < N ; i++)
        {
            DEG[i] = sc.nextInt();
            XORSUM[i] = sc.nextInt();
            if(DEG[i] == 1)
                Q.add(i);              
        }
        ArrayList<pair> ANS = new ArrayList<pair>();
        while(!Q.isEmpty())
        {
            int from = Q.poll();
            if(DEG[from] == 0) continue;
            DEG[from]--;
            int to = XORSUM[from];
            XORSUM[from]=0;
            ANS.add(new pair(from,to));
            XORSUM[to]^=from;
            DEG[to]--;
            if(DEG[to]==1)Q.add(to);
        }
        System.out.println(ANS.size());
        for(pair x:ANS)
            System.out.println(x.first+" "+x.second);
    }
}
class pair
{
    int first , second;
    public pair(int first , int second)
    {
        this.first = first;
        this.second = second;
    }
}