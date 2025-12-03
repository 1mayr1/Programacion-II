package sistema;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    
    private Biblioteca biblioteca;
    
    public VentanaPrincipal() {
        setTitle("Sistema de Biblioteca Universitaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550); 
        setLocationRelativeTo(null);
        
        cargarDatos();

        crearInterfaz();
        
        setVisible(true);
    }
    
    private void cargarDatos() {
        biblioteca = new Biblioteca("Biblioteca Central");
        biblioteca.setHorario("08:00", "20:00", "Lunes a Viernes");
        
        cargarDatosJSON();
    }
    
    private void cargarDatosJSON() {
        List<Autor> autores = JSONManager.cargarLista("autores.json", Autor.class);
        List<Libro> libros = JSONManager.cargarLista("libros.json", Libro.class);
        
        for (Autor autor : autores) {
            biblioteca.agregarAutor(autor);
        }
        
        for (Libro libro : libros) {
            biblioteca.agregarLibro(libro);
        }
    }
    
    private void crearInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblTitulo = new JLabel("GOOD READER");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        lblTitulo.setForeground(new Color(17, 24, 38));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50)); // Reduje un poco el padding
        
        JButton btnLibros = crearBoton("LIBROS", new Color(36, 59, 73));
        JButton btnAgregarLibro = crearBoton("AGREGAR LIBRO", new Color(120, 143, 161));
        JButton btnAgregarAutor = crearBoton("AGREGAR AUTOR", new Color(128, 99, 117));
        JButton btnHorario = crearBoton("HORARIO", new Color(162, 143, 128));
        
        JButton btnHistorial = crearBoton("VER HISTORIAL", new Color(85, 107, 47)); // Color verde oliva
        
        JButton btnCerrar = crearBoton("CERRAR BIBLIOTECA", new Color(199, 185, 176));
        
        panelBotones.add(btnLibros);
        panelBotones.add(btnAgregarLibro);
        panelBotones.add(btnAgregarAutor);
        panelBotones.add(btnHorario);
        panelBotones.add(btnHistorial); 
        panelBotones.add(btnCerrar);
        
        btnLibros.addActionListener(e -> abrirVentanaLibros());
        btnAgregarLibro.addActionListener(e -> abrirDialogoAgregarLibro());
        btnAgregarAutor.addActionListener(e -> abrirDialogoAgregarAutor());
        btnHorario.addActionListener(e -> mostrarHorario());
        
        btnHistorial.addActionListener(e -> abrirHistorial());
        
        btnCerrar.addActionListener(e -> cerrarBiblioteca());
        
        JPanel panelEstado = new JPanel();
        JLabel lblEstado = new JLabel("Libros disponibles: " + biblioteca.getLibrosDisponibles().size() + 
                                    " | Autores: " + biblioteca.getAutorRegistrados().size());
        lblEstado.setFont(new Font("Serif", Font.PLAIN, 12));
        panelEstado.add(lblEstado);
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelEstado, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Serif", Font.BOLD, 16));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25)); // Reducí un poco el padding vertical
        
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    private void abrirVentanaLibros() {
        new VentanaLibros(biblioteca);
    }
    
    private void abrirDialogoAgregarLibro() {
        new AgregarLibros(this, biblioteca);
    }
    
    private void abrirDialogoAgregarAutor() {
        new AgregarAutor(this, biblioteca);
    }
    
    private void mostrarHorario() {
        new RegHorario(biblioteca);
    }
    
    private void abrirHistorial() {
        try {
            new Historial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el historial:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cerrarBiblioteca() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de cerrar la biblioteca?\n" +
            "Se eliminarán todos los préstamos activos.",
            "Confirmar Cierre", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            biblioteca.cerrarBiblioteca();
            JOptionPane.showMessageDialog(this, 
                "Biblioteca cerrada exitosamente.\n" +
                "Préstamos activos eliminados.",
                "Biblioteca Cerrada", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal();
        });
    }
}