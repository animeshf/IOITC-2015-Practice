import java.util.*;
import java.io.*;
class SEQUENCELAND
{
    static Map<Integer,ArrayList<Integer>>map=new HashMap<Integer,ArrayList<Integer>>();
    public static void main(String[]args)throws IOException
    {
        InputReader1 sc=new InputReader1(System.in);
        int N=sc.nextInt(),K=sc.nextInt();
        int visited[]=new int[N+1]; // Arrays which keeps track of who all are extended family members
        Arrays.fill(visited,0);
        //Building An Adjacency List where each person is linked to his set of IDs
        // Building up an adjacency List
        for(int i=1;i<=N;i++)
        {
            int p=sc.nextInt();
            map.put(i,new ArrayList<Integer>());
            while(p-->0)
            {
                int a=sc.nextInt();
                map.get(i).add(a); 
            }
        }
        for(int i=1;i<=N;i++)
        {
            ArrayList<Integer>a=new ArrayList<Integer>(map.get(i)); //Taking ArrayList
            Collections.sort(a);//Sorting ArrayList
            map.put(i,a);//Putting sorted values of ArrayList back into the AdjacencyList.
        }
        Queue<Integer>q=new LinkedList<Integer>();//QUEUE FOR BFS 
        q.add(1); //Add President to the Queue
        visited[1]=1;//The president itself is already visited also
        int ans=0;
        while(!q.isEmpty()) //BFS O(N^3)
        {
            int i=q.poll(); // Taking out node
            //System.out.println("Checking with "+i);
            ans++;
            ArrayList<Integer>b=new ArrayList<Integer>(map.get(i)); // Obtain relative's ArrayList
            for(int j=1;j<=N;j++) // Check for common elements with those people who are not already relatives
            {
                if(i==j)
                    continue;
                if(visited[j]==1) // If person is visited already i.e. already in family , we move to the next person
                    continue;
                //System.out.println("Checking "+i+"  with"+" "+j);
                ArrayList<Integer>c=new ArrayList<Integer>(map.get(j));
                int k=0;
                int l=0;
                int count=0; 
                while((k<b.size())&&(l<c.size())) // Checking for common elements
                {
                    if(b.get(k).equals(c.get(l)))
                    {
                        k++;
                        l++;
                        count++;
                    }
                    else if(b.get(k)>c.get(l))
                        l++;
                    else
                        k++;
                    if(count>=K)
                    {
                        //System.out.println(i+ " "+ j+" are family");
                        visited[j]=1;
                        // Marking the node visited == In the Family of President
                        q.add(j); //Adding newly found 
                        break;
                    }
                }
            }
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

            