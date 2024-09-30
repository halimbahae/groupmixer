package ma.zyn.app.unit.dao.facade.core.member;

import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.dao.facade.core.member.MemberDao;

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

import ma.zyn.app.bean.core.group.Group ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MemberDaoTest {

@Autowired
    private MemberDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByEmail(){
        String email = "email-1";
        Member entity = new Member();
        entity.setEmail(email);
        underTest.save(entity);
        Member loaded = underTest.findByEmail(email);
        assertThat(loaded.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldDeleteByEmail() {
        String email = "email-12345678";
        int result = underTest.deleteByEmail(email);

        Member loaded = underTest.findByEmail(email);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Member entity = new Member();
        entity.setId(id);
        underTest.save(entity);
        Member loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Member entity = new Member();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Member loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Member> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Member> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Member given = constructSample(1);
        Member saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Member constructSample(int i) {
		Member given = new Member();
        given.setName("name-"+i);
        given.setRole("role-"+i);
        given.setGroup(new Group(1L));
        given.setCredentialsNonExpired(false);
        given.setAccountNonExpired(false);
        given.setUsername("username-"+i);
        given.setPasswordChanged(false);
        given.setAccountNonLocked(false);
        given.setPassword("password-"+i);
        given.setEmail("email-"+i);
        given.setEnabled(false);
        return given;
    }

}
