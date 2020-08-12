import 'package:adjust_specialist/model/client.dart';
import 'package:adjust_specialist/model/move.dart';
import 'package:adjust_specialist/model/workout.dart';
import 'package:enum_to_string/enum_to_string.dart';

final String RAMSARI = "دکتر سوسن رامسری";

final String LOGIN = "ورود";
final String REGISTER = "ثبت نام";
final String FORGOTTEN_PASS = "رمز عبور را فراموش کردم!";

final String EMAIL = "ایمیل";
final String PASSWORD = "رمز عبور";
final String PASSWORD_CONFIRM = "تکرار رمز عبور";

final String EMPTY = "لطفا پر کنید";
final String WRONG_EMAIL = "ایمیل اشتباه وارد شده";
final String WRONG_PASSWORD =
    "رمز عبور باید دارای حرف و عدد و بیش از 6 حرف باشد";
final String PASS_NOT_MATCH = "رمز عبور و تکرار آن یکسان نمیباشند";
final String REGISTRATION_FAILED =
    "خطا در ثبت نام! لطفا از صحت عدم ثبت نام مجدد و اتصال اینترنت خود اطمینان حاصل فرمایید.";
final String LOGIN_FAILED =
    "خطا در ورود! لطفا از صحت نام کاربری و رمز عبور وارد شده اطمینان حاصل فرمایید.";
final String RECOVER_PASSWORD =
    " ایمیل خود را که با آن ثبت نام کرده اید وارد کرده و دکمه ی تایید را فشار دهید، سپس برای برای تغییر رمز عبور خود به ایمیل خود مراجعه کنید.";
final String SUCCESS = "عملیات با موفقیت انجام شد!";
final String FAILURE = "خطا در پردازش!";
final String SET_TO_DEFAULT = "برگشت به حالت پیش فرض";
final String CART_EMPTY = "سبد خرید شما خالی میباشد.";
final String SURE_TO_EXIT = "با خروج از برنامه برای شروع مجدد باید ایمیل و رمز عبور خود را مجدداً وارد کنید. آیا مایل به خروج از برنامه میباشید؟";
final String NOT_ENOUGHT_TOKEN = "مقدار توکن کافی نمیباشد";
final String CLIENT_HAS_TUTORIAL = "شما این آموزش را دارید";
final String SURE_WITH_DECISION = "آیا از انتخاب خود اطمینان دارید؟";

final String BACK = "برگرد";
final String OK = "تایید";
final String CANCEL = "لغو";
final String LOGOUT = "خروج";
final String PROFILE = "پروفایل";
final String BUY = "پرداخت";

final String BIRTH = "تاریخ تولد";
final String FIRST_NAME = "نام";
final String LAST_NAME = "نام خانوادگی";
final String GENDER = "جنسیت";
final String AGE = "سن";
final String UPDATE = "بروزرسانی";
final String DEFAULT = "پیش فرض";
final String DEGREE = "درجه";
final String FIELD = "رشته";
final String RESUME = "رزومه";
final String BUSY = "مشغول";

final String CALORIES = "کالری";
final String PROTEIN_PERCENTAGE = "درصد پروتئین";
final String CARBS_PERCENTAGE = "درصد کربوهیدرات";
final String FAT_PERCENTAGE = "درصد چربی";
final String DESCRIPTION = "توضیحات";

final Map<String, String> BUSY_LIST = Map<String, String>()
  ..putIfAbsent("true", () => "بله")
  ..putIfAbsent("false", () => "خیر");

final Map<String, String> GENDER_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(Gender.FEMALE), () => "زن")
  ..putIfAbsent(EnumToString.parse(Gender.MALE), () => "مرد");


final Map<String, String> MUSCLE_TYPE_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(MuscleType.CHEST), () => 'سینه')
  ..putIfAbsent(EnumToString.parse(MuscleType.BACK), () => 'پشت')
  ..putIfAbsent(EnumToString.parse(MuscleType.TRAPEZOID), () => 'کول')
  ..putIfAbsent(EnumToString.parse(MuscleType.BICEP), () => 'جلوبازو')
  ..putIfAbsent(EnumToString.parse(MuscleType.TRICEP), () => 'پشت بازو')
  ..putIfAbsent(EnumToString.parse(MuscleType.ABS), () => 'شکم')
  ..putIfAbsent(EnumToString.parse(MuscleType.CALVES), () => 'ساق پا')
  ..putIfAbsent(EnumToString.parse(MuscleType.FOREARM), () => 'ساعد')
  ..putIfAbsent(EnumToString.parse(MuscleType.GLUTES), () => 'باسن')
  ..putIfAbsent(EnumToString.parse(MuscleType.HAMSTRING), () => 'پشت پا')
  ..putIfAbsent(EnumToString.parse(MuscleType.LATERAL), () => 'زیر بغل')
  ..putIfAbsent(EnumToString.parse(MuscleType.SHOULDER), () => 'شانه')
  ..putIfAbsent(EnumToString.parse(MuscleType.LEG), () => 'پا')
  ..putIfAbsent(EnumToString.parse(MuscleType.MISC), () => 'سایر عضلات');

final String PHONE_NUMBER = "شماره تلفن";
final String COUNTRY = "کشور";
final String STATE = "استان";
final String CITY = "شهر";
final String ADDRESS = "آدرس";

final String ORDER = "سفارش";

final String HEIGHT = "قد";
final String WEIGHT = "وزن";
final String WRIST = "دور مچ";
final String WAIST = "دور کمر";
final String MUSCLE_MASS = "وزن عضله(muscle mass)";
final String FAT_MASS = "وزن چربی(fat mass)";


final String CALORY = "کالری";
final String PROTEIN = "پروتئین";
final String FAT = "چربی";
final String CARBS = "کربوهیدرات";

final String SET = "ست ها";
final String MINREP = "حداقل تعداد";
final String MAXREP = "حداکثر تعداد";
final String MOVE = "حرکت";
final String EQUIPMENT = "تجهیزات";
final String MUSCLE = "عضله";


final String TERMS_BODY_COMPOSITION = "توجه داشته باشید که ورودی هایی که علامت (*) دارند باید پر شوند ولی تمام ورودی ها نیاز به پر کردن نداشته ولی پر نکردن کامل مشخصات بدنی خود باعث کمبود دقت در برنامه های طراحی شده خواهد شد. در نتیجه مسوولیت عدم پر کردن کامل مشخصات بدنی به عهده ی خود شخص میباشد. ضمنا اعداد وارد شده برای مشخصات بدنی به صورت اعشار بوده (برای مثال 12.50 کیلوگرم برابر با 12 کیلو و پانصد گرم میباشد.)";
final String ASSESS_NOTE = "توجه داشته باشید در طول روز فقط یک بار میتوانید عملکرد خود را ارزیابی کرده و امتیاز دریافت کنید. پس ترجیحا در انتهای روز ارزیابی را انجام دهید. تلاش کنید که با خود نیز صادق بوده و ارزیابی روز خود را آنطور که میبینید انجام دهید.";


final List<String> MEAL_NAMES = ["صبحانه", "ناهار", "عصرانه", "شام", "میان وعده اول", "میان وعده دوم", "میان وعده سوم", "میان وعده چهارم", "میان وعده پنجم", "میان وعده ششم"];
final String CHOOSE_NUTRITION = "انتخاب مواد غدایی برای برنامه";
final String CHOOSE_MEAL = "انتخاب وعده غذایی برای برنامه";
final String CHOOSE_UNIT = "انتخاب واحد برای مواد غذایی";
final String WANT_MEAL_REMOVED = "آیا مایل به حذف وعده ی غذایی هستید؟";
final String ATLEAST_ONE_NUTRITION = "لطفا حداقل یک ماده غذایی انتخاب کنید!";
final String ATLEAST_ONE_MEAL = "لطفا حداقل یک وعده غذایی انتخاب کنید!";


final Map<String, String> WORKOUT_TYPE_LIST = Map<String, String>()
  ..putIfAbsent(EnumToString.parse(WorkoutType.POWER), () => "قدرتی")
  ..putIfAbsent(EnumToString.parse(WorkoutType.BODYBUILDING_MASS), () => "بدنسازی حجمی")
  ..putIfAbsent(EnumToString.parse(WorkoutType.BODYBUILDING), () => "بدنسازی")
  ..putIfAbsent(EnumToString.parse(WorkoutType.BODYBUILDING_CUT), () => "بدنسازی تفکیکی")
  ..putIfAbsent(EnumToString.parse(WorkoutType.ENDURANCE), () => "استقامتی")
  ..putIfAbsent(EnumToString.parse(WorkoutType.CARDIO), () => "هوازی");

final String WORKOUT_TYPE = "مدل تمرین";
final String WORKOUT_DAY_NUMBERS = "تعداد روزهای تمرینی";
final String ATLEAST_ONE_WORKOUT_DAY = "حداقل باید یک روز تمرینی وارد کرد!";
final String WORKOUT_TYPE_EMPTY = "مدل تمرین انتخاب نشده";