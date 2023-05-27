package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.old.UserStoryDTO;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryDTOTest {


    @DisplayName("ensure object equals itself")
    @Test
    void ensureObjectEqualsItself(){

        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        UserStoryDTO userStoryDTO2 = userStoryDTO;

        //act
        boolean result = userStoryDTO.equals(userStoryDTO2);

        //assert
        assertTrue(result);
    }

    @DisplayName("ensure object equals another object with the same id")
    @Test
    void ensureObjectEqualsAnotherWithSameId(){
        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.id = "US001";
        userStoryDTO2.actor = "Team member";
        userStoryDTO2.text = "This is another text";
        userStoryDTO2.acceptanceCriteria = "These are top notch acceptance criteria";

        //act
        boolean result = userStoryDTO.equals(userStoryDTO2);

        //assert
        assertTrue(result);
    }

    @DisplayName("ensure object is different from another object with different id")
    @Test
    void ensureObjectNotEqualToAnotherWithDifferentId(){
        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.id = "US002";
        userStoryDTO2.actor = "Team member";
        userStoryDTO2.text = "This is some interesting text";
        userStoryDTO2.acceptanceCriteria = "These are top notch acceptance criteria";

        //act
        boolean result = userStoryDTO.equals(userStoryDTO2);

        //assert
        assertFalse(result);
    }

    @DisplayName("ensure object does not equal null")
    @Test
    void ensureObjectDoesNotEqualNull(){

        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        //act
        boolean result = userStoryDTO.equals(null);

        //assert
        assertFalse(result);
    }

    @DisplayName("ensure object is not equal to other types of objects")
    @Test
    void ensureObjectIsDifferentFromOtherTypesOfObject(){

        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        Object fakeObject = new Object();

        boolean result = userStoryDTO.equals(fakeObject);

        assertFalse(result);
    }


    @DisplayName("ensure same objects have same hashCode")
    @Test
    void ensureSameObjectsHaveSameHashCode(){

        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.id = "US001";
        userStoryDTO2.actor = "Team member";
        userStoryDTO2.text = "This is some interesting text";
        userStoryDTO2.acceptanceCriteria = "These are top notch acceptance criteria";

        int userStoryDTOHashCode = userStoryDTO.hashCode();
        int userStoryDTO2HashCode = userStoryDTO2.hashCode();

        assertEquals(userStoryDTOHashCode, userStoryDTO2HashCode);
    }

    @DisplayName("ensure different objects have different hashCode")
    @Test
    void ensureDifferentObjectsHaveDifferentHashCode(){

        //arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.actor = "Team member";
        userStoryDTO.text = "This is some interesting text";
        userStoryDTO.acceptanceCriteria = "These are top notch acceptance criteria";

        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.id = "US002";
        userStoryDTO2.actor = "Team member";
        userStoryDTO2.text = "This is some interesting text";
        userStoryDTO2.acceptanceCriteria = "These are top notch acceptance criteria";

        int userStoryDTOHashCode = userStoryDTO.hashCode();
        int userStoryDTO2HashCode = userStoryDTO2.hashCode();

        assertNotEquals(userStoryDTOHashCode, userStoryDTO2HashCode);
    }

}