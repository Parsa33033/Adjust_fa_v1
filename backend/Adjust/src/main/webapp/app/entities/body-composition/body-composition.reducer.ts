import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBodyComposition, defaultValue } from 'app/shared/model/body-composition.model';

export const ACTION_TYPES = {
  FETCH_BODYCOMPOSITION_LIST: 'bodyComposition/FETCH_BODYCOMPOSITION_LIST',
  FETCH_BODYCOMPOSITION: 'bodyComposition/FETCH_BODYCOMPOSITION',
  CREATE_BODYCOMPOSITION: 'bodyComposition/CREATE_BODYCOMPOSITION',
  UPDATE_BODYCOMPOSITION: 'bodyComposition/UPDATE_BODYCOMPOSITION',
  DELETE_BODYCOMPOSITION: 'bodyComposition/DELETE_BODYCOMPOSITION',
  SET_BLOB: 'bodyComposition/SET_BLOB',
  RESET: 'bodyComposition/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBodyComposition>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type BodyCompositionState = Readonly<typeof initialState>;

// Reducer

export default (state: BodyCompositionState = initialState, action): BodyCompositionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BODYCOMPOSITION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BODYCOMPOSITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BODYCOMPOSITION):
    case REQUEST(ACTION_TYPES.UPDATE_BODYCOMPOSITION):
    case REQUEST(ACTION_TYPES.DELETE_BODYCOMPOSITION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BODYCOMPOSITION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BODYCOMPOSITION):
    case FAILURE(ACTION_TYPES.CREATE_BODYCOMPOSITION):
    case FAILURE(ACTION_TYPES.UPDATE_BODYCOMPOSITION):
    case FAILURE(ACTION_TYPES.DELETE_BODYCOMPOSITION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BODYCOMPOSITION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BODYCOMPOSITION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BODYCOMPOSITION):
    case SUCCESS(ACTION_TYPES.UPDATE_BODYCOMPOSITION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BODYCOMPOSITION):
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

const apiUrl = 'api/body-compositions';

// Actions

export const getEntities: ICrudGetAllAction<IBodyComposition> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BODYCOMPOSITION_LIST,
  payload: axios.get<IBodyComposition>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IBodyComposition> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BODYCOMPOSITION,
    payload: axios.get<IBodyComposition>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBodyComposition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BODYCOMPOSITION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBodyComposition> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BODYCOMPOSITION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBodyComposition> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BODYCOMPOSITION,
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
