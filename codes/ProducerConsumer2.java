import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ProducerConsuer2{
    public static void main(String[] args){
          Bucket bucket=new Bucket(5, 150);
          Producer pro = new Producer(bucket);
          Consumer con = new Consumer(bucket);
          new Thread(pro).start();
          new Thread(pro).start();
          new Thread(pro).start();
          new Thread(pro).start();
    } 
    static class Producer implements Runnable{
        private Bucket bucket;
        public Producer(Bucket bucket){
            this.bucket=bucket;
        }
        @Override
        public void run(){
            for(int i=0; i < 10; i++){
                this.bucket.set("Token------" + i);
            }
        }
    }
    
    static class Consumer implements Runnable{
        private Bucket bucket = null;
        public Consumer(Bucket bucket){
            this.bucket=bucket;
        }

        @Override
        public void run(){
            for(int i=0;i<10;i++){
                this.bucket.get();
            }
        }
    }

    static class Bucket{
        private Lock lock1 = new ReentrantLock();
        private Condition condition1 = lock1.newCondition();
        private Condition condition2 = lock2.newCondition();
        
        private Deque<String> bucket;
        private int size;
        private int rate;

        public Bucket(int size,int rate){
            bucket=new ArrayDeque<>();
            this.size=size;
            this.rate=rate;
        }
         
       public void set(String val){
           lock1.lock();
           try{
               while(bucket.size()==this.size()){
                   condition1.await();
               }
               this.bucket.addLast(val);
               System.out.println("PUT: "+ val);
               Thread.sleep(this.rate);
               condition2.signalAll();
           }catch (InterruptedException ie){
               ie.printStackTrace();
           }finally{
               lock1.unlock();
           }
       }

       public void get(){
           lock1.lock();
           try{
               while(bucket.isEmpty()){
                   condition2.await();
               }
               Thread.sleep(200);
               System.out.println("GET: "+ this.bucket.removeFirst());
               condition1.signalAll();
           }catch (InterruptedException ie){
               ie.printStackTrace();
           }finally{
               lock1.unlock();
           }
       }

    }


}