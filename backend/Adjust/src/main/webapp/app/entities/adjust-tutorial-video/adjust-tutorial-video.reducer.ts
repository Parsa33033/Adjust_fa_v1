import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustTutorialVideo, defaultValue } from 'app/shared/model/adjust-tutorial-video.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTTUTORIALVIDEO_LIST: 'adjustTutorialVideo/FETCH_ADJUSTTUTORIALVIDEO_LIST',
  FETCH_ADJUSTTUTORIALVIDEO: 'adjustTutorialVideo/FETCH_ADJUSTTUTORIALVIDEO',
  CREATE_ADJUSTTUTORIALVIDEO: 'adjustTutorialVideo/CREATE_ADJUSTTUTORIALVIDEO',
  UPDATE_ADJUSTTUTORIALVIDEO: 'adjustTutorialVideo/UPDATE_ADJUSTTUTORIALVIDEO',
  DELETE_ADJUSTTUTORIALVIDEO: 'adjustTutorialVideo/DELETE_ADJUSTTUTORIALVIDEO',
  SET_BLOB: 'adjustTutorialVideo/SET_BLOB',
  RESET: 'adjustTutorialVideo/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustTutorialVideo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustTutorialVideoState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustTutorialVideoState = initialState, action): AdjustTutorialVideoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTTUTORIALVIDEO):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTTUTORIALVIDEO):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTTUTORIALVIDEO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTTUTORIALVIDEO):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTTUTORIALVIDEO):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTTUTORIALVIDEO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTTUTORIALVIDEO):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTTUTORIALVIDEO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTTUTORIALVIDEO):
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

const apiUrl = 'api/adjust-tutorial-videos';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustTutorialVideo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO_LIST,
  payload: axios.get<IAdjustTutorialVideo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustTutorialVideo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTTUTORIALVIDEO,
    payload: axios.get<IAdjustTutorialVideo>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustTutorialVideo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTTUTORIALVIDEO,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustTutorialVideo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTTUTORIALVIDEO,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustTutorialVideo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTTUTORIALVIDEO,
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
