import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NutritionProgram from './nutrition-program';
import NutritionProgramDetail from './nutrition-program-detail';
import NutritionProgramUpdate from './nutrition-program-update';
import NutritionProgramDeleteDialog from './nutrition-program-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NutritionProgramUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NutritionProgramUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NutritionProgramDetail} />
      <ErrorBoundaryRoute path={match.url} component={NutritionProgram} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NutritionProgramDeleteDialog} />
  </>
);

export default Routes;
