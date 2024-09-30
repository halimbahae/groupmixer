package  ma.zyn.app.ws.dto.group;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.member.MemberDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto  extends AuditBaseDto {

    private String name  ;
    private Integer size  = 0 ;
    private Integer maxStudents  = 0 ;
    private String changeInterval  ;


    private List<MemberDto> members ;


    public GroupDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public Integer getSize(){
        return this.size;
    }
    public void setSize(Integer size){
        this.size = size;
    }


    public Integer getMaxStudents(){
        return this.maxStudents;
    }
    public void setMaxStudents(Integer maxStudents){
        this.maxStudents = maxStudents;
    }


    public String getChangeInterval(){
        return this.changeInterval;
    }
    public void setChangeInterval(String changeInterval){
        this.changeInterval = changeInterval;
    }





    public List<MemberDto> getMembers(){
        return this.members;
    }

    public void setMembers(List<MemberDto> members){
        this.members = members;
    }



}
