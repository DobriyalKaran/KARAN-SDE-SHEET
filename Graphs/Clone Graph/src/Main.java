/*
Problem Description

Clone an undirected graph. Each node in the graph contains a label and a list of
its neighbors.

Note: The test cases are generated in the following format (use the following format
to use See Expected Output option):
First integer N is the number of nodes.
Then, N intgers follow denoting the label (or hash) of the N nodes.
Then, N2 integers following denoting the adjacency matrix of a graph, where Adj[i][j] = 1
denotes presence of an undirected edge between the ith and jth node, O otherwise.



Problem Constraints

1 <= Number of nodes <= 105



Input Format

First and only argument is a node A denoting the root of the undirected graph.



Output Format

Return the node denoting the root of the new clone graph.



Example Input

Input 1:

      1
    / | \
   3  2  4
        / \
       5   6
Input 2:

      1
     / \
    3   4
   /   /|\
  2   5 7 6


Example Output

Output 1:

 Output will the same graph but with new pointers:
      1
    / | \
   3  2  4
        / \
       5   6
Output 2:

      1
     / \
    3   4
   /   /|\
  2   5 7 6


Example Explanation

Explanation 1:

 We need to return the same graph, but the pointers to the node should be different.
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
    }
}

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
};

public  UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;

    // HashMap to store original node -> cloned node
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    // Queue for BFS traversal
    Queue<UndirectedGraphNode> queue = new LinkedList<>();

    // Step 1: Create clone of starting node and push it into the queue
    UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
    map.put(node, clone);
    queue.add(node);

    // Step 2: BFS traversal
    while (!queue.isEmpty()) {
        UndirectedGraphNode curr = queue.poll(); // Pop original node

        // Traverse all its neighbors
        for (UndirectedGraphNode neighbor : curr.neighbors) {
            if (!map.containsKey(neighbor)) {
                // Create new clone for this neighbor
                map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                queue.add(neighbor);
            }
            // Add the cloned neighbor to the cloned node's neighbors list
            map.get(curr).neighbors.add(map.get(neighbor));
        }
    }

    return map.get(node); // Return the new cloned graph
}

// TC : O(N + M)
// SC : O(N)