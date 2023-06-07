package org.switch2022.project.datamodel.JPA;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SprintJpaIDTest {

    @Test
    public void testHashCode() {

        SprintJpaID iD1 = new SprintJpaID("Proj1", 1);
        SprintJpaID iD2 = new SprintJpaID("Proj1", 1);

        assertEquals(iD1.hashCode(), iD2.hashCode());
        }

    @Test
    public void testHashCodeNotEquals() {

        SprintJpaID iD1 = new SprintJpaID("Proj1", 1);
        SprintJpaID iD2 = new SprintJpaID("Proj1", 2);

        assertNotEquals(iD1.hashCode(), iD2.hashCode());
    }

    @Test
    public void testEquals() {

        SprintJpaID iD1 = new SprintJpaID("Proj2", 1);
        SprintJpaID iD2 = new SprintJpaID("Proj2", 1);

        assertEquals(true, iD1.equals(iD2));
    }

    @Test
    public void testEqualsFalse() {

        SprintJpaID iD1 = new SprintJpaID("Proj2", 1);
        SprintJpaID iD3 = new SprintJpaID("Proj3", 2);

        assertEquals(false, iD1.equals(iD3));
    }
}


