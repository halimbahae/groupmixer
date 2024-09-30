package ma.zyn.app.service.facade.admin.group;

import java.util.List;
import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.dao.criteria.core.group.GroupCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface GroupAdminService {







	Group create(Group t);

    Group update(Group t);

    List<Group> update(List<Group> ts,boolean createIfNotExist);

    Group findById(Long id);

    Group findOrSave(Group t);

    Group findByReferenceEntity(Group t);

    Group findWithAssociatedLists(Long id);

    List<Group> findAllOptimized();

    List<Group> findAll();

    List<Group> findByCriteria(GroupCriteria criteria);

    List<Group> findPaginatedByCriteria(GroupCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GroupCriteria criteria);

    List<Group> delete(List<Group> ts);

    boolean deleteById(Long id);

    List<List<Group>> getToBeSavedAndToBeDeleted(List<Group> oldList, List<Group> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
