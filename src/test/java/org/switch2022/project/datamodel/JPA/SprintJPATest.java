
package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SprintJPATest {

    @Test
    public void testHashCode_EqualObjects() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID2 = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2023,2,2);
        Date endDate = new Date(2023,4,4);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        SprintJPA sprint2 = new SprintJPA(sprintID2, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertEquals(sprint1.hashCode(), sprint2.hashCode());
    }

    @Test
    public void testHashCode_DifferentSprintID() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID3 = new SprintJpaID("XYZ", 1);
        Date startDate = new Date(2022,4,4);
        Date endDate = new Date(2022,6,6);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        SprintJPA sprint3 = new SprintJPA(sprintID3, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertNotEquals(sprint1.hashCode(), sprint3.hashCode());
    }

    @Test
    public void testEquals_SameInstance() {
        SprintJpaID sprintID = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2023,6,23);
        Date endDate = new Date(2023,6,23);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertEquals(sprint1, sprint1);
    }

    @Test
    public void testEquals_Null() {
        SprintJpaID sprintID = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2022,2,23);
        Date endDate = new Date(2022,2,24);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertNotEquals(null, sprint1);
    }

    @Test
    public void testEquals_DifferentClass() {
        SprintJpaID sprintID = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2020,3,3);
        Date endDate = new Date(2020,5,5);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertNotEquals("ABC", sprint1);
    }

    @Test
    public void testEquals_EqualValues() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID2 = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2023,10,20);
        Date endDate = new Date(2023,10,20);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        SprintJPA sprint2 = new SprintJPA(sprintID2, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertEquals(sprint1, sprint2);
    }

    @Test
    public void testEquals_DifferentSprintID() {
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID3 = new SprintJpaID("XYZ", 1);
        Date startDate = new Date(2020,12,12);
        Date endDate = new Date(2020,12,31);
        String sprintStatus = "Planned";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        SprintJPA sprint3 = new SprintJPA(sprintID3, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        assertNotEquals(sprint1, sprint3);
    }
    @Test
    public void ensureSprintJPAIsDifferentWithDifferentStatus(){
        //arrange
        SprintJpaID sprintID1 = new SprintJpaID("ABC", 1);
        SprintJpaID sprintID2 = new SprintJpaID("ABC", 1);
        Date startDate = new Date(2020,12,12);
        Date endDate = new Date(2020,12,12);
        String sprintStatus = "Planned";
        String sprintStatusTwo = "Open";
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();

        //act
        SprintJPA sprint1 = new SprintJPA(sprintID1, startDate, endDate, sprintStatus,
                sprintBacklogJPA);
        SprintJPA sprint2 = new SprintJPA(sprintID2, startDate, endDate, sprintStatusTwo,
                sprintBacklogJPA);

        //assert
        assertNotEquals(sprint1, sprint2);
    }
}

