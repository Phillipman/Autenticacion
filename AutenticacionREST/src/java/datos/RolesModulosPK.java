/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author felipe
 */
@Embeddable
public class RolesModulosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rol_idRol")
    private int rolidRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Modulo_idModulo")
    private int moduloidModulo;

    public RolesModulosPK() {
    }

    public RolesModulosPK(int rolidRol, int moduloidModulo) {
        this.rolidRol = rolidRol;
        this.moduloidModulo = moduloidModulo;
    }

    public int getRolidRol() {
        return rolidRol;
    }

    public void setRolidRol(int rolidRol) {
        this.rolidRol = rolidRol;
    }

    public int getModuloidModulo() {
        return moduloidModulo;
    }

    public void setModuloidModulo(int moduloidModulo) {
        this.moduloidModulo = moduloidModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rolidRol;
        hash += (int) moduloidModulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesModulosPK)) {
            return false;
        }
        RolesModulosPK other = (RolesModulosPK) object;
        if (this.rolidRol != other.rolidRol) {
            return false;
        }
        if (this.moduloidModulo != other.moduloidModulo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.RolesModulosPK[ rolidRol=" + rolidRol + ", moduloidModulo=" + moduloidModulo + " ]";
    }
    
}
