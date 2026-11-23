package com.epn.edii.view;

import com.epn.edii.model.Estudiante;

public interface PantallaBienvenida extends Pantalla {
    void mostrarDatos(Estudiante estudiante);
    void alIngresar(Runnable accion);
}
