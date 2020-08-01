import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FitnessProgram from './fitness-program';
import FitnessProgramDetail from './fitness-program-detail';
import FitnessProgramUpdate from './fitness-program-update';
import FitnessProgramDeleteDialog from './fitness-program-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FitnessProgramUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FitnessProgramUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FitnessProgramDetail} />
      <ErrorBoundaryRoute path={match.url} component={FitnessProgram} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FitnessProgramDeleteDialog} />
  </>
);

export default Routes;
