package br.com.controlefluxocaixa.lancamento.service;

import br.com.controlefluxocaixa.lancamento.dto.LancamentoDTO;
import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import br.com.controlefluxocaixa.lancamento.repository.LancamentoRepository;
import br.com.controlefluxocaixa.lancamento.service.LancamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class LancamentoServiceTest {

    @Autowired
    private LancamentoService lancamentoService;

    @MockBean
    private LancamentoRepository lancamentoRepository;

    @MockBean
    private ModelMapper modelMapper;

    private LancamentoDTO lancamentoDTO;

    private Lancamento lancamento;

    private Long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        lancamentoDTO = new LancamentoDTO();
        lancamentoDTO.setData(LocalDate.now());
        lancamentoDTO.setDescricao("Teste");
        lancamentoDTO.setValor(BigDecimal.TEN);

        lancamento = new Lancamento();
        lancamento.setId(id);
        lancamento.setData(LocalDate.now());
        lancamento.setDescricao("Teste");
        lancamento.setValor(BigDecimal.TEN);
    }

    @Test
    void adicionarLancamento_deveRetornarLancamentoDTO() {
        when(modelMapper.map(lancamentoDTO, Lancamento.class)).thenReturn(lancamento);
        when(lancamentoRepository.save(lancamento)).thenReturn(lancamento);
        when(modelMapper.map(lancamento, LancamentoDTO.class)).thenReturn(lancamentoDTO);

        LancamentoDTO result = lancamentoService.adicionarLancamento(lancamentoDTO);

        assertNotNull(result);
        assertEquals(result.getData(), LocalDate.now());
        assertEquals(result.getDescricao(), "Teste");
        assertEquals(result.getValor(), BigDecimal.TEN);
    }

    @Test
    void listarLancamentos_porData_deveRetornarListaDeLancamentosDTO() {
        // Configuração do mock do repository
        List<Lancamento> lancamentos = Collections.singletonList(lancamento);
        when(lancamentoRepository.findByData(LocalDate.now())).thenReturn(lancamentos);

        // Configuração do mock do modelMapper
        when(modelMapper.map(lancamento, LancamentoDTO.class)).thenReturn(lancamentoDTO);

        // Teste com data existente
        List<LancamentoDTO> result = lancamentoService.listarLancamentos(LocalDate.now());

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getData(), LocalDate.now());
        assertEquals(result.get(0).getDescricao(), "Teste");
        assertEquals(result.get(0).getValor(), BigDecimal.TEN);

        // Teste com data inexistente
        when(lancamentoRepository.findByData(LocalDate.of(2023, 5, 7))).thenReturn(Collections.emptyList());
        result = lancamentoService.listarLancamentos(LocalDate.of(2023, 5, 7));

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}