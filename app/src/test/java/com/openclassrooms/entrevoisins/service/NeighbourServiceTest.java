package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void createNeighboursWithSuccess() {
        int initialNeighbourCount = service.getNeighbours().size();
        service.createNeighbour(new Neighbour(100, "John Doe",
                "https://i.pravatar.cc/150?u=a042581f4e29026704b",
                "Saint-Pierre-du-Mont ; 5km", "0975262536",
                "Je suis un test", false));
        int newNeighbourCount = service.getNeighbours().size();
        assertEquals(initialNeighbourCount + 1, newNeighbourCount);
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void updateNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        assertFalse(neighbour.getIsFavorite());
        neighbour.setIsFavorite(true);
        service.updateNeighbours(neighbour);
        assertTrue(service.getNeighbours().get(0).getIsFavorite());
    }
}
