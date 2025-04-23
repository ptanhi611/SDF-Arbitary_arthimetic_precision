
import arbitraryarithmetic.*;

public class MyInfArith {
    public static void main(String[] args) {
        if(args.length!=4){
            System.out.println("Usage:- java MyInfArith <int/float> <add/sub/mul/div> <First Number(string)> <Second Number(String)>");
            return;
        }

        

        if(args[0].equals("int")){

            AInteger x = new AInteger(args[2]);
            AInteger y = new AInteger(args[3]);

            AInteger ans = new AInteger();


            switch (args[1]) {
                case "add":
                    ans = x.add(y);
                    break;
                
                case "sub":
                    ans = x.sub(y);
                    break;
                
                case "mul":
                    ans = x.mul(y);
                    break;

                case "div":
                    ans = x.div(y);
                    break;

                default:
                    System.out.println("Invalid argument. Usage:- java MyInfArith <int/float> <add/sub/mul/div> <First Number(string)> <Second Number(String)>");
                    break;
            }

            String F_ans = ans.I_to_String();
            System.out.println(F_ans);
        }

        else if(args[0].equals("float")){
            AFloat x = new AFloat(args[2]);
            AFloat y = new AFloat(args[3]);

            AFloat ans = new AFloat();

            switch (args[1]) {
                case "add":
                    ans = x.add(y);
                    break;
                
                case "sub":
                    ans = x.sub(y);
                    break;
                
                case "mul":
                    ans = x.mul(y);
                    break;

                case "div":
                    ans = x.div(y);
                    break;

                default:
                    System.out.println("Invalid argument. Usage:- java MyInfArith <int/float> <add/sub/mul/div> <First Number(string)> <Second Number(String)>");
                    break;
            }


            
            String F_ans = ans.F_to_String();
            System.out.println(F_ans);
        }
    }
}
