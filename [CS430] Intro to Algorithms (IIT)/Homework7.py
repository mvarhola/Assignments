# Markiyan Varhola
# A20324717

# python Homework7.py

# Implementation of Dijkstra's algorithm
# Modified from https://startupnextdoor.com/dijkstras-algorithm-in-python-3/

# Modify the start and end vertices in the main() declaration

from collections import namedtuple
import queue

#custom data structure for edges
Edge = namedtuple('Edge', ['vertex', 'weight'])

# Graph with the ability to add vertices and edges
class Graph():
    def __init__(self, vertex_count):
        self.vertex_count = vertex_count
        self.adjacency_list = [[] for _ in range(vertex_count)]

    def add_edge(self, start, dest, weight):
        self.adjacency_list[start].append(Edge(dest, weight))
        self.adjacency_list[dest].append(Edge(start, weight))

    def get_edge(self, vertex):
        for e in self.adjacency_list[vertex]:
            yield e

    def get_vertex(self):
        for v in range(self.vertex_count):
            yield v

# Implementation of Dijkstra's algorithm
# Finds the shortest path from the start to all vertices
def dijkstra(graph, start):
    q = queue.PriorityQueue()
    parents = []
    distances = []
    start_weight = float("inf")

    #Initialize distance 0 for the start vertex, and infinity for the other vertices
    for i in graph.get_vertex():
        weight = start_weight
        if start == i:
            weight = 0
        distances.append(weight)
        parents.append(None)

    q.put(([0, start]))

    # While Q is not empty, pop the node with the smallest distance. 
    # In the first run, source node will be chosen because distance was initialized to 0. 
    # In the next run, the next node with the smallest distance value is chosen.
    while not q.empty():
        v_tuple = q.get()
        v = v_tuple[1]

        for e in graph.get_edge(v):
            candidate_distance = distances[v] + e.weight
            if distances[e.vertex] > candidate_distance:
                # there is a new minimal distance found for the vertex, so update distances(e.vertex) to the new minimal distance value;
                distances[e.vertex] = candidate_distance
                parents[e.vertex] = v
                q.put(([distances[e.vertex], e.vertex]))

    return parents, distances


def get_shortest_path(parents, distances, start, dest):
    # generates the shortest path from a start point to a destination
    # since dijkstras algorithm only gets the shortest path from source to all vertices
    shortest_path = []
    end = dest
    while end is not None: 
        # read off the parents of the destination and keep iterating until start is reached
        shortest_path.append(end)
        end = parents[end]

    shortest_path.reverse() # path starts at the destination, needs to be reversed to be human readablee
    return shortest_path, distances[dest]

def main():
    g = Graph(15)   # create graph with 15 vertices

    # add a bunch of edges with different weights
    g.add_edge(0, 1, 4)
    g.add_edge(1, 7, 6)
    g.add_edge(1, 2, 1)
    g.add_edge(2, 3, 3)
    g.add_edge(3, 7, 1)

    g.add_edge(3, 4, 2)
    g.add_edge(3, 5, 1)
    g.add_edge(4, 5, 1)
    g.add_edge(5, 6, 1)
    g.add_edge(6, 7, 2)

    g.add_edge(6, 8, 2)
    g.add_edge(7, 8, 2)
    g.add_edge(8, 1, 33)
    g.add_edge(9, 7, 1)
    g.add_edge(10, 2, 1)

    g.add_edge(11, 3, 1)
    g.add_edge(12, 7, 1)
    g.add_edge(13, 4, 1)
    g.add_edge(13, 5, 1)
    g.add_edge(13, 7, 2)

    g.add_edge(13, 8, 12)
    g.add_edge(13, 5, 1)
    g.add_edge(14, 6, 1)
    g.add_edge(14, 7, 1)
    g.add_edge(14, 8, 1)
    g.add_edge(14, 10, 1)

    start = 0   # starting vertex
    end = 14    # destination vertex

    parents, distances = dijkstra(g, 0) # get all shortest paths from starting vertex
    path, distance = get_shortest_path(parents, distances, start, end)  # get path and total distance (weight)

    print("Path: {} \nDistance: {}".format(' > '.join(str(x) for x in path),distance))

if __name__ == "__main__":  
    main()