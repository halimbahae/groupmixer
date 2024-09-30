package  ma.zyn.app.ws.facade.admin.group;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.group.Group;
import ma.zyn.app.dao.criteria.core.group.GroupCriteria;
import ma.zyn.app.service.facade.admin.group.GroupAdminService;
import ma.zyn.app.ws.converter.group.GroupConverter;
import ma.zyn.app.ws.dto.group.GroupDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/group/")
public class GroupRestAdmin {




    @Operation(summary = "Finds a list of all groups")
    @GetMapping("")
    public ResponseEntity<List<GroupDto>> findAll() throws Exception {
        ResponseEntity<List<GroupDto>> res = null;
        List<Group> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<GroupDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a group by id")
    @GetMapping("id/{id}")
    public ResponseEntity<GroupDto> findById(@PathVariable Long id) {
        Group t = service.findById(id);
        if (t != null) {
            converter.init(true);
            GroupDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  group")
    @PostMapping("")
    public ResponseEntity<GroupDto> save(@RequestBody GroupDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Group myT = converter.toItem(dto);
            Group t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                GroupDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  group")
    @PutMapping("")
    public ResponseEntity<GroupDto> update(@RequestBody GroupDto dto) throws Exception {
        ResponseEntity<GroupDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Group t = service.findById(dto.getId());
            converter.copy(dto,t);
            Group updated = service.update(t);
            GroupDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of group")
    @PostMapping("multiple")
    public ResponseEntity<List<GroupDto>> delete(@RequestBody List<GroupDto> dtos) throws Exception {
        ResponseEntity<List<GroupDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Group> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified group")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a group and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<GroupDto> findWithAssociatedLists(@PathVariable Long id) {
        Group loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        GroupDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds groups by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<GroupDto>> findByCriteria(@RequestBody GroupCriteria criteria) throws Exception {
        ResponseEntity<List<GroupDto>> res = null;
        List<Group> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<GroupDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated groups by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody GroupCriteria criteria) throws Exception {
        List<Group> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        List<GroupDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets group data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody GroupCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<GroupDto> findDtos(List<Group> list){
        converter.initList(false);
        List<GroupDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<GroupDto> getDtoResponseEntity(GroupDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public GroupRestAdmin(GroupAdminService service, GroupConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final GroupAdminService service;
    private final GroupConverter converter;





}
