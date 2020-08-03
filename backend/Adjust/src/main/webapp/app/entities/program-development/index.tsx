import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProgramDevelopment from './program-development';
import ProgramDevelopmentDetail from './program-development-detail';
import ProgramDevelopmentUpdate from './program-development-update';
import ProgramDevelopmentDeleteDialog from './program-development-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProgramDevelopmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProgramDevelopmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProgramDevelopmentDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProgramDevelopment} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProgramDevelopmentDeleteDialog} />
  </>
);

export default Routes;
