package br.com.fiap.to;

import java.io.Serializable;
import java.util.Calendar;

public class Jogo implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String titulo;
	private Calendar dataLancamento;
	private String genero;
	private String descricao;

	public Jogo() {
		super();
	}

	public Jogo(long id, String titulo, Calendar dataLancamento, String genero, String descricao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.genero = genero;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
