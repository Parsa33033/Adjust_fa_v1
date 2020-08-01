import { IShopingItem } from 'app/shared/model/shoping-item.model';

export interface ICart {
  id?: number;
  username?: string;
  firstName?: string;
  lastName?: string;
  items?: IShopingItem[];
  itemId?: number;
}

export const defaultValue: Readonly<ICart> = {};
