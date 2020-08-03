import { Moment } from 'moment';

export interface IProgramDevelopment {
  id?: number;
  date?: string;
  workoutScore?: number;
  fitnessScore?: number;
  adjustProgramId?: number;
}

export const defaultValue: Readonly<IProgramDevelopment> = {};
