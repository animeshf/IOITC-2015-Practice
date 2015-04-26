/**
 * IOI 2003 Boundary
 * Coded by Animesh Fatehpuria
 * 
 */
import java.io.*;
import java.util.*;
class Boundary
{
    static int MAXN = 100100;
    static double eps = 1e-7; // Accuracy
    static double pi = 4 * Math.atan(1.0);
    static int N,R;
    static ArrayList<Tuple> V = new ArrayList<Tuple>();
    static boolean S[]=new boolean[MAXN];
    static Pair A[] = new Pair[100];
    static Pair O = new Pair(-1,-1);
    static Pair D = new Pair(-1,-1);
    static Pair Z = new Pair(-1,-1);
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<MAXN;i++)S[i]=false;
        N = sc.nextInt(); R = sc.nextInt();
        D = new Pair(sc.nextInt() , sc.nextInt());
        O = new Pair(0,0); // Origin
        int sz = 0;
        for(int qq=1;qq<=R;qq++)
        {
            int p = sc.nextInt();
            for(int i=1;i<=p;i++)
            {
                A[i] = new Pair(sc.nextInt(),sc.nextInt());
                A[i].first -= D.first; // Marking ALL Points Relative to DON.
                A[i].second-= D.second;
            }
            Pair start = new Pair(-1,-1);
            Pair end   = new Pair(-1,-1);
            // find the uppermost and lowermost vertex , wrt origin.
            for(int i=1;i<=p;i++)
            {
                boolean ok1=true,ok2=true;
                /*
                I'm considering each vertex as the topmost, and checking if
                there are any other vertices above it. If there are none, then this
                is uppermost. Similarly for lowermost.
                 */
                for(int j=1;j<=p;j++)
                {
                    if(ccw(O,A[i],A[j])>0)
                        ok1=false;
                    if(ccw(O,A[i],A[j])<0)
                        ok2=false;
                }
                if(ok1) end = A[i]; //Uppermost
                if(ok2) start = A[i];//Lowermost
            }
            V.add(new Tuple(start,-qq));
            /* Why -qq?
            So that when I sort, if two points have same polar angle, the rock start will
            come earlier (pair sort rules), then the  boundary , then the rock ends.
             */
            /*
            Suppose a rock start has angle in IV quadrant, and ends in I quadrant
            then I add it in the beginning, because the whole thing is circular
             */
            V.add(new Tuple(end,qq));
            // S[x] is the rocks currently in view. S[x] = true means the rock is in view.
            // sz is the size of the set S.
            if(polarsort(new Tuple(end,qq),new Tuple(start,-qq))!=0)
            {
                S[qq] = true;
                sz++;
            }
        }
        int boundarystart = V.size();
        // Adding the Boundary Points.
        for(int i=1;i<=N-1;i++)
        {
            V.add(new Tuple(new Pair(0,i),0));
            V.add(new Tuple(new Pair(i,0),0));
            V.add(new Tuple(new Pair(N,i),0));
            V.add(new Tuple(new Pair(i,N),0));
        }
        V.add(new Tuple(new Pair(0,0),0));
        V.add(new Tuple(new Pair(0,N),0));
        V.add(new Tuple(new Pair(N,0),0));
        V.add(new Tuple(new Pair(N,N),0));
        
        for(int j=boundarystart;j<V.size();j++)
        {
            V.get(j).first.first -= D.first;
            V.get(j).first.second-= D.second;
        }
        // Fix them wrt origin ^
        Collections.sort(V); // Sorts everything by polar angle.
        int ans = 0;
        for(int j=0;j<V.size();j++) // main loop, if no rocks in view then a point can be seen
        { 
            Pair X = V.get(j).first; int type = V.get(j).second; 
            if (type > 0) { S[type] = false; sz--; continue; } // <-- deletes rock
            if (type < 0) { S[-type] = true; sz++; continue; } // <-- inserts rock  
            if (sz == 0) ans++; // <- point can be seen,as no rocks in view
        }
        // This is the main sweep. Consider as a ray rotating around Don, from angle 0 to 360
        System.out.println(ans);
    }

    static int ccw(Pair A , Pair B , Pair C)
    {
        // Tells if A,B,C are in CCW orientation or CW
        int x = (B.first - A.first)*(C.second - A.second) - (B.second - A.second)*(C.first - A.first);
        if (x == 0) return 0;
        return x < 0 ? -1 : 1;
    }

    static double at2(double y, double x)
    {
        y = Math.atan2(y,x);
        if(y<0) y = pi+pi+y; // (-1,-1) is -135 degrees. 
        //But I want 225 degrees format. So add 2pi if angle < 0
        return y; 
    }

    static int polarsort(Tuple AA,Tuple BB)
    {
        Pair A = AA.first; Pair B = BB.first;
        double x = at2(A.second,A.first), y = at2(B.second,B.first);
        if (Math.abs(x-y) < eps) return AA.second < BB.second?1:0;
        //Rock starts are negative. Rock ends are positive
        // Boundary points are 0
        return x<y?1:0;
    }
    static class Pair
    {
        int first,second;
        public Pair(int first,int second)
        {
            this.first = first;
            this.second = second;
        }
    }
    static class Tuple implements Comparable<Tuple>
    {
        Pair first ;
        int  second;
        public Tuple(Pair first , int second)
        {
            this.first = first;
            this.second= second;
        }

        public int compareTo(Tuple X)
        {
            Pair A = this.first; Pair B = X.first;
            double x = at2(A.second,A.first), y = at2(B.second,B.first);
            if (Math.abs(x-y) < eps) return Double.compare(this.second , X.second);
            return Double.compare(x,y);
        }
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