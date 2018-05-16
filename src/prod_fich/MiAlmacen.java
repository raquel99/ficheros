package prod_fich;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Iterator;

// Crear la clase Producto y completar los m�todos

public class MiAlmacen
{
    static private ModeloAbs almacen;
    static Scanner sc;
    
    public static void main(String[] args){
        almacen=new ModeloArrayListFile ();
        sc = new Scanner(System.in);
        int opcion=0;
        do{
		mostrarMenu();
                opcion=leerOpcion(1,9);
                switch(opcion){
                    case 1: crear();break;
                    case 2: consultar();break;
                    case 3: borrar();break;
                    case 4: modificarPrecio();break;
                    case 5: comprar();break;
                    case 6: vender();break;
                    case 7: listar();break;
                    case 8: listarPocoStock();break;
                }
                if ( opcion != 9 ){
                System.out.println("\n---------------------------- ");
                System.out.print("Pulse enter para continuar");
                sc.nextLine();
            }
        }while(opcion!=9);
        sc.close();
        System.out.println("Fin de programa");
    }
    
    private static void consultar(){System.out.println("A�n no disponible");        
       System.out.println("<CONSULTA>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if ( p == null){
           System.out.println("El producto no se encuentra en almacen");
        }
       else {
           System.out.println("PRODUCTO "+p);
        }
       
    }
    
    
    static private float leerFloat(){
        
        boolean error = false;
        float valor =0;
        String cadena;
        do {
        error = false;  
          try {
             // Intento leer directamente un entero
             
             cadena = sc.nextLine();
             valor = Float.parseFloat(cadena);
             
            } catch(NumberFormatException e){
              System.out.println("Error en formato.");
              error = true;
            }
        }
       while ( error);
       return valor;
    }
    
    static private int leerEntero(){
       
        boolean error = false;
        int valor =0;
        String cadena;
        do {
        error = false;  
          try {
             // Intento leer directamente un entero
             cadena = sc.nextLine();
             valor = Integer.parseInt(cadena);
             
            } catch(NumberFormatException e){
              System.out.println("Error en formato.");
              error = true;
            }
        }
       while ( error || valor <0);
       return valor;
    }

    
    
    private static void borrar(){       
      System.out.println("<ELIMINAR>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       if (almacen.borrarProducto(codigo) ) {
           System.out.println("--Producto eliminado--");
        }
        else{
           System.out.println("El producto no se encuentra en almacen");
        }  
    }
    
    private static void modificarPrecio (){
       System.out.println("<MODIFICAR PRECIO>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if ( p == null){
              System.out.println("El producto no se encuentra en almacen");
        }
       else {
           System.out.println("PRODUCTO "+p);
        }
        System.out.print("Introduzca el precio nuevo:");
        float precio = leerFloat();
        if ( precio > 0){
             p.setPrecio(precio);
             almacen.modificarProducto(p);
             System.out.println("Producto actualizado");
            }
        else {
            System.out.println("El precio debe ser mayor que cero");
        }
        
    }
    
    private static void comprar(){     
       System.out.println("<COMPRAR>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if ( p == null){
              System.out.println("El producto no se encuentra en almacen");
            
        }
       else {
        System.out.println("PRODUCTO "+p);
        System.out.print("Introduzca cantidad a comprar:");
        int cantidad = leerEntero();
        p.setStock(p.getStock()+cantidad);
        almacen.modificarProducto(p);
        System.out.println("Producto actualizado");
       } 
    }
    
    private static void vender(){
        System.out.println("<VENDER>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if ( p == null){
              System.out.println("El producto no se encuentra en almacen");
        }
       else {
        System.out.println("PRODUCTO "+p);
        System.out.print("Introduzca cantidad a vender:");
        int cantidad = leerEntero();
        if ( cantidad > p.getStock()){
            System.out.println("No hay unidades suficientes.");
        } else {
             p.setStock(p.getStock()-cantidad);
             almacen.modificarProducto(p);
             System.out.println("Producto actualizado");
        }
       }
    }
    
    private static void listar(){
        System.out.println("<LISTAR>");
         almacen.listarProductos();
    }
    
    private static void listarPocoStock(){        
         System.out.println("<LISTAR STOCK MINIMO>");
         Iterator itr = almacen.getIterator();
         int i=1;
         while (itr.hasNext()){
             Producto p = (Producto) itr.next();
             if (p.getStock() <= p.getStock_min()){
             System.out.println("N� "+i+" "+p);
            }    
      }
    
    }
    
    
    private static void crear(){
       System.out.println("<NUEVO PRODUCTO>");
       System.out.print("Codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if ( p != null){
           System.out.println("Ya existe el codigo en almacen "+p);
           return;
        }
       System.out.print("Nombre:");
       String nombre = sc.nextLine();
       p = new Producto(codigo,nombre);
       System.out.print("Stock:");
       int stock = leerEntero(); p.setStock(stock);
       System.out.print("Stock Minimo:");
       int stock_min = leerEntero(); p.setStock(stock_min);
       System.out.print("Precio:");
       float precio = leerFloat(); p.setPrecio(precio);
       almacen.insertarProducto(p);
    }
       

    private static void mostrarMenu(){
        System.out.println("\n\n    MENU");
        System.out.println("1. Nuevo producto ");
        System.out.println("2. Consulta producto ");
        System.out.println("3. Borrar producto ");
        System.out.println("4. Modificar precio ");
        System.out.println("5. Compra de productos ");
        System.out.println("6. Venta de productos ");
        System.out.println("7. Listado completo de productos ");
        System.out.println("8. Listado de productos con stock inferior al m�nimo");
        System.out.println("9. Terminar ");
        System.out.print("Elige una opci�n (1-9)");        
    }
    
    private static int leerOpcion(int primero, int ultimo) {
    	int valor=leerEntero();
    	while(valor<primero || valor>ultimo) {
    		valor=leerEntero();
    	}
    	return valor;
    }
}
