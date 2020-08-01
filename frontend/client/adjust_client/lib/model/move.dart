class Move {
  int id;

  String name;

  String muscleName;

  MuscleType muscleType;

  String equipment;

  String picture;

  String pictureContentType;
  int adjustMoveId;

  Move(this.id, this.name, this.muscleName, this.muscleType, this.equipment,
      this.picture, this.pictureContentType, this.adjustMoveId);
}

enum MuscleType {
  CHEST,
  SHOULDER,
  TRAPEZOID,
  BACK,
  LATERAL,
  BICEP,
  TRICEP,
  FOREARM,
  LEG,
  HAMSTRING,
  GLUTES,
  CALVES,
  ABS,
  MISC
}
