export interface IChatMessage {
  id?: number;
  clientId?: number;
  clientUsername?: string;
  specialistId?: number;
  specialistUsername?: string;
  sender?: string;
  receiver?: string;
  text?: any;
  voiceContentType?: string;
  voice?: any;
  conversationId?: number;
}

export const defaultValue: Readonly<IChatMessage> = {};
