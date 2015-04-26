/**
 * Finding Perimeter of Convex Hull Polygon.
 * Animesh Fatehpuria
 */
import java.io.*;
import java.util.*;
class GardenHull_ConvexHullPerimeter
{
    static Point P[];
    static Point p0;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        P= new Point[N];
        for(int i=0;i<N;i++)
            P[i] = new Point( sc.nextInt() , sc.nextInt() );
        int ymin = P[0].y , min =0;
        // Finding point with lowest y co-ordinate,break ties with lower x co-ordinate
        for(int i=1;i<N;i++)
        {
            int y = P[i].y;
            if ((y < ymin) || (ymin == y && P[i].x < P[min].x))
            {
                ymin = P[i].y;
                min = i;
            }
        }
        // Swap P[0] with Point with Lowest y-coordinate as it will always be present in the convex hull
        Point T = P[0];
        P[0] = P[min] ;
        P[min] = T;
        p0 = P[0]; // Set the global variable p0 according to which the other values will be sroted
        Arrays.sort(P,1,N);// Sort by Polar Angle of P0

        Stack<Point> ST = new Stack<Point>();
        ST.push(P[0]); // Push Top 3 onto stack
        ST.push(P[1]);
        ST.push(P[2]);
        // Process remaining n-3 points
        for (int i = 3; i < N; i++)
        {
            // Keep removing top while the angle formed by points next-to-top,
            // top, and points[i] makes a right turn(oriented cw)
            while (orientation(nextToTop(ST), ST.peek(), P[i]) != 2) // While CW oriented , keep popping!
                ST.pop();
            ST.push(P[i]); // Found a suitable point
            // We always consider the Top 3 points in the stack
        }
        
        // Now we have the Convex Hull Points in the stack ST , We need to find Perimeter of Polygon formed by those pts.
        int PolygonSize = ST.size();
        Point CH[] = new Point[PolygonSize]; 
        int c=PolygonSize-1;        
        // We store CH in reverse order so that CH[0]..CH[1]...CH[N-1] will have pts in CCW Order so we can add its length easily
        while (!ST.isEmpty())
        {
            Point p = ST.peek();
            CH[c--] = p;
            ST.pop();
        }
        int ans = 0;
        Point Y = CH[0] , Z = CH[1];
        c=1;int lim = PolygonSize;
        while(c<=lim)
        {
            ans+=(int)(Math.sqrt(dist(Y,Z))); // Join Points
            if(++c>=lim)break;
            Y=Z;            
            Z=CH[c];            
        }
        ans+=(int)(Math.sqrt(dist(Z,p0))); // Connect the Last Side
        System.out.println(ans);
    }
    static Point nextToTop(Stack<Point>S)
    {
        Point p = S.pop();
        Point res = S.peek();
        S.push(p);
        return res;
    }
    static int dist(Point p1, Point p2)
    {
        return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y); // Distance between 2 points.
    }
    static int orientation(Point p , Point q , Point r)
    {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); // Area Of Triangle Rule
        if (val == 0) return 0;  // collinear
        return (val > 0)? 1: 2; // clock-wise or counter-clock wise
    }
    static class Point implements Comparable<Point>
    {
        int x,y;
        public Point(int x,int y)
        {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p)
        {
            int o = orientation(p0,this,p);
            if(o==0) // Colinear , take nearest point
                return (dist(p0,p)>=dist(p0,this))?-1:1;
            return (o==2)?-1:1;// counter clockwise orientation confirmed , so -1 
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