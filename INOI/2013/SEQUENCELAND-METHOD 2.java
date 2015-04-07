import java.util.*;
import java.io.*;
class SEQUENCELAND2
{
    static Map<Long,ArrayList<Long>>map=new HashMap<Long,ArrayList<Long>>();
    public static void main(String[]args)throws Exception
    {
        InputReader1 sc=new InputReader1(System.in);
        int N=sc.nextInt(),K=sc.nextInt();
        int visited[]=new int[N+1]; 
        Arrays.fill(visited,0);
        for(int i=1;i<=N;i++)
        {
            int p=sc.nextInt();
            map.put((long)i,new ArrayList<Long>());
            while(p-->0)
            {
                long a=sc.nextLong();
                map.get((long)i).add(a); 
            }
            ArrayList<Long>a=new ArrayList<Long>(map.get((long)i)); //Taking ArrayList
            Collections.sort(a);//Sorting ArrayList
            map.put((long)i,a);
            //System.out.println(i+ " " + "IDs:" + " "+a);
        }
        
        // Adjacency Matrix
        // Draw Edge if K elements are common.
        boolean graph[][]=new boolean[N+1][N+1];
        for(int i=1;i<=N;i++)
        {
            ArrayList<Long>b=new ArrayList<Long>(map.get((long)i));
            for(int j=i+1;j<=N;j++)
            {
                ArrayList<Long>c=new ArrayList<Long>(map.get((long)j));
               // System.out.println("Comparing "+ b+"   with"+"  "+c);
                boolean flag=false;
               // if(b.get(0)==c.get(0))
                //flag=true;
               // System.out.println(flag);
                int k=0;
                int l=0;
                int count=0;
                while((k<b.size())&&(l<c.size())) // Checking for common elements
                {
                    long c1=b.get(k);
                    long c2=c.get(l);
                    if(c1==c2)
                    {
                        k++;
                        l++;
                        count++;
                    }
                    else if(c1>c2)
                        l++;
                    else
                        k++;
                    if(count>=K)
                    {
                        graph[i][j]=true;
                        graph[j][i]=true;
                        break;
                    }
                }              
              //  System.out.println(count);
            }
        }
        Queue<Integer>q=new LinkedList<Integer>();//QUEUE FOR BFS 
        q.add(1); //Add President to the Queue
        while(!q.isEmpty()) //BFS O(N^3)
        {
            int i=q.poll(); // Taking out node
            visited[i]=1; // Marking node = family
            for(int j=1;j<=N;j++) 
            {
                if(graph[i][j]&&visited[j]==0)
                q.add(j);
            }
        }
        int ans=0;
        for(int i=1;i<=N;i++) // If 1 then family
        { 
            if(visited[i]==1)
                ans++;
        }
        System.out.println(ans);
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

            