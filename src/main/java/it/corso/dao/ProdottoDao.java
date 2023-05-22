package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Prodotto;

public interface ProdottoDao extends CrudRepository<Prodotto, Integer> {

}
