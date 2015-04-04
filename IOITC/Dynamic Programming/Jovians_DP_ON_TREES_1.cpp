#include<bits/stdc++.h>
#define author animesh_f
using namespace std;

vector<int>adj[5001];
int dp1[5001]; // dp1[u] =Best you can do in the subtree rooted at 'u' by including u
int dp2[5001]; // dp2[u] =Best you can do in the subtree rooted at 'u' by excluding u
// Therefore answer must be max(dp1[u],dp2[u])
void dfs(int node , int parent)
    {
    dp1[node] = 1; // Including node in the subtree rooted at node , so number_of_people++
    dp2[node] = 0; // Not including node in the subtree rooted at node, so number_of_people=0
    int next,sz;
    sz = adj[node].size(); // Size of list of neighbours of node
    if(sz==0)return; // If leaf node
    for(int i=0;i<sz;i++)
        {
        next=adj[node][i];
        if(next!=parent) // DFS
            dfs(next,node);
        dp1[node]+=dp2[next]; // Since we can't add "father and child" together , we will add the sum we can make if we exclude the child "next" of "node" for dp1[node] as in dp1[node] we have included the "node" so we cant include the child of this node , hence we must add dp2[next] which means we dont include the child of this node
        dp2[node]+=max(dp1[next],dp2[next]); // excluding the current node , we can add the max of (taking the child in the subtree , and not taking the child in the subtree) , since both are viable options
    }
  }
int main()
    {
    // Root the tree at any arbitrary node
    int T,N,u,v;
    scanf("%d",&T);
    while(T--)
        {
        for(int i=0;i<5001;i++)adj[i].clear(); // Reset
          scanf("%d",&N);
            for(int i=1;i<N;i++)
                {
                    scanf("%d%d",&u,&v);
                    adj[v].push_back(u); // Make adjacency list
                }
          dfs(0,-1);
         printf("%d\n",max(dp1[0],dp2[0]));    // Max of taking 0 in tree and not taking it
        }
    }