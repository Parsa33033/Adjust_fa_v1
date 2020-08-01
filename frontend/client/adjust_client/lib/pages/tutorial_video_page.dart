import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/tutorial.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:cached_video_player/cached_video_player.dart';
import 'package:flick_video_player/flick_video_player.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:video_player/video_player.dart';
import 'package:flutter/cupertino.dart';

class TutorialVideoPage extends StatefulWidget {
  Tutorial tutorial;

  TutorialVideoPage(this.tutorial);

  @override
  _TutorialVideoPageState createState() => _TutorialVideoPageState();
}

class _TutorialVideoPageState extends State<TutorialVideoPage> {
  FlickManager flickManager;
  VideoPlayerController  _controller;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    initVideoPlayer();
  }

  void initVideoPlayer() async {
    _controller = VideoPlayerController.network(
        GET_CLIENT_TUTORIALS_VIDEO_URL +
            "?video-id=${this.widget.tutorial.videoId}&username=${store.state.clientState.username}&jwt=${store.state.authenticationState.jwt}");
    flickManager = FlickManager(
        videoPlayerController: _controller
    );
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
                this.widget.tutorial.title,
                style: TextStyle(
                    fontFamily: "Iransans", fontSize: 20, color: FONT_COLOR),
              ),
            ),
          ),
          backgroundColor: LIGHT_YELLOW_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            return Container(
                child: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  FlickVideoPlayer(
                    flickManager: flickManager,
                  ),
                  Container(
                    padding: EdgeInsets.all(20),
                    child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          this.widget.tutorial.title,
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 18,
                              color: FONT_COLOR),
                        )),
                  ),
                  Container(
                    padding: EdgeInsets.all(10),
                    child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          this.widget.tutorial.text,
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        )),
                  )
                ],
              ),
            ));
          },
        ));
  }

  @override
  void dispose() {
    // TODO: implement dispose
    flickManager.dispose();
    super.dispose();
  }
}
