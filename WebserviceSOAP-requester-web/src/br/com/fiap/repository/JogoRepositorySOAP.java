package br.com.fiap.repository;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import br.com.fiap.bo.JogoBOStub;
import br.com.fiap.bo.JogoBOStub.Buscar;
import br.com.fiap.bo.JogoBOStub.BuscarResponse;
import br.com.fiap.bo.JogoBOStub.Cadastrar;
import br.com.fiap.bo.JogoBOStub.Listar;
import br.com.fiap.bo.JogoBOStub.ListarResponse;
import br.com.fiap.to.Jogo;

public class JogoRepositorySOAP {
	public List<Jogo> listar() throws RemoteException{
		List<Jogo> jogos = new ArrayList<>();
		JogoBOStub bo = new JogoBOStub();
		Listar params = new Listar();
		ListarResponse resp = bo.listar(params);
		
		br.com.fiap.bo.JogoBOStub.Jogo[] aux = resp.get_return();
		for (br.com.fiap.bo.JogoBOStub.Jogo jogo : aux) {
			jogos.add(parseJogo(jogo));
		}
		
		return jogos;
	}
	
	public Jogo buscar(int id) throws RemoteException{
		JogoBOStub bo = new JogoBOStub();
		Buscar params = new Buscar();
		params.setId(id);
		BuscarResponse resp = bo.buscar(params);
		return parseJogo(resp.get_return());
	}
	
	public void cadastrar(Jogo jogo) throws RemoteException{
		JogoBOStub bo = new JogoBOStub();
		Cadastrar params = new Cadastrar();
		br.com.fiap.bo.JogoBOStub.Jogo j = new br.com.fiap.bo.JogoBOStub.Jogo();
		j.setDataLancamento(jogo.getDataLancamento());
		j.setDescricao(jogo.getDescricao());
		j.setGenero(jogo.getGenero());
		j.setTitulo(jogo.getTitulo());
		params.setEntity(j);
		bo.cadastrar(params);
	}
	
	public Jogo parseJogo(br.com.fiap.bo.JogoBOStub.Jogo jogo){
		if(jogo == null)
			return null;
		Jogo j = new Jogo();
		j.setDescricao(jogo.getDescricao());
		j.setId(jogo.getId());
		j.setGenero(jogo.getGenero());
		j.setTitulo(jogo.getTitulo());
		j.setDataLancamento(jogo.getDataLancamento());
		return j;
	}
}
