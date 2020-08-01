import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Move from './move';
import MoveDetail from './move-detail';
import MoveUpdate from './move-update';
import MoveDeleteDialog from './move-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MoveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MoveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MoveDetail} />
      <ErrorBoundaryRoute path={match.url} component={Move} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MoveDeleteDialog} />
  </>
);

export default Routes;
