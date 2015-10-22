/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felipe
 */
@Entity
@Table(name = "RolesModulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesModulos.findAll", query = "SELECT r FROM RolesModulos r"),
    @NamedQuery(name = "RolesModulos.findByRolidRol", query = "SELECT r FROM RolesModulos r WHERE r.rolesModulosPK.rolidRol = :rolidRol"),
    @NamedQuery(name = "RolesModulos.findByModuloidModulo", query = "SELECT r FROM RolesModulos r WHERE r.rolesModulosPK.moduloidModulo = :moduloidModulo")})
public class RolesModulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesModulosPK rolesModulosPK;
    @ManyToMany(mappedBy = "rolesModulosCollection")
    private Collection<Opcion> opcionCollection;
    @JoinColumn(name = "Modulo_idModulo", referencedColumnName = "idModulo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Modulo modulo;
    @JoinColumn(name = "Rol_idRol", referencedColumnName = "idRol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public RolesModulos() {
    }

    public RolesModulos(RolesModulosPK rolesModulosPK) {
        this.rolesModulosPK = rolesModulosPK;
    }

    public RolesModulos(int rolidRol, int moduloidModulo) {
        this.rolesModulosPK = new RolesModulosPK(rolidRol, moduloidModulo);
    }

    public RolesModulosPK getRolesModulosPK() {
        return rolesModulosPK;
    }

    public void setRolesModulosPK(RolesModulosPK rolesModulosPK) {
        this.rolesModulosPK = rolesModulosPK;
    }

    @XmlTransient
    public Collection<Opcion> getOpcionCollection() {
        return opcionCollection;
    }

    public void setOpcionCollection(Collection<Opcion> opcionCollection) {
        this.opcionCollection = opcionCollection;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesModulosPK != null ? rolesModulosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesModulos)) {
            return false;
        }
        RolesModulos other = (RolesModulos) object;
        if ((this.rolesModulosPK == null && other.rolesModulosPK != null) || (this.rolesModulosPK != null && !this.rolesModulosPK.equals(other.rolesModulosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.RolesModulos[ rolesModulosPK=" + rolesModulosPK + " ]";
    }
    
}
