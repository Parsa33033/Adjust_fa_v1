import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustMove, defaultValue } from 'app/shared/model/adjust-move.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTMOVE_LIST: 'adjustMove/FETCH_ADJUSTMOVE_LIST',
  FETCH_ADJUSTMOVE: 'adjustMove/FETCH_ADJUSTMOVE',
  CREATE_ADJUSTMOVE: 'adjustMove/CREATE_ADJUSTMOVE',
  UPDATE_ADJUSTMOVE: 'adjustMove/UPDATE_ADJUSTMOVE',
  DELETE_ADJUSTMOVE: 'adjustMove/DELETE_ADJUSTMOVE',
  SET_BLOB: 'adjustMove/SET_BLOB',
  RESET: 'adjustMove/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustMove>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustMoveState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustMoveState = initialState, action): AdjustMoveState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTMOVE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTMOVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTMOVE):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTMOVE):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTMOVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTMOVE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTMOVE):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTMOVE):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTMOVE):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTMOVE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTMOVE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTMOVE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTMOVE):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTMOVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTMOVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/adjust-moves';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustMove> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTMOVE_LIST,
  payload: axios.get<IAdjustMove>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustMove> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTMOVE,
    payload: axios.get<IAdjustMove>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustMove> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTMOVE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustMove> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTMOVE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustMove> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTMOVE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
