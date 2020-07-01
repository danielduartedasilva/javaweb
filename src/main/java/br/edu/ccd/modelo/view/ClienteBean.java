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

@Named //----------------------------------------------------------------------------------significa que esta é uma classe nominada em que eu vou poder utiliza-la na tela com jsf
@ViewScoped //-----------------------------------------------------------------------------anotação escopa de view, significa que quando eu abrir no browser a tela html e der um enter eu faço uma requisição http completa, nesse momento o jsf vai criar um viewState com uma numeração enorme, esse fará um link com uma classe Java que é uma instância, do backge Bean, esse conversa acontecerá até que eu mude de tela.
public class ClienteBean implements Serializable { //--------------------------------------ClienteBean é uma classe controladora que vai ajudar a cadastrar, editar, excluir, listar Cliente, ela faz isso com na tela, implements Serializable coloco quando uso ViewScoped

	/**
	 * ------------------	OBS NA AULA 29-05-20 VÍDEO 2 FALA SOBRE OS COMPONENTES DO PRIME FACES--------------------------------------------------------------------Ciclo de vida---o jsf criou o MarcaBean, rodou o cadastrar, criou 1 instancia do objeto cliente, esta instancia recebe os valores da tela, que vão parar la na entidade Cliente nos sets, depois rodou o método cadastrar. 
	 --------------------   OBS NA AULA 03-06-20 VÍDEO 2 FALA SOBRE OS COMPONENTES DO OMNIFACES
	 --------------------   OBS NA AULA 03-06-20 VÍDEO 2 minuto 1:26:25 não deixar deletar a marca por estar relacionada ao veículo
	 --------------------   OBS NA AULA 03-06-20 VÍDEO 1 MINUTO 1:05:00 ELE MOSTRA A DOCUMENTAÇÃO DO JSF
	 */
	private static final long serialVersionUID = -1362309780741967191L;
	
	
	private Cliente cliente; // ------------------------------------------------------------objeto Marca do tipo marca para buscar os itens da entidade Marca
	private List<Cliente> clientes;
	@Inject
    private ServicoCliente servicoCliente; //----------------------------------------------instanciação implícida, substitui a necessidade de dar new no objeto, ele cria um pool de instâncias de classes java, percebendo que a classe java não está sendo usada, injetando a classe java no servicoCliente pra que possa ser usada, ou seja, somente na hora que precisar, economizando memória ric
	
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
			this.cliente = new Cliente(); // --------------------------------------------------usado para o objeto Cliente não ser nulo
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
			Messages.addGlobalInfo("Cliente excluído com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
		
		//----------------------------------------------------------------------------------this.clientes.add(this.cliente);-------------------------------------------------seria para por a marca dentro do array
		//----------------------------------------------------------------------------------this.cliente = new Cliente(); ---------------------------------------------------seria para limpar o campo para cadastrar o novo cliente, criar uma nova instância de objeto, a variável é a mesma, mas o objeto-memória muda, ou seja, vai ser limpo o campo e dar um get para cadastrar um novo cliente
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
	--------------------------------------------------------------------------------------APENAS PARA COMPARAÇÃO DA EVOLUÇÃO, ESSE MÉTODO É COM JPA SEM HERANÇA
	--------------------------------------------------------------------------------------public void excluir(Cliente cliente) {
	--------------------------------------------------------------------------------------try {
	--------------------------------------------------------------------------------------this.servicoCliente.excluir(cliente);
	--------------------------------------------------------------------------------------Messages.addGlobalInfo("Cliente excluído com sucesso!");
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
	
	
	--------------------------------------------------------------------------------------APENAS PARA COMPARAÇÃO DA EVOLUÇÃO, ESSE MÉTODO É COM JPA SEM HERANÇA
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
 * limpar o objeto cliente para o próximo cadastramento
 * vai no atualizar lista faz um select na base e manda a mensagem para a tela do usuário e termina a execução do cadastrar
 * tela faz o update ="@form" que atualiza todo o formulário, inclusivve as tabelas
 * a tabela vai no backge Bean pega o getClientes novamente e a lista vai estar atualizada
 * e mostra o cliente na tela
 * 
 * OBS: na classe veículo ele usou uma anotação na classe para fazer o list diferente da classe marca, aula 03/06/20 video 2 min 33, ajuda a agilizar nas consultas e listagens
 * roda o contrutor
 * posConstruc cria os elementos
 * 
 */
