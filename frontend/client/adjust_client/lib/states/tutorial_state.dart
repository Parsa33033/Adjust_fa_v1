import 'package:adjust_client/model/tutorial.dart';

final TutorialListState tutorialListStateInit = TutorialListState(List());
final ClientTutorialsState clientTutorialsStateInit =
    ClientTutorialsState(List());
final TutorialState tutorialStateInit =
    TutorialState(null, "", "", "", "", "", 0, null, null);

class ClientTutorialsState extends TutorialList {
  ClientTutorialsState(List<Tutorial> items) : super(items);
}

class TutorialListState extends TutorialList {
  TutorialListState(List<Tutorial> items) : super(items);
}

class TutorialState extends Tutorial {
  TutorialState(
      int id,
      String title,
      String description,
      String text,
      String thumbnail,
      String thumbnailContentType,
      double tokenPrice,
      int videoId,
      int clientId)
      : super(id, title, description, text, thumbnail, thumbnailContentType,
            tokenPrice, videoId, clientId);
}
