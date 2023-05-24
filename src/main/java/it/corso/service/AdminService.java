package it.corso.service;

import org.springframework.stereotype.Service;

import it.corso.model.Admin;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Service
public interface AdminService {
    
	boolean controlloLoginAdmin(HttpSession session, String... credenziali);
	// void getAdminById();
}