/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
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
 * @author Julen García
 */
@Entity
@Table(name = "cocheaccesorio")
@NamedQueries({
    @NamedQuery(name = "Cocheaccesorio.findAll", query = "SELECT c FROM Cocheaccesorio c"),
    @NamedQuery(name = "Cocheaccesorio.findByIdRegistro", query = "SELECT c FROM Cocheaccesorio c WHERE c.idRegistro = :idRegistro")})
public class Cocheaccesorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRegistro")
    private Integer idRegistro;
    @JoinColumn(name = "idAccesorio", referencedColumnName = "idAccesorio")
    @ManyToOne
    private Accesorios idAccesorio;
    @JoinColumn(name = "idCoche", referencedColumnName = "idCoche")
    @ManyToOne
    private Coches idCoche;

    public Cocheaccesorio() {
    }

    public Cocheaccesorio(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Accesorios getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(Accesorios idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public Coches getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(Coches idCoche) {
        this.idCoche = idCoche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cocheaccesorio)) {
            return false;
        }
        Cocheaccesorio other = (Cocheaccesorio) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cocheaccesorio[ idRegistro=" + idRegistro + " ]";
    }
    
}
