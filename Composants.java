import java.util.*;
import java.awt.Color;
public class Composants {

    private List<Integer> temp;
    private List<List<Integer>> data;

    public Composants(Graph graph)
    {
        this.temp = new ArrayList<Integer>();
        this.data = new ArrayList<List<Integer>>();
        this.composantsFortementConnexes(graph);
    }

    private void composantsFortementConnexes(Graph graph)
    {
        Graph transposed = graph.toTransposed();
        int order = graph.order();
        Search search = new Search(graph);
        List<Integer> vertices = search.dfs();
        List<Color> colors = new ArrayList<Color>();
        for (int index = 0; index < order; index++) {
            colors.add(Color.WHITE);
        }

        for (int index = 0; index < order; index++) {
            int vertex = vertices.get(index);
            Color color = colors.get(vertex);
            if (colorIsWhite(color))
                recursiveconnexe(colors, vertex, transposed);
        }

    }

    private void recursiveconnexe(List<Color> colors,int vertex, Graph transposed)
    {
        colors.set(vertex,Color.GRAY);
        temp.add(SatProblem.toSatIndex(vertex));
        Vector<Integer> neighbours = transposed.neighbors(vertex);
        for(int index = 0; index<neighbours.size(); index++){
            int neighbour = neighbours.elementAt(index);
            Color color = colors.get(neighbour);
            if(colorIsWhite(color))
                recursiveconnexe(colors, neighbours.elementAt(index),transposed);
        }
        
        colors.set(vertex,Color.BLACK);
        if(!temp.isEmpty())
            getData().add(temp);
        temp = new ArrayList<Integer>();
        
    } 
    
    public List<List<Integer>> getData()
    {
        return this.data;
    }

    public boolean colorIsWhite(Color color)
    {
        return color == Color.WHITE;
    }

}
