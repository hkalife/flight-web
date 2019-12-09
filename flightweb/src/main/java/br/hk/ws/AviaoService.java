/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hk.ws;

import br.hk.flightweb.Aviao;
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
@Path("/aviao")
@Stateless
public class AviaoService {
    
    @PersistenceContext(unitName="flightweb")
    private EntityManager em;
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Aviao create(@FormParam("prefixo") String prefixo, @FormParam("fabricante") String fabricante) {
        Aviao aviao = new Aviao();
        aviao.setPrefixo(prefixo);
        aviao.setFabricante(fabricante);
        em.persist(aviao);
        return aviao;
    }
    
    @GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public String read() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
	CriteriaQuery<Aviao> criteria = builder.createQuery(Aviao.class);
	Root<Aviao> aviao = criteria.from(Aviao.class);
	criteria.select(aviao);
	List<Aviao> a = em.createQuery(criteria).getResultList();
	return generateJson(a);
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Aviao update(@FormParam("id") long id, @FormParam("prefixo") String prefixo, @FormParam("fabricante") String fabricante) {
        Aviao aviao = em.find(Aviao.class, id);
        aviao.setPrefixo(prefixo);
        aviao.setFabricante(fabricante);
	return em.merge(aviao);
    }
    
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") long id) {
	Aviao aviao = em.find(Aviao.class, id);
	em.remove(aviao);
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
