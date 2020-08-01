import 'package:adjust_client/actions/shoping_action.dart';
import 'package:adjust_client/model/shoping_item.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

final ShopingState shopingStateInit = ShopingState(List());

class ShopingState extends Shoping {
  ShopingState(List<ShopingItem> items) : super(items);
}


void setShopingState(BuildContext context, Shoping shoping) {

  ShopingState shopingState = ShopingState(shoping.items);
  StoreProvider.of<AppState>(context).dispatch(GetShopingItemsAction(payload: shopingState));
}

