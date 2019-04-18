package WeightedDirectedGraph;

import edu.princeton.cs.algs4.Bag;

public class WeightedDirectedGraph
{
    private final int size;
    private Bag<WeightedDirectedGraphEdge>[] graph;

    public WeightedDirectedGraph(int size)
    {
        this.size = size;
        //Actual size is greater than input size, because I left 0 in blank.
        graph = (Bag<WeightedDirectedGraphEdge>[]) new Bag[size+1];
        for(int i = 0; i < graph.length; i++) graph[i] = new Bag<>();
    }

    public void addVertex(int from, int to, double weight)
    {
        WeightedDirectedGraphEdge e = new WeightedDirectedGraphEdge(to, weight);
        graph[from].add(e);
    }

    public int getSize() { return size; }

    public Bag<WeightedDirectedGraphEdge>[] getGraph() { return graph; }
}

