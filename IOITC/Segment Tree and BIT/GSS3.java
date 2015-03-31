import java.io.*;
import java.util.*;
class GSS3
{
    static node SegmentTree[]=new node[200004];
    static int a[]=new int[50001];
    static int N;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=200003;i++)
            SegmentTree[i]=new node();
        for(int i=1;i<=N;i++)
            a[i] = Integer.parseInt(st.nextToken());
        build(1,1,N);
        int M =Integer.parseInt(br.readLine());
        StringBuilder op = new StringBuilder();
        while(M-->0)
        {
            String inp[]=br.readLine().split(" ");
            int type = Integer.parseInt(inp[0]);
            if(type==1)
            {
                int left = Integer.parseInt(inp[1]);
                int right = Integer.parseInt(inp[2]);
                int ans = query(1,1,N,left,right).maxSum;
                op.append(ans).append("\n");
            }
            else
            {
                int x=Integer.parseInt(inp[1]);
                int y=Integer.parseInt(inp[2]);
                update(1,1,N,x,y);
            }
        }
        System.out.println(op);
    }
    static void update(int n ,int l , int r , int pos , int val)
    {
        if(l==r)
        {
            SegmentTree[n].sum=val;
            SegmentTree[n].prefixMaxSum=val;
            SegmentTree[n].suffixMaxSum=val;
            SegmentTree[n].maxSum=val;
            return;
        }
        int lc = n*2; int rc=lc+1; int mid=(l+r)/2;
        if(mid>=pos)
        update(lc,l,mid,pos,val);
        else
        update(rc,mid+1,r,pos,val);
        SegmentTree[n]=merge(SegmentTree[lc],SegmentTree[rc]);
    }
    static node query(int n , int l , int r , int qs , int qe)
    {
        if(l==qs&&r==qe)
            return SegmentTree[n]; 
        int lc = n*2;
        int rc= lc+1;
        int mid=(l+r)/2;
        if(qs>mid)
            return query(rc,mid+1,r,qs,qe);
        if(qe<=mid)
            return query(lc,l,mid,qs,qe);
        node x = query(lc,l,mid,qs,mid);
        node y = query(rc,mid+1,r,mid+1,qe);
        node res=merge(x,y);
        return res;
    }
    static void build(int n , int l , int r)
    {
        if(l==r)
        {
            SegmentTree[n].sum=a[l];
            SegmentTree[n].prefixMaxSum=a[l];
            SegmentTree[n].suffixMaxSum=a[l];
            SegmentTree[n].maxSum=a[l]; 
            return;
        }
        int lc = n*2;
        int rc = lc+1;
        int mid = (l+r)/2;
        build(lc,l,mid);
        build(rc,mid+1,r);
        SegmentTree[n]=merge(SegmentTree[lc],SegmentTree[rc]);
    }
    static node merge(node lc , node rc)
    {
        node x=new node();
        x.sum = lc.sum + rc.sum;
        x.prefixMaxSum = Math.max(lc.prefixMaxSum,lc.sum+rc.prefixMaxSum);
        x.suffixMaxSum = Math.max(rc.suffixMaxSum, rc.sum + lc.suffixMaxSum);
        x.maxSum = 
        Math.max(x.prefixMaxSum, Math.max(x.suffixMaxSum, Math.max(lc.maxSum, Math.max(rc.maxSum,  lc.suffixMaxSum + rc.prefixMaxSum))));
        return x;
    }
}
class node
{
    int sum;
    int prefixMaxSum;
    int suffixMaxSum;
    int maxSum;
    public node()
    {
        sum=-99999999;prefixMaxSum=-99999999;suffixMaxSum=-99999999;maxSum=-99999999;
    }
}
