package com.epn.edii.view;

import com.epn.edii.model.OpcionMenu;
import java.util.List;
import java.util.function.Consumer;

public interface PantallaMenu extends Pantalla {
    void mostrarOpciones(List<OpcionMenu> opciones);
    void alSeleccionarOpcion(Consumer<OpcionMenu> accion);
    void alVolver(Runnable accion);
    void mostrarMensaje(String mensaje);
}
