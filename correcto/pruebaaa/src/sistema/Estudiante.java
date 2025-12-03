package sistema;
public class Estudiante {
    private String codigoEstudiante;
    private String nombre;

    public Estudiante() {
        
    }
    
    public Estudiante(String codigoEstudiante, String nombre) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombre = nombre;
    }

    public void mostrarInfo() {
        System.out.println("=== INFORMACION DEL ESTUDIANTE ===");
        System.out.println("Codigo: " + codigoEstudiante);
        System.out.println("Nombre: " + nombre);
        System.out.println("=================================");
    }

    
    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Estudiante: " + nombre + " (Codigo: " + codigoEstudiante + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Estudiante estudiante = (Estudiante) obj;
        return codigoEstudiante.equals(estudiante.codigoEstudiante);
    }

    @Override
    public int hashCode() {
        return codigoEstudiante.hashCode();
    }
}