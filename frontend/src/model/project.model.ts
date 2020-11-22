import {User} from './user.model';

export class Project {
  id: number;
  name: string;
  date: Date;
  deadline: Date;
  members: User[];
  createdBy: User;
  hours: any;
}
