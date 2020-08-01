import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISpecialist, defaultValue } from 'app/shared/model/specialist.model';

export const ACTION_TYPES = {
  FETCH_SPECIALIST_LIST: 'specialist/FETCH_SPECIALIST_LIST',
  FETCH_SPECIALIST: 'specialist/FETCH_SPECIALIST',
  CREATE_SPECIALIST: 'specialist/CREATE_SPECIALIST',
  UPDATE_SPECIALIST: 'specialist/UPDATE_SPECIALIST',
  DELETE_SPECIALIST: 'specialist/DELETE_SPECIALIST',
  SET_BLOB: 'specialist/SET_BLOB',
  RESET: 'specialist/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISpecialist>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SpecialistState = Readonly<typeof initialState>;

// Reducer

export default (state: SpecialistState = initialState, action): SpecialistState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SPECIALIST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SPECIALIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SPECIALIST):
    case REQUEST(ACTION_TYPES.UPDATE_SPECIALIST):
    case REQUEST(ACTION_TYPES.DELETE_SPECIALIST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SPECIALIST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SPECIALIST):
    case FAILURE(ACTION_TYPES.CREATE_SPECIALIST):
    case FAILURE(ACTION_TYPES.UPDATE_SPECIALIST):
    case FAILURE(ACTION_TYPES.DELETE_SPECIALIST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SPECIALIST_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SPECIALIST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SPECIALIST):
    case SUCCESS(ACTION_TYPES.UPDATE_SPECIALIST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SPECIALIST):
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

const apiUrl = 'api/specialists';

// Actions

export const getEntities: ICrudGetAllAction<ISpecialist> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SPECIALIST_LIST,
  payload: axios.get<ISpecialist>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISpecialist> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SPECIALIST,
    payload: axios.get<ISpecialist>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISpecialist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SPECIALIST,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISpecialist> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SPECIALIST,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISpecialist> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SPECIALIST,
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
