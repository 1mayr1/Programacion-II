package sistema;
import java.util.ArrayList;
import java.util.List;
public class Libro {
    private String titulo;
    private String isbn;
    private List<Pagina> paginas;


    public static class Pagina {
        private int numeroPag;
        private String contenido;

         public Pagina() {
        }
         
        public Pagina(int numeroPag, String contenido) {
            this.numeroPag =numeroPag;
            this.contenido =contenido;
        }

        public int getNumeroPag() {
            return numeroPag;
        }
         public void setNumeroPag(int numeroPag) { 
             this.numeroPag = numeroPag; 
         }

        public String getContenido() {
            return contenido;
        }
        public void setContenido(String contenido) { 
            this.contenido = contenido; 
        }

        @Override
        public String toString() {
            return "Pagina "+numeroPag+": "+contenido;
        }
    }

     public Libro() {
        this.paginas = new ArrayList<>();
    }

    public Libro(String titulo,String isbn, List<String>contenidoPaginas) {
        this.titulo =titulo;
        this.isbn =isbn;
        this.paginas =new ArrayList<>();

        if (contenidoPaginas!=null) {
            for (int i=0; i<contenidoPaginas.size(); i++) {
                Pagina pagina =new Pagina(i+1, contenidoPaginas.get(i));
                this.paginas.add(pagina);
            }
        }
    }


    public void leer() {
        System.out.println("|-----Leyendo el libro "+titulo+"-------|");
        System.out.println("ISBN: "+ isbn);
        System.out.println("Total de paginas: "+paginas.size());
        System.out.println("|-------------------------------------|");

        for (Pagina pagina:paginas) {
            System.out.println(pagina.toString());
        }
        System.out.println("|----------FIN---------|");
    }

    public void agregarPagina(String contenido) {
        int nuevoNumero=paginas.size() + 1;
        Pagina nuevaPagina=new Pagina(nuevoNumero, contenido);
        paginas.add(nuevaPagina);
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
    }
    public int getTotalPaginas() {
        return paginas.size();
    }
    public void setPaginas(List<Pagina> paginas) { 
        this.paginas = paginas; 
    }

    public List<Pagina> getPaginas() {
        return new ArrayList<>(paginas);
    }

    @Override
    public String toString() {
        return "Libro: "+titulo+" ,ISBN: "+isbn+", Paginas: "+paginas.size();
    }
}