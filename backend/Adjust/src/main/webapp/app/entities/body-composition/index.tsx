import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BodyComposition from './body-composition';
import BodyCompositionDetail from './body-composition-detail';
import BodyCompositionUpdate from './body-composition-update';
import BodyCompositionDeleteDialog from './body-composition-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BodyCompositionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BodyCompositionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BodyCompositionDetail} />
      <ErrorBoundaryRoute path={match.url} component={BodyComposition} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BodyCompositionDeleteDialog} />
  </>
);

export default Routes;
