import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INutritionProgram, defaultValue } from 'app/shared/model/nutrition-program.model';

export const ACTION_TYPES = {
  FETCH_NUTRITIONPROGRAM_LIST: 'nutritionProgram/FETCH_NUTRITIONPROGRAM_LIST',
  FETCH_NUTRITIONPROGRAM: 'nutritionProgram/FETCH_NUTRITIONPROGRAM',
  CREATE_NUTRITIONPROGRAM: 'nutritionProgram/CREATE_NUTRITIONPROGRAM',
  UPDATE_NUTRITIONPROGRAM: 'nutritionProgram/UPDATE_NUTRITIONPROGRAM',
  DELETE_NUTRITIONPROGRAM: 'nutritionProgram/DELETE_NUTRITIONPROGRAM',
  SET_BLOB: 'nutritionProgram/SET_BLOB',
  RESET: 'nutritionProgram/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INutritionProgram>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type NutritionProgramState = Readonly<typeof initialState>;

// Reducer

export default (state: NutritionProgramState = initialState, action): NutritionProgramState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NUTRITIONPROGRAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NUTRITIONPROGRAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NUTRITIONPROGRAM):
    case REQUEST(ACTION_TYPES.UPDATE_NUTRITIONPROGRAM):
    case REQUEST(ACTION_TYPES.DELETE_NUTRITIONPROGRAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NUTRITIONPROGRAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NUTRITIONPROGRAM):
    case FAILURE(ACTION_TYPES.CREATE_NUTRITIONPROGRAM):
    case FAILURE(ACTION_TYPES.UPDATE_NUTRITIONPROGRAM):
    case FAILURE(ACTION_TYPES.DELETE_NUTRITIONPROGRAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUTRITIONPROGRAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUTRITIONPROGRAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NUTRITIONPROGRAM):
    case SUCCESS(ACTION_TYPES.UPDATE_NUTRITIONPROGRAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NUTRITIONPROGRAM):
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

const apiUrl = 'api/nutrition-programs';

// Actions

export const getEntities: ICrudGetAllAction<INutritionProgram> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NUTRITIONPROGRAM_LIST,
  payload: axios.get<INutritionProgram>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<INutritionProgram> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NUTRITIONPROGRAM,
    payload: axios.get<INutritionProgram>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INutritionProgram> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NUTRITIONPROGRAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INutritionProgram> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NUTRITIONPROGRAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INutritionProgram> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NUTRITIONPROGRAM,
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
