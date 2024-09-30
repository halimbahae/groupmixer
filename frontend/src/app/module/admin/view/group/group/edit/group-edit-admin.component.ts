import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {GroupAdminService} from 'src/app/shared/service/admin/group/GroupAdmin.service';
import {GroupDto} from 'src/app/shared/model/group/Group.model';
import {GroupCriteria} from 'src/app/shared/criteria/group/GroupCriteria.model';


import {MemberDto} from 'src/app/shared/model/member/Member.model';
import {MemberAdminService} from 'src/app/shared/service/admin/member/MemberAdmin.service';

@Component({
  selector: 'app-group-edit-admin',
  templateUrl: './group-edit-admin.component.html'
})
export class GroupEditAdminComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;


    private _membersElement = new MemberDto();





    constructor(private service: GroupAdminService , private memberService: MemberAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {

    }

    public prepareEdit() {
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new GroupDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateMembers(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
    }

   public addMembers() {
        if( this.item.members == null )
            this.item.members = new Array<MemberDto>();
       this.validateMembers();
       if (this.errorMessages.length === 0) {
            if(this.membersElement.id == null){
                this.item.members.push(this.membersElement);
            }else{
                const index = this.item.members.findIndex(e => e.id == this.membersElement.id);
                this.item.members[index] = this.membersElement;
            }
          this.membersElement = new MemberDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs', detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
   }

    public deleteMembers(p: MemberDto) {
        this.item.members.forEach((element, index) => {
            if (element === p) { this.item.members.splice(index, 1); }
        });
    }

    public editMembers(p: MemberDto) {
        this.membersElement = {... p};
        this.activeTab = 0;
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
    }






    get membersElement(): MemberDto {
        if( this._membersElement == null )
            this._membersElement = new MemberDto();
         return this._membersElement;
    }

    set membersElement(value: MemberDto) {
        this._membersElement = value;
    }



	get items(): Array<GroupDto> {
        return this.service.items;
    }

    set items(value: Array<GroupDto>) {
        this.service.items = value;
    }

    get item(): GroupDto {
        return this.service.item;
    }

    set item(value: GroupDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): GroupCriteria {
        return this.service.criteria;
    }

    set criteria(value: GroupCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }


}
