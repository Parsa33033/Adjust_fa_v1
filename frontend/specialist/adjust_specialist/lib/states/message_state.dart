import 'package:adjust_specialist/model/message.dart';

final MessagesState messagesStateInit = MessagesState(messages: List());

class MessagesState {
  List<MessageState> messages;

  MessagesState({this.messages});
}


class MessageState extends Message {
  MessageState(String sender, String receiver, String message, int senderId,
      int receiverId)
      : super(sender, receiver, message, senderId, receiverId);
}
