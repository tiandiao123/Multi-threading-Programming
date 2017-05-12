import java.util.*;

public class SleepExample{
   public static void main(String[] args){

        MyThread thread1=new MyThread();
        MyThread thread2=new MyThread();
        thread1.start();
        // try {
        //     thread1.join();
        // }catch(InterruptedException ie){
        //     ie.printStackTrace();
        // }

        thread2.start();

   }

   public static class MyThread extends Thread{
       @Override
       public void run(){

          for(int i=0;i<10;i++){
                System.out.println(i);
                try{
                    Thread.sleep(200);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
          }
       }
   }

}