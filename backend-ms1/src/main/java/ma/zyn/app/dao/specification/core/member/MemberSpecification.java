package  ma.zyn.app.dao.specification.core.member;

import ma.zyn.app.dao.criteria.core.member.MemberCriteria;
import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MemberSpecification extends  AbstractSpecification<MemberCriteria, Member>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("role", criteria.getRole(),criteria.getRoleLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateFk("group","id", criteria.getGroup()==null?null:criteria.getGroup().getId());
        addPredicateFk("group","id", criteria.getGroups());
    }

    public MemberSpecification(MemberCriteria criteria) {
        super(criteria);
    }

    public MemberSpecification(MemberCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
