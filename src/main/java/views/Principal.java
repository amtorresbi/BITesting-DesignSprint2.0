package views;

import com.formdev.flatlaf.FlatDarculaLaf;
// IMPORT CONFIGURATIONS DATA
import config.Mensajes;
import config.MensajesObserver;
import config.Preferencias;
// IMPORT TEST SUITES
import controllers.Login;
import controllers.transferencia.Transferencia_propia;
import controllers.transferencia.Transferencia_propia_f2;
import controllers.transferencia.Transferencia_propia_f3;
import controllers.transferencia.Transferencia_propia_f4;
import controllers.transferencia.Tpropias_belapp;
import controllers.transferencia.Tpropias_belappF;
// IMPORT HELPERS
import helpers.Bi_helper;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.Timer;


public class Principal extends JFrame implements MensajesObserver {
    static Preferencias preferencias = Preferencias.getInstance();
    private String AMBIENTE_JSON = preferencias.obtenerAtributo("rutaJsonAmbiente");
    private static String flowTest = ""; // Nombre del flujo de prueba actual
    private static float percentage = 0; // Porcentaje de tests ejecutados
    private static String nameTest; // Nombre del test actual
    private static Map<Integer, String> ambientes = new HashMap<>(); // Mapa de ambientes

    private static JFrame percentageFrame; // Ventana que muestra el porcentaje de tests ejecutados
    private static JPanel panelTipoPrueba = new JPanel(); // Panel que contiene Web o App
    private static JPanel panelMedioPrueba = new JPanel(); // Panel que contiene Chrome, Firefox, Edge, Safari, Android o iOS
    private static JPanel panelAmbiente = new JPanel(); // Panel que contiene los ambientes de QA
    private static JPanel panelPruebas = new JPanel(); // Panel que contiene los modulos de prueba
    private static JPanel panelFlujos = new JPanel(); // Panel que contiene los flujos de prueba
    private static JPanel panelReinicio = new JPanel(new BorderLayout()); // Panel que contiene el botón de reinicio
    private static JPanel panelMensaje = new JPanel(new BorderLayout()); // Panel que contiene el mensaje de éxito o fallo
    private static JLabel labelMensaje = new JLabel(); // Label que contiene el mensaje de éxito o fallo
    private static JLabel labelNameTest = new JLabel("¡Test iniciado!"); // Label que contiene el nombre del test actual
    private static JLabel labelPorcentaje = new JLabel("Tests ejecutados: 0"); // Label que contiene el porcentaje de tests ejecutados

    private final Color colorFondo = new Color(158, 218, 255);

    public Principal() {
        generarPlantillas(); // Genera las plantillas de los archivos de configuración
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        setTitle("Automatizaciones Bi"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configuración cierre de la ventana
        setLayout(new GridBagLayout());
        setSize(1200, 700); // Tamaño de la ventana
        setIconImage(Objects.requireNonNull(Bi_helper.rutaImg("icono.png")).getImage()); // Icono de la ventana
        getAmbientes();

        // Configuración de las partes de la ventana
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 2, 2, 2);
        c.gridy = 0;
        c.gridx = 0;
        add(columnaTipoPrueba(), c);
        c.gridx = 1;
        add(columnaMedioPrueba(), c);
        c.gridx = 2;
        add(columnaAmbiente(panelAmbiente, panelPruebas), c);
        c.gridx = 3;
        add(columnaTest(panelPruebas, panelFlujos), c);
        c.gridx = 4;
        add(columnaFlujos(), c);
        c.gridy = 1;
        c.gridwidth = 5;
        c.gridx = 0;
        add(btnReinicio(), c);

        c.gridy = 2;
        c.gridwidth = 5;
        c.gridx = 0;
        add(vistaMensaje(), c); // Vista de los mensajes (correcto o fallo)

        System.out.println("HashCode de la instancia en views: " + preferencias.hashCode());

        iniciarBotones();
        showPercentage();
        getContentPane().setBackground(Color.decode("#111"));
        setVisible(true);
    }

    private JPanel columnaTipoPrueba() {
        JLabel titulo = new JLabel("Tipo de Prueba", SwingConstants.CENTER);
        JPanel secundario = new JPanel(new BorderLayout());

        //secundario.setBackground(Color.decode("#505151"));
        secundario.setPreferredSize(new Dimension(150, 270));

        titulo.setPreferredSize(new Dimension(300, 25));
        titulo.setFont(new Font("Arial", Font.BOLD, 15));

        Principal.panelTipoPrueba.setLayout(new BoxLayout(Principal.panelTipoPrueba, BoxLayout.Y_AXIS));
        Principal.panelTipoPrueba.setBackground(Color.decode("#505151"));

        secundario.add(titulo, BorderLayout.NORTH);

        JButton webBtn = new JButton("Web");
        webBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        webBtn.addActionListener(new AccionBtn(Principal.panelTipoPrueba, Principal.panelMedioPrueba));
        webBtn.addActionListener(e -> {
            preferencias.valorAtributo("typeTest", "web");
            webBtn.setBackground(new Color(158, 218, 255));
            webBtn.setSelected(true);
            List<JButton> botones = obtenerTodosLosJButtons(this);
            for (JButton boton : botones) {
                if (boton.getText().equals("Android") || boton.getText().equals("iOS")) {
                    boton.setVisible(false);
                } else if (boton.getText().equals("Chrome") || boton.getText().equals("Firefox") || boton.getText().equals("Edge") || boton.getText().equals("Safari")) {
                    boton.setVisible(true);
                }
            }
        });
        Principal.panelTipoPrueba.add(webBtn);

        JButton mobileBtn = new JButton("Mobile");
        mobileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        mobileBtn.addActionListener(new AccionBtn(Principal.panelTipoPrueba, Principal.panelMedioPrueba));
        mobileBtn.addActionListener(e -> {
            preferencias.valorAtributo("typeTest", "mobile");
            mobileBtn.setBackground(new Color(158, 218, 255));
            mobileBtn.setSelected(true);
            List<JButton> botones = obtenerTodosLosJButtons(this);
            for (JButton boton : botones) {
                if (boton.getText().equals("Chrome") || boton.getText().equals("Firefox") || boton.getText().equals("Edge") || boton.getText().equals("Safari")) {
                    boton.setVisible(false);
                } else if (boton.getText().equals("Android") || boton.getText().equals("iOS")) {
                    boton.setVisible(true);
                }
            }
        });
        Principal.panelTipoPrueba.add(mobileBtn);

        secundario.add(Principal.panelTipoPrueba, BorderLayout.CENTER);

        return secundario;
    }

    private JPanel columnaMedioPrueba() {
        JLabel titulo = new JLabel("Medio de prueba", SwingConstants.CENTER);
        JPanel secundario = new JPanel(new BorderLayout());

        JButton btnChrome = new JButton("Chrome", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Chrome.png")), 25, 25));
        JButton btnFirefox = new JButton("Firefox", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Firefox.png")), 25, 25));
        JButton btnEdge = new JButton("Edge", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Edge.png")), 25, 25));
        JButton btnSafari = new JButton("Safari", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Safari.png")), 25, 25));
        JButton btnAndroid = new JButton("Android", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Android.png")), 25, 25));
        JButton btnIos = new JButton("iOS", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Ios.png")), 25, 25));

        //secundario.setBackground(colorFondo);
        secundario.setPreferredSize(new Dimension(150, 270));

        titulo.setPreferredSize(new Dimension(300, 25));
        titulo.setFont(new Font("Arial", Font.BOLD, 15));

        Principal.panelMedioPrueba.setLayout(new BoxLayout(Principal.panelMedioPrueba, BoxLayout.Y_AXIS));
        Principal.panelMedioPrueba.setBackground(Color.decode("#505151"));

        btnChrome.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnChrome.addActionListener(new AccionBtn(Principal.panelMedioPrueba, Principal.panelAmbiente));
        btnChrome.addActionListener(e -> {
            preferencias.valorAtributo("navegadorTipo", "1");
            preferencias.valorAtributo("navegadorNombre", "Chrome");
            btnChrome.setBackground(colorFondo);
            btnChrome.setSelected(true);
        });

        btnFirefox.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFirefox.addActionListener(new AccionBtn(Principal.panelMedioPrueba, Principal.panelAmbiente));
        btnFirefox.addActionListener(e -> {
            preferencias.valorAtributo("navegadorTipo", "2");
            preferencias.valorAtributo("navegadorNombre", "Firefox");
            btnFirefox.setBackground(colorFondo);
            btnFirefox.setSelected(true);
        });

        btnEdge.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEdge.addActionListener(new AccionBtn(Principal.panelMedioPrueba, Principal.panelAmbiente));
        btnEdge.addActionListener(e -> {
            preferencias.valorAtributo("navegadorTipo", "3");
            preferencias.valorAtributo("navegadorNombre", "Edge");
            btnEdge.setBackground(colorFondo);
            btnEdge.setSelected(true);
        });

        btnSafari.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSafari.addActionListener(new AccionBtn(Principal.panelMedioPrueba, Principal.panelAmbiente));
        btnSafari.addActionListener(e -> {
            preferencias.valorAtributo("navegadorTipo", "4");
            preferencias.valorAtributo("navegadorNombre", "Safari");
            btnSafari.setBackground(colorFondo);
            btnSafari.setSelected(true);
        });

        btnAndroid.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAndroid.addActionListener(new AccionBtn(Principal.panelMedioPrueba, Principal.panelAmbiente));
        btnAndroid.addActionListener(e -> {
            preferencias.valorAtributo("dispositivoTipo", "1");
            preferencias.valorAtributo("dispositivoNombre", "Android");
            btnAndroid.setBackground(colorFondo);
            btnAndroid.setSelected(true);
        });

        btnIos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIos.addActionListener(new AccionBtn(Principal.panelMedioPrueba, Principal.panelAmbiente));
        btnIos.addActionListener(e -> {
            preferencias.valorAtributo("dispositivoTipo", "2");
            preferencias.valorAtributo("dispositivoNombre", "iOS");
            btnIos.setBackground(colorFondo);
            btnIos.setSelected(true);
        });

        secundario.add(titulo, BorderLayout.NORTH);
        Principal.panelMedioPrueba.add(btnChrome);
        Principal.panelMedioPrueba.add(btnFirefox);
        Principal.panelMedioPrueba.add(btnEdge);
        Principal.panelMedioPrueba.add(btnSafari);
        Principal.panelMedioPrueba.add(btnAndroid);
        Principal.panelMedioPrueba.add(btnIos);
        secundario.add(Principal.panelMedioPrueba, BorderLayout.CENTER);

        return secundario;
    }

    public JPanel columnaAmbiente(JPanel actual, JPanel siguiente) {
        JLabel titulo = new JLabel("QA's", SwingConstants.CENTER);
        JPanel secundario = new JPanel(new BorderLayout());

        //secundario.setBackground(colorFondo);
        secundario.setPreferredSize(new Dimension(150, 270));

        titulo.setPreferredSize(new Dimension(300, 25));
        titulo.setFont(new Font("Arial", Font.BOLD, 15));

        actual.setLayout(new BoxLayout(actual, BoxLayout.Y_AXIS));
        actual.setBackground(Color.decode("#505151"));

        secundario.add(titulo, BorderLayout.NORTH);

        for (Map.Entry<Integer, String> entry : ambientes.entrySet()) {

            JButton boton = new JButton(entry.getValue());
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.addActionListener(new AccionBtn(actual, siguiente));
            boton.addActionListener(e -> {
                setAmbientes(entry.getKey());
                boton.setBackground(colorFondo);
                boton.setSelected(true);
            });
            actual.add(boton);
        }

        secundario.add(actual, BorderLayout.CENTER);

        return secundario;
    }

    public JPanel columnaTest(JPanel actual, JPanel siguiente) {
        JLabel titulo = new JLabel("Modulos", SwingConstants.CENTER);
        JPanel secundario = new JPanel(new BorderLayout());

        //secundario.setBackground(colorFondo);
        secundario.setPreferredSize(new Dimension(200, 270));

        titulo.setPreferredSize(new Dimension(300, 25));
        titulo.setFont(new Font("Arial", Font.BOLD, 15));

        actual.setLayout(new BoxLayout(actual, BoxLayout.Y_AXIS));
        actual.setBackground(Color.decode("#505151"));

        // Identificación de Flujos Login
        JButton loginTest = new JButton("Login");
        loginTest.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTest.addActionListener(new AccionBtn(actual, siguiente));
        loginTest.addActionListener(e -> {
            loginTest.setBackground(colorFondo);
            loginTest.setSelected(true);
            List<JButton> botones = obtenerTodosLosJButtons(panelFlujos); // Obtener todos los botones del panel de flujos
            String tipoPrueba = preferencias.obtenerAtributo("typeTest");            
            if (tipoPrueba == "web") {
                for (JButton boton : botones) {
                    if (boton.getText().contains("Login - WEB -")) {
                        boton.setVisible(true);
                    } else {
                        boton.setVisible(false);
                    }
                }
            } else if (tipoPrueba == "mobile") {
                for (JButton boton : botones) {
                    if (boton.getText().contains("Login - Online -") || boton.getText().contains("Login - Fisico -")) {
                        boton.setVisible(true);
                    } else {
                        boton.setVisible(false);
                    }
                }
            }

        });

        // Identificación de Flujos Transferencia Propia
        JButton tpTest = new JButton("Transferencias Propias");
        tpTest.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpTest.addActionListener(new AccionBtn(actual, siguiente));
        tpTest.addActionListener(e -> {
            tpTest.setBackground(colorFondo);
            tpTest.setSelected(true);
            List<JButton> botones = obtenerTodosLosJButtons(panelFlujos); // Obtener todos los botones del panel de flujos
            String tipoPrueba = preferencias.obtenerAtributo("typeTest");
            if (tipoPrueba == "web") {
                for (JButton boton : botones) {
                    if (boton.getText().contains("TP - WEB -")) {
                        boton.setVisible(true);
                    } else {
                        boton.setVisible(false);
                    }
                }
            } else if (tipoPrueba == "mobile") {
                for (JButton boton : botones) {
                    if (boton.getText().contains("TP - Online -") || boton.getText().contains("TP - Fisico -")) {
                        boton.setVisible(true);
                    } else {
                        boton.setVisible(false);
                    }
                }
            }
        });

        secundario.add(titulo, BorderLayout.NORTH);
        actual.add(loginTest);
        actual.add(tpTest);
        secundario.add(actual, BorderLayout.CENTER);

        return secundario;
    }

    public JPanel columnaFlujos() {
        JLabel titulo = new JLabel("Flujos", SwingConstants.CENTER);
        JPanel secundario = new JPanel(new BorderLayout());

        //secundario.setBackground(colorFondo);
        secundario.setPreferredSize(new Dimension(200, 270));

        titulo.setPreferredSize(new Dimension(300, 25));
        titulo.setFont(new Font("Arial", Font.BOLD, 15));

        Principal.panelFlujos.setLayout(new BoxLayout(Principal.panelFlujos, BoxLayout.Y_AXIS));
        Principal.panelFlujos.setBackground(Color.decode("#505151"));

        // Configuración de los botones de los flujos de prueba
        // Login - WEB - F1
        JButton loginWebF1 = new JButton("Login - WEB - F1", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("webTest.png")), 25, 25));
        loginWebF1.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginWebF1.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        loginWebF1.addActionListener(e -> {
            setBotonEjecucion(loginWebF1, "LoginWEBF1");
        });
        Principal.panelFlujos.add(loginWebF1);

        // Login - Online - F1
        JButton loginOnF1 = new JButton("Login - Online - F1", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("onlineTest.png")), 25, 25));
        loginOnF1.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginOnF1.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        loginOnF1.addActionListener(e -> {
            setBotonEjecucion(loginOnF1, "LoginWEBF1");
        });
        Principal.panelFlujos.add(loginOnF1);

        // TP - WEB - F1
        JButton tpWebF1 = new JButton("TP - WEB - F1", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("webTest.png")), 25, 25));
        tpWebF1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpWebF1.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        tpWebF1.addActionListener(e -> {
            setBotonEjecucion(tpWebF1, "TPWEBF1");
        });
        Principal.panelFlujos.add(tpWebF1);

        // TP - WEB - F2
        JButton tpWebF2 = new JButton("TP - WEB - F2", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("webTest.png")), 25, 25));
        tpWebF2.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpWebF2.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        tpWebF2.addActionListener(e -> {
            setBotonEjecucion(tpWebF2, "TPWEBF2");
        });
        Principal.panelFlujos.add(tpWebF2);

        // TP - WEB - F3
        JButton tpWebF3 = new JButton("TP - WEB - F3", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("webTest.png")), 25, 25));
        tpWebF3.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpWebF3.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        tpWebF3.addActionListener(e -> {
            setBotonEjecucion(tpWebF3, "TPWEBF3");
        });
        Principal.panelFlujos.add(tpWebF3);

        // TP - WEB - F4
        JButton tpWebF4 = new JButton("TP - WEB - F4", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("webTest.png")), 25, 25));
        tpWebF4.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpWebF4.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        tpWebF4.addActionListener(e -> {
            setBotonEjecucion(tpWebF4, "TPWEBF4");
        });
        Principal.panelFlujos.add(tpWebF4);

        // TP - Online - F1
        JButton tpOnF1 = new JButton("TP - Online - F1", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("onlineTest.png")), 25, 25));
        tpOnF1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpOnF1.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        tpOnF1.addActionListener(e -> {
            setBotonEjecucion(tpOnF1, "TPOnlineF1");
        });
        Principal.panelFlujos.add(tpOnF1);

        // TP - Fisico - F1
        JButton tpFisF1 = new JButton("TP - Fisico - F1", iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("physicTest.png")), 25, 25));
        tpFisF1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tpFisF1.addActionListener(new AccionBtn(Principal.panelFlujos, Principal.panelReinicio));
        tpFisF1.addActionListener(e -> {
            setBotonEjecucion(tpFisF1, "TPFisicoF1");
        });
        Principal.panelFlujos.add(tpFisF1);

        secundario.add(titulo, BorderLayout.NORTH);
        secundario.add(Principal.panelFlujos, BorderLayout.CENTER);
        secundario.add(new JScrollPane(Principal.panelFlujos));

        return secundario;
    }

    private JPanel btnReinicio() {
        JButton temp = new JButton("Reiniciar");
        temp.setEnabled(true);
        temp.addActionListener(e -> iniciarBotones());

        Principal.panelReinicio.add(temp);

        return Principal.panelReinicio;
    }

    private JPanel vistaMensaje() {
        Principal.panelMensaje.setLayout(new BoxLayout(panelMensaje, BoxLayout.Y_AXIS));
        Principal.panelMensaje.setBorder(new LineBorder(Color.black));

        labelMensaje.setHorizontalAlignment(JLabel.CENTER);

        Principal.panelMensaje.add(labelMensaje);
        return Principal.panelMensaje;
    }

    private void setMensajeInicioEjecucion() {
        labelMensaje.setIcon(iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Loading.png")), 36, 36));
        labelMensaje.setText("Ejecución iniciada");
        panelMensaje.setBackground(Color.decode("#097703"));
        labelMensaje.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelMensaje.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        labelMensaje.setFont(new Font("Serif", Font.BOLD, 12));
        labelMensaje.setForeground(Color.white);
        panelMensaje.setVisible(true);
    }
    private void setBotonEjecucion(JButton btn, String flowTest){
        SwingUtilities.invokeLater(() -> {
            btn.setBackground(colorFondo);
            btn.setSelected(true);
            setMensajeInicioEjecucion();
        });
        percentageFrame.setVisible(true);
        percentageFrame.toFront();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
             @Override
            protected Void doInBackground() throws Exception {
                Principal.flowTest = flowTest;
                switch (flowTest) {
                    case "TPWEBF1":
                        Transferencia_propia.main(null);
                        break;
                    case "TPWEBF2":
                        Transferencia_propia_f2.main(null);
                        break;
                    case "TPWEBF3":
                        Transferencia_propia_f3.main(null);
                        break;
                    case "TPWEBF4":
                        Transferencia_propia_f4.main(null);
                        break;
                    case "TPOnlineF1":
                        Tpropias_belapp.main(null);
                        break;
                    case "TPFisicoF1":
                        Tpropias_belappF.main(null);
                        break;
                    case "LoginWEBF1":
                        Login.main(null);
                        break;
                    default:
                        break;
                }
                return null;
            }

            @Override
            protected void done() {
                percentageFrame.toFront();
                if (Mensajes.getMensaje().isEmpty()) {
                    actualizar(new ArrayList<>());
                }
                JLabel Final = new JLabel("Ejecución finalizada");
                Final.setForeground(Color.WHITE);
                percentageFrame.add(Final);
            }
        };
        worker.execute();
    }

    private static class AccionBtn implements ActionListener {
        private final JPanel siguiente;
        private final JPanel previo;

        public AccionBtn(JPanel previo, JPanel siguiente) {
            this.previo = previo;
            this.siguiente = siguiente;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Component previo : previo.getComponents()) {
                if (previo instanceof JButton) {
                    previo.setEnabled(false);
                }
            }
            for (Component siguiente : siguiente.getComponents()) {
                if (siguiente instanceof JButton) {
                    siguiente.setEnabled(true);
                }
            }
        }
    }

    @Override
    public void actualizar(ArrayList<String> nuevoMensaje) {

        if (nuevoMensaje.isEmpty()) {
            labelMensaje.setIcon(iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Correcto.png")), 36, 36));
            labelMensaje.setText("Test ejecutado con éxito");
            panelMensaje.setBackground(Color.decode("#097703"));
        } else {
            StringBuilder mensaje = new StringBuilder("<html>");
            for (String msg : nuevoMensaje) {
                mensaje.append("<p>").append(msg).append("</p>").append("\n");
            }

            mensaje.append("</html>");

            labelMensaje.setIcon(iconoRedimensionado(Objects.requireNonNull(Bi_helper.rutaImg("Fallo.png")), 36, 36));
            labelMensaje.setText(mensaje.toString());
            panelMensaje.setBackground(Color.decode("#840c03"));
        }

        Border border = BorderFactory.createEmptyBorder(15, 5, 15, 25);
        labelMensaje.setBorder(border);
        new LineBorder(Color.RED, 4);

        labelMensaje.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelMensaje.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        labelMensaje.setFont(new Font("Serif", Font.BOLD, 12));
        labelMensaje.setForeground(Color.white);

        panelMensaje.setVisible(true);
    }

    private static Icon iconoRedimensionado(ImageIcon icono, int w, int h) {
        Image imagenRedimensionada = icono.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

    private static void getAmbientes() {
        ambientes.put(1, "QA01");
        ambientes.put(2, "QA02");
        ambientes.put(3, "QA03");
        ambientes.put(4, "QA04");
        ambientes.put(5, "QA05");
        ambientes.put(6, "QA06");
        ambientes.put(7, "QA07");
        ambientes.put(8, "QA08");
        ambientes.put(9, "Produccion");
    }

    private void setAmbientes(Integer valor) {

        String temp;

        if (ambientes.containsKey(valor)) {
            temp = String.valueOf(ambientes.get(valor));
        } else {
            temp = "Produccion";
        }

        preferencias.valorAtributo("paginaWeb", Bi_helper.obtenerDato(temp, "url", AMBIENTE_JSON));
    }

    private void iniciarBotones() {
        List<JButton> botones = obtenerTodosLosJButtons(this);
        Color tmp = new JButton().getBackground();
        for (JButton boton : botones) {
            if (boton.getText() != "Reiniciar"){
                boton.setSelected(false);
                boton.setEnabled(false);
                boton.setVisible(true);
                boton.setBackground(tmp);
            }


        }

        for (Component panelTipoPrueba : panelTipoPrueba.getComponents()) {
            if (panelTipoPrueba instanceof JButton) {
                panelTipoPrueba.setEnabled(true);
            }
        }

        Mensajes.limpiarMensaje();
        // Reinicio de valores de tests
        if (flowTest == "LoginWEBF1"){
            Login.resetValues();
        } else if (flowTest == "TPWEBF1") {
            Transferencia_propia.resetValues();
        } else if (flowTest == "TPWEBF2") {
            Transferencia_propia_f2.resetValues();
        } else if (flowTest == "TPWEBF3") {
            Transferencia_propia_f3.resetValues();
        } else if (flowTest == "TPWEBF4") {
            Transferencia_propia_f4.resetValues();
        } else if (flowTest == "TPOnlineF1") {
            // Falta implementacion en Controller
        } else {
            nameTest = "No hay ejecución en proceso";
            percentage = 0;
        }
        flowTest = "";

        updateGUI();
        panelMensaje.setVisible(false);
    }

    private List<JButton> obtenerTodosLosJButtons(Container container) {
        List<JButton> botones = new ArrayList<>();
        buscarJButtons(container, botones);
        return botones;
    }

    private void buscarJButtons(Container container, List<JButton> botones) {
        Component[] componentes = container.getComponents();

        for (Component componente : componentes) {
            if (componente instanceof JButton) {
                botones.add((JButton) componente);
            } else if (componente instanceof Container) {
                buscarJButtons((Container) componente, botones);
            }
        }
    }

    private static void generarPlantillas() {
        Preferencias.getInstance().generarPlantillas();
    }

    private static void showPercentage() {
        SwingUtilities.invokeLater(() -> {
            percentageFrame = new JFrame();
            percentageFrame.setTitle("Porcentaje de tests ejecutados");
            percentageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            percentageFrame.setLayout(new BorderLayout());
            percentageFrame.setLayout(new BoxLayout(percentageFrame.getContentPane(), BoxLayout.Y_AXIS));
            percentageFrame.setSize(300, 130);
            // Obtiene el tamaño de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Calcula las coordenadas para la esquina superior derecha
            int x = screenSize.width - percentageFrame.getWidth();
            int y = 0;

            // Establece la ubicación del JFrame en la esquina superior derecha
            percentageFrame.setLocation(x, y);
            labelPorcentaje.setForeground(Color.WHITE);
            labelNameTest.setForeground(Color.WHITE);
            percentageFrame.setResizable(false);
            percentageFrame.add(Box.createVerticalStrut(5));
            percentageFrame.add(labelNameTest, BorderLayout.CENTER);
            percentageFrame.add(Box.createVerticalStrut(10));
            percentageFrame.add(labelPorcentaje);
            percentageFrame.add(Box.createVerticalStrut(10));
            percentageFrame.setVisible(true);
            percentageFrame.setAlwaysOnTop(true);

            Timer timer = new Timer(1000, e -> updateGUI());
            timer.start();
        });
    }

    private static void updateGUI() {
        if (flowTest == "LoginWEBF1"){
            nameTest = Login.getNameTest();
            percentage = Login.getExecutedTests();
        } else if (flowTest == "TPWEBF1") {
            nameTest = Transferencia_propia.getNameTest();
            percentage = Transferencia_propia.getExecutedTests();
        } else if (flowTest == "TPWEBF2") {
            nameTest = Transferencia_propia_f2.getNameTest();
            percentage = Transferencia_propia_f2.getExecutedTests();
        } else if (flowTest == "TPWEBF3") {
            nameTest = Transferencia_propia_f3.getNameTest();
            percentage = Transferencia_propia_f3.getExecutedTests();
        } else if (flowTest == "TPWEBF4") {
            nameTest = Transferencia_propia_f4.getNameTest();
            percentage = Transferencia_propia_f4.getExecutedTests();
        } else if (flowTest == "TPOnlineF1") {
            // Falta implementacion en Controller
        } else {
            nameTest = "No hay ejecución en proceso";
            percentage = 0;
        }

        labelNameTest.setText(nameTest);
        DecimalFormat df = new DecimalFormat("0");
        labelPorcentaje.setText("Tests ejecutados: " + df.format(percentage));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Principal ventana = new Principal();
            Mensajes.addObserver(ventana);
        });
    }
}