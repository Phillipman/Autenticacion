/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "Opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByIdOpcion", query = "SELECT o FROM Opcion o WHERE o.idOpcion = :idOpcion"),
    @NamedQuery(name = "Opcion.findByNombre", query = "SELECT o FROM Opcion o WHERE o.nombre = :nombre")})
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOpcion")
    private Integer idOpcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "RolesModulosOpciones", joinColumns = {
        @JoinColumn(name = "Opcion_idOpcion", referencedColumnName = "idOpcion")}, inverseJoinColumns = {
        @JoinColumn(name = "RolesModulos_Rol_idRol", referencedColumnName = "Rol_idRol"),
        @JoinColumn(name = "RolesModulos_Modulo_idModulo", referencedColumnName = "Modulo_idModulo")})
    @ManyToMany
    private Collection<RolesModulos> rolesModulosCollection;
    @JoinColumn(name = "Modulo_idModulo", referencedColumnName = "idModulo")
    @ManyToOne(optional = false)
    private Modulo moduloidModulo;

    public Opcion() {
    }

    public Opcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Opcion(Integer idOpcion, String nombre) {
        this.idOpcion = idOpcion;
        this.nombre = nombre;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<RolesModulos> getRolesModulosCollection() {
        return rolesModulosCollection;
    }

    public void setRolesModulosCollection(Collection<RolesModulos> rolesModulosCollection) {
        this.rolesModulosCollection = rolesModulosCollection;
    }

    public Modulo getModuloidModulo() {
        return moduloidModulo;
    }

    public void setModuloidModulo(Modulo moduloidModulo) {
        this.moduloidModulo = moduloidModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Opcion[ idOpcion=" + idOpcion + " ]";
    }
    
}
