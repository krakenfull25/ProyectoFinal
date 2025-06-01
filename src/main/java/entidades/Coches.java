
package entidades;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jfervic933
 */
@Entity
@Table(name = "coches")

// Las named queries son consultas predefinidas que se pueden usar
// en el código sin necesidad de escribir la consulta completa
// Están escritas en el formato JPQL (Java Persistence Query Language)
// No son objeto de estudio en nuestro curso, pero se incluyen para que
// veas que existen y que se pueden usar
// Las siguientes consultas son sencillas y fáciles de entender
@NamedQueries({
    @NamedQuery(name = "coches.findAll", query = "SELECT c FROM coches c"),
    @NamedQuery(name = "coches.findByIdCoche", query = "SELECT c FROM coches c WHERE c.idCoche = :idCoche"),
    @NamedQuery(name = "coches.findByModelo", query = "SELECT c FROM coches c WHERE c.modelo = :modelo"),
    @NamedQuery(name = "coches.findByAnio", query = "SELECT c FROM coches c WHERE c.anio = :anio"),
    @NamedQuery(name = "coches.findByPrecio", query = "SELECT c FROM coches c WHERE c.precio = :precio"),
    @NamedQuery(name = "coches.findByTipoMotor", query = "SELECT c FROM coches c WHERE c.tipoMotor = :tipoMotor")
        
})
public class Coches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCoche")
    private Integer idCoche;
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "anio")
    private Year anio;
    @Column(name = "precio")
    private double precio;
    @Column(name = "tipoMotor")
    private String tipoMotor;
    
    
    // Cada Venta tiene un atributo Cliente que realiza esa venta
    // Relación bidireccional con Venta. Esta entidad no es propietaria
    // de la relación (no tiene la clave foránea de Venta)
    @JoinColumn(name = "idMarca", referencedColumnName = "idMarca")
    @ManyToOne
    private Integer idMarca;

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Coches() {
    }

    public Coches(Integer id) {
        this.idCoche = id;
    }

    public Coches(Integer idCoche, String modelo, Year anio, double precio, String tipoMotor) {
        this.idCoche = idCoche;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.tipoMotor = tipoMotor;
        
    }

    public Integer getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(Integer idCoche) {
        this.idCoche = idCoche;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Year getAnio() {
        return anio;
    }

    public void setAnio(Year anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    

   


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoche != null ? idCoche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Coches)) {
            return false;
        }
        Coches other = (Coches) object;
        return !((this.idCoche == null && other.idCoche != null) || (this.idCoche != null && !this.idCoche.equals(other.idCoche)));
    }

    @Override
    public String toString() {
        return "Coches{" + "idCoche=" + idCoche + ", modelo=" + modelo + ", anio=" + anio + ", precio=" + precio + ", tipoMotor=" + tipoMotor + ", idMarca=" + idMarca + '}';
    }

    

    
    
    
  
}
