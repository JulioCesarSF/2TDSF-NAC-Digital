package br.com.fiap.managedbeans;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.repository.JogoRepositorySOAP;
import br.com.fiap.to.Jogo;

@ManagedBean
@ViewScoped
public class JogoMB {
	private Jogo jogo;
	private List<Jogo> jogos;
	private JogoRepositorySOAP rep = new JogoRepositorySOAP();
	private String idPesquisado;
	
	@PostConstruct
	public void init(){
		try{
			int id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id");
			this.jogo = rep.buscar(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JogoMB() {
		jogo = new Jogo();
		jogos = null;
	}
	public void excluir(int id){
		System.out.println(id);
	}
	
	public String editar(int id){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", id);
		return "cadastro";
	}
	public void buscar(){
		try {
			if(this.idPesquisado == null || this.idPesquisado.equals("")){
				jogos = rep.listar();
			}else{
				Jogo jogo = rep.buscar(Integer.parseInt(this.idPesquisado));
				this.jogos = new ArrayList<>();
				this.jogos.add(jogo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(){
		try {
			if(this.jogo.getId() == 0){
				rep.cadastrar(this.jogo);
				this.jogo = new Jogo();
			}else{
				System.out.println("alterar"); 
				//alterar
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public List<Jogo> getJogos() {
		if(this.jogos == null){
			try {
				jogos = rep.listar();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return jogos;
	}
	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	public String getIdPesquisado() {
		return idPesquisado;
	}
	public void setIdPesquisado(String idPesquisado) {
		this.idPesquisado = idPesquisado;
	}
	
	
	
}
