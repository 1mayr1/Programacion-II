package sistemaTeleferico;
import java.util.ArrayList;
import java.util.List;

public class Cabina {
    private int nroCabina;
    private List<Persona> personasAbordo;
    private static final int MAX_PERSONAS=10;
    private static final float PESO_MAXIMO=850.0F;

    public Cabina(int nroCabina) {
        this.nroCabina=nroCabina;
        this.personasAbordo=new ArrayList();
    }
    
    public boolean agregarPersona(Persona p){
        if (personasAbordo.isEmpty()){
            if(p.getPesoPersona()<=PESO_MAXIMO){
                personasAbordo.add(p);
                return true;
            }
        }
        return false;
    }
    
    
}
