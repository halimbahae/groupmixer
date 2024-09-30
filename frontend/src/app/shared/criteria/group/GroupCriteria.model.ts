import {MemberCriteria} from '../member/MemberCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class GroupCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
     public size: number;
     public sizeMin: number;
     public sizeMax: number;
     public maxStudents: number;
     public maxStudentsMin: number;
     public maxStudentsMax: number;
    public changeInterval: string;
    public changeIntervalLike: string;
      public members: Array<MemberCriteria>;

}
