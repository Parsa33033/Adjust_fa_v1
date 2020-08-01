export interface IShopingItem {
  id?: number;
  name?: string;
  description?: string;
  token?: number;
  price?: number;
  imageContentType?: string;
  image?: any;
  orderable?: boolean;
  cartId?: number;
}

export const defaultValue: Readonly<IShopingItem> = {
  orderable: false,
};
