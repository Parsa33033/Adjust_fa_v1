import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustMove from './adjust-move';
import AdjustMoveDetail from './adjust-move-detail';
import AdjustMoveUpdate from './adjust-move-update';
import AdjustMoveDeleteDialog from './adjust-move-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustMoveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustMoveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustMoveDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustMove} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustMoveDeleteDialog} />
  </>
);

export default Routes;
