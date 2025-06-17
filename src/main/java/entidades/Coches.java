/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
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
 * @author Julen García
 */
@Entity
@Table(name = "coches")
@NamedQueries({
    @NamedQuery(name = "Coches.findAll", query = "SELECT c FROM Coches c"),
    @NamedQuery(name = "Coches.findByIdCoche", query = "SELECT c FROM Coches c WHERE c.idCoche = :idCoche"),
    @NamedQuery(name = "Coches.findByModelo", query = "SELECT c FROM Coches c WHERE c.modelo = :modelo"),
    @NamedQuery(name = "Coches.findByAnio", query = "SELECT c FROM Coches c WHERE c.anio = :anio"),
    @NamedQuery(name = "Coches.findByPrecio", query = "SELECT c FROM Coches c WHERE c.precio = :precio"),
    @NamedQuery(name = "Coches.findByTipoMotor", query = "SELECT c FROM Coches c WHERE c.tipoMotor = :tipoMotor")})
public class Coches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCoche")
    private Integer idCoche;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anio")

    private Integer anio;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "tipoMotor")
    private String tipoMotor;
    @OneToMany(mappedBy = "idCoche", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Cocheaccesorio> cocheaccesorioCollection;
    @JoinColumn(name = "idMarca", referencedColumnName = "idMarca")
    @ManyToOne
    private Marcas idMarca;

    public Coches() {
    }

    public Coches(String modelo, Integer anio, Double precio, String tipoMotor, Marcas idMarca) {
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.tipoMotor = tipoMotor;
        this.idMarca = idMarca;
    }

    public Coches(Integer idCoche, String modelo, Integer anio, Double precio, String tipoMotor, Marcas idMarca) {
        this.idCoche = idCoche;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.tipoMotor = tipoMotor;
        this.idMarca = idMarca;
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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public Collection<Cocheaccesorio> getCocheaccesorioCollection() {
        return cocheaccesorioCollection;
    }

    public void setCocheaccesorioCollection(Collection<Cocheaccesorio> cocheaccesorioCollection) {
        this.cocheaccesorioCollection = cocheaccesorioCollection;
    }

    public Marcas getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marcas idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoche != null ? idCoche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coches)) {
            return false;
        }
        Coches other = (Coches) object;
        if ((this.idCoche == null && other.idCoche != null) || (this.idCoche != null && !this.idCoche.equals(other.idCoche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coches{" + "idCoche=" + idCoche + ", modelo=" + modelo + ", anio=" + anio + ", precio=" + precio + ", tipoMotor=" + tipoMotor + ", idMarca=" + idMarca + '}';
    }

    

}
