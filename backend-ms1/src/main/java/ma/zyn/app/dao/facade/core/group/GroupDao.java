package ma.zyn.app.dao.facade.core.group;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.group.Group;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface GroupDao extends AbstractRepository<Group,Long>  {



}
