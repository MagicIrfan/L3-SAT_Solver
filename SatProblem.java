import java.util.*;

public class SatProblem 
{
    private class Clauses {

        public int x, y;
        public Clauses(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    
        public String toString()
        {
             return "[" + this.x + ", " + this.y + "]";
        }
    }
    private int variables;
    private int clauses;
    static List<Clauses> liste;
    private int [][][] clausesTo2SAT;

    public SatProblem(String fileName)
    {
        SatProblem.liste = new ArrayList<Clauses>();
        DataReader reader = new DataReader(fileName);
        List<List<Integer>> temp = reader.data;
        this.variables = temp.get(0).get(0);
        this.clauses = temp.get(0).get(1);
        temp = temp.subList(1, temp.size());
        for (int index = 0; index < temp.size(); index++){
            SatProblem.liste.add(new Clauses(temp.get(index).get(0), temp.get(index).get(1)));
        }
        this.clausesTo2SAT = new int[this.clauses][2][2];
        for (int index = 0; index<SatProblem.liste.size(); index++){
                this.clausesTo2SAT[index][0][0] = SatProblem.toInternalIndex(-SatProblem.liste.get(index).x);
                this.clausesTo2SAT[index][0][1] = SatProblem.toInternalIndex(SatProblem.liste.get(index).y);
                this.clausesTo2SAT[index][1][0] = SatProblem.toInternalIndex(-SatProblem.liste.get(index).y);
                this.clausesTo2SAT[index][1][1] = SatProblem.toInternalIndex(SatProblem.liste.get(index).x);
            
        }
        
    }

    static int getMinimalValue()
    {
        int min = Integer.MAX_VALUE;
        for (int index = 0; index < SatProblem.liste.size(); index++){
            int minxy = Math.min(SatProblem.liste.get(index).x, SatProblem.liste.get(index).y);
            if (minxy < min)
                min = minxy;
        }
        return min;
    }

    static int toInternalIndex(int index)
    {
        int min = SatProblem.getMinimalValue();
        return (index >= 1) ? index - min - 1 : index - min;
    }

    static int toSatIndex(int index)
    {
        int min = SatProblem.getMinimalValue();
        int absmin = Math.abs(min);
        return ( index >= absmin) ? index - absmin + 1 : index - absmin;
    }

    public String toString()
    {
        StringBuilder build = new StringBuilder("[");
        for(int i = 0; i<this.clausesTo2SAT.length; i++)
        {
            build.append("[");
            for(int j = 0; j<this.clausesTo2SAT[0].length; j++)
            {
                build.append("[");
                for(int k = 0; k<this.clausesTo2SAT[0][0].length; k++)
                {
                    
                    build.append(SatProblem.toSatIndex(this.clausesTo2SAT[i][j][k]));
                    if (k!=this.clausesTo2SAT[0][0].length-1)
                        build.append(", ");
                }
                build.append("]");
                if (j!=this.clausesTo2SAT[0].length-1)
                    build.append(", ");
                
            }
            build.append("]");
            if (i!=this.clausesTo2SAT.length-1)
                build.append(", ");

        }
        build.append("]");

        return build.toString();
    }

    public Graph createIncidencyGraph()
    {
        Graph incidency = new Graph(this.variables*2);
        for(int i = 0; i<this.clausesTo2SAT.length; i++)
        {
            for(int j = 0; j<this.clausesTo2SAT[0].length; j++)
            {
                incidency.addArc(this.clausesTo2SAT[i][j][0], this.clausesTo2SAT[i][j][1]);                
            }
        }
        return incidency;
    }

    public boolean isSatisfiable(Graph graph)
    {
        Composants csat = new Composants(graph);
        List<List<Integer>> connexes = csat.getData();
        for (int index=0; index<connexes.size(); index++){
            for (int j=0; j <connexes.get(index).size(); j++){
                int element = connexes.get(index).get(j);
                int negative = -element;
                if(connexes.get(index).contains(negative) && connexes.get(index).contains(element))
                    return false;
            }
        }
        return true;
    }
    
}
