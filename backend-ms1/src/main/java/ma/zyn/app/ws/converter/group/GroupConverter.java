package  ma.zyn.app.ws.converter.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.member.MemberConverter;
import ma.zyn.app.bean.core.member.Member;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.ws.dto.group.GroupDto;

@Component
public class GroupConverter {

    @Autowired
    private MemberConverter memberConverter ;
    private boolean members;

    public  GroupConverter() {
        initList(true);
    }

    public Group toItem(GroupDto dto) {
        if (dto == null) {
            return null;
        } else {
        Group item = new Group();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getSize()))
                item.setSize(dto.getSize());
            if(StringUtil.isNotEmpty(dto.getMaxStudents()))
                item.setMaxStudents(dto.getMaxStudents());
            if(StringUtil.isNotEmpty(dto.getChangeInterval()))
                item.setChangeInterval(dto.getChangeInterval());

            if(this.members && ListUtil.isNotEmpty(dto.getMembers()))
                item.setMembers(memberConverter.toItem(dto.getMembers()));


        return item;
        }
    }


    public GroupDto toDto(Group item) {
        if (item == null) {
            return null;
        } else {
            GroupDto dto = new GroupDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getSize()))
                dto.setSize(item.getSize());
            if(StringUtil.isNotEmpty(item.getMaxStudents()))
                dto.setMaxStudents(item.getMaxStudents());
            if(StringUtil.isNotEmpty(item.getChangeInterval()))
                dto.setChangeInterval(item.getChangeInterval());
        if(this.members && ListUtil.isNotEmpty(item.getMembers())){
            memberConverter.init(true);
            memberConverter.setGroup(false);
            dto.setMembers(memberConverter.toDto(item.getMembers()));
            memberConverter.setGroup(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.members = value;
    }
	
    public List<Group> toItem(List<GroupDto> dtos) {
        List<Group> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GroupDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GroupDto> toDto(List<Group> items) {
        List<GroupDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Group item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GroupDto dto, Group t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getMembers() != null)
            t.setMembers(memberConverter.copy(dto.getMembers()));
    }

    public List<Group> copy(List<GroupDto> dtos) {
        List<Group> result = new ArrayList<>();
        if (dtos != null) {
            for (GroupDto dto : dtos) {
                Group instance = new Group();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public MemberConverter getMemberConverter(){
        return this.memberConverter;
    }
    public void setMemberConverter(MemberConverter memberConverter ){
        this.memberConverter = memberConverter;
    }
    public boolean  isMembers(){
        return this.members ;
    }
    public void  setMembers(boolean members ){
        this.members  = members ;
    }
}
