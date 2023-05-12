package br.com.controlefluxocaixa.repository;

import br.com.controlefluxocaixa.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    List<Lancamento> findByData(LocalDate data);
}
