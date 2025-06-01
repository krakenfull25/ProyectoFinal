package entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jfervic933
 */
@Entity
@Table(name = "accesorios")
@NamedQueries({
    @NamedQuery(name = "accesorios.findAll", query = "SELECT v FROM accesorios v"),
    @NamedQuery(name = "accesorios.findByNombre", query = "SELECT v FROM accesorios v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "accesorios.findByDescripcion", query = "SELECT v FROM accesorios v WHERE v.descripcion = :descripcion"),
    })
public class Accesorios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccesorio")
    private Integer idAccesorio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    

    public Accesorios() {
    }

    public Accesorios(Integer id) {
        this.idAccesorio = id;
    }

    // Este lo creo yo
    public Accesorios(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        
    }

    public Integer getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(Integer idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccesorio != null ? idAccesorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Accesorios)) {
            return false;
        }
        Accesorios other = (Accesorios) object;
        return !((this.idAccesorio == null && other.idAccesorio != null) || (this.idAccesorio != null && !this.idAccesorio.equals(other.idAccesorio)));
    }

    @Override
    public String toString() {
        return "Accesorios{" + "idAccesorio=" + idAccesorio + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

   

}
