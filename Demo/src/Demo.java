/**
 * Demo class
 *
 * @author Jun Zhang
 * @date 2018/2/28
 */
public class Demo {
   private int add(int a, int b){
        try{
            return a+b;
        }catch (Exception e){
            System.out.println("catch");
        }finally{
            System.out.println("finally");
        }
        return 0;
    }
    
    public static void main(String[] args){
        Demo d = new Demo();
        System.out.println("Sum is " + d.add(9,34));
    }
}
