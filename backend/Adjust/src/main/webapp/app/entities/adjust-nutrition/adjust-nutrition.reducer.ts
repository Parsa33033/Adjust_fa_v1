import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustNutrition, defaultValue } from 'app/shared/model/adjust-nutrition.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTNUTRITION_LIST: 'adjustNutrition/FETCH_ADJUSTNUTRITION_LIST',
  FETCH_ADJUSTNUTRITION: 'adjustNutrition/FETCH_ADJUSTNUTRITION',
  CREATE_ADJUSTNUTRITION: 'adjustNutrition/CREATE_ADJUSTNUTRITION',
  UPDATE_ADJUSTNUTRITION: 'adjustNutrition/UPDATE_ADJUSTNUTRITION',
  DELETE_ADJUSTNUTRITION: 'adjustNutrition/DELETE_ADJUSTNUTRITION',
  RESET: 'adjustNutrition/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustNutrition>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustNutritionState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustNutritionState = initialState, action): AdjustNutritionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTNUTRITION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTNUTRITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTNUTRITION):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTNUTRITION):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTNUTRITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTNUTRITION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTNUTRITION):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTNUTRITION):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTNUTRITION):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTNUTRITION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTNUTRITION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTNUTRITION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTNUTRITION):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTNUTRITION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTNUTRITION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/adjust-nutritions';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustNutrition> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTNUTRITION_LIST,
  payload: axios.get<IAdjustNutrition>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustNutrition> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTNUTRITION,
    payload: axios.get<IAdjustNutrition>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustNutrition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTNUTRITION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustNutrition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTNUTRITION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustNutrition> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTNUTRITION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
