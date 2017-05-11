import java.util.*;

public class JoinExample3 {
   public static void main(String[] args) throws InterruptedException{

      for(int i=0;i<100;i++){
         Thread th=new Thread(new MyClass3(),"thread "+i);
         th.start();
         try{
          th.join();
         }catch (InterruptedException ie){
             ie.printStackTrace();
         }
      }

      System.out.println("the program ended");
   }
}
 
class MyClass3 implements Runnable{
 
    @Override
    public void run() {
    	Thread t = Thread.currentThread();
        System.out.println("Thread started: "+t.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Thread ended: "+t.getName());    
    }
}