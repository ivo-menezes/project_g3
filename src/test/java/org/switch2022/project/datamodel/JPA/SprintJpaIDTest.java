package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(iD1, iD2);
    }

    @Test
    public void testEqualsFalse() {

        SprintJpaID iD1 = new SprintJpaID("Proj2", 1);
        SprintJpaID iD3 = new SprintJpaID("Proj3", 2);

        assertNotEquals(iD1, iD3);
    }
    @Test
    public void testEquals_SameInstance() {
        SprintJpaID sprint1 = new SprintJpaID("ABC", 1);
        assertEquals(sprint1, sprint1);
    }

    @Test
    public void testEquals_Null() {
        SprintJpaID sprint1 = new SprintJpaID("ABC", 1);
        assertNotEquals(null, sprint1);
    }

    @Test
    public void testEquals_DifferentClass() {
        SprintJpaID sprint1 = new SprintJpaID("ABC", 1);
        assertNotEquals("ABC", sprint1);
    }

    @Test
    public void testEquals_EqualValues() {
        SprintJpaID sprint1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprint2 = new SprintJpaID("ABC", 1);
        assertEquals(sprint1, sprint2);
    }

    @Test
    public void testEquals_DifferentProjectCode() {
        SprintJpaID sprint1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprint3 = new SprintJpaID("XYZ", 1);
        assertNotEquals(sprint1, sprint3);
    }

    @Test
    public void testEquals_DifferentSprintNumber() {
        SprintJpaID sprint1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprint4 = new SprintJpaID("ABC", 2);
        assertNotEquals(sprint1, sprint4);
    }
}



