
package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;



import br.com.fiap.bo.JogoBOStub;
import br.com.fiap.bo.JogoBOStub.Buscar;
import br.com.fiap.bo.JogoBOStub.BuscarResponse;
import br.com.fiap.bo.JogoBOStub.Jogo;

@ManagedBean
public class BuscaJogoBean {
	
	private int codigo;
	private Jogo jogo;
	
	@PostConstruct
	private void inicializacao(){
		jogo = new Jogo();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
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

}