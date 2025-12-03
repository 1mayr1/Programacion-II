package sistema;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
         VentanaPrincipal.main(args);
        
        System.out.println("BIBLIOTECA UNIVERSITARIA");
       
        List<Autor> autores = JSONManager.cargarLista("autores.json", Autor.class);
        List<Libro> libros = JSONManager.cargarLista("libros.json", Libro.class);
        List<Estudiante> estudiantes = JSONManager.cargarLista("estudiantes.json", Estudiante.class);
        
        Biblioteca biblioteca = new Biblioteca("GOOD BOOK");
        
        if (autores.isEmpty()) {
            System.out.println("Creando autores");
            autores = crearAutoresEjemplo();
        }
        
        if (libros.isEmpty()) {
            System.out.println("Creando libros");
            libros = crearLibrosEjemplo();
        }
        
        if (estudiantes.isEmpty()) {
            System.out.println("Creando estudiantes");
            estudiantes = crearEstudiantesEjemplo();
        }
        
        System.out.println("ACTUALIZANDO BIBLIOTECA");
        for (Autor autor : autores) {
            biblioteca.agregarAutor(autor);
        }
        
        for (Libro libro : libros) {
            biblioteca.agregarLibro(libro);
        }
        
        biblioteca.setHorario("08:00", "20:00", "Lunes a Viernes");
 
        System.out.println(" ESTADO INICIAL DE LA BIBLIOTECA:");
        biblioteca.mostrarEstado();
        
        System.out.println(" INFORMACION DE AUTORES:");
        if (!autores.isEmpty()) {
            autores.get(0).mostrarInfo();
        }
        
        System.out.println("INFORMACION DE ESTUDIANTES:");
        if (!estudiantes.isEmpty()) {
            estudiantes.get(0).mostrarInfo();
        }
        
        System.out.println("REALIZANDO PRESTAMO DE EJEMPLO...");
        if (!libros.isEmpty() && !estudiantes.isEmpty()) {
            biblioteca.prestarLibro(estudiantes.get(0), libros.get(0));
        }
        
        System.out.println("ESTADO DESPUES DEL PRESTAMO:");
        biblioteca.mostrarEstado();
        
   
        System.out.println("DETALLES DEL PRESTAMO:");
        if (!biblioteca.getPrestamosActivos().isEmpty()) {
            biblioteca.getPrestamosActivos().get(0).mostrarInfo();
        }
        
      
        JSONManager.guardarTodo(biblioteca, estudiantes);
        
        System.out.println("MOSTRANDO CONTENIDO DE UN ARCHIVO");
        JSONManager.mostrarContenidoJSON("libros.json");
        
        System.out.println("PROGRAMA FINALIZADO");
        System.out.println("Los datos han sido guardados en archivos JSON.");
        System.out.println("Puedes abrirlos con cualquier editor de texto.");
    }
  
    private static List<Autor> crearAutoresEjemplo() {
        List<Autor> autores = new ArrayList<>();
        autores.add(new Autor("Taylor Jenkins", "Estadounidense"));
        autores.add(new Autor("Isabel Allende", "Chilena"));
        autores.add(new Autor("Stephen King", "Estadounidense"));
        return autores;
    }
    
    private static List<Libro> crearLibrosEjemplo() {
        List<Libro> libros = new ArrayList<>();
        
        // Libro 1
        List<String> paginas1 = Arrays.asList(
            "Evelyn Hugo, el icono de Hollywood que se ha recluido,decide contar la verdad sobre su vida..."
        );
        libros.add(new Libro("Los Siete Maridos de Evelin Hugo", "978-1234456555", paginas1));
        
        // Libro 2
        List<String> paginas2 = Arrays.asList(
            "En una carta dirigida a una persona a la que ama por encima de todas las demás...."
        );
        libros.add(new Libro("Violeta", "978-1234567890", paginas2));
        
         List<String> paginas3 = Arrays.asList(
            "un grupo de siete amigos en el pueblo de Derry, Maine"+
             "luchan contra una antigua entidad sobrenatural..."
        );
        libros.add(new Libro("IT", "978-125688882", paginas3));
        
        return libros;
        
        
    }
    
    private static List<Estudiante> crearEstudiantesEjemplo() {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("2023001", "Sofia Lopez"));
        estudiantes.add(new Estudiante("2023002", "Carlos Rodriguez"));
        estudiantes.add(new Estudiante("2023003", "Ana Martinez"));
        return estudiantes;
    }
}