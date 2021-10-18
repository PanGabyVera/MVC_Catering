
package Modelo;


public class Menu_paquete {
    private String cod_paq;
    private String cod_menu;
    private int cantidad;
    private String nombre;

    public Menu_paquete() {
    }

    public Menu_paquete(String cod_paq, String cod_menu, int cantidad, String nombre) {
        this.cod_paq = cod_paq;
        this.cod_menu = cod_menu;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    

    public String getCod_menu() {
        return cod_menu;
    }

    public void setCod_menu(String cod_menu) {
        this.cod_menu = cod_menu;
    }

    public String getCod_paq() {
        return cod_paq;
    }

    public void setCod_paq(String cod_paq) {
        this.cod_paq = cod_paq;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    

}
