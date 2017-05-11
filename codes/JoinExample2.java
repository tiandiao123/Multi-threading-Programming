import java.util.*;

public class JoinExample2 {
   public static void main(String[] args) {

      for(int i=0;i<100;i++){
         Thread th=new Thread(new MyClass2(),"thread"+i);
         th.start();
      }
   }
}
 
class MyClass2 implements Runnable{
 
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