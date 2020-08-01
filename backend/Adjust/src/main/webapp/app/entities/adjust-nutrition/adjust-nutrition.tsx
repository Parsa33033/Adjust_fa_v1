import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-nutrition.reducer';
import { IAdjustNutrition } from 'app/shared/model/adjust-nutrition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustNutritionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustNutrition = (props: IAdjustNutritionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustNutritionList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-nutrition-heading">
        <Translate contentKey="adjustApp.adjustNutrition.home.title">Adjust Nutritions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustNutrition.home.createLabel">Create new Adjust Nutrition</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustNutritionList && adjustNutritionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.unit">Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.adjustNutritionId">Adjust Nutrition Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.caloriesPerUnit">Calories Per Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.proteinPerUnit">Protein Per Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.carbsPerUnit">Carbs Per Unit</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustNutrition.fatInUnit">Fat In Unit</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustNutritionList.map((adjustNutrition, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustNutrition.id}`} color="link" size="sm">
                      {adjustNutrition.id}
                    </Button>
                  </td>
                  <td>{adjustNutrition.name}</td>
                  <td>{adjustNutrition.description}</td>
                  <td>{adjustNutrition.unit}</td>
                  <td>{adjustNutrition.adjustNutritionId}</td>
                  <td>{adjustNutrition.caloriesPerUnit}</td>
                  <td>{adjustNutrition.proteinPerUnit}</td>
                  <td>{adjustNutrition.carbsPerUnit}</td>
                  <td>{adjustNutrition.fatInUnit}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustNutrition.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustNutrition.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustNutrition.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.adjustNutrition.home.notFound">No Adjust Nutritions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustNutrition }: IRootState) => ({
  adjustNutritionList: adjustNutrition.entities,
  loading: adjustNutrition.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustNutrition);
