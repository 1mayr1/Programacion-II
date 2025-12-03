package sistema;

public class Autor {
    private String nombre;
    private String nacionalidad;
    
    // Constructores
    public Autor() {
    }
    
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public void mostrarInfo(){
        System.out.println("Autor:"+nombre+"("+nacionalidad+")");
    }
    @Override
    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}