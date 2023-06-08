
package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

public class SprintJPATest {

    @Test
    public void testHashCode_EqualObjects() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID2 = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2023,2,2);
        Date endDate = new Date(2023,4,4);
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate);
        SprintJPA sprint2 = new SprintJPA(sprintID2, startDate, endDate);
        assertEquals(sprint1.hashCode(), sprint2.hashCode());
    }

    @Test
    public void testHashCode_DifferentSprintID() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID3 = new SprintJpaID("XYZ", 1);
        Date startDate = new Date(2022,4,4);
        Date endDate = new Date(2022,6,6);
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate);
        SprintJPA sprint3 = new SprintJPA(sprintID3, startDate, endDate);
        assertNotEquals(sprint1.hashCode(), sprint3.hashCode());
    }
    @Test
    public void testEquals_SameInstance() {
        SprintJpaID sprintID = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2023,6,23);
        Date endDate = new Date(2023,6,23);
        SprintJPA sprint1 = new SprintJPA(sprintID, startDate, endDate);
        assertEquals(sprint1, sprint1);
    }
    @Test
    public void testEquals_Null() {
        SprintJpaID sprintID = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2022,2,23);
        Date endDate = new Date(2022,2,24);
        SprintJPA sprint1 = new SprintJPA(sprintID, startDate, endDate);
        assertNotEquals(null, sprint1);
    }

    @Test
    public void testEquals_DifferentClass() {
        SprintJpaID sprintID = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2020,3,3);
        Date endDate = new Date(2020,5,5);
        SprintJPA sprint1 = new SprintJPA(sprintID, startDate, endDate);
        assertNotEquals("ABC", sprint1);
    }

    @Test
    public void testEquals_EqualValues() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID2 = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2023,10,20);
        Date endDate = new Date(2023,10,20);
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate);
        SprintJPA sprint2 = new SprintJPA(sprintID2, startDate, endDate);
        assertEquals(sprint1, sprint2);
    }

    @Test
    public void testEquals_DifferentSprintID() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID3 = new SprintJpaID("XYZ", 1);
        Date startDate = new Date(2020,12,12);
        Date endDate = new Date(2020,12,31);
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate);
        SprintJPA sprint3 = new SprintJPA(sprintID3, startDate, endDate);
        assertNotEquals(sprint1, sprint3);
    }
}

