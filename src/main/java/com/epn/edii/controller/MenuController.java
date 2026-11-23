package com.epn.edii.controller;

import com.epn.edii.model.OpcionMenu;
import com.epn.edii.view.PantallaMenu;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador del menú. No sabe qué hace cada opción: recibe un mapa
 * OpcionMenu -> acción (Open/Closed: agregar módulos no obliga a modificar esta clase).
 */
public class MenuController {

    private final PantallaMenu vista;
    private final Map<OpcionMenu, Runnable> acciones;

    public MenuController(PantallaMenu vista, Map<OpcionMenu, Runnable> acciones, Runnable alVolver) {
        this.vista = vista;
        this.acciones = new EnumMap<>(OpcionMenu.class);
        this.acciones.putAll(acciones);

        vista.mostrarOpciones(List.of(OpcionMenu.values()));
        vista.alSeleccionarOpcion(this::ejecutar);
        vista.alVolver(alVolver);
    }

    private void ejecutar(OpcionMenu opcion) {
        Runnable accion = acciones.get(opcion);
        if (accion == null) {
            vista.mostrarMensaje("«" + opcion.getTitulo() + "» aún está en construcción.");
        } else {
            accion.run();
        }
    }
}
