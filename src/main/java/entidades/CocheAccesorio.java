
package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jfervic933
 */
@Entity
@Table(name = "cocheaccesorio")
@NamedQueries({
    @NamedQuery(name = "cocheaccesorio.findAll", query = "SELECT d FROM cocheaccesorio d"),
    @NamedQuery(name = "cocheaccesorio.findByIdCoche", query = "SELECT d FROM cocheaccesorio d WHERE d.idCoche = :idCoche"),
    @NamedQuery(name = "cocheaccesorio.findByIdAccesorio", query = "SELECT d FROM cocheaccesorio d WHERE d.idAccesorio = :idAccesorio")})
public class CocheAccesorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    
    
    // En este caso DetalleVenta es la propietaria de la relación con Producto @JoinColumn
    // Esta tabla tiene la clave foránea a Producto
    @JoinColumn(name = "idCoche", referencedColumnName = "idCoche")
    @ManyToOne
    private Coches idCoche;
    
    // En este caso DetalleVenta es la propietaria de la relación con Venta @JoinColumn
    // Esta tabla tiene la clave foránea a Venta
    @JoinColumn(name = "idAccesorio", referencedColumnName = "idAccesorio")
    @ManyToOne
    private Accesorios idAccesorio;

    public CocheAccesorio() {
    }

    

    public Coches getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(Coches idCoche) {
        this.idCoche = idCoche;
    }

    public Accesorios getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(Accesorios idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idCoche);
        hash = 53 * hash + Objects.hashCode(this.idAccesorio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CocheAccesorio other = (CocheAccesorio) obj;
        if (!Objects.equals(this.idCoche, other.idCoche)) {
            return false;
        }
        return Objects.equals(this.idAccesorio, other.idAccesorio);
    }

    @Override
    public String toString() {
        return "CocheAccesorio{" + "idCoche=" + idCoche + ", idAccesorio=" + idAccesorio + '}';
    }

    
    

   

    
}
