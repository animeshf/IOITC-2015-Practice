import java.util.*;
import java.io.*;

class MICROSCOPE
{
    public static void main(String[]args)
    {
        InputReader1 sc=new InputReader1(System.in);
        int M=sc.nextInt(), N=sc.nextInt(); // Irrelevant Inputs
        int R=sc.nextInt(), C=sc.nextInt(); // Grid of View of Microscope
        Point upper=new Point(0,0);
        // Microscope Grid has the following :
        //Upper denotes the Top Left Corner
        Point lower=new Point(R-1,C-1);
        // Lower denotes the Bottom Right Corner
        int T=sc.nextInt();
        long moves=0,d=0;
        while(T-->0)
        {
            Point p=new Point(sc.nextInt(),sc.nextInt());
            if(p.x>lower.x) // If point has x co-ordinate which is greater than
            // x co-ordinate of bottom right corner i.e. below the field of view of
            // microscope
            {
                d=p.x-lower.x;
                // Take the micrscope lower by d moves
                lower.x+=d; // increase x co-ordinate of microscope
                upper.x+=d; // increase x co-ordinate of microscope 
                moves+=d;
            }
            else if(p.x<upper.x)// If Point has x co-ordinate lesser than that of
            // Upper left corner of Grid Of View Of Microscope
            // If point is above field of view of Microscope
            {
                d=upper.x-p.x;
                //Take the Microscope Up by 'd' moves
                upper.x-=d; // Decrease x-cordinate
                lower.x-=d;
                moves+=d; 
            }

            if(p.y>lower.y) // If y co-ordinate of point is greater than y co-ordinate
            // of the bottom right corner of field of view of microscope
            // I.E. THE POINT IS TO THE RIGHT OF THE MICROSCOPE GRID
            {
                d=p.y-lower.y;
                //Move Microscope to the right by 'd' moves
                lower.y+=d; // Increase y-ordinate of the microscope grid
                upper.y+=d;
                moves+=d;
            }
            else if(p.y<upper.y)// If y-co-ordinate of point is lesser than y-cordinate of
            // the top left corner of field of view of microscope
            // I.E. THE POINT IS TO THE LEFT OF THE MICROSCOPE
            {
                d=upper.y-p.y;
                // Move Microscope to the left by 'd' moves
                upper.y-=d;//Adjust co-ordinates
                lower.y-=d;
                moves+=d;
            }
        }
        System.out.println(moves);
    }
}
class Point 
{
    int x,y;
    public Point(int x, int y)
    {
        this.x=x;
        this.y=y;
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

            