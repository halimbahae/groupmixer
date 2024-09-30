import {MemberDto} from '../member/Member.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class GroupDto extends BaseDto{

    public name: string;

    public size: null | number;

    public maxStudents: null | number;

    public changeInterval: string;

     public members: Array<MemberDto>;


    constructor() {
        super();

        this.name = '';
        this.size = null;
        this.maxStudents = null;
        this.changeInterval = '';
        this.members = new Array<MemberDto>();

        }

}
