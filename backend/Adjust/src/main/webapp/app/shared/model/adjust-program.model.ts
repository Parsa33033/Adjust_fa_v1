import { Moment } from 'moment';
import { IProgramDevelopment } from 'app/shared/model/program-development.model';
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
  programDevelopments?: IProgramDevelopment[];
  bodyCompostions?: IBodyComposition[];
  clientId?: number;
  specialistId?: number;
}

export const defaultValue: Readonly<IAdjustProgram> = {
  designed: false,
  done: false,
  paid: false,
};
