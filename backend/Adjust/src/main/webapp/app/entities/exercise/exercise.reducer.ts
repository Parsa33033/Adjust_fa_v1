import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IExercise, defaultValue } from 'app/shared/model/exercise.model';

export const ACTION_TYPES = {
  FETCH_EXERCISE_LIST: 'exercise/FETCH_EXERCISE_LIST',
  FETCH_EXERCISE: 'exercise/FETCH_EXERCISE',
  CREATE_EXERCISE: 'exercise/CREATE_EXERCISE',
  UPDATE_EXERCISE: 'exercise/UPDATE_EXERCISE',
  DELETE_EXERCISE: 'exercise/DELETE_EXERCISE',
  RESET: 'exercise/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IExercise>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ExerciseState = Readonly<typeof initialState>;

// Reducer

export default (state: ExerciseState = initialState, action): ExerciseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EXERCISE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EXERCISE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_EXERCISE):
    case REQUEST(ACTION_TYPES.UPDATE_EXERCISE):
    case REQUEST(ACTION_TYPES.DELETE_EXERCISE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_EXERCISE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EXERCISE):
    case FAILURE(ACTION_TYPES.CREATE_EXERCISE):
    case FAILURE(ACTION_TYPES.UPDATE_EXERCISE):
    case FAILURE(ACTION_TYPES.DELETE_EXERCISE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXERCISE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXERCISE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_EXERCISE):
    case SUCCESS(ACTION_TYPES.UPDATE_EXERCISE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_EXERCISE):
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

const apiUrl = 'api/exercises';

// Actions

export const getEntities: ICrudGetAllAction<IExercise> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EXERCISE_LIST,
  payload: axios.get<IExercise>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IExercise> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EXERCISE,
    payload: axios.get<IExercise>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IExercise> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EXERCISE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IExercise> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EXERCISE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IExercise> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EXERCISE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
