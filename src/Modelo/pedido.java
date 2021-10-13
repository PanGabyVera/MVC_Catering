/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author USER
 */
public class pedido {
     private String cod_pedido;
     private String cod_cliente;
     private String cod_paquete;
      private Time hora_entrega;
       private Date Fecha_enterega;
       private String direccion_entre;

    public String getCod_pedido() {
        return cod_pedido;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getCod_paquete() {
        return cod_paquete;
    }

    public void setCod_paquete(String cod_paquete) {
        this.cod_paquete = cod_paquete;
    }

    public String getDireccion_entre() {
        return direccion_entre;
    }

    public void setDireccion_entre(String direccion_entre) {
        this.direccion_entre = direccion_entre;
    }

    public void setCod_pedido(String cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public Time getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(Time hora_entrega) {
        this.hora_entrega = hora_entrega;
    }

    public Date getFecha_enterega() {
        return Fecha_enterega;
    }

    public void setFecha_enterega(Date Fecha_enterega) {
        this.Fecha_enterega = Fecha_enterega;
    }

    public pedido() {
    }

    public pedido(String cod_pedido, String cod_cliente, String cod_paquete, Time hora_entrega, Date Fecha_enterega, String direccion_entre) {
        this.cod_pedido = cod_pedido;
        this.cod_cliente = cod_cliente;
        this.cod_paquete = cod_paquete;
        this.hora_entrega = hora_entrega;
        this.Fecha_enterega = Fecha_enterega;
        this.direccion_entre = direccion_entre;
    }

    
    
}
