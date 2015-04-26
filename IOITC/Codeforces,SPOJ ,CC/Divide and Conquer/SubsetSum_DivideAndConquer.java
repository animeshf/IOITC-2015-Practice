import java.io.*;
import java.util.*;
class SubsetSum_DivideAndConquer
{
    static int N , A , B;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt() ; A = sc.nextInt();
        B = sc.nextInt();
        int arr[]=new int[50];
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        for(int i=0;i<N;i++) arr[i]= sc.nextInt();
        Subset(0,N/2,arr,left);
        int lim = N%2==1?N/2+1:N/2;
        Subset(N/2,lim,arr,right);
        Collections.sort(right);
        long ans = 0;
        int low , high;
        for(int i=0;i<left.size();i++)
        {
            low=lower_bound(right,A-left.get(i));
            high=upper_bound(right,B-left.get(i));
            ans+=((long)high-(long)low);
        }	
        System.out.println(ans);
    }
    static int lower_bound(ArrayList<Integer> x , int val)
    {
        int l = 0 , h = x.size() - 1;
        if(h==-1) return 0;
        while(l!=h)
        {
            int mid = (l+h)/2;
            if(x.get(mid) < val) 
                l = mid + 1;
            else
                h = mid;
        }
        if(x.get(l) < val)return x.size(); // convention
        return l ;
    }
    static int upper_bound(ArrayList<Integer> x , int val)
    {
        int l = 0 , h = x.size() - 1;
        if(h==-1) return 0;
        while(l!=h)
        {
            int mid = (l+h)/2;
            if(x.get(mid) <= val) 
                l = mid + 1;
            else
                h = mid;
        }
        if(x.get(l) <= val) return x.size();// convention
        return l ;
    }
    static void Subset(int start,int Num,int[]arr,ArrayList<Integer>vect)
    {
        int sum;
        for(int mask=0;mask<(1<<Num);mask++)
        {
            sum=0;
            for(int item=0;item<Num;item++)
                if((mask & (1<<item)) >0)
                    sum+=arr[item+start];
            vect.add(sum);
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