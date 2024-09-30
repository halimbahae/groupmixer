package ma.zyn.app.unit.dao.facade.core.group;

import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.dao.facade.core.group.GroupDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class GroupDaoTest {

@Autowired
    private GroupDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Group entity = new Group();
        entity.setId(id);
        underTest.save(entity);
        Group loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Group entity = new Group();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Group loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Group> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Group> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Group given = constructSample(1);
        Group saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Group constructSample(int i) {
		Group given = new Group();
        given.setName("name-"+i);
        given.setSize(i);
        given.setMaxStudents(i);
        given.setChangeInterval("changeInterval-"+i);
        return given;
    }

}
