import {Role} from './role.enum';

export class User{
  id: number;
  name: string;
  surname: string;
  email: string;
  password: string;
  profilePicture: string;
  role: Role;
}
