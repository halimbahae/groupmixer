package ma.zyn.app.service.impl.admin.group;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.dao.criteria.core.group.GroupCriteria;
import ma.zyn.app.dao.facade.core.group.GroupDao;
import ma.zyn.app.dao.specification.core.group.GroupSpecification;
import ma.zyn.app.service.facade.admin.group.GroupAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.member.MemberAdminService ;
import ma.zyn.app.bean.core.member.Member ;

import java.util.List;
@Service
public class GroupAdminServiceImpl implements GroupAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Group update(Group t) {
        Group loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Group.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Group findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Group findOrSave(Group t) {
        if (t != null) {
            Group result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Group> findAll() {
        return dao.findAll();
    }

    public List<Group> findByCriteria(GroupCriteria criteria) {
        List<Group> content = null;
        if (criteria != null) {
            GroupSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private GroupSpecification constructSpecification(GroupCriteria criteria) {
        GroupSpecification mySpecification =  (GroupSpecification) RefelexivityUtil.constructObjectUsingOneParam(GroupSpecification.class, criteria);
        return mySpecification;
    }

    public List<Group> findPaginatedByCriteria(GroupCriteria criteria, int page, int pageSize, String order, String sortField) {
        GroupSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GroupCriteria criteria) {
        GroupSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        memberService.deleteByGroupId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Group> delete(List<Group> list) {
		List<Group> result = new ArrayList();
        if (list != null) {
            for (Group t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Group create(Group t) {
        Group loaded = findByReferenceEntity(t);
        Group saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getMembers() != null) {
                t.getMembers().forEach(element-> {
                    element.setGroup(saved);
                    memberService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Group findWithAssociatedLists(Long id){
        Group result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setMembers(memberService.findByGroupId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Group> update(List<Group> ts, boolean createIfNotExist) {
        List<Group> result = new ArrayList<>();
        if (ts != null) {
            for (Group t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Group loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Group t, Group loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Group group){
    if(group !=null && group.getId() != null){
        List<List<Member>> resultMembers= memberService.getToBeSavedAndToBeDeleted(memberService.findByGroupId(group.getId()),group.getMembers());
            memberService.delete(resultMembers.get(1));
        emptyIfNull(resultMembers.get(0)).forEach(e -> e.setGroup(group));
        memberService.update(resultMembers.get(0),true);
        }
    }








    public Group findByReferenceEntity(Group t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Group> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Group>> getToBeSavedAndToBeDeleted(List<Group> oldList, List<Group> newList) {
        List<List<Group>> result = new ArrayList<>();
        List<Group> resultDelete = new ArrayList<>();
        List<Group> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Group> oldList, List<Group> newList, List<Group> resultUpdateOrSave, List<Group> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Group myOld = oldList.get(i);
                Group t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Group myNew = newList.get(i);
                Group t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private MemberAdminService memberService ;

    public GroupAdminServiceImpl(GroupDao dao) {
        this.dao = dao;
    }

    private GroupDao dao;
}
