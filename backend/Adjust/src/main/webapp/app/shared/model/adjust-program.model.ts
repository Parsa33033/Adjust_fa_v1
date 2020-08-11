import { Moment } from 'moment';
import { IProgramDevelopment } from 'app/shared/model/program-development.model';
import { IBodyComposition } from 'app/shared/model/body-composition.model';

export interface IAdjustProgram {
  id?: number;
  createdAt?: string;
  expirationDate?: string;
  designed?: boolean;
  nutritionDone?: boolean;
  fitnessDone?: boolean;
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
  nutritionDone: false,
  fitnessDone: false,
  paid: false,
};
