import { IExercise } from 'app/shared/model/exercise.model';

export interface IWorkout {
  id?: number;
  dayNumber?: number;
  exercises?: IExercise[];
  programId?: number;
}

export const defaultValue: Readonly<IWorkout> = {};
