package it.corso.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.corso.dao.OrdineDao;
import it.corso.model.Ordine;
import it.corso.model.Prodotto;
import it.corso.model.Utente;

public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineDao ordineDao;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	@Override
	public void registraOrdine(Ordine ordine, Object... dati) {
		
		LocalDate data = (LocalDate) dati[0];
		Utente utente = (Utente)dati[1];
		int[] idProdotti = (int[])dati[2];
		
		// imposto data dell'ordine
		ordine.setData(data);
		
		// imposto utente dell'ordine
		ordine.setUtente(utente);
		
		// svuoto per sicurezza la lista di prodotti di un ordine
		ordine.getProdotti().clear();
		// popolo lista prodotti dell'ordine con un ciclo
		for (int idProdotto : idProdotti) {
			Prodotto prodotto = prodottoService.getProdottoById(idProdotto);
			ordine.getProdotti().add(prodotto);
		}
		
		// calcolo l'importo totale dell'ordine
		double importo = 0;
		for (Prodotto p : ordine.getProdotti()) {
			importo += p.getPrezzo();
		}
		ordine.setImporto(importo);
		
		// una volta settati tutti i campi dell'ordine, lo registro in DB
		ordineDao.save(ordine);

	}

	@Override
	public Ordine getOrdineById(int id) {
		
		return ordineDao.findById(id).get();
	}

	@Override
	public List<Ordine> getOrdini() {
		
		return (List<Ordine>)ordineDao.findAll();
	}

	@Override
	public void cancellaOrdine(Ordine ordine) {
		
		// per cancellare un ordine è necessario...
		//...rimuoverlo dalla lista ordini del cliente..
		ordine.getUtente().getOrdini().remove(ordine);
		
		//.. e rimuovere tutti i prodotti da quell'ordine
		ordine.getProdotti().clear();
		
		// ora posso eliminare
		ordineDao.delete(ordine);

	}

}
