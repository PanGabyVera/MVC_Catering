/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Pandora
 */
public class empleado_paquete {
    private String cod_empl;
    private String cod_paq;

    public empleado_paquete() {
    }

    public empleado_paquete(String cod_empl, String cod_paq) {
        this.cod_empl = cod_empl;
        this.cod_paq = cod_paq;
    }

    public String getCod_empl() {
        return cod_empl;
    }

    public void setCod_empl(String cod_empl) {
        this.cod_empl = cod_empl;
    }

    public String getCod_paq() {
        return cod_paq;
    }

    public void setCod_paq(String cod_paq) {
        this.cod_paq = cod_paq;
    }
    
}
