class User {
  String login;
  String firstName;
  String lastName;
  String email;
  String imageUrl;
  String langKey;

  User(this.login, this.firstName, this.lastName, this.email, this.imageUrl,
      this.langKey);
}

class ManagedUser extends User {
  String password;

  ManagedUser(this.password, String login, String firstName, String lastName, String email,
      String imageUrl, String langKey)
      : super(login, firstName, lastName, email, imageUrl, langKey);
}
