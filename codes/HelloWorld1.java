import java.util.*;


public class HelloWorld1{
    

    public static void main(String[] args) throws InterruptedException{

     	Thread thread=new Thread(new PrintHello());
     	thread.start();
     	thread.join();
     	System.out.println("Sucess!");
    	
    }
   
    private static class PrintHello implements Runnable{

       @Override
       public void run(){
           System.out.println("hello world");

       }

    }
    



}