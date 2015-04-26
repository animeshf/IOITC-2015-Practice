import java.io.*;
import java.util.*;
class FoxAndNames
{
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String arr[] = new String[T];
        for(int i=0;i<T;i++)arr[i]=br.readLine();
        int inDegree[]=new int[26];
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<26;i++)adjList.add(new ArrayList<Integer>());
        for(int i=0;i<T-1;i++)
        {
            int j=0;
            String one = arr[i] , two = arr[i+1];
            int l1 = one.length() , l2 = two.length();
            while(one.charAt(j) == two.charAt(j))
            {
                j++;
                if(j>=l1 || j>=l2)
                {
                    if(l1>l2) {System.out.println("Impossible");System.exit(0);}
                    break;
                }
            }
            if(j<l1 && j<l2)
            {
                adjList.get(one.charAt(j)-'a').add(two.charAt(j)-'a');
                inDegree[two.charAt(j)-'a']++;
            }
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<26;i++) 
            if(inDegree[i]==0) 
                q.add(i);
        String ans="";
        while(!q.isEmpty())
        {
            int x = q.poll();
            ans = ans +(char)(x+97);
            for(int next : adjList.get(x))
            {
                inDegree[next]--;
                if(inDegree[next]==0) 
                    q.add(next);
            }
        }
        if(ans.length()!=26) System.out.println("Impossible");
        else System.out.println(ans);
    }
}