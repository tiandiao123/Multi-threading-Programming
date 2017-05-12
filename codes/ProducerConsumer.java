import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class ProducerConsumer{
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
    
    static class PutExcuter implements Runnable{ 
        private Bucket bucket;
        public PutExcuter(Bucket bucket){
            this.bucket = bucket;
        }

        @Override
        public void run(){
            int counter = 0;
            while(counter < 10){
                bucket.putToken(new Token(counter,Thread.currentThread().getName()));
                System.out.println("PUT: "+Thread.currentThread().getName()+ " " + counter);
                counter++;
            }
        }
    }

    static class GetExecuter implements Runnable{
        private Bucket bucket;
        private int num;
        public GetExecuter(Bucket bucket,int num){
            this.bucket = bucket;
            this.num=num;
        }
        @Override
        public void run(){
            int counter=0;
            while(counter < this.num){
                System.out.println("Get: "+ Thread.currentThread().getName()+" From "+bucket.getToken());
                counter++;
            }
        }
    }
}
