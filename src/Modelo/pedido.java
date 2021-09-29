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
     private int cod_pedido;
      private Date hora_entrega;
       private Date Fecha_enterega;

    public int getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(int cod_pedido) {
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

    public pedido(int cod_pedido, Date hora_entrega, Date Fecha_enterega) {
        this.cod_pedido = cod_pedido;
        this.hora_entrega = hora_entrega;
        this.Fecha_enterega = Fecha_enterega;
    }
    
}
