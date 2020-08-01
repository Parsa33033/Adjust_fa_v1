import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustShopingItem, defaultValue } from 'app/shared/model/adjust-shoping-item.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTSHOPINGITEM_LIST: 'adjustShopingItem/FETCH_ADJUSTSHOPINGITEM_LIST',
  FETCH_ADJUSTSHOPINGITEM: 'adjustShopingItem/FETCH_ADJUSTSHOPINGITEM',
  CREATE_ADJUSTSHOPINGITEM: 'adjustShopingItem/CREATE_ADJUSTSHOPINGITEM',
  UPDATE_ADJUSTSHOPINGITEM: 'adjustShopingItem/UPDATE_ADJUSTSHOPINGITEM',
  DELETE_ADJUSTSHOPINGITEM: 'adjustShopingItem/DELETE_ADJUSTSHOPINGITEM',
  SET_BLOB: 'adjustShopingItem/SET_BLOB',
  RESET: 'adjustShopingItem/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustShopingItem>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustShopingItemState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustShopingItemState = initialState, action): AdjustShopingItemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTSHOPINGITEM):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTSHOPINGITEM):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTSHOPINGITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTSHOPINGITEM):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTSHOPINGITEM):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTSHOPINGITEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTSHOPINGITEM):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTSHOPINGITEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTSHOPINGITEM):
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

const apiUrl = 'api/adjust-shoping-items';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustShopingItem> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM_LIST,
  payload: axios.get<IAdjustShopingItem>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustShopingItem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTSHOPINGITEM,
    payload: axios.get<IAdjustShopingItem>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustShopingItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTSHOPINGITEM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustShopingItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTSHOPINGITEM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustShopingItem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTSHOPINGITEM,
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
