package  ma.zyn.app.dao.criteria.core.member;


import ma.zyn.app.dao.criteria.core.group.GroupCriteria;

import ma.zyn.app.zynerator.security.dao.criteria.core.UserCriteria;

import java.util.List;

public class MemberCriteria extends UserCriteria  {

    private String name;
    private String nameLike;
    private String role;
    private String roleLike;
    private Boolean credentialsNonExpired;
    private Boolean accountNonExpired;
    private String username;
    private String usernameLike;
    private Boolean passwordChanged;
    private Boolean accountNonLocked;
    private String password;
    private String passwordLike;
    private String email;
    private String emailLike;
    private Boolean enabled;

    private GroupCriteria group ;
    private List<GroupCriteria> groups ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getRoleLike(){
        return this.roleLike;
    }
    public void setRoleLike(String roleLike){
        this.roleLike = roleLike;
    }

    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }

    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    public GroupCriteria getGroup(){
        return this.group;
    }

    public void setGroup(GroupCriteria group){
        this.group = group;
    }
    public List<GroupCriteria> getGroups(){
        return this.groups;
    }

    public void setGroups(List<GroupCriteria> groups){
        this.groups = groups;
    }
}
