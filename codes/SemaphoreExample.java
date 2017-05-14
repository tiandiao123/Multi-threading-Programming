import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.Date;
public class SemaphoreExample
{
   public static void main(String[] args){
      PrinterQueue printerQueue = new PrinterQueue();
      Thread thread[] = new Thread[10];
      for (int i = 0; i < 10; i++)
      {
         thread[i] = new Thread(new PrintingJob(printerQueue), "Thread " + i);
      }
      for (int i = 0; i < 10; i++)
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
              Long duration = (long) (Math.random() * 10000);
              System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds :: Time - " + new Date());
              Thread.sleep(duration);
            } catch (InterruptedException e){
              e.printStackTrace();
            } finally{
             System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
             semaphore.release();
            }
       }
     }
}