import { Moment } from 'moment';

export interface IBodyComposition {
  id?: number;
  createdAt?: string;
  height?: number;
  weight?: number;
  bmi?: number;
  bodyCompositionFileContentType?: string;
  bodyCompositionFile?: any;
  bloodTestFileContentType?: string;
  bloodTestFile?: any;
  programId?: number;
}

export const defaultValue: Readonly<IBodyComposition> = {};
