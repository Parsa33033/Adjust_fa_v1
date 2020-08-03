import { Moment } from 'moment';
import { IBodyComposition } from 'app/shared/model/body-composition.model';

export interface IAdjustProgram {
  id?: number;
  createdAt?: string;
  expirationDate?: string;
  designed?: boolean;
  done?: boolean;
  paid?: boolean;
  fitnessProgramId?: number;
  nutritionProgramId?: number;
  bodyCompostions?: IBodyComposition[];
  clientId?: number;
  specialistId?: number;
}

export const defaultValue: Readonly<IAdjustProgram> = {
  designed: false,
  done: false,
  paid: false,
};
