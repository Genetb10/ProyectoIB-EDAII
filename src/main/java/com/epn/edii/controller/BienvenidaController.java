package com.epn.edii.controller;

import com.epn.edii.model.Estudiante;
import com.epn.edii.view.PantallaBienvenida;

/** Conecta la pantalla principal con el modelo y dispara la navegación. */
public class BienvenidaController {

    public BienvenidaController(PantallaBienvenida vista, Estudiante modelo, Runnable alIngresar) {
        vista.mostrarDatos(modelo);
        vista.alIngresar(alIngresar);
    }
}
