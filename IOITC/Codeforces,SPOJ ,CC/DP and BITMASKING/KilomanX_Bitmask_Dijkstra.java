/*
http://topcoder.bgcoder.com/print.php?id=487
So you start with 1 weapon.
and you need to defeat all the bosses
everytime you defeat a boss you pick up his weapon
you need to minimize the number of shots required.

Basically you can model this as a shortest path problem
at each state, you try out each one of the remaining bosses using all your weapons, and add them to the queue.
you find the next element to try by choosing the nodes which have the least number of shots taken so far.

The least total number of shots spent is stored in every node na? similar to shortest path, where you store the shortest path so far.

Just think that a particular subset of weapons is unique.
and think of this subset as a vertex.
So you have max 2**15 vertices. You keep a visited array just like dijkstra. Rememember that all the bosses you've killed, you have their weapons only.No other weapons.So still I can visit the unkilled bosses Thats what you do when you arrive at a particular state.



Think of a graph.
Each vertex contains an array of the bosses killed.
Each vertex is connected to only those other vertexes which have one more boss killed compared to the current vertex.

The cost of going from one vertex to another is the least number of shots required to kill the extra boss in the next vertex using any of the weapons you currently have. Like Edge Weight .

Source vertex is the vertex where no bosses have been killed.
Destination vertex is the one where all bosses have been killed.

Find the shortest path from source to dest using Dijsktra.
Using Bitmasks to represent the array of bosses stored in any vertex.
*/

boolean visited[32768];
class KilomanX_Bitmask_Dijkstra
class node 
{
 int weapons;
 int shots;
  // Define a comparator that puts nodes with less shots on top appropriate to your language
};

int leastShots(String[] damageChart, int[] bossHealth)
{
   priorityQueue pq;

  // default, 0 weapons, 0 shots current
   pq.push(node(0, 0));

  // Basic Dijkstra mostly.
 while (pq.empty() == false)
 {
  node top = pq.top();
  pq.pop();

  // Make sure we don't visit the same configuration twice
  // We use bitmasks to check whether current vertex(subset) has been visited.
  if (visited[top.weapons]) continue; // skip the current node, as its already been visited. 
  // top is the current node in dijkstra, top.weapons is the bitmask representing the current array of weapons
  visited[top.weapons] = true;
  // array represents bit mask which represents a number. 
 //  O(E + V log V) where V = 2**N = 2**16 
   
   // A quick trick to check if we have all the weapons, meaning we defeated all the bosses.
  // We use the fact that (2^numWeapons - 1) will have all the numWeapons bits set to 1 == destination vertex  == all the bosses have been killed .
  // if numWeapons = 8, then (1 << numWeapons) - 1) = 11111111 ( in binary ) which means all bosses have been killed
  if (top.weapons == (1 << numWeapons) - 1)   // if all the bosses are killed.
   return top.shots; // return the answer topcoder style. you can print.

  // go through all the bosses
  for (int i = 0; i < damageChart.length; i++)
  {
   // Check if we've already visited this boss, then don't bother trying him again
   // if the current boss is already killed
    // same, check if ith boss has been killed or not.
   if (top.weapons & (1 << i)) continue;

   // Now figure out what the best amount of time that we can destroy this boss is, given the weapons we have.
   // We initialize this value to the boss's health, as that is our default (with our KiloBuster).
  // we have a default weapon, which does 1 damage, so the number of shots required is the health of the current boss
  // we are trying to find the best possible edge weight to go to next vertex
    best = Health of ith Boss
    for (int j = 0; j < damageChart.length; j++)
    {
      // we cant use the current boss's weapon
      if (i == j) continue;
    // if we have the current weapon and its damage is greater than 0
      // ((top.weapons >> j) & 1) or (top.weapons & (1 << j) = check if jth bit is set or not or check if we have jth weapon.
    if ((top.weapons & (1 << j)) && damageChart[j][i] != '0') {
     // We have this weapon, so try using it to defeat this boss
      // calculate the shots required using current weapon to defeat current boss
     int shotsNeeded = bossHealth[i] / (damageChart[j][i] - '0');
     if (bossHealth[i] % (damageChart[j][i] - '0') != 0) shotsNeeded++;
      // check if this is better
     best = min(best, shotsNeeded);
    }
   }

   // Add the new node to be searched, showing that we defeated boss i, and we used 'best' shots to defeat him.
    // add a new vertex with the current boss killed to the queue
    // cant understand bit manipulation part
    // top.weapons | (1 << i) = set ith bit in mask or set ith boss as killed or set that we have the ith weapon.ok com up
   pq.add(node(top.weapons | (1 << i), top.shots + best));
  }
 }
}