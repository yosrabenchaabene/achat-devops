package tn.esprit.rh.achat.services;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ProduitServiceImplTest {
    @InjectMocks
    private ProduitServiceImpl produitService;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @AfterEach
    public void cleanup(){
    produitRepository.deleteAll();
    }

    @Test
    public void testRetrieveAllProduits() {
        // Create a list of products that you expect to be returned by the repository
        List<Produit> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Produit(1L, "Product 1", 10));
        expectedProducts.add(new Produit(2L, "Product 2", 20));

        // Define the behavior of the mock repository
        when(produitRepository.findAll()).thenReturn(expectedProducts);

        // Call the service method
        List<Produit> actualProducts = produitService.retrieveAllProduits();

        // Verify the result
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testAddProduit() {
        // Create a new product
        Produit newProduct = new Produit(null, "New Product", 30);

        // Define the behavior of the mock repository
        when(produitRepository.save(any(Produit.class))).thenReturn(newProduct);

        // Call the service method
        Produit addedProduct = produitService.addProduit(newProduct);

        // Verify the result
        assertEquals("New Product", addedProduct.getLibelleProduit());

        System.out.println("Test retrieve all succeffly passed"+addedProduct.getLibelleProduit());

    }

    @Test
    public void testUpdateProduit() {
        // Create a modified version of the product
        Produit modifiedProduct = new Produit(1L, "Modified Product", 50);

        // Define the behavior of the mock repository for the "save" method
        when(produitRepository.save(any(Produit.class))).thenReturn(modifiedProduct);

        // Call the service method
        Produit updatedProduct = produitService.updateProduit(modifiedProduct);

        // Verify the result
        assertEquals("Modified Product", updatedProduct.getLibelleProduit());
    }



    @Test
    public void testDeleteProduit() {
        // Create a product for deletion
        Produit productToDelete = new Produit(1L, "Product to Delete", 60);

        // Define the behavior of the mock repository
        when(produitRepository.findById(productToDelete.getIdProduit())).thenReturn(Optional.of(productToDelete));

        // Call the service method to delete the product
        produitService.deleteProduit(productToDelete.getIdProduit());

        // Verify that the product has been deleted (not found in the repository)
        when(produitRepository.findById(productToDelete.getIdProduit())).thenReturn(Optional.empty());
        assertNull(produitService.retrieveProduit(productToDelete.getIdProduit()));
    }




}

