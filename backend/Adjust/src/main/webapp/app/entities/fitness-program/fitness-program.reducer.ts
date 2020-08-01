import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFitnessProgram, defaultValue } from 'app/shared/model/fitness-program.model';

export const ACTION_TYPES = {
  FETCH_FITNESSPROGRAM_LIST: 'fitnessProgram/FETCH_FITNESSPROGRAM_LIST',
  FETCH_FITNESSPROGRAM: 'fitnessProgram/FETCH_FITNESSPROGRAM',
  CREATE_FITNESSPROGRAM: 'fitnessProgram/CREATE_FITNESSPROGRAM',
  UPDATE_FITNESSPROGRAM: 'fitnessProgram/UPDATE_FITNESSPROGRAM',
  DELETE_FITNESSPROGRAM: 'fitnessProgram/DELETE_FITNESSPROGRAM',
  SET_BLOB: 'fitnessProgram/SET_BLOB',
  RESET: 'fitnessProgram/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFitnessProgram>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type FitnessProgramState = Readonly<typeof initialState>;

// Reducer

export default (state: FitnessProgramState = initialState, action): FitnessProgramState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FITNESSPROGRAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FITNESSPROGRAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_FITNESSPROGRAM):
    case REQUEST(ACTION_TYPES.UPDATE_FITNESSPROGRAM):
    case REQUEST(ACTION_TYPES.DELETE_FITNESSPROGRAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_FITNESSPROGRAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FITNESSPROGRAM):
    case FAILURE(ACTION_TYPES.CREATE_FITNESSPROGRAM):
    case FAILURE(ACTION_TYPES.UPDATE_FITNESSPROGRAM):
    case FAILURE(ACTION_TYPES.DELETE_FITNESSPROGRAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_FITNESSPROGRAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_FITNESSPROGRAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_FITNESSPROGRAM):
    case SUCCESS(ACTION_TYPES.UPDATE_FITNESSPROGRAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_FITNESSPROGRAM):
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

const apiUrl = 'api/fitness-programs';

// Actions

export const getEntities: ICrudGetAllAction<IFitnessProgram> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FITNESSPROGRAM_LIST,
  payload: axios.get<IFitnessProgram>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IFitnessProgram> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FITNESSPROGRAM,
    payload: axios.get<IFitnessProgram>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IFitnessProgram> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FITNESSPROGRAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFitnessProgram> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FITNESSPROGRAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFitnessProgram> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FITNESSPROGRAM,
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
