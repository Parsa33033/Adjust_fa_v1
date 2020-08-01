export interface IAdjustTutorial {
  id?: number;
  title?: string;
  description?: string;
  text?: any;
  thumbnailContentType?: string;
  thumbnail?: any;
  tokenPrice?: number;
  videoId?: number;
}

export const defaultValue: Readonly<IAdjustTutorial> = {};
