import { Moment } from 'moment';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IBodyComposition {
  id?: number;
  createdAt?: string;
  height?: number;
  weight?: number;
  bmi?: number;
  wrist?: number;
  waist?: number;
  lbm?: number;
  muscleMass?: number;
  muscleMassPercentage?: number;
  fatMass?: number;
  fatMassPercentage?: number;
  gender?: Gender;
  age?: number;
  bodyImageContentType?: string;
  bodyImage?: any;
  bodyCompositionFileContentType?: string;
  bodyCompositionFile?: any;
  bloodTestFileContentType?: string;
  bloodTestFile?: any;
  programId?: number;
}

export const defaultValue: Readonly<IBodyComposition> = {};
