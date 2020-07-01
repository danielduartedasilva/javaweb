package br.edu.ccd.modelo.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.edu.ccd.modelo.entidade.Cliente;
import br.edu.ccd.modelo.servico.ServicoCliente;

@Named //----------------------------------------------------------------------------------significa que esta � uma classe nominada em que eu vou poder utiliza-la na tela com jsf
@ViewScoped //-----------------------------------------------------------------------------anota��o escopa de view, significa que quando eu abrir no browser a tela html e der um enter eu fa�o uma requisi��o http completa, nesse momento o jsf vai criar um viewState com uma numera��o enorme, esse far� um link com uma classe Java que � uma inst�ncia, do backge Bean, esse conversa acontecer� at� que eu mude de tela.
public class ClienteBean implements Serializable { //--------------------------------------ClienteBean � uma classe controladora que vai ajudar a cadastrar, editar, excluir, listar Cliente, ela faz isso com na tela, implements Serializable coloco quando uso ViewScoped

	/**
	 * ------------------	OBS NA AULA 29-05-20 V�DEO 2 FALA SOBRE OS COMPONENTES DO PRIME FACES--------------------------------------------------------------------Ciclo de vida---o jsf criou o MarcaBean, rodou o cadastrar, criou 1 instancia do objeto cliente, esta instancia recebe os valores da tela, que v�o parar la na entidade Cliente nos sets, depois rodou o m�todo cadastrar. 
	 --------------------   OBS NA AULA 03-06-20 V�DEO 2 FALA SOBRE OS COMPONENTES DO OMNIFACES
	 --------------------   OBS NA AULA 03-06-20 V�DEO 2 minuto 1:26:25 n�o deixar deletar a marca por estar relacionada ao ve�culo
	 --------------------   OBS NA AULA 03-06-20 V�DEO 1 MINUTO 1:05:00 ELE MOSTRA A DOCUMENTA��O DO JSF
	 */
	private static final long serialVersionUID = -1362309780741967191L;
	
	
	private Cliente cliente; // ------------------------------------------------------------objeto Marca do tipo marca para buscar os itens da entidade Marca
	private List<Cliente> clientes;
	@Inject
    private ServicoCliente servicoCliente; //----------------------------------------------instancia��o impl�cida, substitui a necessidade de dar new no objeto, ele cria um pool de inst�ncias de classes java, percebendo que a classe java n�o est� sendo usada, injetando a classe java no servicoCliente pra que possa ser usada, ou seja, somente na hora que precisar, economizando mem�ria ric
	
	public ClienteBean() {
		//---------------------------------------------------------------------------------this.cliente = new Cliente();
		//----------------------------------------------------------------------------------this.clientes = new ArrayList<Cliente>();
	}
	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
		this.atualizarClientes();
	}
	
	private void atualizarClientes() {
			this.clientes = this.servicoCliente.listar();
		}
	
	public void cadastrar() {
		try {
			this.servicoCliente.cadastrar(this.cliente);
			this.cliente = new Cliente(); // --------------------------------------------------usado para o objeto Cliente n�o ser nulo
			this.atualizarClientes();
			Messages.addGlobalInfo("Cliente cadastrado com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalWarn(e.getMessage());
		}
		
	}
	
	public void editar() {
		try {
			this.servicoCliente.editar(this.cliente);
			this.cliente = new Cliente();
			this.atualizarClientes();
			Messages.addGlobalInfo("Cliente atualizado com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
		
	}
	
	public void paraEditar(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void cancelarEdicao() {
		this.cliente = new Cliente();
	}
	
	public void excluir(Cliente cliente) {
		try {
			this.servicoCliente.excluir(cliente);
			this.atualizarClientes();
			Messages.addGlobalInfo("Cliente exclu�do com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
		
		//----------------------------------------------------------------------------------this.clientes.add(this.cliente);-------------------------------------------------seria para por a marca dentro do array
		//----------------------------------------------------------------------------------this.cliente = new Cliente(); ---------------------------------------------------seria para limpar o campo para cadastrar o novo cliente, criar uma nova inst�ncia de objeto, a vari�vel � a mesma, mas o objeto-mem�ria muda, ou seja, vai ser limpo o campo e dar um get para cadastrar um novo cliente
		/*
		 ---------------------------------------------------------------------------------try {
		----------------------------------------------------------------------------------this.servicoComum.cadastrar(T);
		----------------------------------------------------------------------------------this.cliente = new Cliente();
		----------------------------------------------------------------------------------this.atualizarClientes();
		----------------------------------------------------------------------------------Messages.addGlobalInfo("Cliente cadastrado com sucesso!");
		----------------------------------------------------------------------------------} catch (Exception e) {
		----------------------------------------------------------------------------------Messages.addGlobalError(e.get.Message());
		}
		 */
	
	/*
	--------------------------------------------------------------------------------------APENAS PARA COMPARA��O DA EVOLU��O, ESSE M�TODO � COM JPA SEM HERAN�A
	--------------------------------------------------------------------------------------public void excluir(Cliente cliente) {
	--------------------------------------------------------------------------------------try {
	--------------------------------------------------------------------------------------this.servicoCliente.excluir(cliente);
	--------------------------------------------------------------------------------------Messages.addGlobalInfo("Cliente exclu�do com sucesso!");
	--------------------------------------------------------------------------------------} catch (Exception e) {
	--------------------------------------------------------------------------------------Messages.addGlobalError(e.get.Message());
	--------------------------------------------------------------------------------------}
	--------------------------------------------------------------------------------------}
	
	
	--------------------------------------------------------------------------------------public void paraEditar(Cliente cliente){
	--------------------------------------------------------------------------------------this.cliente =  cliente;
	--------------------------------------------------------------------------------------}
	
	
	--------------------------------------------------------------------------------------public vois cancelarEditar(Cliente cliente){
	--------------------------------------------------------------------------------------this.cliente = new Cliente();
	--------------------------------------------------------------------------------------}
	
	
	--------------------------------------------------------------------------------------APENAS PARA COMPARA��O DA EVOLU��O, ESSE M�TODO � COM JPA SEM HERAN�A
	--------------------------------------------------------------------------------------public void editar() {
	--------------------------------------------------------------------------------------try {
	--------------------------------------------------------------------------------------this.servicoCliente.editar(this.cliente);
	--------------------------------------------------------------------------------------this.cliente = new Cliente();
	--------------------------------------------------------------------------------------this.atualizarClientes();
	--------------------------------------------------------------------------------------Messages.addGlobalInfo("Cliente atualizado com sucesso!");
	--------------------------------------------------------------------------------------} catch (Exception e) {
	--------------------------------------------------------------------------------------Messages.addGlobalError(e.get.Message());
	--------------------------------------------------------------------------------------}
	--------------------------------------------------------------------------------------}
	*/
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

//	public ServicoCliente getServicoCliente() {
//		return servicoCliente;
//	}
//
//	public void setServicoCliente(ServicoCliente servicoCliente) {
//		this.servicoCliente = servicoCliente;
//	}

	
}

/*
 * ROTEIRO CADASTRAR LISTAR COMO O JPA, VAMOS VER SE MUDA DEPOIS
 * cadastrar do backge Bean
 * vai no Servico Comum e manda pro banco de dados
 * limpar o objeto cliente para o pr�ximo cadastramento
 * vai no atualizar lista faz um select na base e manda a mensagem para a tela do usu�rio e termina a execu��o do cadastrar
 * tela faz o update ="@form" que atualiza todo o formul�rio, inclusivve as tabelas
 * a tabela vai no backge Bean pega o getClientes novamente e a lista vai estar atualizada
 * e mostra o cliente na tela
 * 
 * OBS: na classe ve�culo ele usou uma anota��o na classe para fazer o list diferente da classe marca, aula 03/06/20 video 2 min 33, ajuda a agilizar nas consultas e listagens
 * roda o contrutor
 * posConstruc cria os elementos
 * 
 */
