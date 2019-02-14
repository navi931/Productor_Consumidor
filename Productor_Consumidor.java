
package productor_consumidor;
import java.nio.CharBuffer;

public class Productor_Consumidor 
{
    static int tamaño = 3;
    static int i = 0;
    static CharBuffer buffer = CharBuffer.allocate(tamaño);
    static String text = "Este texto es demasiado pequeño como para explicar un concepto de manera clara";
    static boolean lleno = false;
    
    static class workerWriter extends Thread 
        {
          public void run () 
          {
              while(true)
              {
                    if (!lleno)
                    {
                        synchronized (text)
                        {
                          for (; (i < tamaño)&&(i<text.length()); i++)
                          {
                                char c = text.charAt(i);
                                buffer.put(c);
                          }
                        }
                    lleno = true;
                    if(i>=text.length())
                        break;
                    }
              }
          }
        }
    static class workerReader extends Thread 
    {
          public void run () 
          {
           while(true)
           {
            if (lleno)
            {
              synchronized (text)
              {
                 buffer.flip();
                 while (buffer.hasRemaining()) 
                 {
                  System.out.print(buffer.get());                   
                 }
                 buffer.clear();
              }
              tamaño=tamaño+3;
              lleno = false;
              if(i>=text.length())
                break;
            }
           }
           System.out.println("");
          }
    }
    public static void main (String [] args) 
    {
        
        System.out.println("Input text: " + text);
        
        Thread thread1 = new workerWriter();
        Thread thread2 = new workerReader();

        thread1.start();
        thread2.start();

        try 
           {
            thread1.join();
            thread2.join();
           }
        catch (InterruptedException e)
            {
             System.out.println(e);
            }
    }
}
