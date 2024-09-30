import {GroupDto} from '../group/Group.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class MemberDto extends BaseDto{

    public name: string;

    public role: string;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;

    public email: string;

   public enabled: null | boolean;

    public group: GroupDto ;


    constructor() {
        super();

        this.name = '';
        this.role = '';
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;

        }

}
