package it.corso.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.AdminDao;
import it.corso.model.Admin;
import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private AdminDao adminDao;

	@Override
	public boolean controlloLoginAdmin(HttpSession session, String... credenziali) {
		
		for (Admin a:(List<Admin>) adminDao.findAll() ) {
			
			if ( credenziali[0].equalsIgnoreCase(a.getUsername()) &&
					credenziali[1].equals(a.getPassword())) {
				session.setAttribute("admin", a);
				return true;
			}
		}		
		return false;
	}

    
}