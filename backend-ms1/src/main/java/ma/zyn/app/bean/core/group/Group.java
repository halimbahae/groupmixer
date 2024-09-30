package ma.zyn.app.bean.core.group;

import java.util.List;





import ma.zyn.app.bean.core.member.Member;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="group_seq",sequenceName="group_seq",allocationSize=1, initialValue = 1)
public class Group  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    private Integer size = 0;

    private Integer maxStudents = 0;

    @Column(length = 500)
    private String changeInterval;


    private List<Member> members ;

    public Group(){
        super();
    }

    public Group(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="group_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @OneToMany(mappedBy = "group")
    public List<Member> getMembers(){
        return this.members;
    }

    public void setMembers(List<Member> members){
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id != null && id.equals(group.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

