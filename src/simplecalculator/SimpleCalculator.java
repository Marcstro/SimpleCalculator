
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class SimpleCalculator {

    Map<String, Double> values = new HashMap<String, Double> ();
    List<String> allowedOperators = Arrays.asList(new String[]{"add", "subtract", "multiply"});
    List<String> calculations = new ArrayList<String>();
    static boolean KeepGoing = true;
    
    
    public static void main(String fileName[]) {
        SimpleCalculator simpleCalcalculator = new SimpleCalculator();
        
        if(fileName.length>0){
            for(int x=1; x<fileName.length; x++)
                fileName[0]+=" " + fileName[x];
            File file = new File(fileName[0]);
            try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
  
            String line; 
            while ((line = br.readLine()) != null) 
              simpleCalcalculator.process(line); 
            }
            catch (Exception e){
                System.out.println("Could not read the file.");
            }
        }
        else{
            System.out.println("Welcome to Marcus' simple calculator!");
            System.out.println("The Syntax for this calculator is:");
            System.out.println("<Register> <Operator> <Register>, example:");
            System.out.println("A add 3, or");
            System.out.println("A multiply B");
            System.out.println("Valid Operators are: " + simpleCalcalculator.allowedOperators.toString());
            System.out.println("And \"Print <Register>\", \"Quit\"");
            Scanner scanner = new Scanner(System.in);
            while(KeepGoing){
                String insert = scanner.nextLine();
                KeepGoing=simpleCalcalculator.process(insert);
            }
        }
    }
    
    public boolean process(String insert){
        
        insert=insert.toLowerCase();

        if(insert.equals("quit")){
            KeepGoing=false;
            return KeepGoing;
        }

        String[] parts = insert.split(" ");

        if(parts[0].equals("print") && parts.length == 2 && values.containsKey(parts[1])){
            System.out.println(CompleteCalculations(parts[1]));
            return KeepGoing;
        }
        if(!CheckCorrectSyntax(parts)){
            System.out.println("\"" + insert + "\" is an invalid syntax.");
            return KeepGoing;
        }

        if(parts[2].matches("[a-zA-Z]+")){
            //the two lines below are called to initiate the values
            //the declaration happens later, as lazy evaluation
            getValueOf(parts[0]);
            getValueOf(parts[2]);
            calculations.add(insert);
            return KeepGoing;
        }

        values.put(parts[0],
                Calculate(
                        getValueOf(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2])));
        return KeepGoing;
    }
    
    public double CompleteCalculations(String RequestedData){
        Collections.reverse(calculations);
                calculations.forEach((s) -> {
                    values.put(s.split(" ")[0], 
                            Calculate(values.get(s.split(" ")[0]), s.split(" ")[1], values.get(s.split(" ")[2])));
                });
        calculations.clear();
        return(values.get(RequestedData));
    }
    
    public boolean CheckCorrectSyntax(String[] parts){
        if(parts.length == 3 //Check that the commands contains three parts
           && (parts[0].matches("[a-zA-Z]+")) //and the first one is made by only letters
           && allowedOperators.contains(parts[1]) //that the second part is a valid operator
           && (isNumber(parts[2]) || parts[0].matches("[a-zA-Z]+"))){ 
            //and the last part is either a number or a previously used value
            return true;
        }
        return false;
    }
    
    public double getValueOf(String name){
        if(!values.containsKey(name)){
            values.put(name, 0.0);
            return values.get(name);
        }
        return values.get(name);
    }
    
    public double Calculate(double first, String function, double second){
    
        if(function.equalsIgnoreCase("add"))
            first+=second;
        else if (function.equalsIgnoreCase("subtract"))
            first-=second;
        else if (function.equalsIgnoreCase("multiply"))
            first*=second;
        return first;
    }
    
    public boolean isNumber(String string){
        try {
        double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
/*
by Marcus Strom
*/
