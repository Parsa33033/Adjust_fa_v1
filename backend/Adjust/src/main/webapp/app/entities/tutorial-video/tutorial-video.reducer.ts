import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITutorialVideo, defaultValue } from 'app/shared/model/tutorial-video.model';

export const ACTION_TYPES = {
  FETCH_TUTORIALVIDEO_LIST: 'tutorialVideo/FETCH_TUTORIALVIDEO_LIST',
  FETCH_TUTORIALVIDEO: 'tutorialVideo/FETCH_TUTORIALVIDEO',
  CREATE_TUTORIALVIDEO: 'tutorialVideo/CREATE_TUTORIALVIDEO',
  UPDATE_TUTORIALVIDEO: 'tutorialVideo/UPDATE_TUTORIALVIDEO',
  DELETE_TUTORIALVIDEO: 'tutorialVideo/DELETE_TUTORIALVIDEO',
  SET_BLOB: 'tutorialVideo/SET_BLOB',
  RESET: 'tutorialVideo/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITutorialVideo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TutorialVideoState = Readonly<typeof initialState>;

// Reducer

export default (state: TutorialVideoState = initialState, action): TutorialVideoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TUTORIALVIDEO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TUTORIALVIDEO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TUTORIALVIDEO):
    case REQUEST(ACTION_TYPES.UPDATE_TUTORIALVIDEO):
    case REQUEST(ACTION_TYPES.DELETE_TUTORIALVIDEO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TUTORIALVIDEO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TUTORIALVIDEO):
    case FAILURE(ACTION_TYPES.CREATE_TUTORIALVIDEO):
    case FAILURE(ACTION_TYPES.UPDATE_TUTORIALVIDEO):
    case FAILURE(ACTION_TYPES.DELETE_TUTORIALVIDEO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TUTORIALVIDEO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TUTORIALVIDEO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TUTORIALVIDEO):
    case SUCCESS(ACTION_TYPES.UPDATE_TUTORIALVIDEO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TUTORIALVIDEO):
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

const apiUrl = 'api/tutorial-videos';

// Actions

export const getEntities: ICrudGetAllAction<ITutorialVideo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TUTORIALVIDEO_LIST,
  payload: axios.get<ITutorialVideo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITutorialVideo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TUTORIALVIDEO,
    payload: axios.get<ITutorialVideo>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITutorialVideo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TUTORIALVIDEO,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITutorialVideo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TUTORIALVIDEO,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITutorialVideo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TUTORIALVIDEO,
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
