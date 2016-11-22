/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twcode3;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.String;

/**
 *
 * @author kungangzhang
 * Use Stack and dummy sign to simulate a calculator and find the parathesis around the first entry of each expression tree or sub-expression tree. For parathesis not necessary, they are not output in the final results, if there is a simplification operation.
 
 */
class Operations{
    private Stack<Character> Sign = new Stack<Character>();//Stack for storing sign: mimic a calculator with a dummy sign between two entries
    private Stack<Character> Para = new Stack<Character>();//Stack for parathesis
    private Stack<String> Resu = new Stack<String>();//Stack for results
    private Stack<String> Temp = new Stack<String>();//Stack for temporary results of sub-expression tree
    private StrArr2Str strarr2str = new StrArr2Str();//Concatenate an array of string into a single array
    
    public String simplify(String str){
        //Eleminate parathesis around the first entry of a sub-expression tree
        char c = str.charAt(0);//str has no whithspace
        String temp;
        if (c == '('){
            //For initial '(' case
            Sign.push('[');//Bracket wouldn't be printed out, unnecessary first parathesis
            Para.push('[');
        }else{
            Resu.push(Character.toString(c));//Push entry into Stack
            Sign.push('+');//Push a dummy sign
        }
        for (int i = 1; i<str.length(); i++){
            c = str.charAt(i);
            if (c == '('){
                if (Sign.size() > 0 && (Sign.peek() == '(' || Sign.peek() == '[')){
                    //Starting a new sub-expression tree with '('
                    Sign.push('[');
                    Para.push('[');
                }else{
                    //Starting a new sub-expression tree without '('
                    Sign.push(c);
                    Para.push(c);
                }
            }else{
                if (c == ')' && str.charAt(i-1) == '('){
                    //A pair of closed parathesis enclose nothing
                    do {
                        Sign.pop();
                        Para.pop();
                        i++;
                    }while((i<str.length() && str.charAt(i) == ')' && (Sign.peek()=='(' || Sign.peek()=='[')));
                    i--;//Current letter haven't gone through the process
                    if (Sign.size()>0 && !(Sign.peek()=='(' || Sign.peek()=='[') && !(i+1<str.length() && str.charAt(i+1) != ')')){
                        //If there is a dummy sign before the pair of closed parathesis enclosing nothing and there is no entry behind this pair of parathesis, pop the dummy sign
                        Sign.pop();
                    }
                }else{
                    if (c == ')'){
                        //A pair of closed parathesis enclose something
                        if (Sign.peek() == '['){
                            //This parathesis is unnecssary
                            Sign.pop();
                            Para.pop();
                        }else{
                            //A pair of closed parathesis enclose something
                            //Ending a sub-expression tree and it is not empty
                            temp = "";
                            if (Sign.peek()=='(' && Resu.peek().length()==1){
                                //There is no dual-entry operation inside this parathesis
                                Sign.pop();
                                Para.pop();
                                Sign.push('[');
                                Para.push('[');
                            }
                            if (Para.peek() == '('){
                                //Left parathesis
                                Temp.push(")");
                            }
                            while (Sign.peek() != Para.peek()){
                                //Pop out all characters until the most left parathesis or bracket
                                Temp.push(Resu.pop());
                                Sign.pop();    
                            }
                            Temp.push(Resu.pop());//The first entry of sub-expression tree
                            if (Sign.pop() == '('){
                                //Pop out the left parathesis or bracket but only push '('
                                Temp.push("(");
                            }
                            Para.pop();//Pop out in the Para stack
                            while (!Temp.isEmpty()){
                                //Assemble the temporary result
                                temp = temp + Temp.pop();
                            }
                            Resu.push(temp);//Push temporary result into Resu stack
                        }
                    }else{
                        //Not parathesis, must be an entry
                        Resu.push(Character.toString(c));
                    }
                    if (i+1 < str.length() && str.charAt(i+1) != ')'){
                        //If there is something following but not a ')' and there is no trivial pair of parathesis before, add an operator
                        Sign.push('+');
                    }
                }
            }
        }
        temp = "";//Final result
        while (Resu.size()>1){
            Temp.push(Resu.pop());
            Sign.pop();
        }
        Temp.push(Resu.pop());
        while (!Temp.isEmpty()){
            temp = temp + Temp.pop();
        }
        return temp;
    }
        
    
    public String reverse(String str){
        //Rverse a expression tree
        StringBuilder restr = new StringBuilder(str).reverse();//str has no whithspace
        for (int i = 0; i < restr.length(); i++){
            //Exchange '(' and ')'
            if (restr.charAt(i) == '('){
                restr.setCharAt(i,')');
            }else{
                if (restr.charAt(i) == ')'){
                    restr.setCharAt(i,'(');
                }
            }
        }
        return restr.toString();
    }
}

class StrArr2Str{
    //Concatenate a string array into a single string
    public String strArr2Str(String[] ss){
        String res="";
        for (int i = 0; i<ss.length; i++){
            res = res+ss[i];
        }
        return res;
    }
}

public class TwCode3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);//Input scanner
        
        Operations ap_op = new Operations();//Instantialize an Operations class
        StrArr2Str strarr2str = new StrArr2Str();//Instantialize an StrArr2Str class
        String nln,extree,oper;//Nextline, expression tree, operation
        String[] ss;//Series of strings
        char op;//Single operation
        int noper,nre;//Number of operation and number of reverse operation
        while (in.hasNextLine()){
            nln = in.nextLine();
            ss = nln.trim().split("\\s*/\\s*");//Separate expression tree and operations
            extree = strarr2str.strArr2Str(ss[0].split("\\s+"));//Delete whitespace
            if (ss.length == 2){
                //If there are operations
                oper = strarr2str.strArr2Str(ss[1].split("\\s+"));//Delete whitespace
                noper = oper.length();
                for (int i=0;i< noper; i++){
                    op = oper.charAt(i);
                    if (op == 'S' || op == 's'){
                        extree = ap_op.simplify(extree);
                        while (i+1 < noper && (oper.charAt(i+1)=='S' || oper.charAt(i+1)=='s')){
                            //Continuous run of simplification wouldn't change result
                            i++;
                        }
                    }else{
                        if (op == 'R' || op == 'r'){
                            nre = 1;
                            while (i+1 < noper && (oper.charAt(i+1)=='R' || oper.charAt(i+1)=='r')){
                                nre++;
                                i++;
                            }
                            if (nre % 2 == 1){
                                //Odd number run of reverse would reverse the current expression tree
                                extree = ap_op.reverse(extree);
                            }
                        }
                    }    
                }
            }
            System.out.println(extree);
            nln = null;
            ss = null;
            extree = null;
            oper = null;
        }
    }
    
}

/*
Test case 1: L(FF) /s
Output 1: L(FF)
Test case 2: L((())FF) /s
Output 2: L(FF)
Test case 3: L((())F()F) /s
Output 3: L(FF)
Test case 4: L((())F(())()F) /s
Output 4: L(FF)
Test case 5: L((())F(())()F()(())) /s
Output 5: L(FF)

Test case 6: L((())((F))(())()(F)()(())) /s
Output 6: L(FF)
Test case 7: L((())((F))(())()(FM)()(())) /s
Output 7: L(F(FM))

Test case 8: AB(DXWF)(G(VH(UUIJK))L(FF((())()(M)(())(KFF)(())())NOP))KKKMMM /s
Output 8: AB(DXWF)(G(VH(UUIJK))L(FF(M(KFF))NOP))KKKMMM
Test case 9: AB(DXWF)(G(VH(UUIJK))L(FF((())()(M)(())(KFF)(())())NOP))KKKMMM /srrr
Output 9: MMMKKK((PON((FFK)M)FF)L((KJIUU)HV)G)(FWXD)BA
Test case 10: (((AB(DXWF))(G(VH(UUIJK))L(FF((())()(M)(())(KFF)(())())NOP))KKKMMM)) /srrr
Output 10: MMMKKK((PON((FFK)M)FF)L((KJIUU)HV)G)(FWXD)BA
Test case 11: ((((AB(DXWF))(G(VH(UUIJK))L(FF((())()(M)(())(KFF)(())())NOP))K)KKMMM)) /rrrs
Output 11: MMMKK(K(PON(FFKM)FFL(KJIUUHV)G)(FWXDBA))
Test case 12: ((((AB(DXWF))(G(VH(UUIJK))L(FF((())()(M)(())(KFF)(())())NOP))K)KKMMM)) /rrrsss
Output 12: MMMKK(K(PON(FFKM)FFL(KJIUUHV)G)(FWXDBA))

All of them are correct.
*/