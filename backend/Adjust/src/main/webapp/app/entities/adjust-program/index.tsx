import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustProgram from './adjust-program';
import AdjustProgramDetail from './adjust-program-detail';
import AdjustProgramUpdate from './adjust-program-update';
import AdjustProgramDeleteDialog from './adjust-program-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustProgramUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustProgramUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustProgramDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustProgram} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustProgramDeleteDialog} />
  </>
);

export default Routes;
