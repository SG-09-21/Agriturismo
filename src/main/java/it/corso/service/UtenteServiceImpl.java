package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.UtenteDao;
import it.corso.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDao utenteDao;
	
	@Override
	public void registraUtente(Utente utente) {
		
		utenteDao.save(utente);
	}

	@Override
	public Utente getUtenteById(int id) {
		utenteDao.findById(id);
		return null;
	}

	@Override
	public List<Utente> getUtenti() {
		
		return (List<Utente>)utenteDao.findAll();
	}

	@Override
	public void cancellaUtente(Utente utente) {
		
		utenteDao.delete(utente);
	}

}
