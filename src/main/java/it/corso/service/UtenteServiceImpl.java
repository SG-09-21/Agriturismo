package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.UtenteDao;
import it.corso.model.Utente;
import jakarta.servlet.http.HttpSession;

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

	    return utenteDao.findById(id).get();
	}

	@Override
	public List<Utente> getUtenti() {
		
		return (List<Utente>)utenteDao.findAll();
	}

	@Override
	public void cancellaUtente(Utente utente) {
		
		utenteDao.delete(utente);
	}

	@Override
	public boolean controlloLogin(HttpSession session, String... credenziali) {
		for (Utente u : getUtenti()) {
			if (credenziali[0].equalsIgnoreCase(u.getUsername()) &&
					credenziali[1].equals(u.getPassword())) {
				session.setAttribute("utente", u);
				return true;
			}
		}
		
		return false;
	}

}
