import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustFood from './adjust-food';
import AdjustFoodDetail from './adjust-food-detail';
import AdjustFoodUpdate from './adjust-food-update';
import AdjustFoodDeleteDialog from './adjust-food-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustFoodUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustFoodUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustFoodDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustFood} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustFoodDeleteDialog} />
  </>
);

export default Routes;
