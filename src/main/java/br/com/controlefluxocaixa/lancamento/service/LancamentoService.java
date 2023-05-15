package br.com.controlefluxocaixa.lancamento.service;

import br.com.controlefluxocaixa.lancamento.dto.LancamentoDTO;
import br.com.controlefluxocaixa.lancamento.model.Lancamento;
import br.com.controlefluxocaixa.lancamento.repository.LancamentoRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@Service
public class LancamentoService {


    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LancamentoDTO adicionarLancamento(LancamentoDTO lancamentoDTO) {
        log.info("[inicia] LancamentoService - adicionarLancamento");
        var lancamento = modelMapper.map(lancamentoDTO, Lancamento.class);
        var novoLancamento = lancamentoRepository.save(lancamento);
        log.info("[finaliza] LancamentoService - adicionarLancamento");
        return modelMapper.map(novoLancamento, LancamentoDTO.class);
    }

    public List<LancamentoDTO> listarLancamentos(LocalDate data) {
        log.info("[inicia] LancamentoService - listarLancamentos");
        var lancamentos = lancamentoRepository.findByData(data);
        log.info("[finaliza] LancamentoService - listarLancamentos");
        return lancamentos.stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDTO.class))
                .collect(Collectors.toList());
    }
}
