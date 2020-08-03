import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProgramDevelopment, defaultValue } from 'app/shared/model/program-development.model';

export const ACTION_TYPES = {
  FETCH_PROGRAMDEVELOPMENT_LIST: 'programDevelopment/FETCH_PROGRAMDEVELOPMENT_LIST',
  FETCH_PROGRAMDEVELOPMENT: 'programDevelopment/FETCH_PROGRAMDEVELOPMENT',
  CREATE_PROGRAMDEVELOPMENT: 'programDevelopment/CREATE_PROGRAMDEVELOPMENT',
  UPDATE_PROGRAMDEVELOPMENT: 'programDevelopment/UPDATE_PROGRAMDEVELOPMENT',
  DELETE_PROGRAMDEVELOPMENT: 'programDevelopment/DELETE_PROGRAMDEVELOPMENT',
  RESET: 'programDevelopment/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProgramDevelopment>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ProgramDevelopmentState = Readonly<typeof initialState>;

// Reducer

export default (state: ProgramDevelopmentState = initialState, action): ProgramDevelopmentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROGRAMDEVELOPMENT):
    case REQUEST(ACTION_TYPES.UPDATE_PROGRAMDEVELOPMENT):
    case REQUEST(ACTION_TYPES.DELETE_PROGRAMDEVELOPMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT):
    case FAILURE(ACTION_TYPES.CREATE_PROGRAMDEVELOPMENT):
    case FAILURE(ACTION_TYPES.UPDATE_PROGRAMDEVELOPMENT):
    case FAILURE(ACTION_TYPES.DELETE_PROGRAMDEVELOPMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROGRAMDEVELOPMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_PROGRAMDEVELOPMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROGRAMDEVELOPMENT):
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

const apiUrl = 'api/program-developments';

// Actions

export const getEntities: ICrudGetAllAction<IProgramDevelopment> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT_LIST,
  payload: axios.get<IProgramDevelopment>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IProgramDevelopment> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROGRAMDEVELOPMENT,
    payload: axios.get<IProgramDevelopment>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProgramDevelopment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROGRAMDEVELOPMENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProgramDevelopment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROGRAMDEVELOPMENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProgramDevelopment> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROGRAMDEVELOPMENT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
