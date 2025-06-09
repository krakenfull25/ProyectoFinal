/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Julen García
 */
@Entity
@Table(name = "marcas")
@NamedQueries({
    @NamedQuery(name = "Marcas.findAll", query = "SELECT m FROM Marcas m"),
    @NamedQuery(name = "Marcas.findByIdMarca", query = "SELECT m FROM Marcas m WHERE m.idMarca = :idMarca"),
    @NamedQuery(name = "Marcas.findByNombre", query = "SELECT m FROM Marcas m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Marcas.findByPaisOrigen", query = "SELECT m FROM Marcas m WHERE m.paisOrigen = :paisOrigen"),
    @NamedQuery(name = "Marcas.findByFundacion", query = "SELECT m FROM Marcas m WHERE m.fundacion = :fundacion")})
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
    @Temporal(TemporalType.DATE)
    private Date fundacion;
    @OneToMany(mappedBy = "idMarca")
    private Collection<Coches> cochesCollection;

    public Marcas() {
    }

    public Marcas(Integer idMarca) {
        this.idMarca = idMarca;
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

    public Date getFundacion() {
        return fundacion;
    }

    public void setFundacion(Date fundacion) {
        this.fundacion = fundacion;
    }

    public Collection<Coches> getCochesCollection() {
        return cochesCollection;
    }

    public void setCochesCollection(Collection<Coches> cochesCollection) {
        this.cochesCollection = cochesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcas)) {
            return false;
        }
        Marcas other = (Marcas) object;
        if ((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Marcas[ idMarca=" + idMarca + " ]";
    }
    
}
