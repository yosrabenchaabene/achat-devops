import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProduitTest {
    @InjectMocks
    private ProduitServiceImpl produitService;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduit() {
        Produit newProduit = new Produit();
        newProduit.setLibelleProduit("Product A");

        when(produitRepository.save(any(Produit.class))).thenReturn(newProduit);
        when(produitRepository.findById(anyLong())).thenReturn(Optional.of(newProduit));

        Produit addedProduit = produitService.addProduit(newProduit);
        assertNotNull(addedProduit);
        assertEquals("Product A", addedProduit.getLibelleProduit());
    }

    @Test
    public void testDeleteProduit() {
        produitService.deleteProduit(1L);

        verify(produitRepository).deleteById(1L);
    }

    @Test
    public void testRetrieveAllProduits() {
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit());
        produits.add(new Produit());

        when(produitRepository.findAll()).thenReturn(produits);
        List<Produit> retrievedProduits = produitService.retrieveAllProduits();

        verify(produitRepository).findAll();
        assertNotNull(retrievedProduits);
        assertEquals(produits.size(), retrievedProduits.size());
    }

    @Test
    public void testUpdateProduit() {
        Produit existingProduit = new Produit();
        existingProduit.setIdProduit(1L);

        when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(existingProduit);
        existingProduit.setLibelleProduit("updated");
        Produit updatedProduit = produitService.updateProduit(existingProduit);
        verify(produitRepository).save(existingProduit);

        assertNotNull(updatedProduit);
        assertEquals(existingProduit.getLibelleProduit(), updatedProduit.getLibelleProduit());
    }


    @Test
    public void testRetrieveProduit() {
        Long productId = 1L;
        Produit existingProduit = new Produit();
        existingProduit.setIdProduit(productId);

        when(produitRepository.findById(productId)).thenReturn(Optional.of(existingProduit));
        Produit retrievedProduit = produitService.retrieveProduit(productId);
        verify(produitRepository).findById(productId);

        assertNotNull(retrievedProduit);
        assertEquals(productId, retrievedProduit.getIdProduit());
    }

}
