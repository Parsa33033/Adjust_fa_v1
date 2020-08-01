import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Tutorial from './tutorial';
import TutorialDetail from './tutorial-detail';
import TutorialUpdate from './tutorial-update';
import TutorialDeleteDialog from './tutorial-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TutorialUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TutorialUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TutorialDetail} />
      <ErrorBoundaryRoute path={match.url} component={Tutorial} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TutorialDeleteDialog} />
  </>
);

export default Routes;
