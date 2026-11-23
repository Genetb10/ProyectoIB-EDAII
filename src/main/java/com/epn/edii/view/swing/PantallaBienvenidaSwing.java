package com.epn.edii.view.swing;

import com.epn.edii.model.Estudiante;
import com.epn.edii.view.PantallaBienvenida;
import java.awt.*;
import javax.swing.*;

/**
 * Portada: franja azul arriba, escudo en la esquina superior izquierda y,
 * debajo, todo centrado y en vertical: institución, materia, nombre y botón.
 */
public class PantallaBienvenidaSwing extends JFrame implements PantallaBienvenida {

    private static final Color AZUL = new Color(0, 51, 153);

    private final JLabel lblEscudo = new JLabel();
    private final JLabel lblInstitucion = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblMateria = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblNombre = new JLabel("", SwingConstants.CENTER);
    private final JButton btnIngresar = new JButton("Ingresar");

    public PantallaBienvenidaSwing() {
        super("Proyecto - Estructura de Datos II");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(560, 440);
        setResizable(false);
        setLocationRelativeTo(null);
        construirUI();
    }

    private void construirUI() {
        JPanel raiz = new JPanel(new BorderLayout());

        // 1. Franja azul superior (solo decoración)
        JPanel franja = new JPanel();
        franja.setBackground(AZUL);
        franja.setPreferredSize(new Dimension(0, 35));
        raiz.add(franja, BorderLayout.NORTH);

        // 2. Contenido: escudo arriba a la izquierda + bloque central
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 15, 25, 15));

        JPanel esquina = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        lblEscudo.setIcon(Recursos.icono("/Epn.png", 60, 71));
        esquina.add(lblEscudo);
        contenido.add(esquina, BorderLayout.NORTH);

        // 3. Bloque central: uno debajo de otro, todo centrado
        lblInstitucion.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblMateria.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblMateria.setForeground(new Color(90, 90, 90));
        lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnIngresar.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setMaximumSize(new Dimension(160, 42));
        btnIngresar.setPreferredSize(new Dimension(160, 42));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        for (JComponent c : new JComponent[]{lblInstitucion, lblMateria, lblNombre, btnIngresar}) {
            c.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        lblInstitucion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        lblMateria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        lblNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        centro.add(Box.createVerticalGlue());
        centro.add(lblInstitucion);
        centro.add(Box.createVerticalStrut(12));
        centro.add(lblMateria);
        centro.add(Box.createVerticalStrut(12));
        centro.add(lblNombre);
        centro.add(Box.createVerticalStrut(35));
        centro.add(btnIngresar);
        centro.add(Box.createVerticalGlue());

        contenido.add(centro, BorderLayout.CENTER);
        raiz.add(contenido, BorderLayout.CENTER);

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