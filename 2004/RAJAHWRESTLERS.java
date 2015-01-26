import java.io.*;
import java.util.*;
class RAJAHWRESTLERS
{

    public static void main (String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<triple> triples = new ArrayList<triple>();
        for(int i=1;i<=N;i++)
        {
            int id = i;
            int strength = sc.nextInt();
            int ringPower = sc.nextInt();
            triples.add(new triple(i,strength,ringPower));
        }
        Collections.sort(triples);
        for(int i=0;i<N;i++)
        {
            System.out.println(triples.get(i).id);
        }
    }
}
class triple implements Comparable<triple>
{
    int id , strength , ringPower;
    public triple( int id , int strength , int ringPower)
    {
        this.id=id;
        this.strength=strength;
        this.ringPower=ringPower;
    }
    public int compareTo( triple o )
    {
        int powerO = o.strength + (o.ringPower * this.strength);
        int powerThis = this.strength + ( this.ringPower * o.strength);
        return powerO - powerThis;
    }
}
