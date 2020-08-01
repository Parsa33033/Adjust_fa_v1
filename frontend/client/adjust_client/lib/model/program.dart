

class Program {

  int id;

  DateTime createdAt;

  DateTime expirationDate;

  bool designed;

  bool done;

  bool paid;

  int bodyCompostionId;

  int fitnessProgramId;

  int nutritionProgramId;

  int clientId;

  int specialistId;

  Program(
      this.id,
      this.createdAt,
      this.expirationDate,
      this.designed,
      this.done,
      this.paid,
      this.bodyCompostionId,
      this.fitnessProgramId,
      this.nutritionProgramId,
      this.clientId,
      this.specialistId);
}