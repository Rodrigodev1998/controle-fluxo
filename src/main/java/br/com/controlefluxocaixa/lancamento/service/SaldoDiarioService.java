package br.com.controlefluxocaixa.lancamento.service;

import br.com.controlefluxocaixa.lancamento.dto.SaldoDiarioDTO;
import br.com.controlefluxocaixa.lancamento.repository.LancamentoRepository;
import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import br.com.controlefluxocaixa.lancamento.strategy.LancamentoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SaldoDiarioService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    @Qualifier("credito")
    private LancamentoStrategy lancamentoCredito;

    @Autowired
    @Qualifier("debito")
    private LancamentoStrategy lancamentoDebito;

    public SaldoDiarioDTO calcularSaldoDiario(LocalDate data) {
        List<Lancamento> lancamentos = lancamentoRepository.findByData(data);

        if (lancamentos.isEmpty()) {
            return null;
        }

        var totalCreditos = lancamentoCredito.calcular(lancamentos);
        var totalDebitos = lancamentoDebito.calcular(lancamentos);
        if (totalCreditos == null || totalDebitos == null) {
            return null;
        }

        BigDecimal saldoConsolidado = totalCreditos.subtract(totalDebitos);
        BigDecimal saldoNegativo = totalDebitos.compareTo(BigDecimal.ZERO) > 0 ? totalDebitos : BigDecimal.ZERO;

        return SaldoDiarioDTO.builder()
                .data(data)
                .saldoTotal(totalCreditos)
                .saldoConsolidado(saldoConsolidado)
                .saldoNegativo(saldoNegativo)
                .build();
    }

}
