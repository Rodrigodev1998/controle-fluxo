package br.com.controlefluxocaixa.lancamento.strategy;

import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Debito implements LancamentoStrategy {
    @Override
    public BigDecimal calcular(List<Lancamento> lancamentos) {
        return lancamentos.stream()
                .filter(lancamento -> lancamento.getTipo().equalsIgnoreCase("debito"))
                .map(Lancamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
