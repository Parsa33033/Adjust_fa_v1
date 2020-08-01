import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import adjustTokens, {
  AdjustTokensState
} from 'app/entities/adjust-tokens/adjust-tokens.reducer';
// prettier-ignore
import adjustPrice, {
  AdjustPriceState
} from 'app/entities/adjust-price/adjust-price.reducer';
// prettier-ignore
import shopingItem, {
  ShopingItemState
} from 'app/entities/shoping-item/shoping-item.reducer';
// prettier-ignore
import adjustShopingItem, {
  AdjustShopingItemState
} from 'app/entities/adjust-shoping-item/adjust-shoping-item.reducer';
// prettier-ignore
import cart, {
  CartState
} from 'app/entities/cart/cart.reducer';
// prettier-ignore
import order, {
  OrderState
} from 'app/entities/order/order.reducer';
// prettier-ignore
import adjustClient, {
  AdjustClientState
} from 'app/entities/adjust-client/adjust-client.reducer';
// prettier-ignore
import specialist, {
  SpecialistState
} from 'app/entities/specialist/specialist.reducer';
// prettier-ignore
import adjustProgram, {
  AdjustProgramState
} from 'app/entities/adjust-program/adjust-program.reducer';
// prettier-ignore
import bodyComposition, {
  BodyCompositionState
} from 'app/entities/body-composition/body-composition.reducer';
// prettier-ignore
import fitnessProgram, {
  FitnessProgramState
} from 'app/entities/fitness-program/fitness-program.reducer';
// prettier-ignore
import workout, {
  WorkoutState
} from 'app/entities/workout/workout.reducer';
// prettier-ignore
import exercise, {
  ExerciseState
} from 'app/entities/exercise/exercise.reducer';
// prettier-ignore
import move, {
  MoveState
} from 'app/entities/move/move.reducer';
// prettier-ignore
import adjustMove, {
  AdjustMoveState
} from 'app/entities/adjust-move/adjust-move.reducer';
// prettier-ignore
import nutritionProgram, {
  NutritionProgramState
} from 'app/entities/nutrition-program/nutrition-program.reducer';
// prettier-ignore
import meal, {
  MealState
} from 'app/entities/meal/meal.reducer';
// prettier-ignore
import nutrition, {
  NutritionState
} from 'app/entities/nutrition/nutrition.reducer';
// prettier-ignore
import adjustNutrition, {
  AdjustNutritionState
} from 'app/entities/adjust-nutrition/adjust-nutrition.reducer';
// prettier-ignore
import adjustFood, {
  AdjustFoodState
} from 'app/entities/adjust-food/adjust-food.reducer';
// prettier-ignore
import conversation, {
  ConversationState
} from 'app/entities/conversation/conversation.reducer';
// prettier-ignore
import chatMessage, {
  ChatMessageState
} from 'app/entities/chat-message/chat-message.reducer';
// prettier-ignore
import tutorial, {
  TutorialState
} from 'app/entities/tutorial/tutorial.reducer';
// prettier-ignore
import adjustTutorial, {
  AdjustTutorialState
} from 'app/entities/adjust-tutorial/adjust-tutorial.reducer';
// prettier-ignore
import adjustTutorialVideo, {
  AdjustTutorialVideoState
} from 'app/entities/adjust-tutorial-video/adjust-tutorial-video.reducer';
// prettier-ignore
import tutorialVideo, {
  TutorialVideoState
} from 'app/entities/tutorial-video/tutorial-video.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly adjustTokens: AdjustTokensState;
  readonly adjustPrice: AdjustPriceState;
  readonly shopingItem: ShopingItemState;
  readonly adjustShopingItem: AdjustShopingItemState;
  readonly cart: CartState;
  readonly order: OrderState;
  readonly adjustClient: AdjustClientState;
  readonly specialist: SpecialistState;
  readonly adjustProgram: AdjustProgramState;
  readonly bodyComposition: BodyCompositionState;
  readonly fitnessProgram: FitnessProgramState;
  readonly workout: WorkoutState;
  readonly exercise: ExerciseState;
  readonly move: MoveState;
  readonly adjustMove: AdjustMoveState;
  readonly nutritionProgram: NutritionProgramState;
  readonly meal: MealState;
  readonly nutrition: NutritionState;
  readonly adjustNutrition: AdjustNutritionState;
  readonly adjustFood: AdjustFoodState;
  readonly conversation: ConversationState;
  readonly chatMessage: ChatMessageState;
  readonly tutorial: TutorialState;
  readonly adjustTutorial: AdjustTutorialState;
  readonly adjustTutorialVideo: AdjustTutorialVideoState;
  readonly tutorialVideo: TutorialVideoState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  adjustTokens,
  adjustPrice,
  shopingItem,
  adjustShopingItem,
  cart,
  order,
  adjustClient,
  specialist,
  adjustProgram,
  bodyComposition,
  fitnessProgram,
  workout,
  exercise,
  move,
  adjustMove,
  nutritionProgram,
  meal,
  nutrition,
  adjustNutrition,
  adjustFood,
  conversation,
  chatMessage,
  tutorial,
  adjustTutorial,
  adjustTutorialVideo,
  tutorialVideo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
