import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class CambiarIDS
{   
    static void modifyFile(String filePath) throws IOException
    {
        File fileToBeModified = new File(filePath);
         //Se guarda la ruta de el fichero SQL a cambiar
        String oldContent = "";
        //Creo una variable para leer las lineas de el fichero a cambiar
        BufferedWriter out=null;
        BufferedReader reader=null;
        //Creo un Writer y un Reader para leer las lineas de el fichero antiguo y escribirlas
        //En el nuevo una vez cambiadas
        String fileName = "C:\\Users\\User\\Desktop\\SQL.txt";
        //Creo una ruta por la que van a salir las lineas con las ids cambiadas
     
        if (fileName != null) {
          try {
        	  		out = new BufferedWriter(new OutputStreamWriter(
                      new FileOutputStream(fileName), "UTF8"));
           //Asigno el escritor a la ruta en la que quiero que me cree el fichero
          }catch (Exception e) {
          }
          }
        try
        {
             reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
             String line = reader.readLine();
             //Creo un Reader para leer las sentencias SQL antiguas
            int cotn = 0;
            while (line != null) 
            {
            	oldContent = oldContent + line + "\n";
                line = reader.readLine();
                cotn++;
                //Leo todas las lineas y las guardo en un string 
            }
            System.out.println(cotn);
            //Muestro cuantas lineas han sido leidas
            String newContent = oldContent;
            //Guardo las sentencias en un nuevo String para cambiarlas
        	BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\IDsPortatilesPreProd.txt"));
        	//Creo un Reader para el fichero de IDS

        	String idord="";											//Ruta de las id de referencia
        	String idpre = "";
        	String idpro = "";
        	int contador=0;
        	for(String line1 = reader2.readLine(); line1 != null; line1 = reader2.readLine())
        	{
        		 //Creo un lector de linea a linea y asigno cada variable segun estan separadas por comillas
          	    StringTokenizer tokenizer = new StringTokenizer(line1, ";");
          	    idord=tokenizer.nextToken();
          	    idpre=tokenizer.nextToken();
          	    idpro=tokenizer.nextToken();
          	 
               newContent = newContent.replaceAll(idpre, idpro);
               //Cambio todas las Ids que encuentre por las nuevas
               contador++;
        	    
        	}
        	System.out.println(contador);
    	       reader2.close();
    	       out.write(newContent); 
           //Muestro cuantas ids se han cambiado y escribo el nuevo fichero 
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
            	reader.close();
                out.close();
                //Ciero el reader y el writer
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
     
    public static void main(String[] args) throws IOException
    {
    	 modifyFile("C:\\Users\\User\\Desktop\\IdsPortatilesPre.txt");
	       //En el main le mando a el metodo la ruta del SQL
    }
}
