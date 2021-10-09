/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author USER
 */
public class pedido {
     private String cod_pedido;
     private String cod_cliente;
     private String cod_paquete;
      private Date hora_entrega;
       private Date Fecha_enterega;
       private String direccion_entre;

    public String getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(String cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public Date getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(Date hora_entrega) {
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

    public pedido(String cod_pedido, String cod_cliente, String cod_paquete, Date hora_entrega, Date Fecha_enterega, String direccion_entre) {
        this.cod_pedido = cod_pedido;
        this.cod_cliente = cod_cliente;
        this.cod_paquete = cod_paquete;
        this.hora_entrega = hora_entrega;
        this.Fecha_enterega = Fecha_enterega;
        this.direccion_entre = direccion_entre;
    }

    
    
}
