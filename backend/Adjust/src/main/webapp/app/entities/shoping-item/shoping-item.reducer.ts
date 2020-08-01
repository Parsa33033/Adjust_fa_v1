import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IShopingItem, defaultValue } from 'app/shared/model/shoping-item.model';

export const ACTION_TYPES = {
  FETCH_SHOPINGITEM_LIST: 'shopingItem/FETCH_SHOPINGITEM_LIST',
  FETCH_SHOPINGITEM: 'shopingItem/FETCH_SHOPINGITEM',
  CREATE_SHOPINGITEM: 'shopingItem/CREATE_SHOPINGITEM',
  UPDATE_SHOPINGITEM: 'shopingItem/UPDATE_SHOPINGITEM',
  DELETE_SHOPINGITEM: 'shopingItem/DELETE_SHOPINGITEM',
  SET_BLOB: 'shopingItem/SET_BLOB',
  RESET: 'shopingItem/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IShopingItem>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ShopingItemState = Readonly<typeof initialState>;

// Reducer

export default (state: ShopingItemState = initialState, action): ShopingItemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SHOPINGITEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SHOPINGITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SHOPINGITEM):
    case REQUEST(ACTION_TYPES.UPDATE_SHOPINGITEM):
    case REQUEST(ACTION_TYPES.DELETE_SHOPINGITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SHOPINGITEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SHOPINGITEM):
    case FAILURE(ACTION_TYPES.CREATE_SHOPINGITEM):
    case FAILURE(ACTION_TYPES.UPDATE_SHOPINGITEM):
    case FAILURE(ACTION_TYPES.DELETE_SHOPINGITEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOPINGITEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOPINGITEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SHOPINGITEM):
    case SUCCESS(ACTION_TYPES.UPDATE_SHOPINGITEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SHOPINGITEM):
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

const apiUrl = 'api/shoping-items';

// Actions

export const getEntities: ICrudGetAllAction<IShopingItem> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SHOPINGITEM_LIST,
  payload: axios.get<IShopingItem>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IShopingItem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SHOPINGITEM,
    payload: axios.get<IShopingItem>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IShopingItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SHOPINGITEM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IShopingItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SHOPINGITEM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IShopingItem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SHOPINGITEM,
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
