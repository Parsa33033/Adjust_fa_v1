import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustPrice, defaultValue } from 'app/shared/model/adjust-price.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTPRICE_LIST: 'adjustPrice/FETCH_ADJUSTPRICE_LIST',
  FETCH_ADJUSTPRICE: 'adjustPrice/FETCH_ADJUSTPRICE',
  CREATE_ADJUSTPRICE: 'adjustPrice/CREATE_ADJUSTPRICE',
  UPDATE_ADJUSTPRICE: 'adjustPrice/UPDATE_ADJUSTPRICE',
  DELETE_ADJUSTPRICE: 'adjustPrice/DELETE_ADJUSTPRICE',
  RESET: 'adjustPrice/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustPrice>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustPriceState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustPriceState = initialState, action): AdjustPriceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTPRICE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTPRICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTPRICE):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTPRICE):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTPRICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTPRICE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTPRICE):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTPRICE):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTPRICE):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTPRICE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTPRICE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTPRICE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTPRICE):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTPRICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTPRICE):
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

const apiUrl = 'api/adjust-prices';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustPrice> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTPRICE_LIST,
  payload: axios.get<IAdjustPrice>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustPrice> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTPRICE,
    payload: axios.get<IAdjustPrice>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustPrice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTPRICE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustPrice> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTPRICE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustPrice> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTPRICE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
