import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsuer2{
    public static void main(String[] args){

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
         
    }
}