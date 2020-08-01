
class Order {
  int id;

  String username;

  String firstName;

  String lastName;

  String phoneNumber;

  String email;

  String country;

  String state;

  String city;

  String address1;

  String address2;

  bool done;

  bool received;

  int cartId;

  Order(
      this.id,
      this.username,
      this.firstName,
      this.lastName,
      this.phoneNumber,
      this.email,
      this.country,
      this.state,
      this.city,
      this.address1,
      this.address2,
      this.done,
      this.received,
      this.cartId);
}