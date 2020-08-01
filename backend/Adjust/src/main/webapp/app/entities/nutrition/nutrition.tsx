import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nutrition.reducer';
import { INutrition } from 'app/shared/model/nutrition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INutritionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Nutrition = (props: INutritionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { nutritionList, match, loading } = props;
  return (
    <div>
      <h2 id="nutrition-heading">
        <Translate contentKey="adjustApp.nutrition.home.title">Nutritions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.nutrition.home.createLabel">Create new Nutrition</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {nutritionList && nutritionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.unit">Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.adjustNutritionId">Adjust Nutrition Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.caloriesPerUnit">Calories Per Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.proteinPerUnit">Protein Per Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.carbsPerUnit">Carbs Per Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.fatInUnit">Fat In Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.nutrition.meal">Meal</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nutritionList.map((nutrition, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nutrition.id}`} color="link" size="sm">
                      {nutrition.id}
                    </Button>
                  </td>
                  <td>{nutrition.name}</td>
                  <td>{nutrition.description}</td>
                  <td>{nutrition.unit}</td>
                  <td>{nutrition.adjustNutritionId}</td>
                  <td>{nutrition.caloriesPerUnit}</td>
                  <td>{nutrition.proteinPerUnit}</td>
                  <td>{nutrition.carbsPerUnit}</td>
                  <td>{nutrition.fatInUnit}</td>
                  <td>{nutrition.mealId ? <Link to={`meal/${nutrition.mealId}`}>{nutrition.mealId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nutrition.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nutrition.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nutrition.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.nutrition.home.notFound">No Nutritions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ nutrition }: IRootState) => ({
  nutritionList: nutrition.entities,
  loading: nutrition.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Nutrition);
