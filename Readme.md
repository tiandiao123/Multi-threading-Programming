## The Multi-threading Java Programming

### Part I: Threads and Process

In this part, I am demonstring several java programs to help people ubderstand threads and process in the operatin system. Here are the definitions about threads in OS:
• Threads are sometimes called lightweight processes. Creating a new thread requires fewer resources than creating a new process.
• Threads exist within a process — every process has at least one. Threads share the process's resources, including memory and open files.

How To create a thread? Here is a demo:
```
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
```
Join() function is used to wait for current running assigned thread to be ended, then continue to run the other threads! Also, the sleep() function is used to make current thread to be asleep for the assigned time until the assigned time has passed. Here is a demo:
```
import java.util.*;

public class SleepExample{
   public static void main(String[] args){

        MyThread thread1=new MyThread();
        MyThread thread2=new MyThread();
        thread1.start();
        try {
            thread1.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

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
```  
Also, DeadLock is also a very interesting problem in Operating System, here is a Java Demo:
```
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
```

Producer and Consumer Case Studies: [detailed code demo](https://github.com/tiandiao123/Multi-threading-Programming/blob/master/codes/ProducerConsumer.java) 

Here is a code demo:
```
    public static void main(String[] args) {
         Bucket bucket =new Bucket(20, 200);    
         PutExcuter putExcuter = new PutExcuter(bucket);
         GetExecuter getExecuter = new GetExecuter(bucket, 6);
         List<Thread> putThread = new ArrayList<>();
         
         for(int i = 0;i < 2; i++){
             putThread.add(new Thread(putExcuter));
         }

         List<Thread> getThread = new ArrayList<>();
         for(int i=0;i<3;i++){
             getThread.add(new Thread(getExecuter));
         }

         for(Thread thread: putThread){
             thread.start();
         }

         for(Thread thread : getThread){
             thread.start();
         }
    }
```

There is another coding demo in codes file:[here](https://github.com/tiandiao123/Multi-threading-Programming/blob/master/codes/ProducerConsumer2.java)
