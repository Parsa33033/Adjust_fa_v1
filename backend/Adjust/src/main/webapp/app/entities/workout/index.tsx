import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Workout from './workout';
import WorkoutDetail from './workout-detail';
import WorkoutUpdate from './workout-update';
import WorkoutDeleteDialog from './workout-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WorkoutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WorkoutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WorkoutDetail} />
      <ErrorBoundaryRoute path={match.url} component={Workout} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={WorkoutDeleteDialog} />
  </>
);

export default Routes;
