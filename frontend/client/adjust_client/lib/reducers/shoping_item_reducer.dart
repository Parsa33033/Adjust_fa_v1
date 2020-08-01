

import 'package:adjust_client/actions/shoping_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/cart_state.dart';
import 'package:adjust_client/states/shoping_state.dart';
import 'package:adjust_client/states/token_state.dart';

AppState shopingItemReducer(AppState state, dynamic action) {
  if (action is GetShopingItemsAction) {
    ShopingState shopingState = state.shopingState;
    shopingState.items = action.payload.items != null ? action.payload.items : shopingState.items;
    state.shopingState = shopingState;
    return state;
  } else if (action is GetTokenItemsAction) {
    TokenState tokenState = state.tokenState;
    tokenState.items =
    action.payload.items != null ? action.payload.items : tokenState.items;
    state.tokenState = tokenState;
    return state;
  } else if (action is AddToCartAction) {
    CartState cartState = state.cartState;
    cartState.firstName = action.payload.firstName != null ? action.payload.firstName : cartState.firstName;
    cartState.lastName = action.payload.lastName != null ? action.payload.lastName : cartState.lastName;
    cartState.username = action.payload.username != null ? action.payload.username : cartState.username;
    cartState.items = action.payload.items != null ? action.payload.items : cartState.items;
    cartState.id = action.payload.id != null ? action.payload.id : cartState.id;
    state.cartState = cartState;
    return state;
  }  else if (action is RemoveFromCartAction) {
    CartState cartState = state.cartState;
    cartState.firstName = action.payload.firstName != null ? action.payload.firstName : cartState.firstName;
    cartState.lastName = action.payload.lastName != null ? action.payload.lastName : cartState.lastName;
    cartState.username = action.payload.username != null ? action.payload.username : cartState.username;
    cartState.items = action.payload.items != null ? action.payload.items : cartState.items;
    cartState.id = action.payload.id != null ? action.payload.id : cartState.id;
    state.cartState = cartState;
    return state;
  } else {
    return state;
  }
}