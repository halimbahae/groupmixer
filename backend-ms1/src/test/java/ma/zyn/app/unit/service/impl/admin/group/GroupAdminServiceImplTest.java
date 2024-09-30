package ma.zyn.app.unit.service.impl.admin.group;

import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.dao.facade.core.group.GroupDao;
import ma.zyn.app.service.impl.admin.group.GroupAdminServiceImpl;

import ma.zyn.app.bean.core.group.Group ;
import ma.zyn.app.bean.core.member.Member ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class GroupAdminServiceImplTest {

    @Mock
    private GroupDao repository;
    private AutoCloseable autoCloseable;
    private GroupAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new GroupAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllGroup() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveGroup() {
        // Given
        Group toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteGroup() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetGroupById() {
        // Given
        Long idToRetrieve = 1L; // Example Group ID to retrieve
        Group expected = new Group(); // You need to replace Group with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Group result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Group constructSample(int i) {
		Group given = new Group();
        given.setName("name-"+i);
        given.setSize(i);
        given.setMaxStudents(i);
        given.setChangeInterval("changeInterval-"+i);
        List<Member> members = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Member element = new Member();
                                                element.setId((long)id);
                                                element.setName("name"+id);
                                                element.setRole("role"+id);
                                                element.setGroup(new Group(Long.valueOf(3)));
                                                element.setCredentialsNonExpired(true);
                                                element.setAccountNonExpired(true);
                                                element.setUsername("username"+id);
                                                element.setPasswordChanged(true);
                                                element.setAccountNonLocked(true);
                                                element.setPassword("password"+id);
                                                element.setEmail("email"+id);
                                                element.setEnabled(true);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setMembers(members);
        return given;
    }

}
