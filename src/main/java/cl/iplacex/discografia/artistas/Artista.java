package cl.iplacex.discografia.artistas;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "artistas")
public class Artista {

    @Id
    @Field("_id")
    private String _id;

    @Field("nombre")
    private String nombre;

    @Field("estilos")
    private List<String> estilos;

    @Field("anioFundacion")
    private int anioFundacion;

    @Field("estaActivo")
    private boolean estaActivo;

    public Artista() {}

    public Artista(String _id, String nombre, List<String> estilos, int anioFundacion, boolean estaActivo) {
        this._id = _id;
        this.nombre = nombre;
        this.estilos = estilos;
        this.anioFundacion = anioFundacion;
        this.estaActivo = estaActivo;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<String> getEstilos() { return estilos; }
    public void setEstilos(List<String> estilos) { this.estilos = estilos; }

    public int getAnioFundacion() { return anioFundacion; }
    public void setAnioFundacion(int anioFundacion) { this.anioFundacion = anioFundacion; }

    public boolean isEstaActivo() { return estaActivo; }
    public void setEstaActivo(boolean estaActivo) { this.estaActivo = estaActivo; }
}
