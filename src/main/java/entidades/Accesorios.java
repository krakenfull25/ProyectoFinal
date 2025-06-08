/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Julen García
 */
@Entity
@Table(name = "accesorios")
@NamedQueries({
    @NamedQuery(name = "Accesorios.findAll", query = "SELECT a FROM Accesorios a"),
    @NamedQuery(name = "Accesorios.findByIdAccesorio", query = "SELECT a FROM Accesorios a WHERE a.idAccesorio = :idAccesorio"),
    @NamedQuery(name = "Accesorios.findByNombre", query = "SELECT a FROM Accesorios a WHERE a.nombre = :nombre")})
public class Accesorios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccesorio")
    private Integer idAccesorio;
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToMany(mappedBy = "accesoriosCollection")
    private Collection<Coches> cochesCollection;

    public Accesorios() {
    }

    public Accesorios(Integer idAccesorio) {
        this.idAccesorio = idAccesorio;
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

    public Collection<Coches> getCochesCollection() {
        return cochesCollection;
    }

    public void setCochesCollection(Collection<Coches> cochesCollection) {
        this.cochesCollection = cochesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccesorio != null ? idAccesorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accesorios)) {
            return false;
        }
        Accesorios other = (Accesorios) object;
        if ((this.idAccesorio == null && other.idAccesorio != null) || (this.idAccesorio != null && !this.idAccesorio.equals(other.idAccesorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Accesorios[ idAccesorio=" + idAccesorio + " ]";
    }
    
}
