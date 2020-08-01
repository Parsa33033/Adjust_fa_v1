import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TutorialVideo from './tutorial-video';
import TutorialVideoDetail from './tutorial-video-detail';
import TutorialVideoUpdate from './tutorial-video-update';
import TutorialVideoDeleteDialog from './tutorial-video-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TutorialVideoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TutorialVideoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TutorialVideoDetail} />
      <ErrorBoundaryRoute path={match.url} component={TutorialVideo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TutorialVideoDeleteDialog} />
  </>
);

export default Routes;
