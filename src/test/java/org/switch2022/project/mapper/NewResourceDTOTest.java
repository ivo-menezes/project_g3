package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


class NewResourceDTOTest {

    @DisplayName("can create dto")
    @Test
    void createDTO() {
        NewResourceDTO dto = new NewResourceDTO();
        assertInstanceOf(NewResourceDTO.class, dto);
    }

    @DisplayName("identical dto objects")
    @Test
    void identicalDTOsObjects() {
        AccountID accountID = new AccountID(1L);
        Role role = new Role("Product Owner");
        ProjectCode projectCode = new ProjectCode("ABC123");
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023), new Date(25 / 3 / 2023));

        NewResourceDTO dto1 = new NewResourceDTO();
        dto1.accountID = accountID;
        dto1.role = role;
        dto1.projectCode = projectCode;
        dto1.timePeriod = timePeriod;

        NewResourceDTO dto2 = new NewResourceDTO();
        dto2.accountID = accountID;
        dto2.role = role;
        dto2.projectCode = projectCode;
        dto2.timePeriod = timePeriod;

        assertEquals(dto1, dto2);
    }

    @DisplayName("different dto objects with different email")
    @Test
    void differentDTOsObjectsDifferentEmail() {
        AccountID accountID1 = new AccountID(1L);
        AccountID accountID2 = new AccountID(2L);
        Role role = new Role("Product Owner");
        ProjectCode projectCode = new ProjectCode("ABC123");
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023), new Date(25 / 3 / 2023));

        NewResourceDTO dto1 = new NewResourceDTO();
        dto1.accountID = accountID1;
        dto1.role = role;
        dto1.projectCode = projectCode;
        dto1.timePeriod = timePeriod;

        NewResourceDTO dto2 = new NewResourceDTO();
        dto2.accountID = accountID2;
        dto2.role = role;
        dto2.projectCode = projectCode;
        dto2.timePeriod = timePeriod;

        assertNotEquals(dto1, dto2);
    }

    @DisplayName("different dto objects with different email")
    @Test
    void differentDTOsObjectsDifferentRole() {
        AccountID accountID = new AccountID(1L);
        Role role1 = new Role("Product Owner");
        Role role2 = new Role("Scrum Master");
        ProjectCode projectCode = new ProjectCode("ABC123");
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023), new Date(25 / 3 / 2023));

        NewResourceDTO dto1 = new NewResourceDTO();
        dto1.accountID = accountID;
        dto1.role = role1;
        dto1.projectCode = projectCode;
        dto1.timePeriod = timePeriod;

        NewResourceDTO dto2 = new NewResourceDTO();
        dto2.accountID = accountID;
        dto2.role = role2;
        dto2.projectCode = projectCode;
        dto2.timePeriod = timePeriod;

        assertNotEquals(dto1, dto2);
    }

    @DisplayName("different dto objects with different projectCode")
    @Test
    void differentDTOsObjectsDifferentProjectCode() {
        AccountID accountID = new AccountID(1L);
        Role role = new Role("Product Owner");
        ProjectCode projectCode1 = new ProjectCode("ABC123");
        ProjectCode projectCode2 = new ProjectCode("ABC121");
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023), new Date(25 / 3 / 2023));

        NewResourceDTO dto1 = new NewResourceDTO();
        dto1.accountID = accountID;
        dto1.role = role;
        dto1.projectCode = projectCode1;
        dto1.timePeriod = timePeriod;

        NewResourceDTO dto2 = new NewResourceDTO();
        dto2.accountID = accountID;
        dto2.role = role;
        dto2.projectCode = projectCode2;
        dto2.timePeriod = timePeriod;

        assertNotEquals(dto1, dto2);
    }

    @DisplayName("different dto objects with different projectCode")
    @Test
    void differentDTOsObjectsDifferentTimePeriod() {
        AccountID accountID = new AccountID(1L);
        Role role = new Role("Product Owner");
        ProjectCode projectCode = new ProjectCode("ABC123");
        TimePeriod timePeriod1 = new TimePeriod(new Date(2023, 3, 10), new Date(2023, 3, 25));
        TimePeriod timePeriod2 = new TimePeriod(new Date(2023, 5, 10), new Date(2023, 5, 25));

        NewResourceDTO dto1 = new NewResourceDTO();
        dto1.accountID = accountID;
        dto1.role = role;
        dto1.projectCode = projectCode;
        dto1.timePeriod = timePeriod1;

        NewResourceDTO dto2 = new NewResourceDTO();
        dto2.accountID = accountID;
        dto2.role = role;
        dto2.projectCode = projectCode;
        dto2.timePeriod = timePeriod2;

        assertNotEquals(dto1, dto2);
    }
}