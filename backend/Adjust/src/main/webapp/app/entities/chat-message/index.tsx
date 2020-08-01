import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ChatMessage from './chat-message';
import ChatMessageDetail from './chat-message-detail';
import ChatMessageUpdate from './chat-message-update';
import ChatMessageDeleteDialog from './chat-message-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ChatMessageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ChatMessageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ChatMessageDetail} />
      <ErrorBoundaryRoute path={match.url} component={ChatMessage} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ChatMessageDeleteDialog} />
  </>
);

export default Routes;
