package com.epn.edii.view.swing;

import com.epn.edii.model.OpcionMenu;
import com.epn.edii.view.PantallaMenu;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;

/** Pantalla de selección: genera un botón por cada OpcionMenu recibida. */
public class PantallaMenuSwing extends JFrame implements PantallaMenu {

    private final JPanel panelOpciones = new JPanel(new GridLayout(0, 1, 0, 12));
    private final JButton btnVolver = new JButton("Volver");
    private Consumer<OpcionMenu> listenerOpcion = o -> { };

    public PantallaMenuSwing() {
        super("Menú - Seleccione una opción");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(560, 460);
        setLocationRelativeTo(null);
        construirUI();
    }

    private void construirUI() {
        JPanel raiz = new JPanel(new BorderLayout(10, 15));
        raiz.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Seleccione una opción", SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(btnVolver);

        raiz.add(titulo, BorderLayout.NORTH);
        raiz.add(panelOpciones, BorderLayout.CENTER);
        raiz.add(sur, BorderLayout.SOUTH);
        setContentPane(raiz);
    }

    @Override
    public void mostrarOpciones(List<OpcionMenu> opciones) {
        panelOpciones.removeAll();
        for (OpcionMenu op : opciones) {
            JButton btn = new JButton("<html><b>" + op.getTitulo() + "</b><br>"
                    + "<span style='font-size:10px;color:gray'>" + op.getDescripcion() + "</span></html>");
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> listenerOpcion.accept(op));
            panelOpciones.add(btn);
        }
        panelOpciones.revalidate();
        panelOpciones.repaint();
    }

    @Override
    public void alSeleccionarOpcion(Consumer<OpcionMenu> accion) { this.listenerOpcion = accion; }

    @Override
    public void alVolver(Runnable accion) { btnVolver.addActionListener(e -> accion.run()); }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override public void mostrar() { setVisible(true); }
    @Override public void ocultar() { setVisible(false); }
}
