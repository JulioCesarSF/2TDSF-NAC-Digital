package br.com.fiap.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.dao.JogoDAO;
import br.com.fiap.dao.impl.JogoDAOImpl;
import br.com.fiap.entity.Jogo;
import br.com.fiap.exceptions.CommitErrorException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Path("/jogo")
public class JogoResource {

	
private EntityManagerFactory fabrica;
	
	public JogoResource() {
		fabrica = EntityManagerFactorySingleton.getInst();
	}
	
	//rest/jogo/{id} GET
	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Jogo buscar(@PathParam("codigo") long id){
		EntityManager em = fabrica.createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		Jogo jogo = dao.buscar(id);
		em.close();
		return jogo;
	}
	
	//rest/jogo GET
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Jogo> listar(){
		EntityManager em = fabrica.createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		List<Jogo> lista = dao.listar();
		em.close();
		return lista;
	}
	
	//rest/jogo/{codigo} PUT
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Jogo jogo, 
								@PathParam("id") long codigo){
		EntityManager em = fabrica.createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		jogo.setId(codigo);
		try {
			dao.atualizar(jogo);
			dao.salvar();
		} catch (CommitErrorException e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return Response.ok().build();
	}
	
	//rest/jogo/{codigo} DELETE
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long codigo){
		EntityManager em = fabrica.createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		try{
			dao.excluir(codigo);
			dao.salvar();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	}
	
	//rest/jogo POST
	@POST
	@Consumes(MediaType.APPLICATION_JSON) //Recebe JSON
	public Response cadastrar(Jogo jogo, 
				@Context UriInfo uriInfo){
		//Cria o DAO
		EntityManager em = fabrica.createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		//Utiliza o DAO para cadastrar
		try {
			dao.cadastrar(jogo);
			dao.salvar();
		} catch (CommitErrorException e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		//Retorna a URL e o HTTP status 201 (created)
		UriBuilder url = UriBuilder.fromPath(uriInfo.getPath());
		url.path(String.valueOf(jogo.getId()));
		return Response.created(url.build()).build();
	}
	
}
