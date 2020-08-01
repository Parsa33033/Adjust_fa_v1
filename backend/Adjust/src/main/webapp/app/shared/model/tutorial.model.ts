export interface ITutorial {
  id?: number;
  title?: string;
  description?: string;
  text?: any;
  thumbnailContentType?: string;
  thumbnail?: any;
  tokenPrice?: number;
  adjustTutorialId?: number;
  videoId?: number;
  clientId?: number;
}

export const defaultValue: Readonly<ITutorial> = {};
