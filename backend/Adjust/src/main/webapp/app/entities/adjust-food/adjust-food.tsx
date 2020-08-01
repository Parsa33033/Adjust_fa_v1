import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-food.reducer';
import { IAdjustFood } from 'app/shared/model/adjust-food.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustFoodProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustFood = (props: IAdjustFoodProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustFoodList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-food-heading">
        <Translate contentKey="adjustApp.adjustFood.home.title">Adjust Foods</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustFood.home.createLabel">Create new Adjust Food</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustFoodList && adjustFoodList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustFood.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustFood.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustFood.nutrition">Nutrition</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustFoodList.map((adjustFood, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustFood.id}`} color="link" size="sm">
                      {adjustFood.id}
                    </Button>
                  </td>
                  <td>{adjustFood.name}</td>
                  <td>{adjustFood.description}</td>
                  <td>
                    {adjustFood.nutritionId ? <Link to={`adjust-nutrition/${adjustFood.nutritionId}`}>{adjustFood.nutritionId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustFood.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustFood.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustFood.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.adjustFood.home.notFound">No Adjust Foods found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustFood }: IRootState) => ({
  adjustFoodList: adjustFood.entities,
  loading: adjustFood.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustFood);
