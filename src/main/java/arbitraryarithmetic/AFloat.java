package arbitraryarithmetic;

import java.util.ArrayList;
import java.util.List;




public class AFloat  {



    private AInteger value;
    public int scale;
    



    public AFloat(){
        this.scale = 1;
        this.value = new AInteger("00");
    }







    //String Constructor

    public AFloat(String num){
        
        this.value = new AInteger();
        this.value.set_Digits(new ArrayList<>());


        if(num.charAt(0)=='-'){
            this.value.isnegative = true;
            num = num.substring(1);
        }


        int count = 0;
        boolean dec = false;
        

        for(int i=0;i<num.length();i++){
            char ch = num.charAt(i);
            if((ch == '.' && !dec) || Character.isDigit(ch)){
                if(ch == '.'){
                    dec = true;
                    continue;
                }

                else{
                    this.value.get_digits().add(0,ch-'0');
                    if(dec){
                        count +=1;
                    }
                }

                
            }
            else{
                throw new IllegalArgumentException("Invalid input, pls check your input again.");
            }
        }
        this.scale = count;
    }




    //Copy Constructor
    public AFloat(AFloat a){
        this.value=a.value;
        this.scale=a.scale;
    }





    //Helper constructor
    public AFloat(AInteger a, int scale){
        this.value = a;
        this.scale = scale;
    }





    //parser
    public AFloat parse(String s){
        return new AFloat(s);
    }





    //Addition
    public AFloat add(AFloat other){
        int max_scale = Math.max(this.scale,other.scale);
        AInteger shifted_other = other.shift_deci(max_scale);
        AInteger shifted_this = this.shift_deci(max_scale);

        AInteger ans = shifted_this.add(shifted_other);

        AFloat result = new AFloat(ans,max_scale);
        result.removeTrailingZeros();
        return result;
    }







    //Subtraction
    public AFloat sub(AFloat other){
        int max_scale = Math.max(this.scale,other.scale);

        AInteger shifted_other = other.shift_deci(max_scale);
        AInteger shifted_this = this.shift_deci(max_scale);

        AInteger ans = shifted_this.sub(shifted_other);

        AFloat result = new AFloat(ans,max_scale);
        result.removeTrailingZeros();
        return result;
    }
    








    //Multiplication
    public AFloat mul(AFloat other){
        AInteger ans = this.value.mul(other.value);
        int scale = this.scale + other.scale;

        AFloat result = new AFloat(ans,scale);
        result.removeTrailingZeros();
        return result;
    }








    //Division 
    public AFloat div(AFloat other){
        int precision =30;

        int scale = Math.max(this.scale,other.scale);
        

        AInteger shifted_this = this.shift_deci(scale+precision);
        AInteger shifted_other = other.shift_deci(scale);

        AInteger ans = shifted_this.div(shifted_other);
        

        AFloat result = new AFloat(ans,precision);
        result.removeTrailingZeros();



        this.removeLeadingZeros();
        other.removeLeadingZeros();
        this.removeTrailingZeros();
        other.removeTrailingZeros();

        if(this.value.get_digits().size()-this.scale==other.value.get_digits().size()-other.scale){
            result = new AFloat(result.mul(new AFloat("1")));
            result.removeTrailingZeros();
            return result;
            
        }
        else{
            result.removeTrailingZeros();
            return result;
        }
        

        
    }












    //Helper fucntion to print the string from Afloat
    public String F_to_String() {
        StringBuilder ans = new StringBuilder();

        if (this.value.isnegative) {
            ans.append('-');
        }

        List<Integer> digits = this.value.get_digits();
        int n = digits.size();

        if (scale >= n) {
            
            ans.append("0.");
            for (int i = 0; i < scale - n; i++) {
                ans.append('0');
            }
            for (int i = n - 1; i >= 0; i--) {
                ans.append(digits.get(i));
            }
        } 
        else {
        
            for (int i = n - 1; i >= 0; i--) {
                ans.append(digits.get(i));
                if (i == scale && scale != 0) {
                    ans.append('.');
                }
            }
        }

        return ans.toString();
    }





    





    



    //helper function to shift the decimal point towards right for required no. of times
    public AInteger shift_deci(int place){
        int max_shift = Math.abs(this.scale - place);
        AInteger shifted = new AInteger(this.value);
        for (int i=0;i<max_shift;i++){
            shifted = shifted.mul(new AInteger("10"));
        }
        return shifted;
    }







    //Helper function to remove trailing zeroes in a decimal number eg 89.20000
    public void removeTrailingZeros(){
        
        while(this.scale>0 && this.value.get_digits().size()>0 && this.value.get_digits().get(0)==0){
            this.value.get_digits().remove(0);
            this.scale--;
        }
    }








    //Helper function to remove leading zeroes eg 00000.01
    public void removeLeadingZeros() {
        List<Integer> digits = this.value.get_digits();        
        int integerDigits = this.value.get_digits().size() - this.scale;
    
        
        while (integerDigits > 1 && digits.get(digits.size() - 1) == 0) {
            digits.remove(digits.size() - 1);
            integerDigits--;
        }
    }
    

}