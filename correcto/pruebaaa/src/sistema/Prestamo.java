package sistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {
    private String fechaPrestamo;  
    private String fechaDevolucion; 
    private Estudiante estudiante;
    private Libro libro;
    
    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now().toString(); // Guardar como String
        this.fechaDevolucion = null;
    }
    
    public void mostrarInfo() {
        System.out.println("|-------Detalles del Prestamo------|");
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("Codigo: " + estudiante.getCodigoEstudiante());
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Fecha de prestamo: " + formatearFecha(fechaPrestamo));
        
        if (fechaDevolucion != null) {
            System.out.println("Fecha de devolucion: " + formatearFecha(fechaDevolucion));
        } else {
            System.out.println("Fecha de devolucion: PENDIENTE");
        }
        System.out.println("|---------------------------------------|");
    }
    
    public void devolverLibro() {
        this.fechaDevolucion = LocalDate.now().toString(); // Guardar como String
        System.out.println("Libro devuelto: " + libro.getTitulo());
    }
    
    private String formatearFecha(String fechaStr) {
        if (fechaStr == null) return "PENDIENTE";
        
        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return fecha.format(formatter);
        } catch (Exception e) {
            return fechaStr;
        }
    }
    
    public String getFechaPrestamo() { 
        return fechaPrestamo;
    }
    public void setFechaPrestamo(String fechaPrestamo) { 
        this.fechaPrestamo = fechaPrestamo; 
    }
    
    public String getFechaDevolucion() { 
        return fechaDevolucion; 
    }
    public void setFechaDevolucion(String fechaDevolucion) { 
        this.fechaDevolucion = fechaDevolucion; 
    }
    
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { 
        this.estudiante = estudiante;
    }
    
    public Libro getLibro() { 
        return libro; 
    }
    public void setLibro(Libro libro) { 
        this.libro = libro; 
    }
    
    public LocalDate getFechaPrestamoAsLocalDate() {
        return fechaPrestamo != null ? LocalDate.parse(fechaPrestamo) : null;
    }
    
    public LocalDate getFechaDevolucionAsLocalDate() {
        return fechaDevolucion != null ? LocalDate.parse(fechaDevolucion) : null;
    }
    
    @Override
    public String toString() {
        String estado = (fechaDevolucion != null) ? "Devuelto" : "activo";
        return "Prestamo [" + estado + "]-" + libro.getTitulo() + " a " + estudiante.getNombre();
    }
}