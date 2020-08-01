

import 'package:adjust_client/model/shoping_item.dart';

class Cart {
  int id;

  String username;

  String firstName;

  String lastName;

  Cart(this.id, this.username, this.firstName, this.lastName);
}

class AdjustCart extends Cart{
  List<ShopingItem> items;

  AdjustCart(int id, String username, String firstName, String lastName, this.items) : super(id, username, firstName, lastName);
}