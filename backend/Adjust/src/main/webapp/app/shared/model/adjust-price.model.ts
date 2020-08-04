import { PurchaseOption } from 'app/shared/model/enumerations/purchase-option.model';

export interface IAdjustPrice {
  id?: number;
  name?: string;
  option?: PurchaseOption;
  token?: number;
  price?: number;
}

export const defaultValue: Readonly<IAdjustPrice> = {};
