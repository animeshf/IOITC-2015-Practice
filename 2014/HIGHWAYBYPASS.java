import java.util.*;
import java.io.*;
class HIGHWAYBYPASS
{
    public static void main(String [] args)throws IOException
    {
        InputReader1 sc=new InputReader1(System.in);
        int R=sc.nextInt(),C=sc.nextInt(),d=sc.nextInt();
        int mod=20011;
        int a[][]=new int[R][C];
        int dp[][][][]=new int[R+1][C+1][d+1][2];
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                a[i][j]=sc.nextInt();
                if(a[i][j]==0) //   Pothole  so dp[i][j][k][l] is zero for all k and l
                {
                    for(int k=0;k<=d;k++)
                    {
                        for(int l=0;l<=1;l++)
                            dp[i][j][k][l]=0;
                    }
                }
                if(i+1==R||j+1==C) // If out of bounds dp[i][j][k][l]=0
                {
                    for(int k=0;k<=d;k++) 
                    {
                        for(int l=0;l<=1;l++)
                            dp[i+1][j+1][k][l]=0;
                    }   
                }
                if(i==R-1&&j==C-1) // Position (R,C) all k and b are 1
                {
                    for(int k=0;k<=d;k++)
                    {
                        for(int l=0;l<=1;l++)
                            dp[i][j][k][l]=1;
                    }  
                }
            }
        }

        for(int i=R-1;i>=0;i--) 
        {
            for(int j=C-1;j>=0;j--)
            {
                for(int k=0;k<=d;k++) // since it goes <= d and starts from 0 we check whether k<d
                {
                    for(int b=0;b<=1;b++)
                    {
                        if(i==R-1&&j==C-1)//we dont want that one to change for dp[R-1][C-1][ANY K][ANY b]
                            continue;
                        if(a[i][j]==0)
                            continue;
                        int lol=(int)Math.abs(b-1);//the other direction basically
                        if(b==1) 
                        {
                            if(k<d)
                            {
                                dp[i][j][k][b] = dp[i+1][j][k+1][b] + dp[i][j+1][1][lol];
                            }
                            else
                                dp[i][j][k][b] = dp[i][j+1][1][lol];
                                
                        }
                        else
                        {
                            if(k<d)
                            {
                                dp[i][j][k][b]=dp[i][j+1][k+1][b];
                            }
                            dp[i][j][k][b]+=dp[i+1][j][1][lol];
                        }
                        if(dp[i][j][k][b]>mod)
                        dp[i][j][k][b]-=mod;
                    }
                }
            }
        }
        //Diagnostic Array TO CHECK WHAT VALUES ARE STORED
        /**
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                for(int k=0;k<=d;k++)
                {
                    for(int b=0;b<2;b++)
                    {
                        System.out.print(dp[i][j][k][b]);
                    }
                }
            }
            System.out.println();
        }
        **/
        System.out.println(dp[0][0][0][0]);
    }
}

class InputReader1  //FAST IO
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

            