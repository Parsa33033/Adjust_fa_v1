

class Shoping {
  List<ShopingItem> items;

  Shoping(this.items);
}

class ShopingItem {
  int id;
  String name;
  String description;
  double token;
  double price;

  String image;
  String imageContentType;

  bool orderable;

  int cartId;

  ShopingItem(this.id, this.name, this.description, this.token, this.price,
      this.image, this.imageContentType, this.orderable, this.cartId);
}