package GUI;

import Control.Control;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class AjustarDireccionMapa extends JFrame {

    private Control control;

    private JXMapViewer mapViewer;
    private WaypointPainter<Waypoint> waypointPainter;
    private GeoPosition ubicacionSeleccionada = null;

    public AjustarDireccionMapa(Control control) {
        setTitle("GoOrder - Seleccionar Ubicación");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        this.control = control;
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(control.COLOR_FONDO);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        JButton btnRegresar = new JButton("←");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 24));
        btnRegresar.setForeground(Color.LIGHT_GRAY);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegresar.setForeground(control.COLOR_NEON); }
            public void mouseExited(MouseEvent e) { btnRegresar.setForeground(Color.LIGHT_GRAY); }
        });

        btnRegresar.addActionListener(e -> {
            try {
                control.mostrarDomicilioFORM();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al regresar: " + ex.getMessage());
            }
        });

        JLabel lblTitulo = new JLabel("UBICA TU ENTREGA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(control.COLOR_NEON);
        headerPanel.add(btnRegresar, BorderLayout.WEST);
        headerPanel.add(lblTitulo);
        add(headerPanel, BorderLayout.NORTH);

        mapViewer = new JXMapViewer();

        TileFactoryInfo info = new TileFactoryInfo(1, 19, 19,
                256, true, true,
                "https://a.basemaps.cartocdn.com/light_all",
                "x", "y", "z") {
            public String getTileUrl(int x, int y, int zoom) {
                int z = 19 - zoom;
                return this.baseURL + "/" + z + "/" + x + "/" + y + ".png";
            }
        };
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        GeoPosition cdObregon = new GeoPosition(27.4961, -109.9328);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(cdObregon);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        waypointPainter = new WaypointPainter<>();
        mapViewer.setOverlayPainter(waypointPainter);

        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    ubicacionSeleccionada = mapViewer.convertPointToGeoPosition(e.getPoint());
                    actualizarPinEnMapa(ubicacionSeleccionada);
                }
            }
        });

        JPanel mapContainer = new JPanel(new BorderLayout());
        mapContainer.setBorder(BorderFactory.createLineBorder(control.COLOR_NEON, 2));
        mapContainer.add(mapViewer, BorderLayout.CENTER);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(control.COLOR_FONDO);
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        wrapperPanel.add(mapContainer, BorderLayout.CENTER);

        JLabel lblInstrucciones = new JLabel("Haz doble clic en el mapa para marcar tu ubicación", SwingConstants.CENTER);
        lblInstrucciones.setForeground(Color.LIGHT_GRAY);
        lblInstrucciones.setFont(new Font("Arial", Font.ITALIC, 12));
        lblInstrucciones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        wrapperPanel.add(lblInstrucciones, BorderLayout.SOUTH);

        add(wrapperPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(control.COLOR_FONDO);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        BotonNeon btnConfirmar = new BotonNeon("CONFIRMAR UBICACIÓN");
        btnConfirmar.setPreferredSize(new Dimension(340, 50));
        btnConfirmar.setMaximumSize(new Dimension(340, 50));

        btnConfirmar.addActionListener(e -> {
          control.mostrarTotalPrecioProductos();
        });

        footerPanel.add(btnConfirmar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void actualizarPinEnMapa(GeoPosition posicion) {
        Set<Waypoint> waypoints = new HashSet<>();
        waypoints.add(new DefaultWaypoint(posicion));
        waypointPainter.setWaypoints(waypoints);
        mapViewer.repaint();
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

            g2.setFont(new Font("Arial", Font.BOLD, 14));
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