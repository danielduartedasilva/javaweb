package br.edu.ccd.modelo.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity // -------------------------------------------------------------------------------usando essa classe irá virar uma tabela de banco de dados, tem que usar no mínimo o Empty para que o jpa entenda que uma classe de banco de dados e também temos que definir uma chave primária
@Table(name = "TB_CCD_CLIENTE")//---------------------------------------------------------com o @Table eu posso darum novo nome para minha tabela no banco de dados
public class Cliente implements Serializable { // ----------------------------------------serializable, porque o java vai precisar serializar essas classes de vez em quando, significa que quando ele precisar pegar a classe, objeto, transformar num array de bytes e mandar para a rede. Cliente permeia todo o sistema, objeto transiente, como se fosse um modelo de domínio

	/**
	 * 
	 */
	private static final long serialVersionUID = -7749474631389205017L;
	@Id //--------------------------------------------------------------------------------para que seja uma chave primária
	@SequenceGenerator(name = "SEQUENCIAL_CLIENTE", sequenceName = "NUM_SEQ_CLI", allocationSize = 0) // para criar as sequences,  SEQUENCIAL_CLIENTE" é um nome local e NUM_SEQ_CLI(se o banco já for existente é necessário saber ou verificar) o que vai para o banco de dados, este sequencial cria uma numeração paralela como um ID, allocationSize = 0 para contar de 1 em 1
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIAL_CLIENTE") // @GeneratedValue para dizer qual a estratégia de geração de id, SEQUENCE funciona como um contador para cada tabela e quando colocamos AUTO ele faz tudo misturado
	@Column(name = "NUM_SEQ_CLI")//-------------------------------------------------------para dar outro nome a coluna, neste caso deixei o mesmo nome
	private Long id;
	@NotEmpty //--------------------------------------------------------------------------as marcações ficam nessa classe pois é aqui que serão entregues os dados
	private String nome;
	@NotEmpty // --------------------------------------------------------------------------validação para string
	private String email;
	@NotNull
	private Date dataNascimento;
	@NotEmpty
	private String cpf;
	
	private String telefone;
	
	private Double rendaMedia;
	
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true) // mappedBy é porque às vezes temos a mesma tabela a se relacionando com a tabela b de formas diferentes
	private List<Cartao> cartoes;
	
	public Cliente() {
		// nada a fazer por enquanto
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Double getRendaMedia() {
		return rendaMedia;
	}
	public void setRendaMedia(Double rendaMedia) {
		this.rendaMedia = rendaMedia;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) { // -----------------------------------------------para comparar 2 objetos, neste caso será comparado pelo Id, se o id for igual ele retorna true, isso é coisa de java
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
