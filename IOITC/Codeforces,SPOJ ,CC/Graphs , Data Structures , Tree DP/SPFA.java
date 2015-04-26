/* The Shortest Path Faster Algorithm (SPFA) is a single-source
 shortest paths algorithm whose origin is unknown[see references].
 It is similar to Dijkstra's algorithm in that it performs 
 relaxations on nodes popped from some sort of queue, but, 
 unlike Dijkstra's, it is usable on graphs containing edges 
 of negative weight, like the Bellman-Ford algorithm. 
 Its value lies in the fact that, in the average case, 
 it is likely to outperform Bellman-Ford (although not Dijkstra's).
 In theory, this should lead to an improved version of Johnson's 
 algorithm as well.
 
 You can detect shortest path in O(E) best , O(V Log E) avg and O(VE) worst case time
 However it usually works around dijkstra only...
 It works with -ve edges...
 To detect Negative Cycle you can simply check if a node gets 
 added to the Queue more than V-1 times theres a -VE cycle!
 */
class SPFA
// ALL D[i] = INF at first , D[1] = 0;
 Q.push(1), inQ[1] = true;
	while (!Q.empty())
	{
		v = Q.front();
		Q.pop(), 
		inQ[v] = false; // Relax , Vertex not in Q anymore
		for (int i = 0; i < E[v].size(); i++)
		{
			u = E[v][i].first, x = E[v][i].second;
			if (D[u] > D[v] + x) // Add only if contributes to SP
			{
				D[u] = D[v] + x;
				if (!inQ[u])  // If not in Queue Already.
				Q.push(u), inQ[u] = true;
			}
		}
	}