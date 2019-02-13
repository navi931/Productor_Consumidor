
package productor_consumidor;
import java.nio.CharBuffer;

public class Productor_Consumidor 
{public static void main (String [] args) {
        final int tamaño = 3;
        int i = 0;
        CharBuffer buffer = CharBuffer.allocate(tamaño);
        
        String text = "Este texto es demasiado pequeño como para explicar un concepto de manera clara";
        System.out.println("Input text: " + text);
 
        for (; i < tamaño; i++) {
 
            char c = text.charAt(i);
            buffer.put(c);
        }
 
        int buffPos = buffer.position();
        System.out.println("Position after data is written into buffer: " + buffPos);
 
        buffer.flip();
         
        System.out.println("Reading buffer contents:");
         
        while (buffer.hasRemaining()) {
 
            System.out.println(buffer.get());                   
        }
    }
}
