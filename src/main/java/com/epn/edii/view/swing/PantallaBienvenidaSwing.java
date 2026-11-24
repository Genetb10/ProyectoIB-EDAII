package com.epn.edii.view.swing;

import com.epn.edii.model.Estudiante;
import com.epn.edii.view.PantallaBienvenida;
import java.awt.*;
import javax.swing.*;

/** Portada dividida: escudo a la izquierda, información a la derecha. */
public class PantallaBienvenidaSwing extends JFrame implements PantallaBienvenida {

    private static final String FUENTE_TNR = "Times New Roman";

    private final JLabel lblEscudo = new JLabel();
    private final JLabel lblInstitucion = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblMateria = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblNombre = new JLabel("", SwingConstants.CENTER);
    private final JButton btnIngresar = new JButton("Ingresar");

    public PantallaBienvenidaSwing() {
        super("Proyecto - Estructura de Datos II");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        construirUI();
    }

    private void construirUI() {
        // Fondo general con degradado sobrio y cuadrícula sutil (alineado con el menú)
        JPanel raiz = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                GradientPaint degradado = new GradientPaint(
                        0, 0, new Color(243, 247, 253),
                        w, h, new Color(228, 236, 250));
                g2.setPaint(degradado);
                g2.fillRect(0, 0, w, h);

                g2.setColor(new Color(255, 255, 255, 115));
                int paso = 28;
                for (int x = 0; x < w; x += paso) {
                    g2.drawLine(x, 0, x, h);
                }
                for (int y = 0; y < h; y += paso) {
                    g2.drawLine(0, y, w, y);
                }

                g2.dispose();
            }
        };

        // ---------- LADO IZQUIERDO: escudo sobre fondo azul claro sobrio ----------
        JPanel izquierda = new JPanel(new GridBagLayout());
        izquierda.setBackground(Color.decode("#E7F0FD"));
        izquierda.setPreferredSize(new Dimension(260, 0));
        izquierda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(195, 208, 228)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
        lblEscudo.setIcon(Recursos.icono("/Epn.png", 150, 178));
        izquierda.add(lblEscudo);

        // ---------- LADO DERECHO: información ----------
        JPanel derecha = new JPanel();
        derecha.setLayout(new BoxLayout(derecha, BoxLayout.Y_AXIS));
        derecha.setOpaque(false);
        derecha.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 24));

        lblInstitucion.setFont(new Font(FUENTE_TNR, Font.BOLD, 25));
        lblInstitucion.setForeground(new Color(23, 37, 64));

        lblMateria.setFont(new Font(FUENTE_TNR, Font.PLAIN, 17));
        lblMateria.setForeground(new Color(82, 96, 117));

        lblNombre.setFont(new Font(FUENTE_TNR, Font.PLAIN, 15));
        lblNombre.setForeground(new Color(30, 41, 59));

        btnIngresar.setFont(new Font(FUENTE_TNR, Font.BOLD, 15));
        btnIngresar.setForeground(new Color(23, 37, 64));
        btnIngresar.setBackground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setPreferredSize(new Dimension(170, 42));
        btnIngresar.setMaximumSize(new Dimension(170, 42));
        btnIngresar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(148, 174, 212), 1),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)));

        for (JComponent c : new JComponent[]{lblInstitucion, lblMateria, lblNombre, btnIngresar}) {
            c.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        lblInstitucion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        lblMateria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        lblNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        derecha.add(Box.createVerticalGlue());
        derecha.add(lblInstitucion);
        derecha.add(Box.createVerticalStrut(12));
        derecha.add(lblMateria);
        derecha.add(Box.createVerticalStrut(14));
        derecha.add(lblNombre);
        derecha.add(Box.createVerticalStrut(40));
        derecha.add(btnIngresar);
        derecha.add(Box.createVerticalGlue());

        raiz.add(izquierda, BorderLayout.WEST);
        raiz.add(derecha, BorderLayout.CENTER);
        setContentPane(raiz);
        getRootPane().setDefaultButton(btnIngresar);
    }

    @Override
    public void mostrarDatos(Estudiante e) {
        lblInstitucion.setText(e.institucion());
        lblMateria.setText("Proyecto - " + e.materia());
        lblNombre.setText(e.nombre());
    }

    @Override
    public void alIngresar(Runnable accion) {
        btnIngresar.addActionListener(ev -> accion.run());
    }

    @Override public void mostrar() { setVisible(true); }
    @Override public void ocultar() { setVisible(false); }
}