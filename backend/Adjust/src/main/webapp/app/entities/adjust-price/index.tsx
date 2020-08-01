import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustPrice from './adjust-price';
import AdjustPriceDetail from './adjust-price-detail';
import AdjustPriceUpdate from './adjust-price-update';
import AdjustPriceDeleteDialog from './adjust-price-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustPriceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustPriceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustPriceDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustPrice} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustPriceDeleteDialog} />
  </>
);

export default Routes;
