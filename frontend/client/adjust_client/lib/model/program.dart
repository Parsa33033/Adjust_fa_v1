

class Program {

  int id;

  DateTime createdAt;

  DateTime expirationDate;

  bool designed;

  bool nutritionDone;

  bool fitnessDone;

  bool paid;

  int fitnessProgramId;

  int nutritionProgramId;

  int clientId;

  int specialistId;

  Program(
      this.id,
      this.createdAt,
      this.expirationDate,
      this.designed,
      this.nutritionDone,
      this.fitnessDone,
      this.paid,
      this.fitnessProgramId,
      this.nutritionProgramId,
      this.clientId,
      this.specialistId);
}