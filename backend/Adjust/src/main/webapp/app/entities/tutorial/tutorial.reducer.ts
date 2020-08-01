import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITutorial, defaultValue } from 'app/shared/model/tutorial.model';

export const ACTION_TYPES = {
  FETCH_TUTORIAL_LIST: 'tutorial/FETCH_TUTORIAL_LIST',
  FETCH_TUTORIAL: 'tutorial/FETCH_TUTORIAL',
  CREATE_TUTORIAL: 'tutorial/CREATE_TUTORIAL',
  UPDATE_TUTORIAL: 'tutorial/UPDATE_TUTORIAL',
  DELETE_TUTORIAL: 'tutorial/DELETE_TUTORIAL',
  SET_BLOB: 'tutorial/SET_BLOB',
  RESET: 'tutorial/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITutorial>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TutorialState = Readonly<typeof initialState>;

// Reducer

export default (state: TutorialState = initialState, action): TutorialState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TUTORIAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TUTORIAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TUTORIAL):
    case REQUEST(ACTION_TYPES.UPDATE_TUTORIAL):
    case REQUEST(ACTION_TYPES.DELETE_TUTORIAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TUTORIAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TUTORIAL):
    case FAILURE(ACTION_TYPES.CREATE_TUTORIAL):
    case FAILURE(ACTION_TYPES.UPDATE_TUTORIAL):
    case FAILURE(ACTION_TYPES.DELETE_TUTORIAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TUTORIAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TUTORIAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TUTORIAL):
    case SUCCESS(ACTION_TYPES.UPDATE_TUTORIAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TUTORIAL):
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

const apiUrl = 'api/tutorials';

// Actions

export const getEntities: ICrudGetAllAction<ITutorial> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TUTORIAL_LIST,
  payload: axios.get<ITutorial>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITutorial> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TUTORIAL,
    payload: axios.get<ITutorial>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITutorial> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TUTORIAL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITutorial> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TUTORIAL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITutorial> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TUTORIAL,
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
