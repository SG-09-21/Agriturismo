package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Ordine;
import it.corso.model.Utente;

public interface OrdineDao extends CrudRepository<Ordine, Integer> {

	List<Ordine> findByUtente(Utente utente);
}
