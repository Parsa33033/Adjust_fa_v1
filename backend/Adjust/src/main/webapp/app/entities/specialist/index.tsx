import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Specialist from './specialist';
import SpecialistDetail from './specialist-detail';
import SpecialistUpdate from './specialist-update';
import SpecialistDeleteDialog from './specialist-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SpecialistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SpecialistUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SpecialistDetail} />
      <ErrorBoundaryRoute path={match.url} component={Specialist} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SpecialistDeleteDialog} />
  </>
);

export default Routes;
