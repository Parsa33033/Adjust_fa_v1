class Token {
  List<TokenItem> items;

  Token(this.items);
}

class TokenItem {
  int id;
  String name;
  String description;
  double token;
  double price;

  String image;
  String imageContentType;

  bool orderable;

  TokenItem(this.id, this.name, this.description, this.token, this.price,
      this.image, this.imageContentType, this.orderable);
}