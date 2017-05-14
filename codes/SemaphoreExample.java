import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.Date;

//the codes are based on the tutorial:http://howtodoinjava.com/core-java/multi-threading/binary-semaphore-tutorial-and-example/

public class SemaphoreExample
{
   public static void main(String[] args){
      PrinterQueue printerQueue = new PrinterQueue();
      Thread thread[] = new Thread[100];
      for (int i = 0; i < 100; i++)
      {
         thread[i] = new Thread(new PrintingJob(printerQueue), "Thread " + i);
      }
      for (int i = 0; i < 100; i++)
      {
         thread[i].start();
      }
   }

   static class PrintingJob implements Runnable
    {
       private PrinterQueue printerQueue;
     
       public PrintingJob(PrinterQueue printerQueue)
       {
          this.printerQueue = printerQueue;
       }
     
       @Override
       public void run()
       {
          System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
          printerQueue.printJob(new Object());
       }
    }

     static class PrinterQueue{
           private final Semaphore semaphore;
 
           public PrinterQueue(){
              semaphore = new Semaphore(1);
           }
   
           public void printJob(Object document){
           try{
              semaphore.acquire();
              System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + ((float)500 / 1000) + " seconds :: Time - " + new Date());
              Thread.sleep(500);
            } catch (InterruptedException ie){
              ie.printStackTrace();
            } finally{
             System.out.printf("The printer has finished printed : %s\n", Thread.currentThread().getName());
             semaphore.release();
            }
       }
     }
}