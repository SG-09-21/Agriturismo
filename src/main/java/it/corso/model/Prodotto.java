package it.corso.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "prodotti")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "dettagli")
	private String dettagli;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "prezzo")
	private double prezzo;

	@Column(name = "immagine")
	private String immagine;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "ordini_prodotti", joinColumns = @JoinColumn(name = "id_prodotto", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id"))
	private List<Ordine> ordini = new ArrayList<>();

	@Transient
	private int quantita;

	public int isQuantita() {
	    return quantita;
	}

	public void setQuantita(int quantita) {
	    this.quantita = quantita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDettagli() {
		return dettagli;
	}

	public void setDettagli(String dettagli) {
		this.dettagli = dettagli;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	@Override
	public int hashCode() {

	    return this.id;
	}

	@Override
	public boolean equals(Object obj) {

	    if (obj instanceof Prodotto) {
		Prodotto p = (Prodotto) obj;
		return this.id == p.id;
	    }

	    return false;
	}

	@Override
	public String toString() {
	    // TODO Auto-generated method stub
	    return descrizione;
	}

}
