package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exceptions.CodigoInvalidoException;
import br.com.fiap.exceptions.CommitErrorException;

public class GenericDAOImpl<T, K> implements GenericDAO<T, K> {

	protected EntityManager em;

	@SuppressWarnings("unchecked")
	private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	public GenericDAOImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void cadastrar(T entity) {
		em.persist(entity);
	}

	@Override
	public void atualizar(T entity) {
		em.merge(entity);
	}

	@Override
	public void excluir(long id) throws CodigoInvalidoException {
		T entity = buscar(id);
		if (entity == null) {
			throw new CodigoInvalidoException("Código inválido");
		}
		em.remove(entity);
	}

	@Override
	public T buscar(long id) {
		return em.find(clazz, id);
	}

	@Override
	public void salvar() throws CommitErrorException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new CommitErrorException("Erro no commit");
		}
	}

}
