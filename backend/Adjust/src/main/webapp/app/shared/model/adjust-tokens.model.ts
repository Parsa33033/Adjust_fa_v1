export interface IAdjustTokens {
  id?: number;
  name?: string;
  description?: string;
  token?: number;
  price?: number;
  imageContentType?: string;
  image?: any;
}

export const defaultValue: Readonly<IAdjustTokens> = {};
