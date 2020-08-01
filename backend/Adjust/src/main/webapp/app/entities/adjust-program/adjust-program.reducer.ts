import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustProgram, defaultValue } from 'app/shared/model/adjust-program.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTPROGRAM_LIST: 'adjustProgram/FETCH_ADJUSTPROGRAM_LIST',
  FETCH_ADJUSTPROGRAM: 'adjustProgram/FETCH_ADJUSTPROGRAM',
  CREATE_ADJUSTPROGRAM: 'adjustProgram/CREATE_ADJUSTPROGRAM',
  UPDATE_ADJUSTPROGRAM: 'adjustProgram/UPDATE_ADJUSTPROGRAM',
  DELETE_ADJUSTPROGRAM: 'adjustProgram/DELETE_ADJUSTPROGRAM',
  RESET: 'adjustProgram/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustProgram>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustProgramState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustProgramState = initialState, action): AdjustProgramState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTPROGRAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTPROGRAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTPROGRAM):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTPROGRAM):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTPROGRAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTPROGRAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTPROGRAM):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTPROGRAM):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTPROGRAM):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTPROGRAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTPROGRAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTPROGRAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTPROGRAM):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTPROGRAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTPROGRAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/adjust-programs';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustProgram> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTPROGRAM_LIST,
  payload: axios.get<IAdjustProgram>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustProgram> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTPROGRAM,
    payload: axios.get<IAdjustProgram>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustProgram> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTPROGRAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustProgram> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTPROGRAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustProgram> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTPROGRAM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
