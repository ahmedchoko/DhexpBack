package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private Long idFond;
	private String nomFond ;
	private String nomCompletFond ;
	private String nomArabeFond ;
	private String abrevFond ;
	@Enumerated(EnumType.STRING)
	private Fondstatut statut ;
	private Long montantMax ;
	private Long montantMin ;
	private Date dateDemarrageFond;
	private Date dateClotureFond;
	private Long tresorerieFond;
	private String codes ;
	@JsonIgnoreProperties("fond")
	@OneToMany(mappedBy="fond",fetch = FetchType.EAGER)
	private Set<Modalite> modalites ;
	@JsonIgnore()
	@ManyToOne()
	private Quotite quotite ;
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
	private Set<Secteur> secteurs ;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fond")
	@JsonIgnore
	private List<StopLoss> stopLosses ;

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
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
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



}
