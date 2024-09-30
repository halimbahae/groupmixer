package ma.zyn.app.bean.core.member;






import ma.zyn.app.bean.core.group.Group;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "member")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="member_seq",sequenceName="member_seq",allocationSize=1, initialValue = 1)
public class Member  extends User    {


    public Member(String username) {
        super(username);
    }


    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String role;









    private Group group ;


    public Member(){
        super();
    }

    public Member(Long id){
        this.id = id;
    }

    public Member(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="member_seq")
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
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group")
    public Group getGroup(){
        return this.group;
    }
    public void setGroup(Group group){
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id != null && id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

