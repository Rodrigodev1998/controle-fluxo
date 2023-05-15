package br.com.controlefluxocaixa.service;

import br.com.controlefluxocaixa.lancamento.dto.SaldoDiarioDTO;
import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import br.com.controlefluxocaixa.lancamento.repository.LancamentoRepository;
import br.com.controlefluxocaixa.lancamento.service.SaldoDiarioService;
import br.com.controlefluxocaixa.lancamento.strategy.Credito;
import br.com.controlefluxocaixa.lancamento.strategy.Debito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class SaldoDiarioServiceTest {

    @InjectMocks
    private SaldoDiarioService saldoDiarioService;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private Credito lancamentoCredito;

    @Mock
    private Debito lancamentoDebito;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularSaldoDiario() {
        LocalDate data = LocalDate.of(2023, 5, 9);
        Lancamento lancamento1 = new Lancamento();
        lancamento1.setValor(new BigDecimal("100.00"));
        lancamento1.setTipo("credito");
        Lancamento lancamento2 = new Lancamento();
        lancamento2.setValor(new BigDecimal("50.00"));
        lancamento2.setTipo("debito");
        List<Lancamento> lancamentos = new ArrayList<>();
        lancamentos.add(lancamento1);
        lancamentos.add(lancamento2);

        when(lancamentoRepository.findByData(data)).thenReturn(lancamentos);
        when(lancamentoCredito.calcular(lancamentos)).thenReturn(new BigDecimal("100.00"));
        when(lancamentoDebito.calcular(lancamentos)).thenReturn(new BigDecimal("50.00"));

        SaldoDiarioDTO saldoDiarioDTO = saldoDiarioService.calcularSaldoDiario(data);

        assertEquals(new BigDecimal("100.00"), saldoDiarioDTO.getSaldoTotal());
        assertEquals(new BigDecimal("50.00"), saldoDiarioDTO.getSaldoConsolidado());
        assertEquals(new BigDecimal("-50.00"), saldoDiarioDTO.getSaldoNegativo());
    }

    @Test
    void testCalcularSaldoDiarioSemLancamentos() {
        LocalDate data = LocalDate.of(2023, 5, 9);
        List<Lancamento> lancamentos = new ArrayList<>();
        when(lancamentoRepository.findByData(data)).thenReturn(lancamentos);

        SaldoDiarioDTO saldoDiarioDTO = saldoDiarioService.calcularSaldoDiario(data);

        assertNull(saldoDiarioDTO);
    }


    @Test
    void testCalcularSaldoDiarioComLancamentosVazios() {
        LocalDate data = LocalDate.of(2023, 5, 9);
        List<Lancamento> lancamentos = new ArrayList<>();
        when(lancamentoRepository.findByData(data)).thenReturn(lancamentos);

        SaldoDiarioDTO saldoDiarioDTO = saldoDiarioService.calcularSaldoDiario(data);

        assertNull(saldoDiarioDTO);
    }
}
