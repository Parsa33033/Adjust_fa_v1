export interface IAdjustShopingItem {
  id?: number;
  name?: string;
  description?: string;
  token?: number;
  price?: number;
  imageContentType?: string;
  image?: any;
  orderable?: boolean;
}

export const defaultValue: Readonly<IAdjustShopingItem> = {
  orderable: false,
};
