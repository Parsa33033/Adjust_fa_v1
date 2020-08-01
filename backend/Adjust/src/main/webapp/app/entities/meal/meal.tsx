import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './meal.reducer';
import { IMeal } from 'app/shared/model/meal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMealProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Meal = (props: IMealProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { mealList, match, loading } = props;
  return (
    <div>
      <h2 id="meal-heading">
        <Translate contentKey="adjustApp.meal.home.title">Meals</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.meal.home.createLabel">Create new Meal</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {mealList && mealList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.meal.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.meal.number">Number</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.meal.nutritionProgram">Nutrition Program</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mealList.map((meal, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${meal.id}`} color="link" size="sm">
                      {meal.id}
                    </Button>
                  </td>
                  <td>{meal.name}</td>
                  <td>{meal.number}</td>
                  <td>
                    {meal.nutritionProgramId ? (
                      <Link to={`nutrition-program/${meal.nutritionProgramId}`}>{meal.nutritionProgramId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${meal.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${meal.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${meal.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.meal.home.notFound">No Meals found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ meal }: IRootState) => ({
  mealList: meal.entities,
  loading: meal.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Meal);
