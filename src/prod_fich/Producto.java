package prod_fich;

import java.io.Serializable;

public class Producto

{
    // instance variables - replace the example below with your own
    int codigo;    // C�digo del producto, se utiliza para buscar
    String nombre; // Nombre un texto
    int stock;    // existencia actuales
    int stock_min; // N�mero m�nimo de existencias recomedadas
    float precio;  // Precio

    /**
     * Constructor for objects of class Producto
     */
    
    
    public Producto ( ){
        
    }
    public Producto ( int codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
       
    }
    
    public Producto ( int codigo, String nombre, int stock,  int stock_min, float precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.stock_min = stock_min;
        this.precio = precio;
    }

    
    public int getCodigo (){
        return codigo;
    }
    
    @Override
    public String toString(){
       return codigo +":"+ nombre +":"+ stock;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setStock( int valor ){
        stock = valor;
    }
    
    public int getStock_min(){
        return stock_min;
    }
    
    public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setStock_min( int valor ){
        stock_min = valor;
    }
    
    
    public float getPrecio(){
        return precio;
    }
    
    public void setPrecio( float valor ){
        precio = valor;
    }
}