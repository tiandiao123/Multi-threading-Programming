import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Bucket{
     private BlockingQueue<Token> que;
     private int rate;
  public Bucket(int size,int rate){
      this.que=new ArrayBlockingQueue<Token>(size);
      this.rate=rate;
  }    
  
  public void putToken(Token token){
      try {
          Thread.sleep(this.rate);
      } catch (InterruptedException ie){
          ie.printStackTrace();
      }
  }

  public Token getToken(){
      try{
          Thread.sleep(100);
          return que.take();
      }catch (InterruptedException ie){
          ie.printStackTrace();
          return null;
      }
  }
}