package  ma.zyn.app.ws.converter.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.group.GroupConverter;
import ma.zyn.app.bean.core.group.Group;

import ma.zyn.app.bean.core.group.Group;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.ws.dto.member.MemberDto;

@Component
public class MemberConverter {

    @Autowired
    private GroupConverter groupConverter ;
    private boolean group;

    public  MemberConverter() {
        initObject(true);
    }

    public Member toItem(MemberDto dto) {
        if (dto == null) {
            return null;
        } else {
        Member item = new Member();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getRole()))
                item.setRole(dto.getRole());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());
            if(dto.getGroup() != null && dto.getGroup().getId() != null){
                item.setGroup(new Group());
                item.getGroup().setId(dto.getGroup().getId());
                item.getGroup().setId(dto.getGroup().getId());
            }



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public MemberDto toDto(Member item) {
        if (item == null) {
            return null;
        } else {
            MemberDto dto = new MemberDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getRole()))
                dto.setRole(item.getRole());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(this.group && item.getGroup()!=null) {
                dto.setGroup(groupConverter.toDto(item.getGroup())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.group = value;
    }
	
    public List<Member> toItem(List<MemberDto> dtos) {
        List<Member> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MemberDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MemberDto> toDto(List<Member> items) {
        List<MemberDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Member item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MemberDto dto, Member t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getGroup() == null  && dto.getGroup() != null){
            t.setGroup(new Group());
        }else if (t.getGroup() != null  && dto.getGroup() != null){
            t.setGroup(null);
            t.setGroup(new Group());
        }
        if (dto.getGroup() != null)
        groupConverter.copy(dto.getGroup(), t.getGroup());
    }

    public List<Member> copy(List<MemberDto> dtos) {
        List<Member> result = new ArrayList<>();
        if (dtos != null) {
            for (MemberDto dto : dtos) {
                Member instance = new Member();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public GroupConverter getGroupConverter(){
        return this.groupConverter;
    }
    public void setGroupConverter(GroupConverter groupConverter ){
        this.groupConverter = groupConverter;
    }
    public boolean  isGroup(){
        return this.group;
    }
    public void  setGroup(boolean group){
        this.group = group;
    }
}
