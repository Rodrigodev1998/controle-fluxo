package br.com.controlefluxocaixa.strategy;

import br.com.controlefluxocaixa.model.Lancamento;

import java.math.BigDecimal;
import java.util.List;

public interface LancamentoStrategy {
    BigDecimal calcular(List<Lancamento> lancamentos);
}
