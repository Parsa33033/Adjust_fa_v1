import { Moment } from 'moment';

export interface IProgramDevelopment {
  id?: number;
  date?: string;
  nutritionScore?: number;
  fitnessScore?: number;
  adjustProgramId?: number;
}

export const defaultValue: Readonly<IProgramDevelopment> = {};
