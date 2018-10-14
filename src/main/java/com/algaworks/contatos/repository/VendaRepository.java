package com.algaworks.contatos.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.contatos.model.Usuario;
import com.algaworks.contatos.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	//@Query(value = "  from Venda  where vendedor_id = :id and data BETWEEN :inicio AND :fim")
	@Query(value = "  from Venda  where vendedor_id = :id and MONTH(data) = :inicio and YEAR(data) = :fim ")
	List<Venda> findByPeriodo(@Param("id") Long id , @Param("inicio") int inicio, @Param("fim") int fim );	
	List<Venda> findByVendedor(Usuario usuario);
	List<Venda> findByDataBetween(Date inicio, Date fim);

	//List<Produto> findByNomeStartingWith(String nome);
	//List<Produto> findByNomeStartingWithOrderByNome(String nome);
    // Buscar onde a data cadastro está dentro de um período.
    // List<Produto> findByCadastroBetween(Date inicio, Date fim);
}
