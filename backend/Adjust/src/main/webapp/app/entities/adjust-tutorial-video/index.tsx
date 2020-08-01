import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustTutorialVideo from './adjust-tutorial-video';
import AdjustTutorialVideoDetail from './adjust-tutorial-video-detail';
import AdjustTutorialVideoUpdate from './adjust-tutorial-video-update';
import AdjustTutorialVideoDeleteDialog from './adjust-tutorial-video-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustTutorialVideoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustTutorialVideoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustTutorialVideoDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustTutorialVideo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustTutorialVideoDeleteDialog} />
  </>
);

export default Routes;
