import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustClient from './adjust-client';
import AdjustClientDetail from './adjust-client-detail';
import AdjustClientUpdate from './adjust-client-update';
import AdjustClientDeleteDialog from './adjust-client-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustClientUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustClientUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustClientDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustClient} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustClientDeleteDialog} />
  </>
);

export default Routes;
