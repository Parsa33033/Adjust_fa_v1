import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustTokens from './adjust-tokens';
import AdjustTokensDetail from './adjust-tokens-detail';
import AdjustTokensUpdate from './adjust-tokens-update';
import AdjustTokensDeleteDialog from './adjust-tokens-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustTokensUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustTokensUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustTokensDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustTokens} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustTokensDeleteDialog} />
  </>
);

export default Routes;
