package com.wevioo.demande.servicesImpl;

import com.wevioo.demande.dto.DemandeDto;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.repository.DemandeRepository;
import com.wevioo.demande.services.DemandeService;
import com.wevioo.parametrage.enums.Fondstatut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService{
@Autowired
DemandeRepository demandeRepository;
	@Override
	public boolean Verifmatriculefiscal(String matricule) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Demande createDemande(Demande demande) {

		Demande nouvDemande = Demande.builder()
				.actifs(demande.getActifs())
				.autorisation(demande.getAutorisation())
				.beneficiare(demande.getBeneficiare())
				.fond(demande.getFond())
				.codeCentraleRisque(demande.getCodeCentraleRisque())
				.codedouane(demande.getCodedouane())
				.credit(demande.getCredit())
				.dateDeclaration(demande.getDateDeclaration())
				.referenceDemande(demande.getReferenceDemande())
				.numeroPret(demande.getNumeroPret())
				.numeroRne(demande.getNumeroRne())
				.partenaire(demande.getPartenaire())
				.passifs(demande.getPassifs())
				.projet(demande.getProjet())
				.statut(demande.getStatut())
				.numeroCompte(demande.getNumeroCompte())
				.Utilisateur(demande.getUtilisateur())
				.montantInvestissement(demande.getMontantInvestissement())
				.nouveauPromoteur(demande.getNouveauPromoteur())
				.pieceJointes(demande.getPieceJointes())
				.modalite(demande.getModalite())
				.build();
		return demandeRepository.save(nouvDemande);
	}

	@Override
	public List<Demande> getDemandes() {
		return demandeRepository.findAll();
	}

}
