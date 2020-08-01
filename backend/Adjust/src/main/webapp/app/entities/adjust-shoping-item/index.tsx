import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustShopingItem from './adjust-shoping-item';
import AdjustShopingItemDetail from './adjust-shoping-item-detail';
import AdjustShopingItemUpdate from './adjust-shoping-item-update';
import AdjustShopingItemDeleteDialog from './adjust-shoping-item-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustShopingItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustShopingItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustShopingItemDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustShopingItem} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustShopingItemDeleteDialog} />
  </>
);

export default Routes;
