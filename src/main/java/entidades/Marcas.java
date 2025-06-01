
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
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
@Table(name = "marcas")
@NamedQueries({
    @NamedQuery(name = "marcas.findAll", query = "SELECT p FROM marcas p"),
    @NamedQuery(name = "marcas.findById", query = "SELECT p FROM marcas p WHERE p.idMarca = :idMarca"),
    @NamedQuery(name = "marcas.findByNombre", query = "SELECT p FROM marcas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "marcas.findByPais", query = "SELECT p FROM marcas p WHERE p.paisOrigen = :paisOrigen"),
    @NamedQuery(name = "marcas.findByFundacion", query = "SELECT p FROM marcas p WHERE p.fundacion = :fundacion")})
public class Marcas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarca")
    private Integer idMarca;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "paisOrigen")
    private String paisOrigen;
    @Column(name = "fundacion")
    private Year fundacion;
    
    // Se omite la relación de Producto hacia detalleVenta para que no sea
    // bidireccional, simplemente con fines didácticos para que comprobéis
    // que no siempre es obligatorio que las relaciones sean en ambas direcciones
    // Al comantar esta relación, no se cargarían los detalleVenta asociados
    // a este producto
    //    @OneToMany(mappedBy = "idproducto")
    //    private Collection<Detalleventa> detalleventaCollection;
    
    public Marcas() {
    }

    public Marcas(Integer id) {
        this.idMarca = id;
    }

    public Marcas(String nombre, String paisOrigen, Year fundacion) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.fundacion = fundacion;
//        this.detalleventaCollection = new ArrayList<>();
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Year getFundacion() {
        return fundacion;
    }

    public void setFundacion(Year fundacion) {
        this.fundacion = fundacion;
    }

    

//    public Collection<Detalleventa> getDetalleventaCollection() {
//        return detalleventaCollection;
//    }
//
//    public void setDetalleventaCollection(Collection<Detalleventa> detalleventaCollection) {
//        this.detalleventaCollection = detalleventaCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Marcas)) {
            return false;
        }
        Marcas other = (Marcas) object;
        return !((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca)));
    }

    @Override
    public String toString() {
        return "Marcas{" + "idMarca=" + idMarca + ", nombre=" + nombre + ", paisOrigen=" + paisOrigen + ", fundacion=" + fundacion + '}';
    }

   

}
