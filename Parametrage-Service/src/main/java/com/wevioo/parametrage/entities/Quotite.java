package com.wevioo.parametrage.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.parametrage.enums.Choix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
	
	@OneToMany(mappedBy="quotitee",fetch = FetchType.EAGER)
	private Set<Zone> zones ;
	@OneToMany(mappedBy="quotite",fetch = FetchType.EAGER)
	private Set<Fond> fonds ;
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
	public Set<Zone> getZones() {
		return zones;
	}
	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}
	public Set<Fond> getFonds() {
		return fonds;
	}
	public void setFonds(Set<Fond> fonds) {
		this.fonds = fonds;
	}
	
}
