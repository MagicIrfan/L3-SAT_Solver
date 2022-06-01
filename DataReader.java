import java.util.*;
import java.io.*;
class DataReader
{
    public List<List<Integer>> data;
    public DataReader (String fileName)
    {
        data = new ArrayList<List<Integer>>();
        try 
        {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                StringBuilder build = new StringBuilder();
                List<Integer> linetoInt = new ArrayList<Integer>();
                boolean containsdigits = false;
                for(int index = 0 ; index < line.length(); index++){
                    if (isValid(line.charAt(index)))  
                    {
                        build.append(line.charAt(index)); 
                        containsdigits = true; 
                    }                                      
                    if (isEndOfLine(line, index, build) && containsdigits)
                    {
                        linetoInt.add(Integer.parseInt(build.toString()));
                        build.setLength(0);
                    }           
                }
                if(containsdigits)
                    data.add(linetoInt);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("ERREUR : Le fichier n'existe pas !");
            e.printStackTrace();
            System.exit(1);
        }


    }

    public boolean isValid(char str) {
        return ((int)str-48 > 0 && (int)str-48 <= 9) || str == '-';
    }

    public boolean isEndOfLine(String line, int index, StringBuilder build)
    {
        return (line.charAt(index) == ' ' || index == line.length()-1) && !build.toString().isEmpty();
    }
    
}