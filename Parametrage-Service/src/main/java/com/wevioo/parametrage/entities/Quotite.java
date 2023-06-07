package com.wevioo.parametrage.entities;



import javax.persistence.*;

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
	@Column(name = "idQuotite")
	private Long idQuotite;
	//quotité dépend de la zone ou non
	@Column(name = "zonal", nullable = false)
	@Enumerated(EnumType.STRING)
	private Choix zonal;
	//quotité dépend du paramètre RITIC ou non
	@Column(name = "ritic", nullable = false)
	@Enumerated(EnumType.STRING)
	private Choix ritic;
	//quotité dépend du promoteur nouveau ou non
	@Column(name = "nouveau_promoteur", nullable = false)
	@Enumerated(EnumType.STRING)
	private Choix nouvPromo;
	//quotité dépend du crédit leasing ou non
	@Column(name = "credit_leasing", nullable = false)
	@Enumerated(EnumType.STRING)
	private Choix creditLeas;
	//pourcentage de la quotité
	@Column(name = "valeur_quotité", nullable = false)
	private int valeurAppl;
	//zone à laquelle s'applique la quotité
	@JsonIgnoreProperties("quotitee")
	@OneToOne()
	private Zone zone ;
	//fond auquel s'applique la quotité
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
