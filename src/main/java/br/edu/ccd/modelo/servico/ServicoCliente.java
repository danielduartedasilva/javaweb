package br.edu.ccd.modelo.servico;

import javax.ejb.Stateless;

import br.edu.ccd.modelo.entidade.Cliente;

@Stateless //------------------------------------------------------------------------------anota��o usada para trabalhar inje��o de depend�ncia, com camadas de neg�cio, j� vem com fun��es do inject e com melhorias para intercepta��es, inje��o no EntityManager, j� faz o JTA, inje��es para trabalhar com APIs, dai no backgeBean pode-se colocar anata��es de @inject ou @Ejb
public class ServicoCliente extends ServicoComum<Cliente> {

	@Override
	protected void validarCadastramento(Cliente t) throws Exception {
		//nada a fazer
		
	}

	@Override
	protected void validarExclusao(Cliente t) throws Exception {
		//nada a fazer
		
	}

	@Override
	protected void validarEdicao(Cliente t) throws Exception {
		//nada a fazer
		
	}

	
	
	
	/*
	 public Cliente buscarPorNome(String nome){
	 EntityManager em = JPAUtils.getInstance();
		//Query query = em.createQuery("FROM Cliente c WHERE c.nome = " +nome).setParameter("nome", nome);
		Query query = em.createQuery("FROM Cliente c WHERE c.nome = " :p1).setParameter("p1", nome);
		try {
		return (Cliente) query.getSingleResult(); //---------------------------------------------------------s� retorna 1 elemento
	 	} catch (NoResultException e) {
	 	return null;
	 	}
	 */
}
