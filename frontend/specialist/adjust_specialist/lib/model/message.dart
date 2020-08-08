

class Message {
  String sender;
  String receiver;
  String message;
  int clientId;
  int specialistId;

  Message(this.sender, this.receiver, this.message, this.clientId,
      this.specialistId);
}
