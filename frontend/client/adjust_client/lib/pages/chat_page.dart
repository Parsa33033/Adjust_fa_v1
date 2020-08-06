import 'dart:async';
import 'dart:io';

import 'package:adjust_client/config/stomp.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/dto/message_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/Message.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:dash_chat/dash_chat.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';


class ChatPage extends StatefulWidget {
  SpecialistState specialistState;
  ChatPage({this.specialistState});

  @override
  _ChatPageState createState() => _ChatPageState();
}


class _ChatPageState extends State<ChatPage> {
  final GlobalKey<DashChatState> _chatViewKey = GlobalKey<DashChatState>();

  final ChatUser user = ChatUser(
    name: "Fayeed",
    firstName: "Fayeed",
    lastName: "Pawaskar",
    uid: "12345678",
    avatar: "https://www.wrappixel.com/ampleadmin/assets/images/users/4.jpg",
  );

  final ChatUser otherUser = ChatUser(
    name: "Mrfatty",
    uid: "25649654",
  );

  List<ChatMessage> messages = List<ChatMessage>();
  var m = List<ChatMessage>();

  var i = 0;

  StompInstance stompInstance;

  @override
  void initState() {
    super.initState();

    mainStompClient.subscribe(destination: "/topic/"+ StompInstance.getUsername() +"/reply", headers: StompInstance.getHeaders(), callback: (StompFrame frame) {
      print("------->" + frame.body.toString());
      ChatMessage chatMessage = ChatMessage(
          text: frame.body.toString(),
          user: otherUser
      );
      messages.add(chatMessage);
    });

  }

  void systemMessage() {
    Timer(Duration(milliseconds: 300), () {
      if (i < 6) {
        setState(() {
          messages = [...messages, m[i]];
        });
        i++;
      }
      Timer(Duration(milliseconds: 300), () {
        _chatViewKey.currentState.scrollController
          ..animateTo(
            _chatViewKey.currentState.scrollController.position.maxScrollExtent,
            curve: Curves.easeOut,
            duration: const Duration(milliseconds: 300),
          );
      });
    });
  }

  void onSend(ChatMessage message) async {
    AppState state = store.state;
    String username = state.userState.login;
    int clientId = state.clientState.id;
    int specialistId = this.widget.specialistState.id;

    MessageDTO messageDTO = MessageDTO(username, this.widget.specialistState.username, message.text, clientId, specialistId);
    StompInstance.sendMessage(context, mainStompClient, messageDTO);

//    stompInstance.sendMessage(context, messageDTO);
//    stompInstance.sendMessage(context, stompClient, messageDTO);

//    var documentReference = Firestore.instance
//        .collection('messages')
//        .document(DateTime.now().millisecondsSinceEpoch.toString());
//
//    await Firestore.instance.runTransaction((transaction) async {
//      await transaction.set(
//        documentReference,
//        message.toJson(),
//      );
//    });

    setState(() {
      messages = [...messages, message];
      print(messages.length);
    });

//    if (i == 0) {
//      systemMessage();
//      Timer(Duration(milliseconds: 600), () {
//        systemMessage();
//      });
//    } else {
//      systemMessage();
//    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Align(
            alignment: Alignment.centerRight,
            child: Directionality(
              textDirection: TextDirection.rtl,
              child: Text(
                "سوال از متخصص",
                style: TextStyle(
                    fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
              ),
            ),
          ),
          backgroundColor: YELLOW_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: Container(
          alignment: Alignment.centerRight,
          child: DashChat(
            inputTextDirection: TextDirection.rtl,
            key: _chatViewKey,
            inverted: false,
            onSend: onSend,
            sendOnEnter: true,
            textInputAction: TextInputAction.send,
            user: user,
            inputDecoration:
            InputDecoration.collapsed(hintText: "افزودن پیام"),
            dateFormat: DateFormat('yyyy-MMM-dd'),
            timeFormat: DateFormat('HH:mm'),
            messages: messages,
            showUserAvatar: false,
            showAvatarForEveryMessage: false,
            scrollToBottom: true,
            onPressAvatar: (ChatUser user) {
              print("OnPressAvatar: ${user.name}");
            },
            onLongPressAvatar: (ChatUser user) {
              print("OnLongPressAvatar: ${user.name}");
            },
            inputMaxLines: 5,
            messageContainerPadding: EdgeInsets.only(left: 5.0, right: 5.0),
            alwaysShowSend: true,
            inputTextStyle: TextStyle(fontSize: 16.0, fontFamily: "Iransans"),
            inputContainerStyle: BoxDecoration(
              border: Border.all(width: 0.0),
              color: Colors.white,
            ),
            onQuickReply: (Reply reply) {
              setState(() {
                messages.add(ChatMessage(
                    text: reply.value, createdAt: DateTime.now(), user: user, ));
                messages = [...messages];
              });

              Timer(Duration(milliseconds: 300), () {
                _chatViewKey.currentState.scrollController
                  ..animateTo(
                    _chatViewKey
                        .currentState.scrollController.position.maxScrollExtent,
                    curve: Curves.easeOut,
                    duration: const Duration(milliseconds: 300),
                  );

                if (i == 0) {
                  systemMessage();
                  Timer(Duration(milliseconds: 600), () {
                    systemMessage();
                  });
                } else {
                  systemMessage();
                }
              });
            },
            onLoadEarlier: () {
              print("laoding...");
            },
            shouldShowLoadEarlier: false,
            showTraillingBeforeSend: true,
          ),
        )
    );
  }
}
