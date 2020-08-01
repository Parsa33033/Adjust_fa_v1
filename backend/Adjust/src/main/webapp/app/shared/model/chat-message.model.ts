export interface IChatMessage {
  id?: number;
  clientId?: number;
  clientUsername?: string;
  specialistId?: number;
  specialistUsername?: string;
  text?: string;
  voiceContentType?: string;
  voice?: any;
  conversationId?: number;
}

export const defaultValue: Readonly<IChatMessage> = {};
