package br.com.spring.pais.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.pais.entity.Arquivo;

/*
 * CrudRepository<Arquivo, Long>
 * Fornece métodos CRUD básicos, como save, findById, delete, findAll, etc.
 * É a interface mais genérica, adequada para casos em que as operações básicas de CRUD são suficientes e não há necessidade de funcionalidades específicas do JPA.
 * Ideal quando você precisa de uma abordagem mais genérica e não quer depender de funcionalidades específicas do provedor JPA.
 */
@Repository
public interface ArquivoRepository extends CrudRepository<Arquivo, Long> {

}
