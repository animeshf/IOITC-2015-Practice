/** 
M_x = Matrix
M_x(i, j) = Maximum value in a path between vertex i and j in x steps.
M_1 = Original Inputted Matrix.

Supoose M_a is the matrix of maximum value you can travel  in a steps.
Suppose M_b is the matrix of maximum value you can travel  in b steps.  

To calculate M_(a + b),
What we can do is we take every pair of vertices i, j, and we try to go through another intermediate vertex k, 

M_(a + b)(i, j) is maximum value in between i and j in (a + b) steps.

so M_(a + b)(i, j) will be max(M_a(i, k) + M_b(k, j))

M_b stores the max possible value in b steps between every possible pair.
ans M_a stores the same for a steps.
So when we use the above algorithm it will look like this

   a steps       b steps                      a + b steps
i ----------> k ----------> j     =    i ---------------------> j

we try every possible k (1 to N) and find out the best possible

M_(a + b)(i, j) = max(for k from 1 to N: M_a(i, k) + M_b(k, j)) 

Ok So now according to problem statement, We can travel stepsPerSecond vertices per second.
let combine(A, B) =>
R = is new matrix
for i = 1 to N:
for j = 1 to N:
R[i][j] = max(for k from 1 to N: A(i, k) + B(k, j))
return R

M_2 = combine(M_1, M_1)
M_3 = combine(M_2, M_1)
.
M_stepsPerSecond = combine(M_(stepsPerSecond - 1), M_1)

So We can compute that.

Now we have a TimeLimit, A common error is to think that the answer is M_(stepsPerSecond * timeLimit)[1][2].
But no, we can always stop ahead of the timelimit. So we use a nice trick for that.

Let P = M_stepsPerSecond
Suppose we want to stop before the TimeLimit, what we do is after we have calculated M_stepsPerSecond,
We just Add an Edge from the destination vertex to itself with value 0, Indicating That We Want To Stop Early If Possible.

Now the reason behind this is, that suppose we have already arrived once at the destination, and we have a certain value at it.
However if we take a few more steps, it goes down further, if you add a self edge with value 0, this makes sure that we do not go on anymore cycles which have a negative total weight.
If they have a positive weight, then its fine because we will take max and that value will come out.

So, after we calculate M_stepsPerSecond

if(P[2][2] < 0) P[2][2] = 0

if (max weight path from 2 to 2 is < 0)
then make it zero   // Self loop creation for preventing negative edge cycle causing INFmization of answer     

So now that we have M_stepsPerSecond, we just need to combine this timeLimit times
We need to calculate M_(stepsPerSecond * timeLimit)
M_(stepsPerSecond * 2) = combine(P, P)
M_(stepsPerSecond * 3) = combine(M_(stepsPerSecond * 2), P)
.
M_(stepsPerSecond * timeLimit) = combine(M_(stepsPerSecond * (timeLimit - 1)), P)

answer is M_(stepsPerSecond * timeLimit)[1][2].
if answer == -999, it is impossible.

Combine a matrix with itself N times.
Combine_N(M, N)
if(N == 1) return M
if(M % 2 == 1) return combine(M, combine_N(M, N - 1))

P = combine_N(M, N/2)
return combine(P, P)

P = M_numberOfSteps = combine_N(M, numberOfSteps)
if(P[2][2] < 0) P[2][2] = 0
ans = combine_N(P, timeLimit)
if(ans[1][2] == -999) print "IMPOSSIBLE"
else print ans[1][2]
DONE!
Complexity = O(N^3 log timeLimit)
 */
import java.io.*;
import java.util.*;
class Ants
{
    static long INF = Long.MIN_VALUE;
    static int N ;
    static long[][]combine(long [][] A , long [][] B)
    {
        long [][] R = new long[N][N];
        for (int i = 0; i < N; ++i) 
        {
            for (int j = 0; j < N; ++j)
            {
                R[i][j] = INF; // INITIALIZATION
                for (int k = 0; k < N; ++k) 
                {
                    if (A[i][k] != INF && B[k][j] != INF) 
                    {
                        R[i][j] = Math.max(R[i][j], A[i][k] + B[k][j]);
                        // Best path between (i,j) in the resultant matrix =
                        // Best path between i to an intermediate vertex 'k' A + Best path between 'k' to j in B
                        // Number of Steps taken in R = Number of Steps taken in A + Number of Steps taken in B
                    }
                }
            }
        }
        return R;
    }
    // Logarithmic Exponentiation of a Matrix M a value matrix with itself a number of times:
    static long[][]expo(long[][] M, int times)
    {
        // Logarithmic Matrix Exponentiation with a Combine Function
        if (times == 1) return M; 
        if (times%2 == 1) return combine(expo(M, times - 1), M);
        long[][] T = expo(M, times/2);
        return combine(T, T);
    }

    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        int stepsPerSecond = sc.nextInt() , timeLimit = sc.nextInt();
        long [][] M = new long [N][N];
        for (int i = 0; i < N; ++i) 
        {
            for (int j = 0; j < N; ++j)    
            {
                long v = sc.nextInt();
                M[i][j] = v == -999 ? INF : v ;
            }
        }
        M = expo(M, stepsPerSecond);
        if (M[1][1] < 0) M[1][1] = 0; // 0 based indexing , to stop at the destination earlier than TimeLimit
        // This basically ensures that we get the optimum answer and we check for cases where we stop 
        // at any time t <= TimeLimit
        M = expo(M, timeLimit);
        if(M[0][1]==INF)
        System.out.println("IMPOSSIBLE");
        else
        System.out.println(M[0][1]);
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