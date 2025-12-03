package sistema;
public class Autor {
    private String nombre;
    private String nacionalidad;
    
    public Autor() {
    }

    public Autor(String nombre,String nacionalidad) {
        this.nombre =nombre;
        this.nacionalidad= nacionalidad;
    }

    public void mostrarInfo() {
        System.out.println("|--------Iformacion del Autor-------|");
        System.out.println("Nombre: "+nombre);
        System.out.println("Nacionalidad: " +nacionalidad);
        System.out.println("|----------------------------------|");
    }

    public String getNombre() {
        return nombre;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Autor: "+nombre+" ("+nacionalidad+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj ==null || getClass() !=obj.getClass()) return false;

        Autor autor =(Autor) obj;
        return nombre.equals(autor.nombre) && nacionalidad.equals(autor.nacionalidad);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode() + nacionalidad.hashCode();
    }
}