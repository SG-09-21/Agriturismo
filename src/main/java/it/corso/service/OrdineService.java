package it.corso.service;

import java.util.List;

import it.corso.model.Ordine;

public interface OrdineService {
	
	void registraOrdine(Ordine ordine, Object... dati);
	
	Ordine getOrdineById(int id);
	
	List<Ordine> getOrdini();
	
	void cancellaOrdine(Ordine ordine);
	
	
}
