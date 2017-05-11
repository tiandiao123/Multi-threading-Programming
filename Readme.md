### Author: Cuiqing Li
### Time: 5/11/2017

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

