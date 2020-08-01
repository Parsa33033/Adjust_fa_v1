import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustTutorial, defaultValue } from 'app/shared/model/adjust-tutorial.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTTUTORIAL_LIST: 'adjustTutorial/FETCH_ADJUSTTUTORIAL_LIST',
  FETCH_ADJUSTTUTORIAL: 'adjustTutorial/FETCH_ADJUSTTUTORIAL',
  CREATE_ADJUSTTUTORIAL: 'adjustTutorial/CREATE_ADJUSTTUTORIAL',
  UPDATE_ADJUSTTUTORIAL: 'adjustTutorial/UPDATE_ADJUSTTUTORIAL',
  DELETE_ADJUSTTUTORIAL: 'adjustTutorial/DELETE_ADJUSTTUTORIAL',
  SET_BLOB: 'adjustTutorial/SET_BLOB',
  RESET: 'adjustTutorial/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustTutorial>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustTutorialState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustTutorialState = initialState, action): AdjustTutorialState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTUTORIAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTUTORIAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTTUTORIAL):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTTUTORIAL):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTTUTORIAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTUTORIAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTUTORIAL):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTTUTORIAL):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTTUTORIAL):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTTUTORIAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTUTORIAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTUTORIAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTTUTORIAL):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTTUTORIAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTTUTORIAL):
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

const apiUrl = 'api/adjust-tutorials';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustTutorial> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTTUTORIAL_LIST,
  payload: axios.get<IAdjustTutorial>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustTutorial> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTTUTORIAL,
    payload: axios.get<IAdjustTutorial>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustTutorial> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTTUTORIAL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustTutorial> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTTUTORIAL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustTutorial> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTTUTORIAL,
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
