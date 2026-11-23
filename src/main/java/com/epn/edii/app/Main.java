package com.epn.edii.app;

import com.epn.edii.controller.BienvenidaController;
import com.epn.edii.controller.MenuController;
import com.epn.edii.model.Estudiante;
import com.epn.edii.model.OpcionMenu;
import com.epn.edii.view.PantallaBienvenida;
import com.epn.edii.view.PantallaMenu;
import com.epn.edii.view.swing.PantallaBienvenidaSwing;
import com.epn.edii.view.swing.PantallaMenuSwing;
import java.util.Map;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Raíz de composición: único lugar donde se crean y conectan las piezas. */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        SwingUtilities.invokeLater(Main::iniciar);
    }

    private static void iniciar() {
        Estudiante estudiante = new Estudiante(
                "Génesis Tufiño", "Escuela Politécnica Nacional", "Estructura de Datos II");

        PantallaBienvenida bienvenida = new PantallaBienvenidaSwing();
        PantallaMenu menu = new PantallaMenuSwing();

        // Aquí se irán registrando los módulos conforme los construyamos.
        Map<OpcionMenu, Runnable> acciones = Map.of();

        new BienvenidaController(bienvenida, estudiante, () -> {
            bienvenida.ocultar();
            menu.mostrar();
        });
        new MenuController(menu, acciones, () -> {
            menu.ocultar();
            bienvenida.mostrar();
        });

        bienvenida.mostrar();
    }
}
