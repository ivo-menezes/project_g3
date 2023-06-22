package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewAssembledUSDTO;
import org.switch2022.project.mapper.UpdateSprintDTO;
import org.switch2022.project.mapper.UpdateSprintDomainDTO;
import org.switch2022.project.mapper.UpdateUsInSprintDomainDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SprintRestDTOMapperTest {

    SprintRestDTOMapper mapper = new SprintRestDTOMapper();

    @Test
    public void ensureMapperCreatesDTOToServicesCorrectly() throws ParseException {
        //arrange
        SprintRestDTO mockDTO = mock(SprintRestDTO.class);
        ProjectCode mockCode = mock(ProjectCode.class);

        when(mockCode.toString()).thenReturn("mockedProjectCode");

        mockDTO.projectCode = "mockedProjectCode";
        mockDTO.sprintNumber = 1;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mockDTO.startDate = dateFormat.parse("2023-03-21");
        mockDTO.endDate = dateFormat.parse("2023-04-21");

        //act
        NewSprintDTO result = mapper.toDomainDTO(mockDTO);

        //assert
        assertInstanceOf(NewSprintDTO.class, result);
    }

    @Test
    public void ensureMapperCreatesProjectCode() {
        //arrange
        SprintRestDTO mockDTO = mock(SprintRestDTO.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        NewSprintDTO mockController = mock(NewSprintDTO.class);
        mockController.projectCode = mockCode;

        when(mockCode.toString()).thenReturn("mockedProjectCode");

        mockDTO.projectCode = "mockedProjectCode";

        //act
        NewSprintDTO result = mapper.createProjectCode(mockDTO);

        //assert
        assertEquals(mockController.projectCode.toString(), result.projectCode.toString());
    }

    @Test
    public void ensureMapperConvertsToRestDTO() throws ParseException {
        //arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ProjectCode mockCode = mock(ProjectCode.class);
        SprintID mockID = mock(SprintID.class);
        NewSprintDTO mockDTOtoController = mock(NewSprintDTO.class);
        SprintNumber mockNumber = mock(SprintNumber.class);
        TimePeriod mockPeriod = mock(TimePeriod.class);
        SprintStatus mockStatus = mock(SprintStatus.class);
        mockDTOtoController.sprintID = mockID;
        when(mockPeriod.getStartDate()).thenReturn(dateFormat.parse("2023-03-21"));
        when(mockPeriod.getEndDate()).thenReturn(dateFormat.parse("2023-03-31"));

        when(mockNumber.getValue()).thenReturn(1);
        when(mockCode.toString()).thenReturn("P1");
        when(mockID.getProjectCode()).thenReturn(mockCode);
        when(mockStatus.toString()).thenReturn("Planned");

        when(mockDTOtoController.sprintID.getProjectCode()).thenReturn(mockCode);
        when(mockDTOtoController.sprintID.getSprintNumber()).thenReturn(mockNumber);
        mockDTOtoController.timePeriod = mockPeriod;
        mockDTOtoController.status = mockStatus;

        //act
        SprintRestDTO result = mapper.toRestDTO(mockDTOtoController);

        //assert
        assertInstanceOf(SprintRestDTO.class, result);
    }

    @Test
    public void getSprintList() throws ParseException {
        //Arrange
        List<NewSprintDTO> allSprintsFromProject = new ArrayList<>();
        ProjectCode projectCode = new ProjectCode("1X");
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2023-10-10");
        Date endDate = dateFormat.parse("2023-11-11");
        TimePeriod timePeriod = new TimePeriod(startDate, endDate);

        NewSprintDTO sprintDTOToController = mock(NewSprintDTO.class);
        sprintDTOToController.sprintID = sprintID;
        sprintDTOToController.timePeriod = timePeriod;
        sprintDTOToController.status = SprintStatus.Planned;
        allSprintsFromProject.add(sprintDTOToController);

        //Act
        List<SprintRestDTO> result = mapper.getSprintList(allSprintsFromProject);

        //Assert
        assertInstanceOf(SprintRestDTO.class, result.get(0));
    }

    @Test
    public void sizeOfListOfSprintDTOUIEqualsSizeOfListSprintDTOToController() throws ParseException {
        //Arrange
        List<NewSprintDTO> allSprintsFromProject = new ArrayList<>();
        ProjectCode projectCode1 = new ProjectCode("1X");
        SprintNumber sprintNumber1 = new SprintNumber(1);
        ProjectCode projectCode2 = new ProjectCode("2X");
        SprintNumber sprintNumber2 = new SprintNumber(4);
        SprintID sprintID1 = new SprintID(projectCode1, sprintNumber1);
        SprintID sprintID2 = new SprintID(projectCode2, sprintNumber2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2023-10-10");
        Date endDate = dateFormat.parse("2023-11-11");
        TimePeriod timePeriod = new TimePeriod(startDate, endDate);

        NewSprintDTO sprintDTOToController1 = mock(NewSprintDTO.class);
        NewSprintDTO sprintDTOToController2 = mock(NewSprintDTO.class);
        sprintDTOToController1.sprintID = sprintID1;
        sprintDTOToController1.timePeriod = timePeriod;
        sprintDTOToController1.status = SprintStatus.Planned;
        sprintDTOToController2.sprintID = sprintID2;
        sprintDTOToController2.timePeriod = timePeriod;
        sprintDTOToController2.status = SprintStatus.Planned;
        allSprintsFromProject.add(sprintDTOToController1);
        allSprintsFromProject.add(sprintDTOToController2);

        List<SprintRestDTO> dtoList = new ArrayList<>();
        SprintRestDTO sprintDTOUI1 = mock(SprintRestDTO.class);
        SprintRestDTO sprintDTOUI2 = mock(SprintRestDTO.class);

        sprintDTOUI1.projectCode = sprintDTOToController1.sprintID.getProjectCode().toString();
        sprintDTOUI1.sprintNumber = sprintDTOToController1.sprintID.getSprintNumber().getValue();
        sprintDTOUI1.startDate = sprintDTOToController1.timePeriod.getStartDate();
        sprintDTOUI1.endDate = sprintDTOToController1.timePeriod.getEndDate();

        sprintDTOUI2.projectCode = sprintDTOToController2.sprintID.getProjectCode().toString();
        sprintDTOUI2.sprintNumber = sprintDTOToController2.sprintID.getSprintNumber().getValue();
        sprintDTOUI2.startDate = sprintDTOToController2.timePeriod.getStartDate();
        sprintDTOUI2.endDate = sprintDTOToController2.timePeriod.getEndDate();

        dtoList.add(sprintDTOUI1);
        dtoList.add(sprintDTOUI2);

        //Act
        List<SprintRestDTO> result = mapper.getSprintList(allSprintsFromProject);

        //Assert
        assertEquals(dtoList.size(), result.size());
    }
    @Test
    @DisplayName("Ensure UpdateSprintDTO is converted to UpdateSprintDomainDTO correctly.")
    void updateSprintDomainDTOSuccess() {
        //Arrange
        UpdateSprintDTO updateSprintDTO = mock(UpdateSprintDTO.class);
        updateSprintDTO.projectCode = "A1";
        updateSprintDTO.sprintNumber = 1;
        updateSprintDTO.sprintStatus = "Open";

        SprintRestDTOMapper mapperDouble = new SprintRestDTOMapper();

        //Act
        UpdateSprintDomainDTO result = mapperDouble.toDomainDTO(updateSprintDTO);

        //Assert
        assertInstanceOf(UpdateSprintDomainDTO.class, result);
    }

    @Test
    @DisplayName("Ensure UpdateSprintDomainDTO is converted to UpdateSprintDTO correctly.")
    void updateSprintToDataDTOSuccess() {
        //Arrange
        SprintNumber sprintNumber = mock(SprintNumber.class);
        when(sprintNumber.getValue()).thenReturn(1);

        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.toString()).thenReturn("A1");

        SprintStatus sprintStatus = mock(SprintStatus.class);
        when(sprintStatus.toString()).thenReturn("Open");

        SprintID sprintID = mock(SprintID.class);
        when(sprintID.getSprintNumber()).thenReturn(sprintNumber);
        when(sprintID.getProjectCode()).thenReturn(projectCode);

        UpdateSprintDomainDTO updateSprintDomainDTO = mock(UpdateSprintDomainDTO.class);
        updateSprintDomainDTO.sprintID = sprintID;
        updateSprintDomainDTO.sprintStatus = sprintStatus;

        SprintRestDTOMapper mapperDouble = new SprintRestDTOMapper();

        //Act
        UpdateSprintDTO result = mapperDouble.toDataDTO(updateSprintDomainDTO);

        //Assert
        assertInstanceOf(UpdateSprintDTO.class, result);
    }

    @Test
    @DisplayName("Ensure InputUsInSprintStatusDTO is converted to UpdateUsInSprintDomainDTO correctly.")
    void uSInSprintToDomainDTOSuccess() {
        // Arrange
        InputUsInSprintStatusDTO inputDto = new InputUsInSprintStatusDTO();
        inputDto.projectCode = "A1";
        inputDto.userStoryNumber = "US2";
        inputDto.sprintNumber = 1;
        inputDto.userStoryStatus = "TO_DO";

        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectCode.toString()).thenReturn(inputDto.projectCode);

        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        when(userStoryNumber.toString()).thenReturn(inputDto.userStoryNumber);

        SprintNumber sprintNumber = mock(SprintNumber.class);
        when(sprintNumber.getValue()).thenReturn(inputDto.sprintNumber);

        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        when(userStoryStatus.toString()).thenReturn(inputDto.userStoryStatus);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(userStoryID.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStoryID.getProjectCode()).thenReturn(projectCode);

        SprintID sprintID = mock(SprintID.class);
        when(sprintID.getProjectCode()).thenReturn(projectCode);
        when(sprintID.getSprintNumber()).thenReturn(sprintNumber);

        UserStoryInSprintID userStoryInSprintID = mock(UserStoryInSprintID.class);
        when(userStoryInSprintID.getSprintID()).thenReturn(sprintID);
        when(userStoryInSprintID.getUserStoryID()).thenReturn(userStoryID);

        UpdateUsInSprintDomainDTO expectedDto = new UpdateUsInSprintDomainDTO();
        expectedDto.userStoryInSprintID = userStoryInSprintID;
        expectedDto.sprintNumber = sprintNumber;
        expectedDto.userStoryInSprintStatus = userStoryStatus;

        // Create the object under test
        SprintRestDTOMapper sprintRestDTOMapper = new SprintRestDTOMapper();

        // Act
        UpdateUsInSprintDomainDTO result = sprintRestDTOMapper.uSInSprintToDomainDTO(inputDto);

        // Assert
        assertInstanceOf(UpdateUsInSprintDomainDTO.class, result);
    }

    @Test
    @DisplayName("Ensure UpdateUsInSprintDomainDTO is converted to InputUsInSprintStatusDTO correctly.")
    void toDataDTOSuccess() {
        // Arrange
        InputUsInSprintStatusDTO updateDto = mock(InputUsInSprintStatusDTO.class);
        updateDto.projectCode = "A1";
        updateDto.userStoryNumber = "US1";
        updateDto.sprintNumber = 1;
        updateDto.userStoryStatus = "TO_DO";

        SprintRestDTOMapper mapperDouble = new SprintRestDTOMapper();

        //Act
        UpdateUsInSprintDomainDTO result = mapperDouble.uSInSprintToDomainDTO(updateDto);

        //Assert
        assertInstanceOf(UpdateUsInSprintDomainDTO.class, result);
    }

    @Test
    @DisplayName("Ensure AssembledUS toRestDto returns the expected object type")
    void ensureAssembledUSToRestDtoReturnsTheExpectedObjectType() {

        //Arrange

        NewAssembledUSDTO newAssembledUSDTO = mock(NewAssembledUSDTO.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);


        newAssembledUSDTO.userStoryNumber = userStoryNumber;
        newAssembledUSDTO.projectCode = projectCode;
        newAssembledUSDTO.sprintNumber = sprintNumber;
        newAssembledUSDTO.userStoryActor = userStoryActor;
        newAssembledUSDTO.userStoryDescription = userStoryDescription;
        newAssembledUSDTO.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
        newAssembledUSDTO.userStoryStatus = userStoryStatus;
        newAssembledUSDTO.userStoryEffortEstimate = userStoryEffortEstimate;

        SprintRestDTOMapper mapper = new SprintRestDTOMapper();

        //Act
        AssembledUSRestDto resultDto = mapper.assembledUSToRestDto(newAssembledUSDTO);

        //Assert
        assertInstanceOf(AssembledUSRestDto.class,resultDto);
    }
}