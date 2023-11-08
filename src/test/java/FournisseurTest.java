import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FournisseurTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFournisseur() {
        // Données de test
        Fournisseur newFournisseur = new Fournisseur();
        newFournisseur.setLibelle("Fournisseur A");
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        newFournisseur.setDetailFournisseur(detailFournisseur);

        // Comportement simulé du repository
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(newFournisseur);

        // Appel de la méthode du service
        Fournisseur addedFournisseur = fournisseurService.addFournisseur(newFournisseur);

        // Assertions
        assertNotNull(addedFournisseur);
        assertEquals("Fournisseur A", addedFournisseur.getLibelle());
        assertNotNull(addedFournisseur.getDetailFournisseur());
    }

    @Test
    public void testDeleteFournisseur() {
        // Appel de la méthode du service
        fournisseurService.deleteFournisseur(1L);

        // Vérification que le repository a été appelé avec le bon ID
        verify(fournisseurRepository).deleteById(1L);
    }

    @Test
    public void testRetrieveAllFournisseurs() {
        // Données de test
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(new Fournisseur());
        fournisseurs.add(new Fournisseur());

        // Comportement simulé du repository
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // Appel de la méthode du service
        List<Fournisseur> retrievedFournisseurs = fournisseurService.retrieveAllFournisseurs();

        // Vérification que le repository a été appelé
        verify(fournisseurRepository).findAll();

        // Assertions
        assertNotNull(retrievedFournisseurs);
        assertEquals(fournisseurs.size(), retrievedFournisseurs.size());
    }

    @Test
    public void testUpdateFournisseur() {
        // Données de test
        Fournisseur existingFournisseur = new Fournisseur();
        existingFournisseur.setIdFournisseur(1L);
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        existingFournisseur.setDetailFournisseur(detailFournisseur);

        // Comportement simulé du repository
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(existingFournisseur);

        // Appel de la méthode du service
        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(existingFournisseur);

        // Vérification que le repository a été appelé
        verify(fournisseurRepository).save(existingFournisseur);

        // Assertions
        assertNotNull(updatedFournisseur);
        assertEquals(existingFournisseur.getIdFournisseur(), updatedFournisseur.getIdFournisseur());
    }

    @Test
    public void testRetrieveFournisseur() {
        // Données de test
        Long fournisseurId = 1L;
        Fournisseur existingFournisseur = new Fournisseur();
        existingFournisseur.setIdFournisseur(fournisseurId);

        // Comportement simulé du repository
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(existingFournisseur));

        // Appel de la méthode du service
        Fournisseur retrievedFournisseur = fournisseurService.retrieveFournisseur(fournisseurId);

        // Vérification que le repository a été appelé avec le bon ID
        verify(fournisseurRepository).findById(fournisseurId);

        // Assertions
        assertNotNull(retrievedFournisseur);
        assertEquals(fournisseurId, retrievedFournisseur.getIdFournisseur());
    }
}
