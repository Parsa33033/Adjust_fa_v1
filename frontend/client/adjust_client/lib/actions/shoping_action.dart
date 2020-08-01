import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/order_dto.dart';
import 'package:adjust_client/dto/shoping_item_dto.dart';
import 'package:adjust_client/dto/token_dto.dart';
import 'package:adjust_client/model/cart.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/shoping_item.dart';
import 'package:adjust_client/model/token.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/cart_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/shoping_state.dart';
import 'package:adjust_client/states/token_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

import 'jwt.dart';

class GetShopingItemsAction {
  ShopingState payload;

  GetShopingItemsAction({this.payload});
}

class GetTokenItemsAction {
  TokenState payload;

  GetTokenItemsAction({this.payload});
}

class AddToCartAction {
  CartState payload;

  AddToCartAction({this.payload});
}

class RemoveFromCartAction {
  CartState payload;

  RemoveFromCartAction({this.payload});
}

Future<int> getShopingItems(BuildContext context) async {
  http.Response response = await http.get(
    CLIENT_SHOPING_URL,
  );
  if (response.statusCode == HttpStatus.ok) {
    List m = jsonDecode(utf8.decode(response.bodyBytes));
    List<ShopingItem> shopingList =
      m.map((e) => ShopingItemDTO.fromJson(e)).toList();
    Shoping shoping = Shoping(shopingList);
    setShopingState(context, shoping);
    return 1;
  }
  return 0;
}

Future<int> getTokenItems(BuildContext context) async {
  http.Response response = await http.get(
    CLIENT_TOKENS_URL,
  );
  if (response.statusCode == HttpStatus.ok) {
    List m = jsonDecode(utf8.decode(response.bodyBytes));
    List content = m.map((e) {
      return e;
    }).toList();

    List<TokenItem> tokenList =
        content.map((e) => TokenItemDTO.fromJson(e)).toList();
    Token token = Token(tokenList);
    setTokenState(context, token);
    return 1;
  }
  return 0;
}

Future<int> buyToken(BuildContext context, int shopingItemId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.post(BUY_TOKEN_URL,
      headers: headers, body: shopingItemId.toString());
  if (response.statusCode == HttpStatus.ok) {
    double token = double.parse(response.body);
    Client client = Client(
        null, null, null, null, null, null, null, token, null, null, null);
    setClientState(context, client);
    return 1;
  }
  return 0;
}

void addToCart(BuildContext context, AdjustCart cart) {
  CartState cartState = CartState(
      cart.id, cart.username, cart.firstName, cart.lastName, cart.items);
  StoreProvider.of<AppState>(context)
      .dispatch(AddToCartAction(payload: cartState));
}

Future<int> order(BuildContext context, OrderDTO orderDTO) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  String content = jsonEncode(orderDTO.toJson());

  http.Response response = await http.post(ORDER_URL,
      body: content, headers: headers, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    return 1;
  }
  return 0;
}
