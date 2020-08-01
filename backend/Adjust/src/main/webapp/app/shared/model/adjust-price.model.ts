export interface IAdjustPrice {
  id?: number;
  name?: string;
  token?: number;
  price?: number;
}

export const defaultValue: Readonly<IAdjustPrice> = {};
