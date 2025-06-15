/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import controladores.MarcasJpaController;
import entidades.Marcas;
import java.util.List;

/**
 *
 * @author Julen García
 */
public class main {

    private static final MarcasJpaController cc = new MarcasJpaController();

    public static void main(String[] args) {
        List<Marcas> marcas = cc.findAll();
        if (marcas.isEmpty()) {
            System.out.println("No se encontraron marcas en la base de datos.");
        } else {
            for (Marcas marca : marcas) {
                System.out.println(marca);

            }
        }
    }
}
