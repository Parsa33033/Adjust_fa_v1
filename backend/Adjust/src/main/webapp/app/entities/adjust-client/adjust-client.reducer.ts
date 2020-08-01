import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustClient, defaultValue } from 'app/shared/model/adjust-client.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTCLIENT_LIST: 'adjustClient/FETCH_ADJUSTCLIENT_LIST',
  FETCH_ADJUSTCLIENT: 'adjustClient/FETCH_ADJUSTCLIENT',
  CREATE_ADJUSTCLIENT: 'adjustClient/CREATE_ADJUSTCLIENT',
  UPDATE_ADJUSTCLIENT: 'adjustClient/UPDATE_ADJUSTCLIENT',
  DELETE_ADJUSTCLIENT: 'adjustClient/DELETE_ADJUSTCLIENT',
  SET_BLOB: 'adjustClient/SET_BLOB',
  RESET: 'adjustClient/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustClient>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustClientState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustClientState = initialState, action): AdjustClientState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTCLIENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTCLIENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTCLIENT):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTCLIENT):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTCLIENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTCLIENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTCLIENT):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTCLIENT):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTCLIENT):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTCLIENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTCLIENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTCLIENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTCLIENT):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTCLIENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTCLIENT):
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

const apiUrl = 'api/adjust-clients';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustClient> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTCLIENT_LIST,
  payload: axios.get<IAdjustClient>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustClient> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTCLIENT,
    payload: axios.get<IAdjustClient>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustClient> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTCLIENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustClient> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTCLIENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustClient> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTCLIENT,
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
