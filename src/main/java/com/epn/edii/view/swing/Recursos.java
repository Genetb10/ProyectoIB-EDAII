package com.epn.edii.view.swing;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/** Carga de recursos gráficos (responsabilidad única). */
final class Recursos {
    private Recursos() { }

    static ImageIcon icono(String ruta, int ancho, int alto) {
        URL url = Recursos.class.getResource(ruta);
        if (url == null) return null;
        Image img = new ImageIcon(url).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
