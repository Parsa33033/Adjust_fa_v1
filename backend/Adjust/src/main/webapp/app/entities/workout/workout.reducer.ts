import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IWorkout, defaultValue } from 'app/shared/model/workout.model';

export const ACTION_TYPES = {
  FETCH_WORKOUT_LIST: 'workout/FETCH_WORKOUT_LIST',
  FETCH_WORKOUT: 'workout/FETCH_WORKOUT',
  CREATE_WORKOUT: 'workout/CREATE_WORKOUT',
  UPDATE_WORKOUT: 'workout/UPDATE_WORKOUT',
  DELETE_WORKOUT: 'workout/DELETE_WORKOUT',
  RESET: 'workout/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IWorkout>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type WorkoutState = Readonly<typeof initialState>;

// Reducer

export default (state: WorkoutState = initialState, action): WorkoutState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_WORKOUT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_WORKOUT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_WORKOUT):
    case REQUEST(ACTION_TYPES.UPDATE_WORKOUT):
    case REQUEST(ACTION_TYPES.DELETE_WORKOUT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_WORKOUT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_WORKOUT):
    case FAILURE(ACTION_TYPES.CREATE_WORKOUT):
    case FAILURE(ACTION_TYPES.UPDATE_WORKOUT):
    case FAILURE(ACTION_TYPES.DELETE_WORKOUT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_WORKOUT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_WORKOUT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_WORKOUT):
    case SUCCESS(ACTION_TYPES.UPDATE_WORKOUT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_WORKOUT):
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

const apiUrl = 'api/workouts';

// Actions

export const getEntities: ICrudGetAllAction<IWorkout> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_WORKOUT_LIST,
  payload: axios.get<IWorkout>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IWorkout> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_WORKOUT,
    payload: axios.get<IWorkout>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IWorkout> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_WORKOUT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IWorkout> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_WORKOUT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IWorkout> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_WORKOUT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
