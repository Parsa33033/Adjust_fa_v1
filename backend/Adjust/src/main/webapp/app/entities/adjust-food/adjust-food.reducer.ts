import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustFood, defaultValue } from 'app/shared/model/adjust-food.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTFOOD_LIST: 'adjustFood/FETCH_ADJUSTFOOD_LIST',
  FETCH_ADJUSTFOOD: 'adjustFood/FETCH_ADJUSTFOOD',
  CREATE_ADJUSTFOOD: 'adjustFood/CREATE_ADJUSTFOOD',
  UPDATE_ADJUSTFOOD: 'adjustFood/UPDATE_ADJUSTFOOD',
  DELETE_ADJUSTFOOD: 'adjustFood/DELETE_ADJUSTFOOD',
  RESET: 'adjustFood/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustFood>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustFoodState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustFoodState = initialState, action): AdjustFoodState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTFOOD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTFOOD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTFOOD):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTFOOD):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTFOOD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTFOOD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTFOOD):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTFOOD):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTFOOD):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTFOOD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTFOOD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTFOOD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTFOOD):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTFOOD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTFOOD):
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

const apiUrl = 'api/adjust-foods';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustFood> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTFOOD_LIST,
  payload: axios.get<IAdjustFood>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustFood> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTFOOD,
    payload: axios.get<IAdjustFood>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustFood> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTFOOD,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustFood> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTFOOD,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustFood> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTFOOD,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
