package  ma.zyn.app.dao.specification.core.group;

import ma.zyn.app.dao.criteria.core.group.GroupCriteria;
import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class GroupSpecification extends  AbstractSpecification<GroupCriteria, Group>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateInt("size", criteria.getSize(), criteria.getSizeMin(), criteria.getSizeMax());
        addPredicateInt("maxStudents", criteria.getMaxStudents(), criteria.getMaxStudentsMin(), criteria.getMaxStudentsMax());
        addPredicate("changeInterval", criteria.getChangeInterval(),criteria.getChangeIntervalLike());
    }

    public GroupSpecification(GroupCriteria criteria) {
        super(criteria);
    }

    public GroupSpecification(GroupCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
