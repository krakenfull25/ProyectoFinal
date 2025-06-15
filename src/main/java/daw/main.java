/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import controladores.CochesJpaController;
import controladores.MarcasJpaController;
import entidades.Coches;

/**
 *
 * @author Julen García
 */
public class main {

    private static final CochesJpaController cc = new CochesJpaController();
    private static final MarcasJpaController cm = new MarcasJpaController();

    public static void main(String[] args) {
        cc.delete(4);
       cc.findAll().forEach(System.out::println);
    }
     

    

    public static void mostrarTodosClientes() {
        cc.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }
}
