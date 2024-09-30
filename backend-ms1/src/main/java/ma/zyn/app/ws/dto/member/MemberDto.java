package  ma.zyn.app.ws.dto.member;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;


import ma.zyn.app.ws.dto.group.GroupDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto  extends UserDto {

    private String name  ;
    private String role  ;

    private GroupDto group ;



    private Collection<Role> roles;
    public MemberDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }










    public GroupDto getGroup(){
        return this.group;
    }

    public void setGroup(GroupDto group){
        this.group = group;
    }







    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
