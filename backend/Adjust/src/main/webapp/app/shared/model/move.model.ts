import { MuscleType } from 'app/shared/model/enumerations/muscle-type.model';

export interface IMove {
  id?: number;
  name?: string;
  muscleName?: string;
  muscleType?: MuscleType;
  equipment?: string;
  pictureContentType?: string;
  picture?: any;
  adjustMoveId?: number;
  exerciseId?: number;
}

export const defaultValue: Readonly<IMove> = {};
