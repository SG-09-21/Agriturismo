package it.corso.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.corso.dao.ProdottoDao;
import it.corso.model.Prodotto;

@Service
public class ProdottoServiceImpl implements ProdottoService {

	@Autowired
	private ProdottoDao prodottoDao;

	@Override
	public void registraProdotto(Prodotto prodotto, Object... dati) {

		prodotto.setDescrizione((String) dati[0]);
		prodotto.setCategoria((String) dati[1]);
		prodotto.setPrezzo((double) dati[2]);

		MultipartFile file = (MultipartFile) dati[3];

		if (file != null && !file.isEmpty()) {
			try {
				String contentType = file.getContentType();
				prodotto.setImmagine(
						"data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		prodottoDao.save(prodotto);
	}

	@Override
	public Prodotto getProdottoById(int id) {
		return prodottoDao.findById(id).get();
	}

	@Override
	public List<Prodotto> getProdotti() {
		return (List<Prodotto>) prodottoDao.findAll();
	}

	@Override
	public void cancellaProdotto(Prodotto prodotto) {
		prodottoDao.delete(prodotto);
	}
}