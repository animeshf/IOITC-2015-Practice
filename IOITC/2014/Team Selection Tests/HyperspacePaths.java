import java.util.*;
import java.io.*;
class HyperspacePaths
{ 
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int K = sc.nextInt();
        int start[]=new int[K];
        int end[]=new int[K];
        long d[]=new long[K];
        int step[]=new int[K];
        
        for(int i=0;i<K;i++)start[i]=sc.nextInt();
        for(int i=0;i<K;i++)end[i]=sc.nextInt();
        for(int i=0;i<K;i++)d[i]=sc.nextLong();

        int even=0,odd=0;int flag=0;
        int dontMatter=0;

        /**
         * Checking For Validity of Path
         */

        for(int i=0;i<K;i++)
        {
            int steps=(int)Math.abs(start[i]-end[i]);
            if(d[i]==0 && start[i]!=end[i])
            {
                flag=1;break;
            }
            if(d[i]==0)
            {
                step[i]=0;dontMatter++;continue;
            }
            if(steps%d[i]!=0)
            {
                flag=1;continue;
            }
            step[i]=steps/(int)d[i];
            if(step[i]%2==0)even++;
            else odd++;
        }

        if(flag==1)
            System.out.println("0");
        else if(odd!=K-dontMatter && even!=K-dontMatter)
            System.out.println("0");
        else
        {
            System.out.println("1");
            int max=-1;
            for(int i=0;i<K;i++) 
                max=Math.max(max,step[i]);
            System.out.println(max);
            long ans=0;
            for(int i=0;i<K;i++)
            {
                if(d[i]==0)
                {
                    ans+=(start[i]*(max+1));
                    continue;
                }
                long offset=max-step[i];
                long begin = start[i];
                long en = end[i];
                long jump = d[i]; 
                long total=0;
                ans+=begin;
                if(en<begin)
                    jump=jump-(2*jump);
                if(jump<0)
                {
                    for(long j=begin+jump;j>=en;j+=jump)
                    {
                        total+=j;
                    }
                    if(offset>0)
                    {
                        long rightTrav=offset/2;
                        for(long j=1;j<=rightTrav;j++)
                            total+=(begin+(d[i]*j));
                        for(long j=1;j<rightTrav;j++)
                            total+=(begin+(d[i]*j));
                        total+=begin;
                    }
                }
                else
                {
                    for(long j=begin+jump;j<=en;j+=jump)
                        total+=j;
                    if(offset>0)
                    {
                        long rightTrav=offset/2;
                        for(long j=1;j<=rightTrav;j++)
                            total+=(en+(d[i]*j));
                        for(long j=1;j<rightTrav;j++)
                            total+=(en+(d[i]*j));
                        total+=en;
                    }
                }
                ans+=total;
            }
            System.out.println(ans);
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

