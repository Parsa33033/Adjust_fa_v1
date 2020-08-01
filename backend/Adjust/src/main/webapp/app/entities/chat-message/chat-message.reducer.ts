import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IChatMessage, defaultValue } from 'app/shared/model/chat-message.model';

export const ACTION_TYPES = {
  FETCH_CHATMESSAGE_LIST: 'chatMessage/FETCH_CHATMESSAGE_LIST',
  FETCH_CHATMESSAGE: 'chatMessage/FETCH_CHATMESSAGE',
  CREATE_CHATMESSAGE: 'chatMessage/CREATE_CHATMESSAGE',
  UPDATE_CHATMESSAGE: 'chatMessage/UPDATE_CHATMESSAGE',
  DELETE_CHATMESSAGE: 'chatMessage/DELETE_CHATMESSAGE',
  SET_BLOB: 'chatMessage/SET_BLOB',
  RESET: 'chatMessage/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IChatMessage>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ChatMessageState = Readonly<typeof initialState>;

// Reducer

export default (state: ChatMessageState = initialState, action): ChatMessageState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CHATMESSAGE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CHATMESSAGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CHATMESSAGE):
    case REQUEST(ACTION_TYPES.UPDATE_CHATMESSAGE):
    case REQUEST(ACTION_TYPES.DELETE_CHATMESSAGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CHATMESSAGE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CHATMESSAGE):
    case FAILURE(ACTION_TYPES.CREATE_CHATMESSAGE):
    case FAILURE(ACTION_TYPES.UPDATE_CHATMESSAGE):
    case FAILURE(ACTION_TYPES.DELETE_CHATMESSAGE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHATMESSAGE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHATMESSAGE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CHATMESSAGE):
    case SUCCESS(ACTION_TYPES.UPDATE_CHATMESSAGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CHATMESSAGE):
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

const apiUrl = 'api/chat-messages';

// Actions

export const getEntities: ICrudGetAllAction<IChatMessage> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CHATMESSAGE_LIST,
  payload: axios.get<IChatMessage>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IChatMessage> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CHATMESSAGE,
    payload: axios.get<IChatMessage>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IChatMessage> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CHATMESSAGE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IChatMessage> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CHATMESSAGE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IChatMessage> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CHATMESSAGE,
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
