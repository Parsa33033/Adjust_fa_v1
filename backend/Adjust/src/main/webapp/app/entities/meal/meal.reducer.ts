import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMeal, defaultValue } from 'app/shared/model/meal.model';

export const ACTION_TYPES = {
  FETCH_MEAL_LIST: 'meal/FETCH_MEAL_LIST',
  FETCH_MEAL: 'meal/FETCH_MEAL',
  CREATE_MEAL: 'meal/CREATE_MEAL',
  UPDATE_MEAL: 'meal/UPDATE_MEAL',
  DELETE_MEAL: 'meal/DELETE_MEAL',
  RESET: 'meal/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMeal>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MealState = Readonly<typeof initialState>;

// Reducer

export default (state: MealState = initialState, action): MealState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MEAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MEAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MEAL):
    case REQUEST(ACTION_TYPES.UPDATE_MEAL):
    case REQUEST(ACTION_TYPES.DELETE_MEAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MEAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MEAL):
    case FAILURE(ACTION_TYPES.CREATE_MEAL):
    case FAILURE(ACTION_TYPES.UPDATE_MEAL):
    case FAILURE(ACTION_TYPES.DELETE_MEAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MEAL):
    case SUCCESS(ACTION_TYPES.UPDATE_MEAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MEAL):
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

const apiUrl = 'api/meals';

// Actions

export const getEntities: ICrudGetAllAction<IMeal> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MEAL_LIST,
  payload: axios.get<IMeal>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMeal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MEAL,
    payload: axios.get<IMeal>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MEAL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MEAL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMeal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MEAL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
