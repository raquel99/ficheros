package prod_fich;

/**
 * Implementa la parte de Modelo de Datos con un array que se salva a fichero
 * cada vez que se cambian los datos ( Solucion poco eficiente pues siempre graba todo 
 * el contenido no solo lo que ha cambiado )
 * Formato del fichero
 *  int codigo;    // C�digo del producto, se utiliza para buscar
    String nombre; // Nombre un texto
    int stock;    // existencia actuales
    int stock_min; // N�mero m�nimo de existencias recomedadas
    float precio;
    codigo, nombre, stock, stock_min, precio
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;

public class ModeloArrayListFile extends ModeloArrayList
{
    static final String nombrefichero = "productos.csv";
    
    public ModeloArrayListFile()
    {
       super();
       cargarDesdeFichero();
       
    }
    
    private void salvarAfichero(){
        File fproductos =new File (nombrefichero);
        try{
           
            FileOutputStream fos= new FileOutputStream(fproductos);
           
            ObjectOutputStream oos= new ObjectOutputStream(fos);
           
            for (Producto produ: lista){
                oos.writeObject(produ);
               }
      
            oos.close(); 
            fos.close(); 
          }catch(IOException ioe){
               ioe.printStackTrace();
           }  
    }
    
    private void cargarDesdeFichero() {
        File fproductos =new File (nombrefichero);
        if ( !fproductos.exists() ){
            return; 
         }
         try{
          
          FileInputStream fin= new FileInputStream(fproductos);
          
          ObjectInputStream oin= new ObjectInputStream(fin);
          
          try {
               Producto pro = (Producto) oin.readObject();
               while ( true ){
                 lista.add(pro); 
                 pro = (Producto) oin.readObject();  
                 }
             }
           catch (IOException ex){} 
          
          oin.close(); 
          fin.close(); 
        }catch(IOException ioe){
             ioe.printStackTrace();
         }    
        catch (ClassNotFoundException ex){
             ex.printStackTrace();
         }
        
        
    }

    public boolean insertarProducto ( Producto p){
      boolean resu = super.insertarProducto(p);
      if ( resu ){
          salvarAfichero();
      }
      return resu;
    }
 
    public boolean borrarProducto ( int codigo ){
      boolean resu = super.borrarProducto(codigo);
      if ( resu ){
          salvarAfichero();
        }
      return resu;
    }
    
    
    public boolean modificarProducto (Producto nuevo){
       boolean resu = super.modificarProducto(nuevo);
       if ( resu ){
           salvarAfichero();
        }
       return (resu);
    }
    
        
}    
