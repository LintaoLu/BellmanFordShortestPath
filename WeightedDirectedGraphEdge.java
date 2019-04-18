package WeightedDirectedGraph;

class WeightedDirectedGraphEdge
{
    private int to;
    private double weight;

    public WeightedDirectedGraphEdge(int to, double weight)
    {
        this.to = to;
        this.weight = weight;
    }

    public int other() { return to; }

    public double getWeight() { return weight; }
}