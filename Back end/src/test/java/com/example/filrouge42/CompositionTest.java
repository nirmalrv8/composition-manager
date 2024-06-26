package com.example.filrouge42;

import com.example.filrouge42.model.Carre;
import com.example.filrouge42.model.FormeGeometrique;
import com.example.filrouge42.model.Point;
import com.example.filrouge42.model.Composition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CompositionTest {
    private Composition Composition;
    private FormeGeometrique forme1;
    private FormeGeometrique forme2;

    // pour définir des configurations initiales qui doivent être exécutées avant
    // chaque méthode de test dans la classe. Cela garantit un état de départ
    // cohérent avant l'exécution de chaque test.
    @Before
    public void setUp() {
        Composition Composition = new Composition();
        forme1 = new Carre(5,5, new Point(10,10));
        forme2 = new Carre(5,5, new Point(10,10));
        Composition.ajouterForme(forme1);
    }

    @Test
    public void testAjouterForme() {
        assertEquals(1, Composition.getFormes().size());
    }

    @Test
    public void testCalculerAireTotale() {
        Composition.ajouterForme(forme2);

        double expectedAireTotale = forme1.calculerAire() + forme2.calculerAire();

        assertEquals(expectedAireTotale, Composition.calculerAireTotale(), 0.001);
    }

    @Test
    public void testCalculerPerimetreTotal() {
        Composition.ajouterForme(forme2);

        double expectedPerimetreTotal = forme1.calculerPerimetre() + forme2.calculerPerimetre();

        assertEquals(expectedPerimetreTotal, Composition.calculerPerimetreTotal(), 0.001);
    }
}