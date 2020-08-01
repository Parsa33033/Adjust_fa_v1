import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMove, defaultValue } from 'app/shared/model/move.model';

export const ACTION_TYPES = {
  FETCH_MOVE_LIST: 'move/FETCH_MOVE_LIST',
  FETCH_MOVE: 'move/FETCH_MOVE',
  CREATE_MOVE: 'move/CREATE_MOVE',
  UPDATE_MOVE: 'move/UPDATE_MOVE',
  DELETE_MOVE: 'move/DELETE_MOVE',
  SET_BLOB: 'move/SET_BLOB',
  RESET: 'move/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMove>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MoveState = Readonly<typeof initialState>;

// Reducer

export default (state: MoveState = initialState, action): MoveState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MOVE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MOVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MOVE):
    case REQUEST(ACTION_TYPES.UPDATE_MOVE):
    case REQUEST(ACTION_TYPES.DELETE_MOVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MOVE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MOVE):
    case FAILURE(ACTION_TYPES.CREATE_MOVE):
    case FAILURE(ACTION_TYPES.UPDATE_MOVE):
    case FAILURE(ACTION_TYPES.DELETE_MOVE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MOVE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MOVE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MOVE):
    case SUCCESS(ACTION_TYPES.UPDATE_MOVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MOVE):
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

const apiUrl = 'api/moves';

// Actions

export const getEntities: ICrudGetAllAction<IMove> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MOVE_LIST,
  payload: axios.get<IMove>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMove> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MOVE,
    payload: axios.get<IMove>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMove> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MOVE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMove> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MOVE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMove> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MOVE,
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
