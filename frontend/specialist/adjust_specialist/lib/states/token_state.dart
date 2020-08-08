import 'package:adjust_specialist/actions/shoping_action.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/shoping_item.dart';
import 'package:adjust_specialist/model/token.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

final TokenState tokenStateInit = TokenState(List());

class TokenState extends Token {
  TokenState(List<TokenItem> items) : super(items);
}


void setTokenState(BuildContext context, Token token) {

  TokenState tokenState = TokenState(token.items);
  try {
    StoreProvider.of<AppState>(context).dispatch(GetTokenItemsAction(payload: tokenState));
  } catch (e) {
    store.dispatch(GetTokenItemsAction(payload: tokenState));
  }
}
