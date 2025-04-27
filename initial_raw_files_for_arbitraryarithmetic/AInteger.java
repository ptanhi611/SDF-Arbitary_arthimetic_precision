package initial_raw_files_for_arbitraryarithmetic;


import java.util.*;

public class AInteger {

    public boolean isnegative;
    private List<Integer> digit;

    public AInteger(){
        this.digit = new ArrayList<>();
        this.digit.add(0) ;
        this.isnegative = false;
    }

    public void set_Digits(List<Integer> digit) {
        this.digit = new ArrayList<>(digit);
    }

    public List<Integer> get_digits(){
        return this.digit;
    }

    
    

    public AInteger(String num){

        this.digit = new ArrayList<>();
        this.isnegative = false;

        if(num.charAt(0)=='-'){
            this.isnegative = true;
            num = num.substring(1);
        }

        for(int i=num.length()-1;i>=0;i--){
            if(Character.isDigit(num.charAt(i))){
                this.digit.add(num.charAt(i)-'0');
            }
            else{
                throw new IllegalArgumentException("Invalid input, the string should contain digits(0-9) only ");
            }
        }
       

       
    }

    public AInteger(AInteger other){
        this.isnegative = other.isnegative;
        this.digit = new ArrayList<>(other.digit);
    }




    public AInteger parse(String s){
        return new AInteger(s);
    }







    public AInteger add(AInteger other){
        AInteger ans = new AInteger();
        ans.digit.clear();

        if(this.isnegative != other.isnegative){
            if(this.isnegative){
                AInteger temp = new AInteger(this);
                temp.isnegative = false;
                ans.set_Digits(temp.sub(other).get_digits());
                ans.isnegative = !temp.sub(other).isnegative;
            }
            else{
                AInteger temp = new AInteger(other);
                temp.isnegative = false;
                ans = this.sub(temp);
            }
            return ans;
        }

        int length = this.digit.size() > other.digit.size() ? this.digit.size() :other.digit.size();
        
        if(this.isnegative && other.isnegative){
            ans.isnegative=true;
        }
        else{
            ans.isnegative = false;
        }

        int sum=0;
        int carry=0;

        for (int i=0;i<=length-1;i++){
            
            int a = this.digit.size()-1<i?0:this.digit.get(i);
            int b = other.digit.size()-1<i?0:other.digit.get(i);

            sum = a+b+carry;
            carry = sum/10;
            ans.digit.add(sum%10);            
        }

        if(carry==0){
            ans.removeLeadingZeros();
            return ans;
        }
        else{
            ans.digit.add(carry);
            ans.removeLeadingZeros();
            return ans;
        }
        
    }
   






    public AInteger sub(AInteger other){
        AInteger ans = new AInteger();
        ans.digit.clear();

        AInteger big = new AInteger();
        AInteger small = new AInteger();


        if(this.isnegative != other.isnegative){
            AInteger temp = new AInteger(other);
            temp.isnegative = !other.isnegative;         
            return this.add(temp);
        }
        
        else{
            if(this.compare(other)){
                if(!this.isnegative){
                    big = new AInteger (this);
                    small = new AInteger(other);
                    ans.isnegative = false;
                }
                else{
                    ans.isnegative = true;
                    AInteger temp = new AInteger(other);
                    this.isnegative = false;
                    temp.isnegative = !other.isnegative;
                    return this.sub(temp);
                }
            }

            else{
                ans.isnegative = !this.isnegative;
                ans.digit= other.sub(this).digit;
            }
        }

        int borrow = 0;
        int diff = 0; 

        for(int i=0;i<big.digit.size();i++){
            int a = big.digit.get(i);
            int b = i<small.digit.size()?small.digit.get(i):0;

            diff = a-b-borrow;

            if(diff<0){
                diff+=10;
                borrow = 1;
            }
            else{
                borrow = 0;
            }

            ans.digit.add(diff);
        }


        ans.removeLeadingZeros();
        return ans;
    }





    public boolean compare(AInteger other){

        this.removeLeadingZeros();
        other.removeLeadingZeros();

        
       if(this.digit.size()>other.digit.size()) return true;
       if(this.digit.size()<other.digit.size()) return false;

       else{
        for(int i=this.digit.size()-1;i>=0;i--){
            if(this.digit.get(i)>other.digit.get(i)) return true;
            if(this.digit.get(i)<other.digit.get(i)) return false;
        }  
        
        return true;
       }
    }



    public AInteger mul(AInteger other){
        AInteger ans = new AInteger();
        ans.digit.clear();
        
        ans.isnegative =false;



        if(this.isnegative!=other.isnegative){
            ans.isnegative =true;
        }

        long[] temp= new long[this.digit.size()+other.digit.size()];
        
        for(int i=0;i<this.digit.size();i++){
            for (int j=0;j<other.digit.size();j++){
                temp[i+j] += this.digit.get(i)*other.digit.get(j);
                
            }           
        }


        long sum = 0;
        int carry = 0;



        for(int i=0;i<this.digit.size()+other.digit.size();i++){
            sum = carry + temp[i];
            carry = (int)sum/10;
            ans.digit.add((int)sum%10);
        }
        
        ans.removeLeadingZeros();
        return ans;
    }


    public AInteger div(AInteger other) {


        if (other.digit.size() == 1 && other.digit.get(0) == 0) {
            throw new ArithmeticException("Cannot divide by 0. Please check your input.");
        }

        if(!this.compare(other)){
            return new AInteger();
        }
        

        this.removeLeadingZeros();
        other.removeLeadingZeros();

    
        AInteger result = new AInteger();
        result.digit.clear();
       
        
        result.isnegative = (this.isnegative != other.isnegative);
        this.isnegative=false;
        other.isnegative=false;

        AInteger dividend = new AInteger();
        dividend.digit = new ArrayList<>(this.digit.subList(Math.max(0,this.digit.size() - other.digit.size()), this.digit.size()));
        
    
        if(this.digit.size() == other.digit.size()){
            int count = 0;
            while (dividend.compare(other)) {
                dividend = dividend.sub(other);
                count++;
            }
            result.digit.add(0, count); 
        }
        
        else{
            for (int i = this.digit.size() - other.digit.size()-1 ; i >= 0; i--) {
                dividend.digit.add(0, this.digit.get(i));
                dividend.removeLeadingZeros();
        
                int count = 0;
                while (dividend.compare(other)) {
                    dividend = dividend.sub(other);
                    count++;
                }
                result.digit.add(0, count);
            }
        }

        
    
        result.removeLeadingZeros();
        return result;
    }




    
    public String I_to_String(){
        String ans = "";
        if(this.isnegative){
            ans+='-';
        }

        for(int i=this.digit.size()-1;i>=0;i--){
            ans+=this.digit.get(i);
        }

        return ans;
    }





    public void removeLeadingZeros(){
        
        for(int i=this.digit.size()-1;i>0;i--){
            if(this.digit.get(i)==0){
                this.digit.remove(i);
            }

            else{
                break;
            }
        }
    }



}
