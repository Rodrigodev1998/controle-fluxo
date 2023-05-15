package br.com.controlefluxocaixa.lancamento.service;

import br.com.controlefluxocaixa.lancamento.dto.SaldoDiarioDTO;
import br.com.controlefluxocaixa.lancamento.model.TipoLancamento;
import br.com.controlefluxocaixa.lancamento.repository.LancamentoRepository;
import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class SaldoDiarioService {
    @Autowired
    private LancamentoRepository lancamentoRepository;

    public SaldoDiarioDTO calcularSaldoDiario(LocalDate data) {
        log.info("[inicia] SaldoDiarioService - calcularSaldoDiario");
        List<Lancamento> lancamentos = lancamentoRepository.findByData(data);
        SaldoDiarioDTO saldoConsolidado = gerarSaldosConsolidados(lancamentos);
        saldoConsolidado.setData(data);
        log.info("[finaliza] SaldoDiarioService - calcularSaldoDiario");
        return saldoConsolidado;
    }

    private SaldoDiarioDTO gerarSaldosConsolidados(List<Lancamento> lancamentos) {
        log.info("[inicia] SaldoDiarioService - gerarSaldosConsolidados");
        var totalCreditos = calcularTotalPorTipo(TipoLancamento.CREDITO,lancamentos);
        var totalDebitos = calcularTotalPorTipo(TipoLancamento.DEBITO, lancamentos);
        BigDecimal saldoConsolidado = totalCreditos.subtract(totalDebitos);
        log.info("[finaliza] SaldoDiarioService - gerarSaldosConsolidados");
        return SaldoDiarioDTO.builder()
                .saldoTotal(totalCreditos)
                .saldoConsolidado(saldoConsolidado)
                .build();
    }
    public BigDecimal calcularTotalPorTipo(TipoLancamento tipo, List<Lancamento> lancamentos) {
        return lancamentos.stream()
                .filter(lancamento -> lancamento.getTipo() == tipo)
                .map(Lancamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

