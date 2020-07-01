package br.edu.ccd.modelo.view;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import br.edu.ccd.modelo.entidade.Cartao;
import br.edu.ccd.modelo.servico.ServicoCartao;

@Named
@ViewScoped
public class CartaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5605928538974678920L;

	private Cartao cartao;
	
	private List<Cartao> cartoes;
	@Inject
	private ServicoCartao servicoCartao;
	
	public CartaoBean() {
		// Nada a fazer
	}
	@PostConstruct
	public void init( ) {
		this.cartao = new Cartao();
		this.atualizarCartoes();
	}
	
	private void atualizarCartoes() {
		this.cartoes = this.servicoCartao.listar();
	}
	
	public void cadastrar() {
		try {
			this.servicoCartao.cadastrar(this.cartao);
			this.cartao = new Cartao();
			this.atualizarCartoes();
			Messages.addGlobalInfo("Cartão cadastrado com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalWarn(e.getMessage());
		}
	}
	
	public void editar() {
		try {
			this.servicoCartao.editar(this.cartao);
			this.cartao = new Cartao();
			this.atualizarCartoes();
			Messages.addGlobalInfo("Cartão atualizado com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void paraEditar(Cartao cartao) {
		this.cartao = cartao;
	}
	
	public void cancelarEdicao() {
		this.cartao = new Cartao();
	}

	public void excluir(Cartao cartao) {
		try {
			this.servicoCartao.excluir(cartao);
			this.atualizarCartoes();
			Messages.addGlobalInfo("Cartão excluído com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	//-----------------------------------------------------------
	public static void main(String[] args) {
		sorteioFracaoCartao();
		sorteioCVV();
	}
	
	private static String gerarCartao() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
		    sb.append(sorteioFracaoCartao());
		    if(i < 3) {
		       sb.append(" ");
		    }
		}
		return sb.toString();
	}

	private static String sorteioFracaoCartao() {
		Double sorteio = Math.random();
		sorteio = sorteio * 9999;
		Integer valorFinal = sorteio.intValue();
		DecimalFormat df = new DecimalFormat("0000");
		String valorFormatado = df.format(valorFinal);
		return valorFormatado;
	}
	
	private static String sorteioCVV() {
		Double sorteio = Math.random();
		sorteio = sorteio * 999;
		Integer valorFinal = sorteio.intValue();
		DecimalFormat df = new DecimalFormat("000");
		String valorFormatado = df.format(valorFinal);
		return valorFormatado;
	}
	//-----------------------------------------------------------
	
	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
	
	
}
