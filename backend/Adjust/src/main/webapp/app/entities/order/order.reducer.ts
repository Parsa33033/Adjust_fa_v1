import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOrder, defaultValue } from 'app/shared/model/order.model';

export const ACTION_TYPES = {
  FETCH_ORDER_LIST: 'order/FETCH_ORDER_LIST',
  FETCH_ORDER: 'order/FETCH_ORDER',
  CREATE_ORDER: 'order/CREATE_ORDER',
  UPDATE_ORDER: 'order/UPDATE_ORDER',
  DELETE_ORDER: 'order/DELETE_ORDER',
  RESET: 'order/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOrder>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type OrderState = Readonly<typeof initialState>;

// Reducer

export default (state: OrderState = initialState, action): OrderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ORDER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ORDER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ORDER):
    case REQUEST(ACTION_TYPES.UPDATE_ORDER):
    case REQUEST(ACTION_TYPES.DELETE_ORDER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ORDER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ORDER):
    case FAILURE(ACTION_TYPES.CREATE_ORDER):
    case FAILURE(ACTION_TYPES.UPDATE_ORDER):
    case FAILURE(ACTION_TYPES.DELETE_ORDER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORDER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORDER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ORDER):
    case SUCCESS(ACTION_TYPES.UPDATE_ORDER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ORDER):
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

const apiUrl = 'api/orders';

// Actions

export const getEntities: ICrudGetAllAction<IOrder> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ORDER_LIST,
  payload: axios.get<IOrder>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IOrder> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ORDER,
    payload: axios.get<IOrder>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IOrder> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ORDER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOrder> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ORDER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOrder> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ORDER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
