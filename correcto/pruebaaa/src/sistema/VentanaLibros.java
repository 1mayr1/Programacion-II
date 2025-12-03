package sistema;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaLibros extends JFrame {
    
    private Biblioteca biblioteca;
    private JPanel panelContenido;
    
    public VentanaLibros(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        
        setTitle(" Libros Disponibles");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        crearInterfaz();
        cargarLibros();
        
        setVisible(true);
    }
    
    private void crearInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel lblTitulo = new JLabel("LIBROS DISPONIBLES PARA PRÉSTAMO");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 30));
        lblTitulo.setForeground(new Color(26, 59, 73));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnVolver = new JButton("← Volver al Menú Principal");
        btnVolver.addActionListener(e -> dispose());
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(btnVolver, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void cargarLibros() {
        panelContenido.removeAll();
        
        List<Libro> libros = biblioteca.getLibrosDisponibles();
        
        if (libros.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay libros disponibles en este momento.");
            lblVacio.setFont(new Font("Serif", Font.ITALIC, 14));
            lblVacio.setForeground(Color.GRAY);
            lblVacio.setHorizontalAlignment(SwingConstants.CENTER);
            panelContenido.add(lblVacio);
        } else {
            for (Libro libro : libros) {
                panelContenido.add(crearPanelLibro(libro));
                panelContenido.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }
        
        panelContenido.revalidate();
        panelContenido.repaint();
    }
    
    private JPanel crearPanelLibro(Libro libro) {
        JPanel panelLibro = new JPanel(new BorderLayout());
        panelLibro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panelLibro.setBackground(Color.WHITE);
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(Color.WHITE);
        
        JLabel lblTituloLibro = new JLabel(libro.getTitulo());
        lblTituloLibro.setFont(new Font("Serif", Font.BOLD, 16));
        lblTituloLibro.setForeground(new Color(44, 62, 80));
        
        String autorNombre = "Autor no asignado";
        String nacionalidad = "";

        JButton btnPrestamo = new JButton("SOLICITAR PRÉSTAMO");
        btnPrestamo.setBackground(new Color(36, 59, 73));
        btnPrestamo.setForeground(Color.WHITE);
        btnPrestamo.setFont(new Font("Serif", Font.BOLD, 12));
        btnPrestamo.addActionListener(e -> {
            new RegistroPrestamo(this, biblioteca, libro);
        });
        
        JLabel lblAutor = new JLabel("Autor: " + autorNombre);
        JLabel lblISBN = new JLabel("ISBN: " + libro.getIsbn());
        JLabel lblPaginas = new JLabel("Número de páginas: " + libro.getTotalPaginas());
        
        String contenido = libro.getPaginas().isEmpty() ? "Sin contenido disponible" 
                           : libro.getPaginas().get(0).getContenido();
        JLabel lblContenido = new JLabel("<html>Contenido:<br><i>\"" + contenido + "\"</i></html>");
        lblContenido.setFont(new Font("Serif", Font.PLAIN, 12));
        lblContenido.setForeground(new Color(127, 140, 141));
        
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(220, 220, 220));
        
        panelInfo.add(lblTituloLibro);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 8)));
        panelInfo.add(lblAutor);
        panelInfo.add(lblISBN);
        panelInfo.add(lblPaginas);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 8)));
        panelInfo.add(lblContenido);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(separador);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        
        panelLibro.add(panelInfo, BorderLayout.CENTER);
        panelLibro.add(btnPrestamo, BorderLayout.EAST);
        
        return panelLibro;
    }
}