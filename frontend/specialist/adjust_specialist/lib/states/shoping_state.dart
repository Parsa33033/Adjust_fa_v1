import 'package:adjust_specialist/actions/shoping_action.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/shoping_item.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

final ShopingState shopingStateInit = ShopingState(List());

class ShopingState extends Shoping {
  ShopingState(List<ShopingItem> items) : super(items);
}


void setShopingState(BuildContext context, Shoping shoping) {

  ShopingState shopingState = ShopingState(shoping.items);
  try {
    StoreProvider.of<AppState>(context).dispatch(GetShopingItemsAction(payload: shopingState));
  } catch (e) {
    store.dispatch(GetShopingItemsAction(payload: shopingState));
  }
}

