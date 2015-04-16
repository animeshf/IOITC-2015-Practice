
/*
Suppose A[i] = 25, and A[j] = -5 . You need to transform A[i] to 0 using A[j] only.
Now you can do that by:

A[i] + A[j] * 5 = 0
=> A[i] + A[j] * (4 + 1) = 0
=> A[i] + A[j] * 2^2 + A[j] * 2^0 = 0

So this is the basic method.
You can convert any positive number to 0 using ONE negative number.
Similarly You can convert any negative number to 0 using ONE positive number.

So first we will make a list of all positive and negative indexes .

Let's take the sample test :

25 + 2^2*-5 = 5
5 + 2^0*-5 = 0

A = [2, -4, 4]
positive = [1, 3]
negative = [2]

Now we need to maintain a balance in the number of positive and negative and 
We can never let any one of them touch zero unless the other is 1.

So if positive > negative, we try to make the last element in positive 0
else we try to make the last element (index) in negative 0.
This is because we need a positive number to make a negative 0 and vice versa.

Now since positive > negative, we take positve. 
If positive == negative , Then take which ever.

Now let i be the index of element we are trying to make 0,j is index of element we are using.
How will we pick 'j' ?
So if we pick i in positive, we pick j in negative (last index) and vice versa.

For now i = 3, j = 2
A[i] = 4
A[j] = -4

Now abs(A[i])/abs(A[j]) = 1, and this is perfectly divisible
so we just have the find the sequence of moves to multiply by 1 which is:

3 2 0

If abs(a[i])/abs(a[j]) = 5
then we would have printed
3 2 2
3 2 0

First step is done and now since A[i] is perfectly divisible we can make it 0.
Now we pop off the last index in positive since that is already 0.

Now the lists looks like :
positive = [1]     negative = [2]
now since both are equal, we are going to take :

i = 2, j = 1
A[i] = -4, A[j] = 2
abs(A[i])/abs(A[j]) = 2.
so we need to multiply by 2.
which is just 2^1.
So
2 1 1
since it is perfectly divisible we can make A[i] = 0 and we are done.

Therefore final moves are:

3 2 0
2 1 1

Now what if A[i]/A[j] are not perfectly divisble, lets say:
A[i] = -13, A[j] = 5
abs(A[i])/abs(A[j]) = 2 (integer division)
now we just multiply this by 2 first.

i j 1

A[i] + A[j] * 2

abs(A[i]) % abs(A[j]) = 3
Now we still have 3 left.
now A[i] = -3, A[j] = 5.
now if you notice (abs)A[i] < (abs)A[j], so we just flip ;)

Let arr = [-13, 5]

First move = 1 2 1, 
A[1] += A[2] * 2

Now arr is:
arr = [-3, 5]

a % b = 3 is still left 

Second move = 2 1 0,  
A[2] += A[1] * 1 .
Now Array is :
arr = [-3, 2]
Now abs(i) > abs(j) so dont swap indices.

Third move = 1 2 0,   
A[1] += A[2] * 1
arr = [-1, 2]

fourth move = 2 1 1,   
A[2] += A[1] * 2
arr = [-1, 0]

 */
import java.util.*;
import java.io.*;
class Transform
{
    static ArrayList<tuple> steps = new ArrayList<tuple>();
    static int A[]; 
    public static void main(String[]args) 
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        A=new int[N];
        for(int i = 0; i < N; i++)
            A[i] = sc.nextInt();
        Stack<Integer> positive = new Stack<Integer>();
        Stack<Integer> negative = new Stack<Integer>();
        // Start checking for whether possible
        int zero = 0;
        for(int i = 0; i < N; i++)
        {
            // pushing the indexes into the respective arrays
            if(A[i] < 0)
                negative.push(i);
            else if(A[i] > 0)
                positive.push(i);
            else
                zero++;
        }

        if(zero == (N - 1)) 
            System.out.println("1\n0");
        else if(zero == N || positive.size() == 0 || negative.size() == 0) 
            System.out.println("0");
        else
        {
            System.out.println("1");
            // this is basically looping till one number is non-zero
            while(!positive.empty() && !negative.empty())
            {
                // i = Number to be changed to 0. j = number to used.
                int i = positive.peek(), j = negative.peek(); 
                // we are setting this up as positive i and negative j
                // now WE remember that if negative is > positive,
                //we use negative last index as i, and positive last index as j.
                if(negative.size() > positive.size()) 
                {
                    // we are taking care of the condition here.
                    int t = i;
                    i = j;
                    j = t;
                    int idc = negative.pop();
                }
                else 
                {
                    int idc = positive.pop();
                }
                A[j] = reduce(A[i], A[j], i + 1, j + 1);
                A[i] = 0; 
            }
            System.out.println(steps.size());
            for(tuple it: steps)
            {
                System.out.println(it.i+" "+it.j+" "+it.k);
            }
        }
    }
    static int reduce(int a, int b, int i, int j)
    {
        // while a and b are not perfectly divisible
        // here you are checking if a % b != 0 or not, if b == 0, this will error out, right? yeag
        // so we need to make sure that b is not equal to 0
        while(a % b != 0)
        {
            // we just store the sign, 
            int a_sign = a/abs(a), b_sign = b/abs(b);
            // then we take the absolute value since that is all we need.
            a = abs(a); b = abs(b);
            // now if a is less than b.
            if(a < b)
            {
                // we divide b by a, to find the multiple since b > a
                int d = b/a;
                // we then take the remainder of b.
                b %= a;
                // ok so that is indeed necessary because if b is 0, we can continue in the while loop, because of division by 0.
                // so we just make it a, and reduce the multiple by 1, does this make sense? no
                if(b == 0) 
                {
                    b = a;
                    d--; 
                }
                // so now you just solve for j, i, d.
                solve(j, i, d);
            }
            else 
            {
                int d = a/b;
                a %= b;
                solve(i, j, d);
            }
            a *= a_sign; // we need to restore the original sign, because remeber we set them to their absolute value. 
            //this isn't necessary here, but is necessary in the return statement at the end.
            b *= b_sign;
        }
        // when a is perfectly divisble by b, we just perform one last solve to convert a to 0.
        solve(i, j, abs(a)/abs(b));
        // btw, return b because b has been changed in the course of many
        //solve operations so we need to update that in the array as well!
        return b;
    }
    static int abs(int a) 
    {
        return (a < 0 ? -a : a);
    }
    static void solve(int i, int j, int multiple)
    {
        // Every integer has 32 bits, 31 bits for storing the number and 1 bit for storing the sign (+ or -)
        // So we go over all the 31 bits
        for(int k = 0; k <= 31; k++)
        {
            // check if the correct bit is set or not
            if((multiple & (1 << k)) == 1 << k)    // To check if k'th bit is set , (then we need to make that move)    
            {
                steps.add(new tuple(i, j, k));     // if it is set, we add a step.
            }
        }
    }
}
class tuple
{
    int i , j , k;
    public tuple ( int i , int j , int k)
    {
        this.i = i;
        this.j = j;
        this.k = k;
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