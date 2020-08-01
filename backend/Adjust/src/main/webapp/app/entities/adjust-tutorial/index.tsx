import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustTutorial from './adjust-tutorial';
import AdjustTutorialDetail from './adjust-tutorial-detail';
import AdjustTutorialUpdate from './adjust-tutorial-update';
import AdjustTutorialDeleteDialog from './adjust-tutorial-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustTutorialUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustTutorialUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustTutorialDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustTutorial} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustTutorialDeleteDialog} />
  </>
);

export default Routes;
