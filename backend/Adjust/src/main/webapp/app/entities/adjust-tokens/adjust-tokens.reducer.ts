import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustTokens, defaultValue } from 'app/shared/model/adjust-tokens.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTTOKENS_LIST: 'adjustTokens/FETCH_ADJUSTTOKENS_LIST',
  FETCH_ADJUSTTOKENS: 'adjustTokens/FETCH_ADJUSTTOKENS',
  CREATE_ADJUSTTOKENS: 'adjustTokens/CREATE_ADJUSTTOKENS',
  UPDATE_ADJUSTTOKENS: 'adjustTokens/UPDATE_ADJUSTTOKENS',
  DELETE_ADJUSTTOKENS: 'adjustTokens/DELETE_ADJUSTTOKENS',
  SET_BLOB: 'adjustTokens/SET_BLOB',
  RESET: 'adjustTokens/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustTokens>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustTokensState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustTokensState = initialState, action): AdjustTokensState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTOKENS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTOKENS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTTOKENS):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTTOKENS):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTTOKENS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTOKENS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTOKENS):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTTOKENS):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTTOKENS):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTTOKENS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTOKENS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTOKENS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTTOKENS):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTTOKENS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTTOKENS):
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

const apiUrl = 'api/adjust-tokens';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustTokens> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTTOKENS_LIST,
  payload: axios.get<IAdjustTokens>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustTokens> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTTOKENS,
    payload: axios.get<IAdjustTokens>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustTokens> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTTOKENS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustTokens> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTTOKENS,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustTokens> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTTOKENS,
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
