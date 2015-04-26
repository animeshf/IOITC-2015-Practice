/*For an interval i, let li be the start point and ri be the endpoint. 
You need to find the interval j with the rightmost lj such that rj < li..
Now you maintain a BIT and you query the range sum of [lj, li]. 
Then you update the point ri with the value you get from query. Thats how you proceed till N.
Once you have found the interval j, do the query on the BIT you are maintaining. 
Query the range [lj, li]. Then update the point ri with the result you get .
Vectors/ArrayLists - one for coordinate compression, 2 others for intervals sorted by start point and end point

4 
1 7
2 8
3 9
10 12

We take intervals sorted by start point. So we first take 1 7. 
Now there is no rightmost interval ending before this..
So we update point 7 in the BIT with 1..
Next We take 2 8. Again no interval ending before it. So we update point 8 with 1. 
Same with 3 9. with 10 12, The rightmost interval which ends before 10 is 3 9. 
So You Query the range 3 9 of BIT. You get 3.. 
So you update point 12 in BIT with 3.. 
Since its the last interval, the answer is 3. 
This method continues , kinda like DP , finding best answer till 1..x where x=i(loopvar)
 */
import java.io.*;
import java.util.*;
class Marathon
{
    static int n , mod = 1000000007,Csize =0 , Ssize = 0 , Esize = 0;
    static Map<Integer,Integer> M = new HashMap<Integer,Integer>();
    static ArrayList<pair> S = new ArrayList<pair>();
    static ArrayList<pair> E = new ArrayList<pair>();
    static ArrayList<Integer> C = new ArrayList<Integer>();
    static long BIT[] = new long [200020];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        n = sc.nextInt();
        E.add(new pair(0,-1));
        for(int i=1;i<=n;i++)
        {
            int x = sc.nextInt() , y = sc.nextInt();
            S.add(new pair(x,y));
            E.add(new pair(y,x));
            C.add(x);
            C.add(y);
        }
        Csize = C.size() ; Ssize = S.size() ; Esize = E.size();
        Collections.sort(S);
        Collections.sort(E);
        Collections.sort(C);
        int p=0;
        for(int i=0;i<Csize;i++)
            if(i==0 || C.get(i-1)!=C.get(i))
                M.put(C.get(i),++p);
        M.put(-1,0);
        System.out.println(compute());
    }

    static long compute()
    {
        int ie=0,mxs=0;
        for(int is=0;is<Ssize;++is)
        {
            while(ie<Esize && E.get(ie).first < S.get(is).first)
            {
                mxs = Math.max(mxs,M.get(E.get(ie).second));
                ie++;
            }
            long q = (query(M.get(S.get(is).first)-1) - query(mxs-1)+mod)%mod;
            if(mxs==0)q=1;
            update(M.get(S.get(is).second),q);
        }
        int rie = E.get(Esize-1).first , ris = S.get(Ssize-1).first;
        long ans = query(M.get(rie)) - query(M.get(ris)-1);
        ans = (ans+mod)%mod;
        return ans;
    }

    static void update(int i , long v)
    {
        for(;i<200020;i+=i&-i) 
        {
            BIT[i] +=v;
            if(BIT[i] >= mod) BIT[i] %= mod;
        }
    }

    static long query(int i)
    {
        long res=0;
        for(;i>0;i-=i&-i) 
        {
            res+=BIT[i];
            if(res < 0 || res >= mod) res = (res + mod) % mod;
        }
        return res;
    }
}
class pair implements Comparable<pair>
{
    int first,second;
    public pair(int first,int second)
    {
        this.first = first;
        this.second = second;
    }

    public int compareTo(pair x)
    {
        return this.first==x.first?this.second-x.second:this.first-x.first;
    }
}
class InputReader1 
{

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader1(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public String nextLine() {
        int c = read();
        //while (c != '\n' && c != '\r' && c != '\t' && c != -1)
        //c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (c != '\n' && c != '\r' && c != '\t' && c != -1);
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

}   