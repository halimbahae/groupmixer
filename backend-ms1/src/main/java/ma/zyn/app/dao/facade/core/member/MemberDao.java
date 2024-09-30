package ma.zyn.app.dao.facade.core.member;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.member.Member;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.member.Member;
import java.util.List;


@Repository
public interface MemberDao extends AbstractRepository<Member,Long>  {
    Member findByEmail(String email);
    int deleteByEmail(String email);

    List<Member> findByGroupId(Long id);
    int deleteByGroupId(Long id);
    long countByGroupId(Long id);
    Member findByUsername(String username);

    @Query("SELECT NEW Member(item.id,item.email) FROM Member item")
    List<Member> findAllOptimized();

}
