//******************************************************************************
// Libraries
//******************************************************************************
#include <iostream>
#include <fstream>
#include <sstream>
#include <cmath>
#include <cctype>
#include <stdlib.h>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

//******************************************************************************
// Defines
//******************************************************************************
#define iiPair pair< int, int >
#define ipPair pair< int, iiPair >

//******************************************************************************
// Class for Disjoint-set Forest  
//******************************************************************************
class DSF {
    int* parent;
    int* rank;

public:
    DSF(int n){
        parent = new int[n];
        rank = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            rank[i] = 1;
        }
    }
    
    int findSet(int);
    void uniteSet(int, int);
    
};

//******************************************************************************
// Class for Graph
//******************************************************************************
class Graph {
    int nodes, edges;
    vector< ipPair > edgeList;
    
public:
    Graph(void) {}

    void readFile(void);
    void kruskal(void);

};

//***********************************************************************************
// Driver Code
//***********************************************************************************
int main() {
    time_t start, stop;
    
    Graph g;                        // Run Constructor for Graph
    
    g.readFile();                   // Open Graph from txt file

    start = clock();                // Start Timing
    g.kruskal();                    // Run Kruskal's algorithm on the selected graph
    stop = clock();                 // End Timing
    
    cout << "Runtime: " << double(stop - start) / CLOCKS_PER_SEC << "s\n"; 
    
    return 0;
} 

//***********************************************************************************
// Graph Functions
//***********************************************************************************
//******************************************************************************
// Function to read input files and place into graph class
//******************************************************************************
void Graph::readFile(void) {
    int sel, u, v, wt;
    char dir, uChar, vChar;
    string temp;
    
    cout << "Select the graph to run: enter 1-4 \n";
    while(1) {
        cin >> temp;                    // Take input as a string
        sel = atoi(temp.c_str());       // convert input string to int 
        cout << "\n";
        if(sel > 0 & sel < 5) {         // Input converted from string to int to help reject invalid selections
            switch(sel) {
                case 1:
                    temp = "text1.txt";  // Read text file 1
                    break;
                case 2:
                    temp = "text2.txt";  // Read text file 2
                    break;
                case 3:
                    temp = "text3.txt";  // Read text file 3
                    break;
                case 4:
                    temp = "text4.txt";  // Read text file 4
                    break;
            }
            break;
        } else  
            cout << "Invlaid input, try again. \n";
    }
    
    ifstream textFile(temp);        // Read text file
    getline(textFile, temp);        // Write line 1 in temp
    stringstream s(temp);           // convert temp to string stream
    s >> nodes;                     // 1st line position 1 tells # of nodes
    s >> edges;                     // 1st line position 2 tells # of edges
    s >> dir;                       // 1st line position 3 tells if directed
    
    if(dir == 'D') {
        cout << "This algorithm only works with undirected graphs \n";
        exit(0);
    }
    
    for(int i = 0; i < edges; i++) {
        getline(textFile, temp);
        stringstream s(temp);
        s >> uChar;
        s >> vChar;
        s >> wt;
        u = uChar - 'A';          // Subtract char A to start at 0
        v = vChar - 'A';
        
        edgeList.push_back({wt, {u, v}});
    }
}

//******************************************************************************
// Applies Kruskal's algorithim to find minimum spanning tree
//******************************************************************************
void Graph::kruskal() {
    int u, v, s1, s2, totalCost;

    sort(edgeList.begin(), edgeList.end());     // Sort edges in ascending  order
    
    DSF dsf(nodes);

    for(int i = 0; i < edges; i++) {
        u = edgeList[i].second.first;
        v = edgeList[i].second.second;
        
        s1 = dsf.findSet(u);
        s2 = dsf.findSet(v);
        
        if(s1 != s2) {                          // Connect u and v by this edge if it doesn't make a cycle
            totalCost += edgeList[i].first;
            dsf.uniteSet(s1, s2);
            cout << (char) (u + 'A') << " --> " << (char) (v + 'A') << ": Cost = " << edgeList[i].first << "\n";
        }
    }
    cout << "Total Cost: " << totalCost << "\n\n"; 
}

//***********************************************************************************
// DS Functions
//***********************************************************************************
//******************************************************************************
// Determines if two nodes belong to the same tree
//******************************************************************************
int DSF::findSet(int x) {
    if(parent[x] == -1) return x;
    return parent[x] = findSet(parent[x]);
}

//******************************************************************************
// Connects 2 nodes to make an edge if it doesn't make a cycle  
//******************************************************************************
void DSF::uniteSet(int s1, int s2) {
    if (rank[s1] < rank[s2]) {
        parent[s1] = s2;
    } else if (rank[s1] > rank[s2]) {
        parent[s2] = s1;
    } else {
        parent[s2] = s1;
        rank[s1] += 1;
    }
}