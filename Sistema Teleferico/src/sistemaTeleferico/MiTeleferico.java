package sistemaTeleferico;

import java.util.ArrayList;
import java.util.List;

public class MiTeleferico {
    private Linea[] lineas;
    private float cantidadIngresos;

    public MiTeleferico(List<Linea[]> linea, float cantidadIngresos) {
        this.lineas =new Linea[3];
        this.cantidadIngresos = 0;
        
        lineas[0]=new Linea("Amarillo");
        lineas[1]=new Linea("Roja");
        lineas[2]=new Linea("Verde");
    }
    
    public void agregarPersonaFila(Persona p, String colorLinea){
        for (Linea linea: lineas){
            if(linea.getColor().equalsIgnoreCase(colorLinea)){
                linea.agregarPersonaFila(p);
            }
        }
    }
    public void calcularIngresosTotales() {
        cantidadIngresos = 0;
        for (Linea linea : lineas) {
            for (Cabina cabina : linea.getCabinas()) {
                for (Persona persona : cabina.getPersonasAbordo()) {
                    if (persona.getEdad() <= 25 || persona.getEdad() >= 60) {
                        cantidadIngresos += 1.5;
                    } else {
                        cantidadIngresos += 3.0;
                    }
                }
            }
        }
    }
    public Linea[] getLineas(){
        return lineas;
    } 
    public float getCantidadIngresos(){
        return cantidadIngresos;

    }
}

