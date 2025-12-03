package sistema;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombreBibli;
    private ArrayList<Libro> librosDisponibles ;
    private ArrayList<Autor> autorRegistrados;
    private ArrayList<Prestamo> prestamosActivos;
    private HorarioAtencion horario;


    public static class HorarioAtencion {
        private String apertura;
        private String cierre;
        private String diasAtencion;
        
       
        public HorarioAtencion() {
        }

        public HorarioAtencion(String apertura, String cierre, String diasAtencion){
            this.apertura=apertura;
            this.cierre=cierre;
            this.diasAtencion=diasAtencion;
        }
        
        public String getApertura() { 
            return apertura; 
        }
        public void setApertura(String apertura) { 
            this.apertura = apertura; 
        }
        
        public String getCierre() { 
            return cierre; 
        }
        public void setCierre(String cierre) { 
            this.cierre = cierre; 
        }
        
        public String getDiasAtencion() { 
            return diasAtencion; 
        }
        public void setDiasAtencion(String diasAtencion) { 
            this.diasAtencion = diasAtencion; 
        }
        
        @Override
        public String toString() {
            return "Horario: "+apertura+"-"+ cierre+", Dias de atencion: "+diasAtencion;
        }
    }
    
     public Biblioteca() {
        this.librosDisponibles =new ArrayList<>();
        this.autorRegistrados =new ArrayList<>();
        this.prestamosActivos =new ArrayList<>();
    }

    public Biblioteca(String nombreBibli) {
        this.nombreBibli =nombreBibli;
        this.librosDisponibles =new ArrayList<>();
        this.autorRegistrados =new ArrayList<>();
        this.prestamosActivos =new ArrayList<>();
    }
    public void agregarLibro(Libro libro) {
        if (libro != null && !librosDisponibles.contains(libro)) {
            librosDisponibles.add(libro);
            System.out.println("Libro agregado: " +libro.getTitulo());
        }
    }
    public void agregarAutor(Autor autor){
        if (autor != null && !autorRegistrados.contains(autor)) {
            autorRegistrados.add(autor);
            System.out.println("Autor registrado: " +autor.getNombre());
        }
    }

    public void prestarLibro(Estudiante estudiante, Libro libro) {
        if (estudiante != null && libro != null && librosDisponibles.contains(libro)) {
            Prestamo prestamo = new Prestamo(estudiante, libro);
            prestamosActivos.add(prestamo);
            librosDisponibles.remove(libro);
            System.out.println("Prestamo realizado: "+libro.getTitulo()+" al "+estudiante.getNombre());
        } else {
            System.out.println("No se puede realizar el prestamo. Verifique los datos.");
        }
    }

    public void mostrarEstado() {
        System.out.println("|--------Estado de la biblioteca "+nombreBibli +"---------|");
        System.out.println("Libros disponibles: "+librosDisponibles.size());
        for (Libro libro :librosDisponibles) {
            System.out.println("-" + libro.getTitulo());
        }

        System.out.println("|--Autores registrados: "+autorRegistrados.size());
        for (Autor autor:autorRegistrados) {
            System.out.println("-"+autor.getNombre());
        }

        System.out.println("|--Prestamos activos: "+prestamosActivos.size());
        for (Prestamo prestamo:prestamosActivos) {
            System.out.println("-"+prestamo.getLibro().getTitulo()+" prestado a "+prestamo.getEstudiante().getNombre());
        }

        if (horario != null) {
            System.out.println("\n" + horario.toString());
        }
        System.out.println("|-------------------------------------------|");
    }

    public void cerrarBiblioteca() {
        System.out.println("La biblioteca "+nombreBibli+" esta cerrando");
        System.out.println("Todos los prestamos activos dejo de existir");
        prestamosActivos.clear();
    }


    public String getNombreBibli() {
        return nombreBibli;
    }
    public void setNombreBibli(String nombreBibli) {
        this.nombreBibli = nombreBibli;
    }
    public List<Libro> getLibrosDisponibles() {
        return new ArrayList<>(librosDisponibles);
    }
    public List<Autor> getAutorRegistrados() {
        return new ArrayList<>(autorRegistrados);
    }
    public List<Prestamo> getPrestamosActivos() {
        return new ArrayList<>(prestamosActivos);
    }
    public HorarioAtencion getHorario() {
        return horario;
    }
    public void setHorario(HorarioAtencion horario) {
        this.horario = horario;
    }

    public void setHorario(String apertura, String cierre, String diasAtencion) {
        this.horario = new HorarioAtencion(apertura, cierre, diasAtencion);
    }
}

