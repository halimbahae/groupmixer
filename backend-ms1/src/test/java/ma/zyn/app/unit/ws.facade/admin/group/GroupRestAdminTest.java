package ma.zyn.app.unit.ws.facade.admin.group;

import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.service.impl.admin.group.GroupAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.group.GroupRestAdmin;
import ma.zyn.app.ws.converter.group.GroupConverter;
import ma.zyn.app.ws.dto.group.GroupDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private GroupAdminServiceImpl service;
    @Mock
    private GroupConverter converter;

    @InjectMocks
    private GroupRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllGroupTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<GroupDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<GroupDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveGroupTest() throws Exception {
        // Mock data
        GroupDto requestDto = new GroupDto();
        Group entity = new Group();
        Group saved = new Group();
        GroupDto savedDto = new GroupDto();

        // Mock the converter to return the group object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved group DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<GroupDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        GroupDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved group DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getSize(), responseBody.getSize());
        assertEquals(savedDto.getMaxStudents(), responseBody.getMaxStudents());
        assertEquals(savedDto.getChangeInterval(), responseBody.getChangeInterval());
    }

}
