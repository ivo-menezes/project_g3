package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryMapperDDDTest {

    @Test
    @DisplayName("ensure a list of userStoryDTOs is successfully created with a single element")
    void ensureUserStoryDTOListIsCreatedOneElement () {

        // Arrange
        UserStoryID id = mock(UserStoryID.class);
        UserStoryActor actor = mock(UserStoryActor.class);
        Description description = mock(Description.class);
        UserStoryAcceptanceCriteria acceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus status = mock(UserStoryStatus.class);
        UserStoryDDD userStory = mock(UserStoryDDD.class);
        when(userStory.identity()).thenReturn(id);
        when(userStory.getActor()).thenReturn(actor);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getAcceptanceCriteria()).thenReturn(acceptanceCriteria);
        when(userStory.getStatus()).thenReturn(status);

        List<UserStoryDDD> userStoryList = new ArrayList<>();
        userStoryList.add(userStory);

        UserStoryMapperDDD mapper = new UserStoryMapperDDD();

        // Act
        List<UserStoryDTOForListDDD> result = mapper.toDTOList(userStoryList);

        //Assert
        assertEquals(1, result.size());
        assertSame(id, result.get(0).id);
        assertSame(actor, result.get(0).actor);
        assertSame(description, result.get(0).description);
        assertSame(acceptanceCriteria, result.get(0).acceptanceCriteria);
        assertSame(status, result.get(0).status);
    }

    @Test
    @DisplayName("ensure a list of userStoryDTOs is successfully created with two elements")
    void ensureUserStoryDTOListIsCreatedTwoElements () {

        // Arrange
        UserStoryID id = mock(UserStoryID.class);
        UserStoryActor actor = mock(UserStoryActor.class);
        Description description = mock(Description.class);
        UserStoryAcceptanceCriteria acceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus status = mock(UserStoryStatus.class);
        UserStoryDDD userStory = mock(UserStoryDDD.class);
        when(userStory.identity()).thenReturn(id);
        when(userStory.getActor()).thenReturn(actor);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getAcceptanceCriteria()).thenReturn(acceptanceCriteria);
        when(userStory.getStatus()).thenReturn(status);

        UserStoryID id2 = mock(UserStoryID.class);
        UserStoryActor actor2 = mock(UserStoryActor.class);
        Description description2 = mock(Description.class);
        UserStoryAcceptanceCriteria acceptanceCriteria2 = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus status2 = mock(UserStoryStatus.class);
        UserStoryDDD userStory2 = mock(UserStoryDDD.class);
        when(userStory2.identity()).thenReturn(id2);
        when(userStory2.getActor()).thenReturn(actor2);
        when(userStory2.getDescription()).thenReturn(description2);
        when(userStory2.getAcceptanceCriteria()).thenReturn(acceptanceCriteria2);
        when(userStory2.getStatus()).thenReturn(status2);

        List<UserStoryDDD> userStoryList = new ArrayList<>();
        userStoryList.add(userStory);
        userStoryList.add(userStory2);

        UserStoryMapperDDD mapper = new UserStoryMapperDDD();

        // Act
        List<UserStoryDTOForListDDD> result = mapper.toDTOList(userStoryList);

        //Assert
        assertEquals(2, result.size());
        assertSame(id, result.get(0).id);
        assertSame(actor, result.get(0).actor);
        assertSame(description, result.get(0).description);
        assertSame(acceptanceCriteria, result.get(0).acceptanceCriteria);
        assertSame(status, result.get(0).status);
        assertSame(id2, result.get(1).id);
        assertSame(actor2, result.get(1).actor);
        assertSame(description2, result.get(1).description);
        assertSame(acceptanceCriteria2, result.get(1).acceptanceCriteria);
        assertSame(status2, result.get(1).status);
    }

}