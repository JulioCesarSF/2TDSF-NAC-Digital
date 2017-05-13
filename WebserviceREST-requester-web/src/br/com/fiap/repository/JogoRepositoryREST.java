package br.com.fiap.repository;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.to.Jogo;

public class JogoRepositoryREST {
	
	private static final String URLBASE = "http://localhost:8080/WebserviceREST-req/rest";
	
	public List<Jogo> listar() throws Exception{
		Client client = Client.create();
		WebResource resouce = client.resource(URLBASE+"/jogo");
		
		ClientResponse response = resouce.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if(response.getStatus() == 200){
			Jogo[] array = response.getEntity(Jogo[].class);
			return Arrays.asList(array);
		}
		throw new Exception("Erro " + response.getStatus());
	}
	
	public Jogo buscar(int codigo) throws Exception{
		Client client = Client.create();
		WebResource resouce = client.resource(URLBASE+"/jogo/"+codigo);
		
		ClientResponse response = resouce.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if(response.getStatus() == 200){
			Jogo to = response.getEntity(Jogo.class);
			return to;
		}
		throw new Exception("Erro " + response.getStatus());
	}
	
	public void cadastrar(Jogo to) throws Exception{
		Client client = Client.create();
		WebResource resouce = client.resource(URLBASE+"/jogo");
		
		ClientResponse response = resouce.accept(MediaType.APPLICATION_JSON).type("application/json").post(ClientResponse.class,to);
		
		if(response.getStatus() != 201){
			throw new Exception("Erro " + response.getStatus());
		}
	}
	
	public void alterar(Jogo to) throws Exception{
		Client client = Client.create();
		WebResource resouce = client.resource(URLBASE+"/jogo/"+to.getId());
		
		ClientResponse response = resouce.accept(MediaType.APPLICATION_JSON).type("application/json").put(ClientResponse.class,to);
		
		if(response.getStatus() != 200){
			throw new Exception("Erro " + response.getStatus());
		}
	}
	
	public void remover(int codigo) throws Exception{
		Client client = Client.create();
		WebResource resouce = client.resource(URLBASE+"/jogo/"+codigo);
		
		ClientResponse response = resouce.accept(MediaType.APPLICATION_JSON).type("application/json").delete(ClientResponse.class);
		
		if(response.getStatus() != 200){
			throw new Exception("Erro " + response.getStatus());
		}
	}
}
