import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Nutrition from './nutrition';
import NutritionDetail from './nutrition-detail';
import NutritionUpdate from './nutrition-update';
import NutritionDeleteDialog from './nutrition-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NutritionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NutritionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NutritionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Nutrition} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NutritionDeleteDialog} />
  </>
);

export default Routes;
