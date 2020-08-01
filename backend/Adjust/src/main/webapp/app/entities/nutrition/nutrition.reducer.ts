import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INutrition, defaultValue } from 'app/shared/model/nutrition.model';

export const ACTION_TYPES = {
  FETCH_NUTRITION_LIST: 'nutrition/FETCH_NUTRITION_LIST',
  FETCH_NUTRITION: 'nutrition/FETCH_NUTRITION',
  CREATE_NUTRITION: 'nutrition/CREATE_NUTRITION',
  UPDATE_NUTRITION: 'nutrition/UPDATE_NUTRITION',
  DELETE_NUTRITION: 'nutrition/DELETE_NUTRITION',
  RESET: 'nutrition/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INutrition>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type NutritionState = Readonly<typeof initialState>;

// Reducer

export default (state: NutritionState = initialState, action): NutritionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NUTRITION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NUTRITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NUTRITION):
    case REQUEST(ACTION_TYPES.UPDATE_NUTRITION):
    case REQUEST(ACTION_TYPES.DELETE_NUTRITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NUTRITION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NUTRITION):
    case FAILURE(ACTION_TYPES.CREATE_NUTRITION):
    case FAILURE(ACTION_TYPES.UPDATE_NUTRITION):
    case FAILURE(ACTION_TYPES.DELETE_NUTRITION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUTRITION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUTRITION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NUTRITION):
    case SUCCESS(ACTION_TYPES.UPDATE_NUTRITION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NUTRITION):
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

const apiUrl = 'api/nutritions';

// Actions

export const getEntities: ICrudGetAllAction<INutrition> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NUTRITION_LIST,
  payload: axios.get<INutrition>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<INutrition> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NUTRITION,
    payload: axios.get<INutrition>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INutrition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NUTRITION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INutrition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NUTRITION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INutrition> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NUTRITION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
