package com.epn.edii.view.swing;

import com.epn.edii.model.OpcionMenu;
import com.epn.edii.view.PantallaMenu;
import java.awt.*;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/** Pantalla de selección: genera un botón por cada OpcionMenu recibida. */
public class PantallaMenuSwing extends JFrame implements PantallaMenu {

    private static final String FUENTE_TNR = "Times New Roman";
    private final JPanel panelOpciones = new JPanel(new GridLayout(0, 1, 0, 12));
    private final JButton btnVolver = new JButton("Volver");
    private Consumer<OpcionMenu> listenerOpcion = o -> { };

    public PantallaMenuSwing() {
        super("Menú - Seleccione una opción");
        configurarFuenteGlobal();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(560, 460);
        setLocationRelativeTo(null);
        construirUI();
    }

    private void configurarFuenteGlobal() {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource fuenteActual = (FontUIResource) value;
                UIManager.put(key, new FontUIResource(
                        FUENTE_TNR, fuenteActual.getStyle(), fuenteActual.getSize()));
            }
        }
    }

    private void construirUI() {
        // Panel raíz con diseño personalizado (degradado + formas sutiles)
        JPanel raiz = new JPanel(new BorderLayout(10, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                // 1. Degradado suave azul-lavanda claro
                GradientPaint degradado = new GradientPaint(
                        0, 0, new Color(238, 243, 252),
                        w, h, new Color(224, 231, 248));
                g2.setPaint(degradado);
                g2.fillRect(0, 0, w, h);

                // 2. Cuadrícula sutil tipo papel técnico
                g2.setColor(new Color(255, 255, 255, 110));
                int paso = 28;
                for (int x = 0; x < w; x += paso) {
                    g2.drawLine(x, 0, x, h);
                }
                for (int y = 0; y < h; y += paso) {
                    g2.drawLine(0, y, w, y);
                }

                // 3. Círculos decorativos translúcidos en las esquinas
                g2.setColor(new Color(147, 174, 230, 55));
                g2.fillOval(-60, -60, 220, 220);
                g2.fillOval(w - 150, h - 140, 210, 210);

                g2.dispose();
            }
        };
        raiz.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Seleccione una opción", SwingConstants.CENTER);
        titulo.setFont(new Font(FUENTE_TNR, Font.BOLD, 20));
        titulo.setForeground(new Color(30, 41, 59));

        // Hacer transparentes los paneles contenedores para que se vea el fondo
        panelOpciones.setOpaque(false);

        btnVolver.setFont(new Font(FUENTE_TNR, Font.PLAIN, 13));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setFocusPainted(false);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.setOpaque(false);
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
            JButton btn = new JButton("<html><div style=\"font-family:'Times New Roman', serif;\">"
                    + "<b>" + op.getTitulo() + "</b><br>"
                    + "<span style=\"font-family:'Times New Roman', serif; font-size:10px; color:gray;\">"
                    + op.getDescripcion() + "</span></div></html>");
            btn.setFont(new Font(FUENTE_TNR, Font.PLAIN, 13));
            btn.setBackground(Color.WHITE);
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