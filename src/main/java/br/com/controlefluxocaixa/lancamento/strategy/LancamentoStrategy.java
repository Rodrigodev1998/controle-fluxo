package br.com.controlefluxocaixa.lancamento.strategy;

import br.com.controlefluxocaixa.lancamento.model.Lancamento;

import java.math.BigDecimal;
import java.util.List;

public interface LancamentoStrategy {
    BigDecimal calcular(List<Lancamento> lancamentos);
}
