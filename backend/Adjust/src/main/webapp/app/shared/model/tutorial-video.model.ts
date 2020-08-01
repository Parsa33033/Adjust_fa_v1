export interface ITutorialVideo {
  id?: number;
  adjustTutorialVideoId?: number;
  contentContentType?: string;
  content?: any;
  tutorialId?: number;
}

export const defaultValue: Readonly<ITutorialVideo> = {};
