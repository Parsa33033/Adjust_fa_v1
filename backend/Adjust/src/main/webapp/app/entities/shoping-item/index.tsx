import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ShopingItem from './shoping-item';
import ShopingItemDetail from './shoping-item-detail';
import ShopingItemUpdate from './shoping-item-update';
import ShopingItemDeleteDialog from './shoping-item-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ShopingItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ShopingItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ShopingItemDetail} />
      <ErrorBoundaryRoute path={match.url} component={ShopingItem} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ShopingItemDeleteDialog} />
  </>
);

export default Routes;
