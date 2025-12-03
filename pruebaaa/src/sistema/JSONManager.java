package sistema;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONManager {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    
    public static <T> void guardarLista(String nombreArchivo, List<T> lista) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(lista, writer);
            System.out.println(" Datos guardados: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public static <T> List<T> cargarLista(String nombreArchivo, Class<T> clase) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.out.println("Archivo no existe: " + nombreArchivo + " - Se creará uno nuevo");
            return new ArrayList<>();
        }
        
        try (FileReader reader = new FileReader(nombreArchivo)) {
            Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();
            List<T> lista = gson.fromJson(reader, tipoLista);
            
            if (lista == null) {
                return new ArrayList<>();
            }
            
            System.out.println("✅ " +lista.size() + " registros cargados de: " + nombreArchivo);
            return lista;
            
        } catch (IOException e) {
            System.err.println("❌ Error al cargar " + nombreArchivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Biblioteca cargarBibliotecaCompleta() {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        
        List<Autor> autores = cargarLista("autores.json", Autor.class);
        for (Autor autor : autores) {
            biblioteca.agregarAutor(autor);
        }
        
        List<Libro> libros = cargarLista("libros.json", Libro.class);
        for (Libro libro : libros) {
            biblioteca.agregarLibro(libro);
        }
        
       
        List<Estudiante> estudiantes = cargarLista("estudiantes.json", Estudiante.class);
        
        List<Prestamo> prestamos = cargarLista("prestamos.json", Prestamo.class);
        
        return biblioteca;
    }
    
    public static void guardarTodo(Biblioteca biblioteca, List<Estudiante> estudiantes) {
        System.out.println("GUARDANDO DATOS");
        
        guardarLista("autores.json", biblioteca.getAutorRegistrados());
        guardarLista("libros.json", biblioteca.getLibrosDisponibles());
        guardarLista("estudiantes.json", estudiantes);
        guardarLista("prestamos.json", biblioteca.getPrestamosActivos());
        
        System.out.println("Todos los datos han sido guardados");
    }
    

    public static void mostrarContenidoJSON(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no existe");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            System.out.println("\n=== CONTENIDO DE " + nombreArchivo + " ===");
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("================================\n");
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }
}