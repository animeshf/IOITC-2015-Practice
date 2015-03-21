import java.util.*;
import java.io.*;
class CountSubstrings
{
    static int length[]=new int[100010];
    static int index[]=new int[100010];
    static long cumulativeFrequency[]=new long[100010];
    static int X[]=new int[100010];
    static int a[]=new int[100010];
    static int ZeroCount[]=new int[100010];
    static int UnoCount[]=new int[100010];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        StringBuilder ansq= new StringBuilder();    
        while(T-->0)
        {
            int N = sc.nextInt();
            int k = sc.nextInt();
            int Q = sc.nextInt();

            String s = sc.nextLine();

            for(int i=1;i<=N;i++)
                a[i]=s.charAt(i-1)-48;

            ZeroCount[1]=0;
            UnoCount[1]=0;

            if(a[1]==0)
                ZeroCount[1]=1;
            else
                UnoCount[1]=1;

            for(int i=2;i<=N;i++)
            {
                if(a[i]==1)
                {
                    UnoCount[i]=UnoCount[i-1]+1;
                    ZeroCount[i]=ZeroCount[i-1];
                }
                else
                {
                    UnoCount[i]=UnoCount[i-1];
                    ZeroCount[i]=ZeroCount[i-1]+1;
                }
            }

            long ans=0;
            for(int i=1;i<=N;i++)
            {
                int zeroes=ZeroCount[i-1];
                int permissablezeroes=zeroes+k;
                int ones=UnoCount[i-1];
                int permissableones=ones+k;
                int zerorange=lowerBound(ZeroCount,permissablezeroes+1,i,N)-1;
                int onerange=lowerBound(UnoCount,permissableones+1,i,N)-1;
                int ran=Math.min(zerorange,onerange);
                length[i]=ran-i+1;
                cumulativeFrequency[i]=length[i]+cumulativeFrequency[i-1];
                index[i]=i+length[i]-1;
                int x=upperBound(index,i,1,i)-1;
                X[i]=x;
            }

            while(Q-->0)
            {
                int l = sc.nextInt();
                int r = sc.nextInt();
                int x=X[r];
                x=Math.max(x,l-1);
                long extra=r-x;
                extra=extra*(extra+1)/2;
                ans = cumulativeFrequency[x]-cumulativeFrequency[l-1]+ extra;
                ansq.append(ans).append("\n");
            }
        }
        System.out.println(ansq);
    }

    //Function which returns the first occurrence of a specified element in an array
    // returns upper limit if element not present
    static int lowerBound(int a[] , int d , int lee , int ree )
    {
        int l=lee,h=ree;
        while( l <= h)
        {
            int m = (l+h)/2;
            //if element is found
            if( a[m] == d )
            {
                //have we reached the beginning of the array
                //or middle and it's previous elements are equal?
                //search the left half
                if( m > 0 && a[m] == a[m-1])
                    h= m-1;
                else
                    return m; //we found the first occurence return it.      
            }
            else if ( a[m] < d)
                l = m+1;
            else
                h = m-1;
        }
        return ree+1;
    }

    static int upperBound(int a[],int d , int lee , int ree)
    {
        int l=lee;
        int r=ree;
        //Returns first element greater than 'd' in array a
        while(r-l>1)
        {
            int m=(l+r)/2;
            if(a[m]>d)
                r=m;
            else
                l=m;
        }
        if(a[l]>d)
            return l;
        else if(a[r]>d)
            return r;
        else
            return ree+1;
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

