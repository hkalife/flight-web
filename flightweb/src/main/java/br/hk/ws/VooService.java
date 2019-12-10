package br.hk.ws;

import entity.Voo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Henrique
 */
@Path("/voo")
@Stateless
public class VooService {
    
    @PersistenceContext(unitName="flightweb")
    private EntityManager em;
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Voo create(@FormParam("destino") String destino, @FormParam("origem") String origem) {
        Voo voo = new Voo();
        voo.setDestino(destino);
        voo.setOrigem(origem);
        em.persist(voo);
        return voo;
    }
    
    @GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public String read() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
	CriteriaQuery<Voo> criteria = builder.createQuery(Voo.class);
	Root<Voo> voo = criteria.from(Voo.class);
	criteria.select(voo);
	List<Voo> v = em.createQuery(criteria).getResultList();
	return generateJson(v);
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Voo update(@FormParam("id") long id, @FormParam("destino") String destino, @FormParam("origem") String origem) {
        Voo voo = em.find(Voo.class, id);
        voo.setDestino(destino);
        voo.setOrigem(origem);
	return em.merge(voo);
    }
    
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") long id) {
	Voo voo = em.find(Voo.class, id);
	em.remove(voo);
	StringBuilder json = new StringBuilder();
	json.append("{\"result\":\"true\", \"id\":\"" + id + "\"}");
        return json.toString();
    }
    
    private <T> String generateJson(List<T> pojo) {
	ObjectMapper mapper = new ObjectMapper();
	String json = null;
	try {
            json = mapper.writeValueAsString(pojo);
	} catch (JsonProcessingException e) {
            e.printStackTrace();
	}
        return json;
    }
    
}
