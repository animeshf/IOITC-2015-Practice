import java.io.*;
import java.util.*;
class Stamps
{
    static int look_up[][][]=new int[10][26][10000];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        int len = sc.nextInt();
        String a = sc.nextLine();
        int [] lengths = new int[10];
        for(int i = 0 ; i < T ; i++)
        {
            int l = sc.nextInt();
            lengths[i]=l;
            String in = sc.nextLine();
            preprocess(i,in,l);
        }
        // Look_up[i][j][k]= First occurence of char 'j' in stamp 'i' after position 'k'
        // Precomputation is O(N*26)
        int stamps=0;
        for(int i=0;i<len;i++)
        {
            int maxMoveForward=0;
            for(int j=0;j<T;j++)
            {
                // Check for j'th stamp!
                int moveForward=0; // Keep track of how much you can move forward with j'th stamp
                int curpos=0; // Initially starting at 0th index of current stamp
                int k;
                for(k=i;k<len;k++)
                {
                    char x = a.charAt(k);
                    int pos = look_up[j][x-97][curpos];
                    if(pos==-1) // If next character not available in current stamp , break;
                        break;
                    moveForward++; // increment 
                    curpos=pos+1;
                    if(curpos>=lengths[j]) // If stamp length exceeded , break;
                        break;
                }
                maxMoveForward = Math.max(moveForward,maxMoveForward);
                // Maximum you can move forward by using one stamp
            }
            if(maxMoveForward==0) // If you can't move forward at all , that means the word can't be made so break!
            {
                stamps=-1;
                break;
            }
            i+=maxMoveForward-1; // you've moved forward
            stamps++; // increment stamps used
        }
        System.out.println(stamps);
    }

    static void preprocess(int idx , String in , int l)
    {
        for(int i=0;i<26;i++) // For each character 'i' find occurence after an index 'j' of stamp idx
        {
            char x = (char)(i+97);
            for(int j=l-1;j>=0;j--)
            {
                char y = in.charAt(j);
                if(y==x) // if it occurs at index 'j' then update
                    look_up[idx][i][j]=j;
                else
                    look_up[idx][i][j]=j==l-1?-1:look_up[idx][i][j+1]; 
            }
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
