package br.com.controlefluxocaixa.lancamento.service;

import br.com.controlefluxocaixa.lancamento.dto.SaldoDiarioDTO;
import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import br.com.controlefluxocaixa.lancamento.repository.LancamentoRepository;
import br.com.controlefluxocaixa.lancamento.service.SaldoDiarioService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SaldoDiarioService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SaldoDiarioServiceTest {

    @InjectMocks
    private SaldoDiarioService saldoDiarioService;

    @Mock
    private LancamentoRepository lancamentoRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
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
