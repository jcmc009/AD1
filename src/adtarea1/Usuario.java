/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea1;

import java.util.ArrayList;

/**
 *
 * @author Jc
 */
public class Usuario {

    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private String identificador, contrasenya, direccion;
    private int anyoNacimiento;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(int anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public Usuario(String identificador, String contrasenya, String direccion, int anyoNacimiento) {
        this.identificador = identificador;
        this.contrasenya = contrasenya;
        this.direccion = direccion;
        this.anyoNacimiento = anyoNacimiento;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "identificador='" + identificador + '\''
                + "contraseña='" + contrasenya + '\''
                + ", direccion='" + direccion + '\''
                + ", añoNacimiento=" + anyoNacimiento
                + '}';
    }

}
