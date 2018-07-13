#include <iostream>
#include <vector>
#include <queue>
#include <bitset>
#include <algorithm>

#define SIZE 10000
void dfsVisit(int u);


typedef enum State{UNV, OPEN, CLOSED} State;
int n,m; // number of node and edges
std::vector<int> g[SIZE];
State state[SIZE];
int parent[SIZE];
int found_cycle;

int main(){
	// get input of the problem
	scanf("%d %d", &n, &m);
	int u,v;
	for(int i=0;i<m;i++){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
	}
	for(int i=0;i<n;i++){
		state[i] = UNV;
	}
	found_cycle = 0;

	// solve the problem
	for(int i=0;i<n && found_cycle==0;i++){
		if(state[i]==UNV){
			dfsVisit(i);
		}
	}

	if(found_cycle==0){
		printf("no\n");
	}
	return 0;
}

void dfsVisit(int u){
	if(found_cycle==0){
		state[u] = OPEN;
		for(int v : g[u]){
			if(state[v] == UNV){
				parent[v] = u;
				dfsVisit(v);
			}else if(state[v] == OPEN){
				found_cycle = 1;
				//printf("%d ",v);
				if(u==v){
					printf("\n");
				}else{
					int w = v; // int w = u;
					while(w != u) { //while(w!=v){
						printf("%d ", w);
						w = parent[w];
					}
					printf("%d\n",w);
				}
				return;
			}
		}
		state[u] = CLOSED;
	}
}
