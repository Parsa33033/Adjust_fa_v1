import 'package:adjust_client/model/cart.dart';
import 'package:adjust_client/model/shoping_item.dart';

final CartState cartStateInit = CartState(0, "", "", "", List());

class CartState extends Cart {
  List<ShopingItem> items;
  CartState(int id, String username, String firstName, String lastName, this.items)
      : super(id, username, firstName, lastName);
}
