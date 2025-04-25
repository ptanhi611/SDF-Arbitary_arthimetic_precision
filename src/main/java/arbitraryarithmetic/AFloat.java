package arbitraryarithmetic;

import java.util.ArrayList;




public class AFloat  {



    private AInteger value;
    public int scale;
    



    public AFloat(){
        this.scale = 1;
        this.value = new AInteger("00");
    }









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










    public AFloat(AInteger a, int scale){
        this.value = a;
        this.scale = scale;
    }






    public AFloat parse(String s){
        return new AFloat(s);
    }




    public AFloat add(AFloat other){
        int max_scale = Math.max(this.scale,other.scale);
        AInteger shifted_other = other.shift_deci(max_scale);
        AInteger shifted_this = this.shift_deci(max_scale);

        AInteger ans = shifted_this.add(shifted_other);

        return new AFloat(ans,max_scale);
    }








    public AFloat sub(AFloat other){
        int max_scale = Math.max(this.scale,other.scale);

        AInteger shifted_other = other.shift_deci(max_scale);
        AInteger shifted_this = this.shift_deci(max_scale);

        AInteger ans = shifted_this.sub(shifted_other);

        return new AFloat(ans,max_scale);
    }
    









    public AFloat mul(AFloat other){
        AInteger ans = this.value.mul(other.value);
        int scale = this.scale + other.scale;

        return new AFloat(ans,scale);
    }







    public AFloat div(AFloat other){

        int scale = Math.max(this.scale,other.scale);


        AInteger shifted_this = this.shift_deci(scale+1000);
        AInteger shifted_other = other.shift_deci(scale);

        AInteger ans = shifted_this.div(shifted_other);
        int before =ans.get_digits().size();

        ans.removeTrailingZeros(scale+1000,Math.max(0, this.value.get_digits().size()-other.value.get_digits().size()+scale-other.scale));

        int after =ans.get_digits().size();

        return new AFloat(ans,1000-before+after);
    }













    
    public String F_to_String(){
        String ans = "";
        if(this.value.isnegative){
            ans+='-';
        }
        
        if(this.scale==this.value.get_digits().size()){
            ans+=0.;
        }
        
        for (int i=this.value.get_digits().size()-1;i>=0;i--){
            if(i==this.scale -1 && this.scale!=this.value.get_digits().size()){
                ans+='.';
                ans+=this.value.get_digits().get(i);
                
            }
            else{
                ans+=this.value.get_digits().get(i);
            }
            
        }

        return ans;
    }




    





    




    public AInteger shift_deci(int place){
        int max_shift = Math.abs(this.scale - place);
        AInteger shifted = new AInteger(this.value);
        for (int i=0;i<max_shift;i++){
            shifted = shifted.mul(new AInteger("10"));
        }
        return shifted;
    }

}