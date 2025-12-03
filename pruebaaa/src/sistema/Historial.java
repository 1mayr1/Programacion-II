package sistema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Historial extends JFrame {
    
    public Historial() {
        setTitle("📋 Historial de Registros");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        crearInterfaz();
        setVisible(true);
    }
    
    private void crearInterfaz() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Título
        JLabel titulo = new JLabel("📋 HISTORIAL DE REGISTROS");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        // Pestañas (Tabs) para organizar
        JTabbedPane pestañas = new JTabbedPane();
        
        // Pestaña 1: Autores
        pestañas.addTab("👨‍🏫 Autores", crearPanelAutores());
        
        // Pestaña 2: Libros
        pestañas.addTab("📚 Libros", crearPanelLibros());
        
        // Pestaña 3: Préstamos
        pestañas.addTab("📝 Préstamos", crearPanelPrestamos());
        
        // Pestaña 4: Todo
        pestañas.addTab("📊 Todo", crearPanelTodo());
        
        // Botón cerrar
        JPanel panelBotones = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setBackground(new Color(231, 76, 60));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnCerrar);
        
        // Agregar componentes
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(pestañas, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    private JPanel crearPanelAutores() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnas = {"Fecha", "Acción", "Nombre del Autor", "Nacionalidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        // Cargar autores desde JSON
        List<Autor> autores = JSONManager.cargarLista("autores.json", Autor.class);
        
        if (autores.isEmpty()) {
            modelo.addRow(new Object[]{"-", "No hay autores", "-", "-"});
        } else {
            for (Autor autor : autores) {
                modelo.addRow(new Object[]{
                    "Registrado",
                    "CREADO",
                    autor.getNombre(),
                    autor.getNacionalidad()
                });
            }
        }
        
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        
        // Etiqueta de cantidad
        JLabel lblInfo = new JLabel("Total autores: " + autores.size());
        lblInfo.setFont(new Font("Arial", Font.BOLD, 12));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblInfo, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearPanelLibros() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnas = {"Fecha", "Acción", "Título del Libro", "ISBN", "Autor"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        // Cargar libros desde JSON
        List<Libro> libros = JSONManager.cargarLista("libros.json", Libro.class);
        
        if (libros.isEmpty()) {
            modelo.addRow(new Object[]{"-", "No hay libros", "-", "-", "-"});
        } else {
            for (Libro libro : libros) {
                String autorNombre = "Sin autor";
                if (libro.getAutor() != null) {
                    autorNombre = libro.getAutor().getNombre();
                }
                
                modelo.addRow(new Object[]{
                    "Registrado",
                    "CREADO",
                    libro.getTitulo(),
                    libro.getIsbn(),
                    autorNombre
                });
            }
        }
        
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        
        // Etiqueta de cantidad
        JLabel lblInfo = new JLabel("Total libros: " + libros.size());
        lblInfo.setFont(new Font("Arial", Font.BOLD, 12));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblInfo, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnas = {"Fecha", "Estudiante", "Código", "Libro Prestado", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        // Cargar préstamos desde JSON
        List<Prestamo> prestamos = JSONManager.cargarLista("prestamos.json", Prestamo.class);
        
        if (prestamos == null || prestamos.isEmpty()) {
            modelo.addRow(new Object[]{"-", "No hay préstamos", "-", "-", "-"});
        } else {
            for (Prestamo prestamo : prestamos) {
                String nombreEstudiante = prestamo.getEstudiante() != null ? 
                    prestamo.getEstudiante().getNombre() : "Desconocido";
                
                String codigoEstudiante = prestamo.getEstudiante() != null ? 
                    prestamo.getEstudiante().getCodigoEstudiante() : "Sin código";
                
                String tituloLibro = prestamo.getLibro() != null ? 
                    prestamo.getLibro().getTitulo() : "Desconocido";
                
                modelo.addRow(new Object[]{
                    "Prestado",
                    nombreEstudiante,
                    codigoEstudiante,
                    tituloLibro,
                    "ACTIVO"
                });
            }
        }
        
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        
        // Etiqueta de cantidad
        int total = prestamos != null ? prestamos.size() : 0;
        JLabel lblInfo = new JLabel("Total préstamos activos: " + total);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 12));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblInfo, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearPanelTodo() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columnas = {"Tipo", "Detalles", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        // 1. Agregar autores
        List<Autor> autores = JSONManager.cargarLista("autores.json", Autor.class);
        for (Autor autor : autores) {
            modelo.addRow(new Object[]{
                "AUTOR",
                autor.getNombre() + " (" + autor.getNacionalidad() + ")",
                "Registrado"
            });
        }
        
        // 2. Agregar libros
        List<Libro> libros = JSONManager.cargarLista("libros.json", Libro.class);
        for (Libro libro : libros) {
            String autorNombre = libro.getAutor() != null ? 
                libro.getAutor().getNombre() : "Sin autor";
            
            modelo.addRow(new Object[]{
                "LIBRO",
                libro.getTitulo() + " - Autor: " + autorNombre,
                "Registrado"
            });
        }
        
        // 3. Agregar préstamos
        List<Prestamo> prestamos = JSONManager.cargarLista("prestamos.json", Prestamo.class);
        if (prestamos != null) {
            for (Prestamo prestamo : prestamos) {
                String estudiante = prestamo.getEstudiante() != null ? 
                    prestamo.getEstudiante().getNombre() : "Desconocido";
                
                String libro = prestamo.getLibro() != null ? 
                    prestamo.getLibro().getTitulo() : "Desconocido";
                
                modelo.addRow(new Object[]{
                    "PRÉSTAMO",
                    estudiante + " -> " + libro,
                    "Prestado"
                });
            }
        }
        
        if (modelo.getRowCount() == 0) {
            modelo.addRow(new Object[]{"-", "No hay registros", "-"});
        }
        
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        
        // Estadísticas
        int totalAutores = autores.size();
        int totalLibros = libros.size();
        int totalPrestamos = prestamos != null ? prestamos.size() : 0;
        
        JLabel lblStats = new JLabel(
            "📊 Total: " + (totalAutores + totalLibros + totalPrestamos) + 
            " registros | Autores: " + totalAutores + 
            " | Libros: " + totalLibros + 
            " | Préstamos: " + totalPrestamos
        );
        lblStats.setFont(new Font("Arial", Font.BOLD, 12));
        lblStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(lblStats, BorderLayout.SOUTH);
        
        return panel;
    }
}