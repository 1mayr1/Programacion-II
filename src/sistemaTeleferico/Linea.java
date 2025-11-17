package sistemaTeleferico;
import java.util.ArrayList;
import java.util.List;

public class Linea {
    private String color;
    private List<Persona> filaPersonas;
    private List<Cabina> cabinas;
    private int cantidadCabinas;

    public Linea(String color) {
        this.color = color;
        this.filaPersonas= new ArrayList();
        this.cabinas=new ArrayList();
        this.cantidadCabinas=0;
        
    }
    public void agregarPersonaFila(Persona p){
        filaPersonas.add(p);
    }
    public void agregarCabina(Cabina cabina){
        cabinas.add(cabina);
        cantidadCabinas++;
    }

    public String getColor() {
        return color;
    }
    public List<Persona> getFilaPersona() {
        return filaPersonas;
    }
    public List<Cabina> getCabinas() {
        return cabinas;
    }
    public int getCantidadCabinas() {
        return cantidadCabinas;
    }
    
}
