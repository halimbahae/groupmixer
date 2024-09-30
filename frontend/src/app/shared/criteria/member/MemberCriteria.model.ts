import {GroupCriteria} from '../group/GroupCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class MemberCriteria extends BaseCriteria {

    public name: string;
    public nameLike: string;
    public role: string;
    public roleLike: string;
    public credentialsNonExpired: null | boolean;
    public accountNonExpired: null | boolean;
    public username: string;
    public usernameLike: string;
    public passwordChanged: null | boolean;
    public accountNonLocked: null | boolean;
    public password: string;
    public passwordLike: string;
    public email: string;
    public emailLike: string;
    public enabled: null | boolean;

}
