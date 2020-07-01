package br.edu.ccd.modelo.servico;

import javax.ejb.Stateless;

import br.edu.ccd.modelo.entidade.Cartao;

@Stateless
public class ServicoCartao extends ServicoComum<Cartao> {

	@Override
	protected void validarCadastramento(Cartao t) throws Exception {
		// nada a fazer
		
	}

	@Override
	protected void validarExclusao(Cartao t) throws Exception {
		// nada a fazer
		
	}

	@Override
	protected void validarEdicao(Cartao t) throws Exception {
		// nada a fazer
		
	}

}
