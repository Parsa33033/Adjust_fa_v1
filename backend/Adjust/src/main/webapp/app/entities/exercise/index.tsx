import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Exercise from './exercise';
import ExerciseDetail from './exercise-detail';
import ExerciseUpdate from './exercise-update';
import ExerciseDeleteDialog from './exercise-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExerciseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExerciseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExerciseDetail} />
      <ErrorBoundaryRoute path={match.url} component={Exercise} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ExerciseDeleteDialog} />
  </>
);

export default Routes;
