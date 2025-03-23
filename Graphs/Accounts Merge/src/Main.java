/*
Given a list of accounts where each element accounts[i] is a list of strings, where the
first element accounts[i][0] is a name, and the rest of the elements are emails representing
emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person
if there is some common email to both accounts. Note that even if two accounts have the same
name, they may belong to different people as people could have the same name. A person can
have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element
of each account is the name, and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.



Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by
other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'],
['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:

Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],
["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],
["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]


Constraints:

1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.

 */

import java.util.*;

class DisjointSet {
    /* To store the ranks, parents and
    sizes of different set of vertices */
    int[] rank, parent, size;

    // Constructor
    DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Function to find ultimate parent
    int findUPar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUPar(parent[node]);
    }

    // Function to implement union by rank
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        }
        else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        }
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    // Function to implement union by size
    void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}


// Solution class
class Solution {

    // Function to merge the accounts
    static List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size(); // Number of accounts

        // Disjoint Set data structure
        DisjointSet ds = new DisjointSet(n);

        // Hashmap to store the pair: {mails, node}
        Map<String, Integer> mapMailNode = new HashMap<>();

        // Iterate on all the accounts
        for (int i = 0; i < n; i++) {

            // Iterate on all the mails of the person
            for (int j = 1; j < accounts.get(i).size(); j++) {

                // Get the mail
                String mail = accounts.get(i).get(j);

                // If this mail was not already existing
                if (!mapMailNode.containsKey(mail)) {

                    // Add it to the hashmap
                    mapMailNode.put(mail, i);
                }

                /* Otherwise join it with
                the previous component */
                else {

                    // Unite the components
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        // To store the merged mails
        List<List<String>> mergedMail = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>());
        }

        // Iterate on the Hashmap
        for (Map.Entry<String, Integer> entry : mapMailNode.entrySet()) {

            String mail = entry.getKey(); // Mail
            int node = ds.findUPar(entry.getValue()); // Node

            // Add the merged mail to the list
            mergedMail.get(node).add(mail);
        }

        // To return the result
        List<List<String>> ans = new ArrayList<>();

        // Iterate on all list of merged mails
        for (int i = 0; i < n; i++) {

            /* If a person has no mails,
            skip the iteration */
            if (mergedMail.get(i).isEmpty())
                continue;

            // Otherwise, add all the merged mails of person
            Collections.sort(mergedMail.get(i));
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(mergedMail.get(i));
            ans.add(temp);
        }
        ans.sort(Comparator.comparing(list -> list.get(0)));
        return ans;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
                Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
                Arrays.asList("Mary","mary@mail.com"),
                Arrays.asList("John","johnnybravo@mail.com")
        );

        // Function call to merge the accounts
        List<List<String>> ans = accountsMerge(accounts);

        // Output
        System.out.println("The merged accounts are:");
        for (List<String> account : ans) {
            System.out.println(String.join(" ", account));
        }
    }
}
/*
Complexity Analysis:
Time Complexity: O(N+E) + O(E*4ɑ) + O(N*(ElogE + E)) (where E = no. of emails)

Visiting all the emails takes O(N+E) time.
In the worst case, all the accounts can be merged taking O(E*4ɑ) time.
All the emails to the result list and Sorting the emails take O(N*(ElogE + E)) times.
Space Complexity: O(N+E)

The hashmap will store all the emails taking O(E) space.
The disjoint set data structure uses parent and size/rank arrays taking O(N) space.
The resulting list will take up O(E) space.
 */