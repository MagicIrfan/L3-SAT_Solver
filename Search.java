import java.util.*;
import java.awt.Color;
public class Search 
{
    public Graph graph;

    public Search(Graph graph)
    {
        this.graph = graph;
    }

    public List<Integer> dfs()
    {
        List<Integer> vertices = new ArrayList<Integer>();
        List<Color> colors = new ArrayList<Color>();
        for (int index = 0; index < this.graph.order(); index++){
            colors.add(Color.WHITE);
        }

        for (int index = 0; index < this.graph.order(); index++){
            if (colors.get(index) == Color.WHITE)
                exploredfs(colors, index,vertices );
        }
        Collections.reverse(vertices);
        return vertices;
    }

    

    public void exploredfs(List<Color> colors,int vertex, List<Integer> vertices)
    {
        colors.set(vertex,Color.GRAY);
        Vector<Integer> neighbours = graph.neighbors(vertex);
        for(int index = 0; index<neighbours.size(); index++){
            if(colors.get(neighbours.elementAt(index)) == Color.WHITE)
                exploredfs(colors, neighbours.elementAt(index), vertices);
        }
        
        colors.set(vertex,Color.BLACK);
        vertices.add(vertex);

    }



    
}
