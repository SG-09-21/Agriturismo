package it.corso.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public interface AdminService {
    
	boolean controlloLoginAdmin(HttpSession session, String... credenziali);

}