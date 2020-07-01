package br.edu.ccd.modelo.servico;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class ServicoComum <T> { //-------------------------------------------------classe abstratc não pode ser dado new, nessa classe todos os serviços vão herdar dela, o T quer dizer que ela recebe um objeto(classe) de um tipo usuário, cliente ou cartao de crédito
	@PersistenceContext //----------------------------------------------------------------injeta o entityManager, abre transação, controla transação
	protected EntityManager em; //----------------------------------------------------------Entitymanager, gerencia as entidades para trabalhar com o banco de dados
	protected Class<T> tipoGenericoT;//----------------------------------------------------para saber qual a classe que está entrando, se é cliente, cartao de crédito ou transação

	public ServicoComum() {
		
	}
	
	@PostConstruct //----------------------------------------------------------------------método que rodo logo depois de construir toda a infra-estrutura, ou seja após a classe ser construída pelo contrutor
	@SuppressWarnings("unchecked")
	public void init() {//-----------------------------------------------------------------é um método que roda depois do construtor, 
		ParameterizedType tiposGenericos = (ParameterizedType) getClass().getGenericSuperclass(); //para recuperar os tipos da classe genérica
		Type[] tipos = tiposGenericos.getActualTypeArguments(); //------------------------ getActualTypeArguments(), retorna um array de tipos
		this.tipoGenericoT = (Class<T>) tipos[0];
		//this.atualizarClientes();
	}
	
	public T cadastrar(T t) throws Exception {
		validarCadastramento(t);
		em.persist(t);
		return t;
		/*
		 ----------------------------------------------------------------------------------EntityManager em = 	JPAUtils.getInstance();
		 ----------------------------------------------------------------------------------Cliente clienteExistente = buscarPorNome(cliente.getNome());
		 ----------------------------------------------------------------------------------if(clienteExistente) != null) {
		 ----------------------------------------------------------------------------------throw new Exception("Esse cliente já existe");
		 ----------------------------------------------------------------------------------} else {
		 ----------------------------------------------------------------------------------try {
		 ----------------------------------------------------------------------------------em.getTransition().begin();
		 ----------------------------------------------------------------------------------em.persist(t);
		 ----------------------------------------------------------------------------------em.getTransition().comit();
		 ----------------------------------------------------------------------------------return t;	
		 ----------------------------------------------------------------------------------} catch (Exception e) {
		 ----------------------------------------------------------------------------------em.getTransition().rollback();
		 ----------------------------------------------------------------------------------throw new Exception("Falha no cadastramento da t. Erro: "+e.get.Message());
		 ----------------------------------------------------------------------------------}
		 ----------------------------------------------------------------------------------}
		 ----------------------------------------------------------------------------------}
		 */
	}
	
	public void excluir(T t) throws Exception { //-----------------------------------------quando eu quero fazer a exclusão eu coloco o objeto T t que está dentro do array como parâmetro, os T t são os objetos reais
		validarExclusao(t);
		em.remove(em.merge(t)); // --------------------------------------------------------removo o objeto que foi passado como parâmetro
		//---------------------------------------------------------------------------------a linha acima é similar a esta this.clientes.remove(t);
		/*
		-----------------------------------------------------------------------------------APENAS PARA COMPARAÇÃO DA EVOLUÇÃO GANHA, ESTE MÉTODO ESTAVA NA CLASSE SERVICO CLIENTE
		-----------------------------------------------------------------------------------EntityManager em = 	JPAUtils.getInstance();
		-----------------------------------------------------------------------------------try {
		-----------------------------------------------------------------------------------em.getTransition().begin();
		-----------------------------------------------------------------------------------em.remove(em.merge)(t)); ------------------sempre que remover algo é necessário o merge
		-----------------------------------------------------------------------------------em.getTransition().comit();	
		-----------------------------------------------------------------------------------} catch (Exception e) {
		-----------------------------------------------------------------------------------e.printStackTrace();
		-----------------------------------------------------------------------------------em.getTransition().rollback();
		-----------------------------------------------------------------------------------throws new Exception("Falha na exclusão da t. Erro: "+e.get.Message());
		}
		 */
	
	}
	
	public T editar(T t) throws Exception {
		validarEdicao(t);
		return em.merge(t);
		//---------------------------------------------------------------------------------a linha acima é similar a esta this.clientes.remove(t);
		/*
		-----------------------------------------------------------------------------------APENAS PARA COMPARAÇÃO DA EVOLUÇÃO GANHA, ESTE MÉTODO ESTAVA NA CLASSE SERVICO CLIENTE
		-----------------------------------------------------------------------------------EntityManager em = 	JPAUtils.getInstance();
		-----------------------------------------------------------------------------------try {
		-----------------------------------------------------------------------------------em.getTransition().begin();
		-----------------------------------------------------------------------------------em.merge)(t));
		-----------------------------------------------------------------------------------em.getTransition().comit();	
		-----------------------------------------------------------------------------------} catch (Exception e) {
		-----------------------------------------------------------------------------------e.printStackTrace();
		-----------------------------------------------------------------------------------em.getTransition().rollback();
		-----------------------------------------------------------------------------------throws new Exception("Falha na edição da t. Erro: "+e.get.Message());
		}
		*/
		
	}
	public List<T> listar(){ // ---------------------------------------------------------- MÉTODO para listar qualquer classe
		String selecionaTudo = "SELECT t FROM {0} t";
	    String JPAQueryString = MessageFormat.format(selecionaTudo, tipoGenericoT.getSimpleName()); // trocando o {0} por um valor, por exemplo cliente, cartão
		return em.createQuery(JPAQueryString, tipoGenericoT).getResultList();//------------------jpa executa a query
	}
	/*
	--------------------------------------------------------------------------------------public List<T> listar(){
	--------------------------------------------------------------------------------------EntityManager em = 	JPAUtils.getInstance();
	--------------------------------------------------------------------------------------Query query = em.createQuery("FROM T t");
	--------------------------------------------------------------------------------------return query.getResultList(); //---------------------------------------------------getResultList() retorna um array vazio caso não tenha nada cadastrado
	}
	
	 */
	protected abstract void validarCadastramento(T t) throws Exception; // ----------------classe abstrata, pois a classe pai não sabe validar mais a filha sabe
	protected abstract void validarExclusao(T t) throws Exception;
	protected abstract void validarEdicao(T t) throws Exception;
	//protected abstract void validarListagem(T t) throws Exception;
	
	
}
