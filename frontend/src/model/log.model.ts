import {Project} from './project.model';
import {User} from './user.model';

export class Log{
  id: number;
  date: Date;
  project: Project;
  createdBy: User;
  hours: any;
}
