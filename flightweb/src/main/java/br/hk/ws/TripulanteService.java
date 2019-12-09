package br.hk.ws;

import br.hk.flightweb.Tripulante;
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
@Path("/tripulante")
@Stateless
public class TripulanteService {
    
    @PersistenceContext(unitName="flightweb")
    private EntityManager em;
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Tripulante create(@FormParam("nome") String nome, @FormParam("email") String email) {
        Tripulante tripulante = new Tripulante();
        tripulante.setNome(nome);
        tripulante.setEmail(email);
        em.persist(tripulante);
        return tripulante;
    }
    
    @GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public String read() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
	CriteriaQuery<Tripulante> criteria = builder.createQuery(Tripulante.class);
	Root<Tripulante> tripulante = criteria.from(Tripulante.class);
	criteria.select(tripulante);
	List<Tripulante> v = em.createQuery(criteria).getResultList();
	return generateJson(v);
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Tripulante update(@FormParam("id") long id, @FormParam("nome") String nome, @FormParam("email") String email) {
        Tripulante tripulante = em.find(Tripulante.class, id);
        tripulante.setNome(nome);
        tripulante.setEmail(email);
	return em.merge(tripulante);
    }
    
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") long id) {
	Tripulante tripulante = em.find(Tripulante.class, id);
	em.remove(tripulante);
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
