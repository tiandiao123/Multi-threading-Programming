import java.util.*;

public class Lock{
     public static void main(String[] args){
                ThreadDemo1 thread1 = new ThreadDemo1();
                ThreadDemo2 thread2 = new ThreadDemo2();

                thread1.start();
                thread2.start();                

     }

    static Object lock1 = new Object();
    static Object lock2 = new Object();
    static class ThreadDemo1 extends Thread {
              public void run(){
                     synchronized(lock1){
                         try{
                             Thread.sleep(100);
                         }catch (InterruptedException ie){
                             ie.printStackTrace();
                         }
                     }

                     synchronized(lock2) {
                         System.out.print("ThreadDemo1 !");
                     }
              }
    }


    static class ThreadDemo2 extends Thread{
          public void run() {
             synchronized (lock2){
                  try{
                      Thread.sleep(100);
                  }catch (InterruptedException ie){
                       ie.printStackTrace();  
                }
             }

             synchronized (lock1){
                   System.out.print("ThreadDemo 2 !"); 
            }
          }
    }
 
}