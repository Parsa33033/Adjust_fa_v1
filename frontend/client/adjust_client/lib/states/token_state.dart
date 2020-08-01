import 'package:adjust_client/actions/shoping_action.dart';
import 'package:adjust_client/model/shoping_item.dart';
import 'package:adjust_client/model/token.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

final TokenState tokenStateInit = TokenState(List());

class TokenState extends Token {
  TokenState(List<TokenItem> items) : super(items);
}


void setTokenState(BuildContext context, Token token) {

  TokenState tokenState = TokenState(token.items);
  StoreProvider.of<AppState>(context).dispatch(GetTokenItemsAction(payload: tokenState));
}
