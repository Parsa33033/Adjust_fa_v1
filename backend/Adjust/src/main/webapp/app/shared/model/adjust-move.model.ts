import { MuscleType } from 'app/shared/model/enumerations/muscle-type.model';

export interface IAdjustMove {
  id?: number;
  name?: string;
  muscleName?: string;
  muscleType?: MuscleType;
  equipment?: string;
  pictureContentType?: string;
  picture?: any;
}

export const defaultValue: Readonly<IAdjustMove> = {};
