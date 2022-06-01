import java.util.*;

public class Graph {
    
    private class Edge 
    {
        public int source;
        public int destination;
    
        public Edge(int from, int to) 
        {
            this.source = from;
            this.destination = to;
        }

        public String toString()
        {
            return "[" + this.source + ", " + this.destination + "]";
        }
    }

    private int cardinal;
    private List<LinkedList<Edge>> incidency;

    public Graph(int size) 
    {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest) {;
        incidency.get(source).addLast(new Edge(source,dest));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(cardinal + "\n");
        for (int index = 0; index<cardinal; index++) {
            for (Edge e : incidency.get(index)) {
                result.append(SatProblem.toSatIndex(index) + " " + SatProblem.toSatIndex(e.source) + " -> " + SatProblem.toSatIndex(e.destination) + " "
                        + "\n");
            }
        }
        return result.toString();

    }

    public Vector<Integer> neighbors(int source)
    {
        Vector<Integer> data = new Vector<Integer>();
        for (Edge e : incidency.get(source)) {
                data.add(e.destination);
        }  
        return data;
    }

    public Graph toTransposed()
    {
        Graph newGraph = new Graph(cardinal);
        for (int index = 0; index < cardinal; index++) {
            for (Edge e : incidency.get(index)) {
                newGraph.addArc(e.destination, e.source);             
            }
            
        }
        return newGraph;
    }

    public boolean isSame(int source, int destination)
    {
        return source == destination;
    }

}
