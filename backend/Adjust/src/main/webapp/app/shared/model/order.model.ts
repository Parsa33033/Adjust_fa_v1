export interface IOrder {
  id?: number;
  username?: string;
  firstName?: string;
  lastName?: string;
  phoneNumber?: string;
  email?: string;
  country?: string;
  state?: string;
  city?: string;
  address1?: string;
  address2?: string;
  done?: boolean;
  received?: boolean;
  cartId?: number;
}

export const defaultValue: Readonly<IOrder> = {
  done: false,
  received: false,
};
