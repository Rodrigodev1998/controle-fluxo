package br.com.controlefluxocaixa.lancamento.dto;

import br.com.controlefluxocaixa.lancamento.model.TipoLancamento;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LancamentoDTO {

    private TipoLancamento tipo;
    private String descricao;
    private BigDecimal valor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;
}
