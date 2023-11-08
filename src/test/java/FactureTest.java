import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.DetailFactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import static org.mockito.Mockito.when;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FactureTest {
    @InjectMocks
    private FactureServiceImpl factureService;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private DetailFactureRepository detailFactureRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFacture() {
        Facture newFacture = new Facture();
        newFacture.setMontantFacture(100);

        when(factureRepository.save(any(Facture.class))).thenReturn(newFacture);
        when(factureRepository.findById(anyLong())).thenReturn(Optional.of(newFacture));

        Facture addedFacture = factureService.addFacture(newFacture);
        assertNotNull(addedFacture);
        assertEquals(100.0, addedFacture.getMontantFacture());
    }

    @Test
    public void testCancelFacture() {
        factureService.cancelFacture(1L);

        verify(factureRepository).updateFacture(1L);
    }

    @Test
    public void testRetrieveFacture() {
        Long factureId = 1L;
        Facture existingFacture = new Facture();
        existingFacture.setIdFacture(factureId);

        when(factureRepository.findById(factureId)).thenReturn(Optional.of(existingFacture));
        Facture retrievedFacture = factureService.retrieveFacture(factureId);
        verify(factureRepository).findById(factureId);

        assertNotNull(retrievedFacture);
        assertEquals(factureId, retrievedFacture.getIdFacture());
    }




}
