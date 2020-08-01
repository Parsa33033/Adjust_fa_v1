import { Moment } from 'moment';
import { ITutorial } from 'app/shared/model/tutorial.model';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IAdjustClient {
  id?: number;
  username?: string;
  firstName?: string;
  lastName?: string;
  birthDate?: string;
  age?: number;
  gender?: Gender;
  token?: number;
  score?: number;
  imageContentType?: string;
  image?: any;
  tutorials?: ITutorial[];
  programs?: IAdjustProgram[];
  conversationId?: number;
}

export const defaultValue: Readonly<IAdjustClient> = {};
