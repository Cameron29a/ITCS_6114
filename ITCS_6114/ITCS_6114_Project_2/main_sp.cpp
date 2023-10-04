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
#include <stack>

using namespace std;

//******************************************************************************
// Defines
//******************************************************************************
#define MAX 500
#define INF 2147483647
#define iiPair pair< int, int >

//******************************************************************************
// Structures
//******************************************************************************
struct myComp {
    bool operator() (const iiPair &p1, const iiPair &p2) {
        return p1.second > p2.second;
    }
};

//******************************************************************************
// Classes
//******************************************************************************
class Graph {
    int nodes, edges, src, dis[MAX], path[MAX];
    vector< iiPair >* adj;

public:
    Graph(){
        adj = new vector<iiPair>[MAX];
    }
    
    void readFile(void);
    void adjlist(void);
    void dijkstra(void);
    void printPath(void);
    void printNext(int);

};

//***********************************************************************************
// Driver Code
//***********************************************************************************
int main() {
    time_t start, stop;
    
    Graph g;                    // Run Constructor for Graph
    
    g.readFile();               // Open graph from txt file
    g.adjlist();                // Uncomment to see adjacency list after import
    
    start = clock();            // Start Timing
    g.dijkstra();               // Run to find shortest Path
    stop = clock();             // End Timing
    
    g.printPath();              // Print results from dijkstra
    
    cout << "Runtime: " << double(stop - start) / CLOCKS_PER_SEC << "s\n"; 
    
    return 0;
} 

//******************************************************************************
// Class Functions
//******************************************************************************
//******************************************************************************
// Function to read input files and place into graph class
//******************************************************************************
void Graph::readFile(void) {
    bool dirGraph;
    int sel, u, v, wt;
    char dir, uChar, vChar;
    string temp;
    
    cout << "Select the graph to run: enter 1-8 \n";
    while(1) {
        cin >> temp;                    // Take input as a string
        sel = atoi(temp.c_str());       // convert input string to int 
        cout << "\n";
        if(sel > 0 & sel < 9) {         // Input converted from string to int to help reject invalid selections
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
                case 5:
                    temp = "text5.txt";  // Read text file 5
                    break;
                case 6:
                    temp = "text6.txt";  // Read text file 6
                    break;
                case 7:
                    temp = "text7.txt";  // Read text file 7
                    break;
                case 8:
                    temp = "text8.txt";  // Read text file 8
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
        dirGraph = true;
        cout << "The selected graph is directed \n";
    } else {
        dirGraph = false;
        cout << "The selected graph is undirected \n";
    }
    
    for(int i = 0; i < edges; i++) {    // Cycle and extract all edges and weights
        getline(textFile, temp);
        stringstream s(temp);
        s >> uChar;                     // Starting node of edge 
        s >> vChar;                     // Ending node of edge
        s >> wt;                        // Weight of edge
        u = uChar - 'A';                // Subtract char 'A' to start node index at 0
        v = vChar - 'A';                // Indexing starting at 0 is a big brain
        
        adj[u].push_back({v, wt});      // Store node as index, store adj node with weight to travel
        if(dirGraph == false) adj[v].push_back({u, wt}); // If undirected, edges are bidirectional
    }
    
    getline(textFile, temp);
    stringstream ss(temp);
    ss >> uChar;
    src = uChar - 'A';      // Final line contains the source node
}

//******************************************************************************
// Applies Dijkstra's algorithim to find single source shortest path  
//******************************************************************************
void Graph::dijkstra(void) {
    bool visited[MAX];
    int u, v, wt;
    priority_queue< iiPair, vector< iiPair >, myComp > pq;
    
    for(int i = 0; i < nodes; i++) {
        dis[i] = INF;
        visited[i] = false;
    }
    pq.push({0, src});  // Write source and weight to prioity que 
    dis[src] = 0;       // Weight to travel from source to source
    path[src] = -1;     // Use negative number to indicate source node
    
    while (!pq.empty()) {
        u = pq.top().second;
        pq.pop();
        if(visited[u]) continue;
        for(int i = 0; i < adj[u].size(); i++) {
            v = adj[u][i].first;
            wt = adj[u][i].second;
            
            if(!visited[v] && dis[v] > dis[u] + wt) {  // Check for a shorter path to v through u.
                dis[v] = dis[u] + wt;   // Update distance v
                pq.push({dis[v], v});   // Push copy of v on to priority queue
                path[v] = u;
            }
        }
        visited[u] = true;
    }
}

//******************************************************************************
// Print out all paths and path costs 
//******************************************************************************
void Graph::printPath(void) {
    for (int i = 0; i < nodes; ++i) {
        if(dis[i] >= INF) {
            cout << "Node " << (char) (i + 'A') << " is unreachable from node " << (char) (src + 'A') << ":\n\n";
        } else if (i == src) {
            cout << "Node " << (char) (src + 'A') << " is the source node.\n\n";
        } else {
            cout << "The shortest path from node " << (char) (src + 'A') << " to node " << (char) (i + 'A') << ":\n";
            cout << "Path: " << (char) (src + 'A');
            printNext(i);           // Recursive function to print path
            cout << "\nPath cost: "<< dis[i] << "\n\n";
        }
    }
}

void Graph::printNext(int i) {
    if(path[i] == -1) return;   // Base case, at source node
    printNext(path[i]);         // Recursive call to print all nodes in path
    cout << " --> " << (char) (i + 'A');
}

//******************************************************************************
// This function is here to test the file import 
//******************************************************************************
void Graph::adjlist(void) {
    for (int u = 0; u < nodes; u++) {
        cout << (char) (u + 'A') << " --> ";
        for (auto it = adj[u].begin(); it!=adj[u].end(); it++)
            cout << (char) (it->first + 'A') << " ";
        cout << "\n";
    }
    cout << "\n";
}