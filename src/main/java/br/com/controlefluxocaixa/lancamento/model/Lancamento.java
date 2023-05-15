package br.com.controlefluxocaixa.lancamento.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "lancamento")
@Table(name = "lancamento")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O tipo do lançamento é obrigatório")
    @Column(name = "tipo")
    private TipoLancamento tipo;

    @NotNull(message = "O valor do lançamento é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor do lançamento deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "O valor do lançamento deve ter até 10 dígitos inteiros e 2 dígitos decimais")
    @Column(name = "valor")
    private BigDecimal valor;

    @NotBlank(message = "A descrição do lançamento é obrigatória")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "A data do lançamento é obrigatória")
    @Column(name = "data")
    private LocalDate data;
}
