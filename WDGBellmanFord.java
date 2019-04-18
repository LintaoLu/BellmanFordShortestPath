package WeightedDirectedGraph;

import edu.princeton.cs.algs4.Bag;
import java.util.Iterator;
import java.util.Stack;

public class WDGBellmanFord
{
    private Bag<WeightedDirectedGraphEdge>[] graph;
    private int size;
    private int[] edgeTo;
    private double[] shortestPath;

    public WDGBellmanFord(WeightedDirectedGraph weightedDirectedGraph)
    {
        graph = weightedDirectedGraph.getGraph();
        size = weightedDirectedGraph.getSize();
        edgeTo = new int[size+1];
        shortestPath = new double[size+1];
        for(int i = 0; i < size+1; i++) shortestPath[i] = Double.POSITIVE_INFINITY;
    }

    public double shortestPath(int from, int to)
    {
        if(BellmanFordSP(from)) return shortestPath[to];
        else return Double.POSITIVE_INFINITY;
    }

    public String tracePath(int from, int to)
    {
        if(!BellmanFordSP(from)) return "Cannot find shortest path!";

        Stack<Integer> stack = new Stack<>();
        stack.push(to);
        int parent = edgeTo[to];
        while(parent != 0)
        {
            stack.push(parent);
            parent = edgeTo[parent];
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.empty())
        {
            sb.append(stack.pop());
            sb.append(" -> ");
        }
        sb.setLength(sb.length() - 4);
        return sb.toString();
    }

    private boolean BellmanFordSP(int from)
    {
        shortestPath[from] = 0;
        edgeTo[from] = 0;
        for(int i = 0; i < size; i++)
        {
            //a simple optimization
            int change = 0;
            //length of graph equals to size + 1
            for(int j = 1; j < graph.length; j++)
            {
                Iterator<WeightedDirectedGraphEdge> iterator = graph[j].iterator();
                while(iterator.hasNext())
                {
                    WeightedDirectedGraphEdge e = iterator.next();
                    int child = e.other();
                    if(shortestPath[j] + e.getWeight() < shortestPath[child])
                    {
                        shortestPath[child] = shortestPath[j] + e.getWeight();
                        edgeTo[child] = j;
                        change++;
                    }
                }
            }
            //if no update, return true
            if(change == 0) return true;
        }

        //detect negative cycle
        int change = 0;
        //length of graph equals to size + 1
        for(int j = 1; j < graph.length; j++)
        {
            Iterator<WeightedDirectedGraphEdge> iterator = graph[j].iterator();
            while(iterator.hasNext())
            {
                WeightedDirectedGraphEdge e = iterator.next();
                int child = e.other();
                if(shortestPath[j] + e.getWeight() < shortestPath[child])
                {
                    shortestPath[child] = shortestPath[j] + e.getWeight();
                    change++;
                }
            }
        }

        return change == 0;
    }

    public static void main(String[] args)
    {
        WeightedDirectedGraph w = new WeightedDirectedGraph(6);
        w.addVertex(1, 2, 8);
        w.addVertex(1, 6, 10);
        w.addVertex(2, 3, 1);
        w.addVertex(3, 4, -1);
        w.addVertex(3, 6, -4);
        w.addVertex(4, 5, -2);
        w.addVertex(5, 6, 1);
        w.addVertex(6, 4, 2);
        WDGBellmanFord b = new WDGBellmanFord(w);
        System.out.println(b.shortestPath(1, 6));
        System.out.println(b.tracePath(1, 6));
    }
}
