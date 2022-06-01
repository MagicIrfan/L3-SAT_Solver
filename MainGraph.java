import java.util.*;
class MainGraph
{
    public static void main(String [] arg)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez le nom d'un fichier : ");
        String file = sc.nextLine();
        SatProblem problem = new SatProblem(file);
        Graph sat = problem.createIncidencyGraph();
        Graph transposed = sat.toTransposed();
        Composants csat = new Composants(sat);
        List<List<Integer>> connexes = csat.getData();
        System.out.println("----------Toutes les clauses----------");
        System.out.println(problem);
        System.out.println("----------Graphe de Implications----------");
        System.out.println(sat);
        System.out.println("----------Graphe Transpose----------");
        System.out.println(transposed);
        System.out.println("----------Composants fortement connexes du graphe des implications----------");
        System.out.println(connexes);
        System.out.println("----------Est-ce qu'une composante contient un litteral et son oppose ?----------");
        if(problem.isSatisfiable(sat))
            System.out.println("Une composante ne contient pas un litteral et son oppose donc la formule est satisfiable");
        else
            System.out.println("Une composante contient un litteral et son oppose donc la formule n'est pas satisfiable");     

    }
}