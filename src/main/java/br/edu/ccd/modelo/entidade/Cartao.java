package br.edu.ccd.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CCD_CARTAO")
@NamedQuery(name = "Cartao.listarTodos", query = "SELECT c FROM Cartao c") // @NamedQuery queri nominada, preparando esta queri, quando eu carregar o sistema, sobe a conexão, o JTA e a queri SELECT c FROM Cartao c será enviada para o banco de dados, este prepara a consulta para quando for chamada ele devolve um id desse query, isso traz rapidez e segunça.
public class Cartao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4266458959095639731L;
	
	@Id
	@SequenceGenerator(name ="SEQUENCIAL_CARTAO", sequenceName ="NUM_SEQ_CAR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEQUENCIAL_CARTAO")
	@Column(name ="NUM_SEQ_CAR")
	private Long id;
	
	@ManyToOne
	//@JoinColumn(name = cliente_id) // @JoinColumn caso não gostar do nome da coluna que o banco criou é só trocar, depois pode ser deletado o nome do banco
	@NotNull
	private Cliente cliente;
	
	//@NotEmpty
	private String numero;
	
	//@NotEmpty
	private String cvv;
	
	@NotNull
	private Double saldo;
	
	//@NotNull
	private Date dataValidade;
	
	//@NotNull
	private Date dataCriacao;
	
	public Cartao() {
		// nada a fazer
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
