package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Dependente;
import com.AppRH.AppRH.models.Funcionario;

public interface DependenteRepository extends CrudRepository<Dependente, Long> {

	Iterable<Dependente> findByFuncionario(Funcionario funcionario);

	Dependente findByCpf(String cpf);
	
	Dependente findById(long id);
	List<Dependente> findByNome(String nome);

	@Query(value = "select u from Dependente u where u.nome like %?1%")
	List<Dependente> findByNomesDependentes(String nome);

}
