import { Moment } from 'moment';

export interface IAdjustProgram {
  id?: number;
  createdAt?: string;
  expirationDate?: string;
  designed?: boolean;
  done?: boolean;
  paid?: boolean;
  bodyCompostionId?: number;
  fitnessProgramId?: number;
  nutritionProgramId?: number;
  clientId?: number;
  specialistId?: number;
}

export const defaultValue: Readonly<IAdjustProgram> = {
  designed: false,
  done: false,
  paid: false,
};
