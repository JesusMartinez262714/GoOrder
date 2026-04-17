package GUI;

import Control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DomicilioFORM extends JFrame {

    private Control control;
    private JTextField txtColonia;
    private JTextField txtCalle;
    private JTextField txtDireccion;
    private JTextArea txtEspecificaciones;

    public DomicilioFORM(Control control) {
        setTitle("GoOrder - Domicilio");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(control.COLOR_FONDO);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        JLabel lblTitulo = new JLabel("DATOS DE ENTREGA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(control.COLOR_NEON);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblTitulo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        BotonNeon btnMapa = new BotonNeon("AJUSTAR DIRECCIÓN EN EL MAPA");
        btnMapa.setPreferredSize(new Dimension(340, 45));
        btnMapa.setMaximumSize(new Dimension(340, 45));
        btnMapa.setFont(new Font("Arial", Font.BOLD, 12));

        btnMapa.addActionListener(e -> control.mostrarAjustarDireccionMapa());
        contentPanel.add(btnMapa);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        txtColonia = crearCampoTexto("Colonia", "Colonia o Fraccionamiento");
        txtCalle = crearCampoTexto("Calle", "Nombre de la calle");
        txtDireccion = crearCampoTexto("Dirección / Número", "Número exterior");

        contentPanel.add(crearPanelInput("Colonia", txtColonia));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        contentPanel.add(crearPanelInput("Calle", txtCalle));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        contentPanel.add(crearPanelInput("Dirección", txtDireccion));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelEspecificaciones = new JPanel();
        panelEspecificaciones.setLayout(new BoxLayout(panelEspecificaciones, BoxLayout.Y_AXIS));
        panelEspecificaciones.setBackground(control.COLOR_FONDO);
        panelEspecificaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEspecificaciones.setMaximumSize(new Dimension(340, 120));

        JLabel lblEspec = new JLabel("Especificaciones");
        lblEspec.setFont(new Font("Arial", Font.BOLD, 12));
        lblEspec.setForeground(Color.WHITE);
        lblEspec.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtEspecificaciones = new JTextArea("Referencias de la casa");
        txtEspecificaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEspecificaciones.setBackground(control.COLOR_INPUT);
        txtEspecificaciones.setForeground(Color.LIGHT_GRAY);
        txtEspecificaciones.setCaretColor(control.COLOR_NEON);
        txtEspecificaciones.setLineWrap(true);
        txtEspecificaciones.setWrapStyleWord(true);
        txtEspecificaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollEspec = new JScrollPane(txtEspecificaciones);
        scrollEspec.setBorder(BorderFactory.createLineBorder(control.COLOR_BORDE, 1));
        scrollEspec.setAlignmentX(Component.LEFT_ALIGNMENT);

        agregarEfectoFoco(txtEspecificaciones, scrollEspec, "Referencias de la casa");

        panelEspecificaciones.add(lblEspec);
        panelEspecificaciones.add(Box.createRigidArea(new Dimension(0, 5)));
        panelEspecificaciones.add(scrollEspec);

        contentPanel.add(panelEspecificaciones);
        contentPanel.add(Box.createVerticalGlue());

        add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnConfirmar = new BotonNeon("CONFIRMAR");
        btnConfirmar.setPreferredSize(new Dimension(340, 50));
        btnConfirmar.setMaximumSize(new Dimension(340, 50));
        btnConfirmar.addActionListener(e -> control.mostrarTotalPrecioProductos());
        footerPanel.add(btnConfirmar);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel crearPanelInput(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(control.COLOR_FONDO);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(340, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setMaximumSize(new Dimension(340, 35));

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(textField);

        return panel;
    }

    private JTextField crearCampoTexto(String nombre, String placeholder) {
        JTextField txt = new JTextField(placeholder);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setBackground(control.COLOR_INPUT);
        txt.setForeground(Color.LIGHT_GRAY);
        txt.setCaretColor(control.COLOR_NEON);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(control.COLOR_BORDE, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        agregarEfectoFoco(txt, txt, placeholder);
        return txt;
    }

    private void agregarEfectoFoco(Component componenteTexto, JComponent componenteBorde, String placeholder) {
        componenteTexto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                componenteBorde.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(control.COLOR_NEON, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));

                if (componenteTexto instanceof JTextField) {
                    JTextField txt = (JTextField) componenteTexto;
                    if (txt.getText().equals(placeholder)) {
                        txt.setText("");
                        txt.setForeground(Color.WHITE);
                    }
                } else if (componenteTexto instanceof JTextArea) {
                    JTextArea txt = (JTextArea) componenteTexto;
                    if (txt.getText().equals(placeholder)) {
                        txt.setText("");
                        txt.setForeground(Color.WHITE);
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                componenteBorde.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(control.COLOR_BORDE, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));

                if (componenteTexto instanceof JTextField) {
                    JTextField txt = (JTextField) componenteTexto;
                    if (txt.getText().isEmpty()) {
                        txt.setForeground(Color.LIGHT_GRAY);
                        txt.setText(placeholder);
                    }
                } else if (componenteTexto instanceof JTextArea) {
                    JTextArea txt = (JTextArea) componenteTexto;
                    if (txt.getText().isEmpty()) {
                        txt.setForeground(Color.LIGHT_GRAY);
                        txt.setText(placeholder);
                    }
                }
            }
        });
    }

    class BotonNeon extends JButton {
        private boolean over = false;

        public BotonNeon(String texto) {
            super(texto);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.CENTER_ALIGNMENT);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { over = true; repaint(); }
                public void mouseExited(MouseEvent e) { over = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (over) {
                g2.setColor(control.COLOR_NEON);
            } else {
                g2.setColor(control.COLOR_BOTON);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setFont(getFont() != null ? getFont() : new Font("Arial", Font.BOLD, 16));
            if (over) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(2, 2, getWidth()-5, getHeight()-5, 15, 15);
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }


}