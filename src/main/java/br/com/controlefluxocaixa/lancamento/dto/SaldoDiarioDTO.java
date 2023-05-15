package br.com.controlefluxocaixa.lancamento.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaldoDiarioDTO {
    private LocalDate data;
    private BigDecimal saldoConsolidado;
    private BigDecimal saldoTotal;
}
