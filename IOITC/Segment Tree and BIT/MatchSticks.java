/**
 * Problem - MatchSticks
 * Author - Animesh Fatehpuria
 * Segment Tree Implementation
 */
import java.util.*;
import java.io.*;
class MatchSticks
{
    static int segTree[]=new int[400001];
    static int segTree2[]=new int[400001];
    public static void main ( String [] args )
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int burnRate[]=new int[N];
        for(int i=0;i<N;i++)
            burnRate[i]=sc.nextInt();
        MinFill(1,0,N-1,burnRate);
        MaxFill(1,0,N-1,burnRate);
        int Q = sc.nextInt();
        StringBuilder total = new StringBuilder();
        while(Q-->0)
        {
            int L = sc.nextInt();
            int R = sc.nextInt();
            int minTime=burnRate[minQuery(1,0,N-1,L,R,burnRate)];
            int index1=maxQuery(1,0,N-1,0,L-1,burnRate);
            int max1=0,max2=0;
            if(index1!=-1)
            max1=burnRate[index1];
            index1=maxQuery(1,0,N-1,R+1,N-1,burnRate);
            if(index1!=-1)
            max2=burnRate[index1];
            int otherMax=Math.max(max1,max2);
            double OptionOne=otherMax+minTime;
            int max3=burnRate[maxQuery(1,0,N-1,L,R,burnRate)];
            double OptionTwo=minTime+(double)((double)(max3-minTime)/2.0);
            double ans=Math.max(OptionOne,OptionTwo);
            String lol = String.format("%.1f",ans);
            total.append(lol).append("\n");
        }
        System.out.println(total);
    }
    static int maxQuery(int node , int start , int end , int queryStart, int queryEnd , int burn[])
    {
        if(end<queryStart || start>queryEnd)
        {
            return -1;
        }
        if(queryStart<=start && end <=queryEnd)
        return segTree2[node];
        int leftChild=2*node;
        int rightChild=2*node+1;
        int leftSubTree=(start+end)/2;
        int p1=maxQuery(leftChild,start,leftSubTree,queryStart,queryEnd,burn);
        int p2=maxQuery(rightChild,leftSubTree+1,end,queryStart,queryEnd,burn);
        if(p1==-1)
        return p2;
        else if(p2==-1)
        return p1;
        else if(burn[p1]>=burn[p2])
        return p1;
        else
        return p2;
    }
    static int minQuery(int node , int start , int end , int queryStart, int queryEnd , int burn[])
    {
        if(end<queryStart || start>queryEnd)
        {
            return -1;
        }
        if(queryStart<=start && end <=queryEnd)
        return segTree[node];
        int leftChild=2*node;
        int rightChild=2*node+1;
        int leftSubTree=(start+end)/2;
        int p1=minQuery(leftChild,start,leftSubTree,queryStart,queryEnd,burn);
        int p2=minQuery(rightChild,leftSubTree+1,end,queryStart,queryEnd,burn);
        if(p1==-1)
        return p2;
        else if(p2==-1)
        return p1;
        else if(burn[p1]<=burn[p2])
        return p1;
        else
        return p2;
    }
    static void MinFill(int node ,int start , int end , int[]burn)
    {
        if(start==end)
        {
            segTree[node]=start;
            return;
        }
        int leftChild=2*node;
        int rightChild=2*node+1;
        int LeftSubTree=(start+end)/2;
        MinFill(leftChild,start,LeftSubTree,burn);
        MinFill(rightChild,LeftSubTree+1,end,burn);
        if(burn[segTree[leftChild]] < burn[segTree[rightChild]])
            segTree[node]=segTree[leftChild];
        else
            segTree[node]=segTree[rightChild];
    }

    static void MaxFill(int node ,int start , int end , int[]burn)
    {
        if(start==end)
        {
            segTree2[node]=start;
            return;
        }
        int leftChild=2*node;
        int rightChild=2*node+1;
        int LeftSubTree=(start+end)/2;
        MaxFill(leftChild,start,LeftSubTree,burn);
        MaxFill(rightChild,LeftSubTree+1,end,burn);
        if(burn[segTree2[leftChild]] > burn[segTree2[rightChild]])
            segTree2[node]=segTree2[leftChild];
        else
            segTree2[node]=segTree2[rightChild];
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
