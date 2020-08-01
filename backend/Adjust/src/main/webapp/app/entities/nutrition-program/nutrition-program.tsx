import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nutrition-program.reducer';
import { INutritionProgram } from 'app/shared/model/nutrition-program.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INutritionProgramProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NutritionProgram = (props: INutritionProgramProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { nutritionProgramList, match, loading } = props;
  return (
    <div>
      <h2 id="nutrition-program-heading">
        <Translate contentKey="adjustApp.nutritionProgram.home.title">Nutrition Programs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.nutritionProgram.home.createLabel">Create new Nutrition Program</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {nutritionProgramList && nutritionProgramList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutritionProgram.dailyCalories">Daily Calories</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutritionProgram.proteinPercentage">Protein Percentage</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutritionProgram.carbsPercentage">Carbs Percentage</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutritionProgram.fatPercentage">Fat Percentage</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutritionProgram.description">Description</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nutritionProgramList.map((nutritionProgram, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nutritionProgram.id}`} color="link" size="sm">
                      {nutritionProgram.id}
                    </Button>
                  </td>
                  <td>{nutritionProgram.dailyCalories}</td>
                  <td>{nutritionProgram.proteinPercentage}</td>
                  <td>{nutritionProgram.carbsPercentage}</td>
                  <td>{nutritionProgram.fatPercentage}</td>
                  <td>{nutritionProgram.description}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nutritionProgram.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nutritionProgram.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nutritionProgram.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.nutritionProgram.home.notFound">No Nutrition Programs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ nutritionProgram }: IRootState) => ({
  nutritionProgramList: nutritionProgram.entities,
  loading: nutritionProgram.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NutritionProgram);
