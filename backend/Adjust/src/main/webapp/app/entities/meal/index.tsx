import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Meal from './meal';
import MealDetail from './meal-detail';
import MealUpdate from './meal-update';
import MealDeleteDialog from './meal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MealUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MealUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MealDetail} />
      <ErrorBoundaryRoute path={match.url} component={Meal} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MealDeleteDialog} />
  </>
);

export default Routes;
