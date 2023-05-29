package com.wevioo.parametrage.entities;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Choix;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
 @NoArgsConstructor @AllArgsConstructor @Builder
public class Quotite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idQuotite;
	
	@Enumerated(EnumType.STRING)
	private Choix zonal;
	
	@Enumerated(EnumType.STRING)
	private Choix ritic;
	
	@Enumerated(EnumType.STRING)
	private Choix nouvPromo;
	
	@Enumerated(EnumType.STRING)
	private Choix creditLeas;
	
	private int valeurAppl;
	@JsonIgnoreProperties("quotitee")
	@OneToOne()
	private Zone zone ;
	@JsonIgnoreProperties("quotites")
	@ManyToOne()
	private Fond  fond  ;
	public Long getIdQuotite() {
		return idQuotite;
	}
	public void setIdQuotite(Long idQuotite) {
		this.idQuotite = idQuotite;
	}
	public Choix getZonal() {
		return zonal;
	}
	public void setZonal(Choix zonal) {
		this.zonal = zonal;
	}
	public Choix getRitic() {
		return ritic;
	}
	public void setRitic(Choix ritic) {
		this.ritic = ritic;
	}
	public Choix getNouvPromo() {
		return nouvPromo;
	}
	public void setNouvPromo(Choix nouvPromo) {
		this.nouvPromo = nouvPromo;
	}
	public Choix getCreditLeas() {
		return creditLeas;
	}
	public void setCreditLeas(Choix creditLeas) {
		this.creditLeas = creditLeas;
	}
	public int getValeurAppl() {
		return valeurAppl;
	}
	public void setValeurAppl(int valeurAppl) {
		this.valeurAppl = valeurAppl;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Fond getFond() {
		return fond;
	}
	public void setFond(Fond fond) {
		this.fond = fond;
	}
	
}
