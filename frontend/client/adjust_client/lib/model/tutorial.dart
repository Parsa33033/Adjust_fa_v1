
class TutorialList {
  List<Tutorial> items;

  TutorialList(this.items);
}

class Tutorial {
  int id;

  String title;

  String description;

  String text;

  String thumbnail;

  String thumbnailContentType;
  double tokenPrice;


  int videoId;

  int clientId;

  Tutorial(
      this.id,
      this.title,
      this.description,
      this.text,
      this.thumbnail,
      this.thumbnailContentType,
      this.tokenPrice,
      this.videoId,
      this.clientId);
}