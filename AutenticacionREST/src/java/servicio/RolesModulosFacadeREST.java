/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import datos.RolesModulos;
import datos.RolesModulosPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author felipe
 */
@Stateless
@Path("datos.rolesmodulos")
public class RolesModulosFacadeREST extends AbstractFacade<RolesModulos> {
    @PersistenceContext(unitName = "AutenticacionRESTPU")
    private EntityManager em;

    private RolesModulosPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;rolidRol=rolidRolValue;moduloidModulo=moduloidModuloValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        datos.RolesModulosPK key = new datos.RolesModulosPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> rolidRol = map.get("rolidRol");
        if (rolidRol != null && !rolidRol.isEmpty()) {
            key.setRolidRol(new java.lang.Integer(rolidRol.get(0)));
        }
        java.util.List<String> moduloidModulo = map.get("moduloidModulo");
        if (moduloidModulo != null && !moduloidModulo.isEmpty()) {
            key.setModuloidModulo(new java.lang.Integer(moduloidModulo.get(0)));
        }
        return key;
    }

    public RolesModulosFacadeREST() {
        super(RolesModulos.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(RolesModulos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, RolesModulos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        datos.RolesModulosPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public RolesModulos find(@PathParam("id") PathSegment id) {
        datos.RolesModulosPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<RolesModulos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<RolesModulos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
