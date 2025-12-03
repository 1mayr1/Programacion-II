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
            libros = crearLibrosEjemplo(autores);
        } else {
            System.out.println("Verificando si los libros tienen autores...");
            verificarAutoresEnLibros(libros, autores);
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
        
        System.out.println("\n📚 LIBROS Y SUS AUTORES:");
        for (Libro libro : libros) {
            String autorNombre = (libro.getAutor() != null) ? 
                libro.getAutor().getNombre() : "Sin autor asignado";
            System.out.println("   - " + libro.getTitulo() + " -> " + autorNombre);
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
    
    private static List<Libro> crearLibrosEjemplo(List<Autor> autores) {
        List<Libro> libros = new ArrayList<>();
        
        if (autores == null || autores.isEmpty()) {
            System.out.println("⚠️  No hay autores para asignar a los libros");
            return crearLibrosSinAutores();
        }
        
        System.out.println("Asignando autores a los libros...");
        
       
        List<String> paginas1 = Arrays.asList(
            "Evelyn Hugo, el icono de Hollywood que se ha recluido, decide contar la verdad sobre su vida..."
        );
        Libro libro1 = new Libro("Los Siete Maridos de Evelyn Hugo", "978-1234456555", paginas1);
        if (autores.size() > 0) {
            libro1.setAutor(autores.get(0)); 
            System.out.println("   ✅ '" + libro1.getTitulo() + "' -> " + autores.get(0).getNombre());
        }
        libros.add(libro1);
        
        
        List<String> paginas2 = Arrays.asList(
            "En una carta dirigida a una persona a la que ama por encima de todas las demás...."
        );
        Libro libro2 = new Libro("Violeta", "978-1234567890", paginas2);
        if (autores.size() > 1) {
            libro2.setAutor(autores.get(1)); // Isabel Allende
            System.out.println("   ✅ '" + libro2.getTitulo() + "' -> " + autores.get(1).getNombre());
        }
        libros.add(libro2);
        
      
        List<String> paginas3 = Arrays.asList(
            "Un grupo de siete amigos en el pueblo de Derry, Maine " +
            "luchan contra una antigua entidad sobrenatural..."
        );
        Libro libro3 = new Libro("IT", "978-125688882", paginas3);
        if (autores.size() > 2) {
            libro3.setAutor(autores.get(2)); // Stephen King
            System.out.println("   ✅ '" + libro3.getTitulo() + "' -> " + autores.get(2).getNombre());
        }
        libros.add(libro3);
        
        return libros;
    }
    
  
    private static List<Libro> crearLibrosSinAutores() {
        List<Libro> libros = new ArrayList<>();
        
        List<String> paginas1 = Arrays.asList(
            "Evelyn Hugo, el icono de Hollywood que se ha recluido, decide contar la verdad sobre su vida..."
        );
        libros.add(new Libro("Los Siete Maridos de Evelyn Hugo", "978-1234456555", paginas1));
        
        List<String> paginas2 = Arrays.asList(
            "En una carta dirigida a una persona a la que ama por encima de todas las demás...."
        );
        libros.add(new Libro("Violeta", "978-1234567890", paginas2));
        
        List<String> paginas3 = Arrays.asList(
            "Un grupo de siete amigos en el pueblo de Derry, Maine " +
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
    

    private static void verificarAutoresEnLibros(List<Libro> libros, List<Autor> autores) {
        int conAutor = 0;
        int sinAutor = 0;
        
        System.out.println("\n🔍 VERIFICACIÓN DE AUTORES EN LIBROS:");
        for (Libro libro : libros) {
            if (libro.getAutor() != null) {
                conAutor++;
                System.out.println("   ✅ " + libro.getTitulo() + " -> " + libro.getAutor().getNombre());
            } else {
                sinAutor++;
                System.out.println("   ❌ " + libro.getTitulo() + " -> SIN AUTOR");
                
               
                asignarAutorPorTitulo(libro, autores);
            }
        }
        
        System.out.println("RESUMEN: " + conAutor + " con autor, " + sinAutor + " sin autor");
    }
    
    // Método para intentar asignar autor basado en palabras clave del título
    private static void asignarAutorPorTitulo(Libro libro, List<Autor> autores) {
        if (autores == null || autores.isEmpty()) return;
        
        String titulo = libro.getTitulo().toLowerCase();
        
        for (Autor autor : autores) {
            String nombreAutor = autor.getNombre().toLowerCase();
            
           
            if (autor.getNombre().equals("Taylor Jenkins") && 
                (titulo.contains("evelyn") || titulo.contains("hugo"))) {
                libro.setAutor(autor);
                System.out.println("      Asignado automáticamente a: " + autor.getNombre());
                return;
            }
            if (autor.getNombre().equals("Isabel Allende") && 
                titulo.contains("violeta")) {
                libro.setAutor(autor);
                System.out.println("      ➡️ Asignado automáticamente a: " + autor.getNombre());
                return;
            }
            if (autor.getNombre().equals("Stephen King") && 
                titulo.contains("it")) {
                libro.setAutor(autor);
                System.out.println("      ➡️ Asignado automáticamente a: " + autor.getNombre());
                return;
            }
        }
    }
}