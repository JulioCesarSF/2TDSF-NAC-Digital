package br.com.fiap.repository;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.to.JogoTO;

public class JogoRepository {

	private Client client = Client.create();

	private final String URL = "http://localhost:8080/WebserviceREST-req/rest/jogo/";

	public void cadastrar(JogoTO jogo) throws WebServiceException {
		WebResource resource = client.resource(URL);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, jogo);
		if (response.getStatus() != 201)
			throw new WebServiceException("HTTP Status: " + response.getStatus());
	}

	public List<JogoTO> listar() throws WebServiceException {
		WebResource resource = client.resource(URL);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() == 200) {
			JogoTO[] jogos = response.getEntity(JogoTO[].class);
			return Arrays.asList(jogos);
		}
		throw new WebServiceException("HTTP Status: " + response.getStatus());
	}

	public void atualizar(JogoTO jogo) throws WebServiceException {
		WebResource resource = client.resource(URL + jogo.getId());
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, jogo);
		if (response.getStatus() != 200)
			throw new WebServiceException("HTTP Status: " + response.getStatus());
	}

	public void remover(long id) throws WebServiceException {
		WebResource resource = client.resource(URL + id);
		ClientResponse response = resource.delete(ClientResponse.class);
		if (response.getStatus() != 204)
			throw new WebServiceException("HTTP Status: " + response.getStatus());
	}

	public JogoTO buscar(long id) throws WebServiceException {
		WebResource resource = client.resource(URL + id);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() == 200) {
			JogoTO jogo = response.getEntity(JogoTO.class);
			return jogo;
		}
		throw new WebServiceException("HTTP Status: " + response.getStatus());
	}

}
