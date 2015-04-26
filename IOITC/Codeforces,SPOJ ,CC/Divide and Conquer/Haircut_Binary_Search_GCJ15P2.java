import java.io.*;
import java.util.*;
class Haircut_Binary_Search_GCJ15P2
{
    static int time[]= new int[1001]; // Time taken for i'th barber
    static int BarberCount = 0 ;
    static long PlaceInLine = 0;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("E:\\input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("E:\\output.txt"));
        int T = Integer.parseInt(br.readLine());
        for(int i=1;i<=T;i++)
        {
            pw.print("Case #"+i+": ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            BarberCount = Integer.parseInt(st.nextToken());
            PlaceInLine = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=BarberCount;j++)
                time[j]=Integer.parseInt(st.nextToken());
            long ans = compute();
            pw.println(ans);
        }
        pw.close();
    }

    static long compute()
    {
        long ans=0;
        long l=0 , r = (long)Math.pow(10,16);
        // Binary Search to find the instant at which the N'th person will go to a barber
        while(l<r)
        {
            long mid = (l+r-1)/2;
            long v = 0;
            for(int i=1;i<=BarberCount;i++)
                v+=(mid/time[i])+1; // Number of People who have got their hair cut by time 'l'
            if(v>=PlaceInLine)
                r=mid;
            else
                l=mid+1;
        }
        long prev = 0;
        for(int i=1;i<=BarberCount;i++)
            prev +=((l-1)/time[i])+1; // Number of People who have got hair cut or are getting it cut presently.
        if(PlaceInLine > BarberCount ) PlaceInLine -= prev;
        int cp = 0;
        for(int i=1;i<=BarberCount;i++)
        {
            if(l%time[i] == 0) // Its gonna be available 
            {
                cp++;
                if(cp==PlaceInLine)
                {
                    return i;
                }
            }
        }
        return -1;
    }
}