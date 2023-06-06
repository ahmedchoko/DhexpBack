package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Set;

import javax.persistence.*;

import com.wevioo.parametrage.enums.Fondstatut;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Fond {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFond")
	private Long idFond;
	//nom du fond
	@Column(name = "nom", nullable = false, unique = true)
	private String nomFond ;
	//nom Complet
	@Column(name = "nom_Complet", nullable = true, unique = false)
	private String nomCompletFond ;
	//nom en Arabe
	@Column(name = "nom_Arabe", nullable = true, unique = false)
	private String nomArabeFond ;
	//abréviation du fond
	@Column(name = "abréviation", nullable = false, unique = true)
	private String abrevFond ;
	//statut du fond
	@Column(name = "statut", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private Fondstatut statut ;
	//montant maximum
	@Column(name = "montant_max", nullable = false, unique = false)
	private Long montantMax ;
	//montant minimum
	@Column(name = "montant_min", nullable = false, unique = false)
	private Long montantMin ;
	//date démaragge du fond
	@Column(name = "date_démarrage", nullable = false, unique = false)
	private Date dateDemarrageFond;
	//date de clôture du fond
	@Column(name = "date_clôture", nullable = false, unique = false)
	private Date dateClotureFond;
	//montant de la trésorerie
	@Column(name = "trésorerie", nullable = false, unique = false)
	private Long tresorerieFond;
	//modalités supportées par le fond
	@Column(name = "modalites")
	@JsonIgnoreProperties("fond")
	@OneToMany(mappedBy="fond",fetch = FetchType.EAGER)
	private Set<Modalite> modalites ;
	//quotitées du fond
	@Column(name = "quotites")
	@JsonIgnoreProperties("fond")
	@OneToMany(mappedBy="fond",fetch = FetchType.EAGER)
	private Set<Quotite> quotites ;
	@Column(name = "secteurs")
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
	private Set<Secteur> secteurs ;
	@Column(name = "liste_stoploss")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fond")
	@JsonIgnore
	private Set<StopLoss> stoplosses ;

	@Override
	public String toString() {
		return "Fond [idFond=" + idFond + ", montantMax=" + montantMax + ", dateClotureFond=" + dateClotureFond
				+ ", tresorerieFond=" + tresorerieFond + " ]";
	}
	public Long getIdFond() {
		return idFond;
	}
	public void setIdFond(Long idFond) {
		this.idFond = idFond;
	}
	public String getNomFond() {
		return nomFond;
	}
	public void setNomFond(String nomFond) {
		this.nomFond = nomFond;
	}
	public String getNomCompletFond() {
		return nomCompletFond;
	}
	public void setNomCompletFond(String nomCompletFond) {
		this.nomCompletFond = nomCompletFond;
	}
	public String getNomArabeFond() {
		return nomArabeFond;
	}
	public void setNomArabeFond(String nomArabeFond) {
		this.nomArabeFond = nomArabeFond;
	}
	public String getAbrevFond() {
		return abrevFond;
	}
	public void setAbrevFond(String abrevFond) {
		this.abrevFond = abrevFond;
	}
	public Fondstatut getStatut() {
		return statut;
	}
	public void setStatut(Fondstatut statut) {
		this.statut = statut;
	}
	public Long getMontantMax() {
		return montantMax;
	}
	public void setMontantMax(Long montantMax) {
		this.montantMax = montantMax;
	}
	public Long getMontantMin() {
		return montantMin;
	}
	public void setMontantMin(Long montantMin) {
		this.montantMin = montantMin;
	}
	public Date getDateDemarrageFond() {
		return dateDemarrageFond;
	}
	public void setDateDemarrageFond(Date dateDemarrageFond) {
		this.dateDemarrageFond = dateDemarrageFond;
	}
	public Date getDateClotureFond() {
		return dateClotureFond;
	}
	public void setDateClotureFond(Date dateClotureFond) {
		this.dateClotureFond = dateClotureFond;
	}
	public Long getTresorerieFond() {
		return tresorerieFond;
	}
	public void setTresorerieFond(Long tresorerieFond) {
		this.tresorerieFond = tresorerieFond;
	}
	public Set<Modalite> getModalites() {
		return modalites;
	}
	public void setModalites(Set<Modalite> modalites) {
		this.modalites = modalites;
	}
	public Set<Secteur> getSecteurs() {
		return secteurs;
	}
	public void setSecteurs(Set<Secteur> secteurs) {
		this.secteurs = secteurs;

	}
	public Fond(String nomFond, String nomCompletFond, String nomArabeFond, Long tresorerieFond) {
		super();
		this.nomFond = nomFond;
		this.nomCompletFond = nomCompletFond;
		this.nomArabeFond = nomArabeFond;
		this.tresorerieFond = tresorerieFond;
	}
	public Fond() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Set<Quotite> getQuotites() {
		return quotites;
	}
	public void setQuotites(Set<Quotite> quotites) {
		this.quotites = quotites;
	}



}
