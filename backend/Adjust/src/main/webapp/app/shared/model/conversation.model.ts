import { IChatMessage } from 'app/shared/model/chat-message.model';

export interface IConversation {
  id?: number;
  clientId?: number;
  specialistId?: number;
  messages?: IChatMessage[];
}

export const defaultValue: Readonly<IConversation> = {};
