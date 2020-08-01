import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustNutrition from './adjust-nutrition';
import AdjustNutritionDetail from './adjust-nutrition-detail';
import AdjustNutritionUpdate from './adjust-nutrition-update';
import AdjustNutritionDeleteDialog from './adjust-nutrition-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustNutritionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustNutritionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustNutritionDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustNutrition} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustNutritionDeleteDialog} />
  </>
);

export default Routes;
