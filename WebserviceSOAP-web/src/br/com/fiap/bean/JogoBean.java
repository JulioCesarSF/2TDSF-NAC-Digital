package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.fiap.bo.JogoBOStub;
import br.com.fiap.bo.JogoBOStub.Buscar;
import br.com.fiap.bo.JogoBOStub.BuscarResponse;
import br.com.fiap.bo.JogoBOStub.Cadastrar;
import br.com.fiap.bo.JogoBOStub.Jogo;

@ManagedBean
public class JogoBean {
	
	private Jogo jogo;
	private int codigo;
	

	@PostConstruct
	private void inicializacao(){
		jogo = new Jogo();
	}
	//http://localhost:8080/WebserviceSOAP-requester-web/faces/cadastra-jogo.xhtml
	public String cadastrar(){
		try {
			JogoBOStub stub = new JogoBOStub();
			Cadastrar params = new Cadastrar();
			params.setEntity(jogo);
			stub.cadastrar(params);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "cadastra-jogo";
	
	}
	
	public void buscar(){
		try {
			JogoBOStub stub = new JogoBOStub();
			Buscar params = new Buscar();
			params.setId(codigo);
			BuscarResponse response = stub.buscar(params);
			jogo = response.get_return();
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}